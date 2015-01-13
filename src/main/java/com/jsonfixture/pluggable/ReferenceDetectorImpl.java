package com.jsonfixture.pluggable;

import com.google.common.base.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sgururaj on 1/9/15.
 */
public class ReferenceDetectorImpl implements ReferenceDetector {
    Pattern pattern = Pattern.compile("!\\w+");
    private static final Logger log = LogManager.getLogger();

    @Override
    public Optional<Position> detectFirstReference(String data) {
        Matcher matcher = pattern.matcher(data);
        if(!matcher.find()) {
            return Optional.absent();
        }
        return Optional.of(new Position(matcher.start(), matcher.end(), matcher.group()));
    }

    @Override
    public List<Position> detectAllReferences(String data) {
        List<Position> result = new ArrayList<Position>();
        Matcher matcher = pattern.matcher(data);
        while(matcher.find()) {
            result.add(new Position(matcher.start(), matcher.end(), matcher.group()));
        }
        //log.debug(result);
        return result;
    }
}
