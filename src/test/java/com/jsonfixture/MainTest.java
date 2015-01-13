package com.jsonfixture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    private static final Logger log = LogManager.getLogger();

    @Test
    public void testApp() throws Exception {
        Main main = new Main();
        main.initialize();
        log.debug(main.getJsonString("com.example.Order", "order1"));

    }
}