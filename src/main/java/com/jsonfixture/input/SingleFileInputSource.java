package com.jsonfixture.input;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sgururaj on 1/9/15.
 */
public class SingleFileInputSource implements InputSource{

    private String path;

    public SingleFileInputSource(String path) {
        this.path = path;
    }

    @Override
    public Iterable<String> readLines() throws IOException {
        List<String> list =  new ArrayList<String>(Resources.asCharSource(Resources.getResource(path), Charset.defaultCharset()).readLines());
        for(int i=0; i<list.size(); i++) {
            list.set(i, list.get(i)+"\n");
        }
        return list;
    }


    @Singleton
    public static class Provider {
        private static final Logger log = LogManager.getLogger();
        private String path;

        public void config(String path) {
            this.path = path;
        }

        private void setDefaultPath() throws URISyntaxException {
            path = "fixture.json";
        }

        public SingleFileInputSource get() throws URISyntaxException {
            if(Strings.isNullOrEmpty(path)) {
                setDefaultPath();
            }
            return new SingleFileInputSource(path);
        }
    }
}
