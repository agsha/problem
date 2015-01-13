package com.jsonfixture.input;

import java.io.IOException;

/**
 * Created by sgururaj on 1/9/15.
 */
public interface InputSource {
    public Iterable<String> readLines() throws IOException;
}
