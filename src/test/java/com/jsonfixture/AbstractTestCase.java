package com.jsonfixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;

/**
 * Created by sgururaj on 1/9/15.
 */
public class AbstractTestCase {

    protected Injector injector;
    @Before
    public void init() {
        injector = Guice.createInjector(new Module());
    }

}
