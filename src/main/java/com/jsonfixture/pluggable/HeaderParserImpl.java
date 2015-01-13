package com.jsonfixture.pluggable;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.jsonfixture.parser.Symbol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import problem.App;

import java.util.regex.Pattern;

/**
 * Created by sgururaj on 1/9/15.
 */
public class HeaderParserImpl implements HeaderParser {
    private static final Logger log = LogManager.getLogger(App.class.getName());

    @Override
    public Optional<Symbol> parseLine(String line) {
        if(!line.startsWith("!")) return Optional.absent();
        line = line.substring(1);
        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(line);
        int count = 0;
        String name = null, qualifiedClassName = null;
        for (String s : split) {
            if(count==0) {
                qualifiedClassName = s;
            } else {
                name = s;
            }
            count++;
        }
        //log.debug(line+ ":"+name+":"+qualifiedClassName);
        if(name == null || qualifiedClassName==null) return Optional.absent();
        return Optional.of(new Symbol(name, qualifiedClassName));
    }
}
