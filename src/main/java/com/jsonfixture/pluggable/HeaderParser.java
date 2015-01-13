package com.jsonfixture.pluggable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.jsonfixture.parser.Symbol;

/**
 * Created by sgururaj on 1/9/15.
 */

/**
 * pluggable class to parse the header of an object .
 * e.g. !com.Example.Data,data1
 */
public interface HeaderParser {

    /**
     * parses the line and returns an empty optional if the line is not of the correct format
     * and returns the Symbol object after parsing if the format is correct.
     * @param line
     * @return
     */
    Optional<Symbol> parseLine(String line);
}
