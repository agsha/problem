package com.jsonfixture;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.jsonfixture.input.InputSource;
import com.jsonfixture.input.SingleFileInputSource;
import com.jsonfixture.parser.Parser;
import com.jsonfixture.pluggable.HeaderParser;
import com.jsonfixture.pluggable.HeaderParserImpl;
import com.jsonfixture.pluggable.ReferenceDetector;
import com.jsonfixture.pluggable.ReferenceDetectorImpl;

import java.net.URISyntaxException;

/**
 * Created by sgururaj on 1/9/15.
 */
public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(ReferenceDetector.class).to(ReferenceDetectorImpl.class);
        bind(HeaderParser.class).to(HeaderParserImpl.class);
    }

    @Provides
    InputSource getInputSource(SingleFileInputSource.Provider provider) throws URISyntaxException {
        return provider.get();
    }

    @Provides
    Parser getParser(Parser.Provider provider) {
       return provider.get();
    }
}
