package com.jsonfixture.pluggable;

import com.google.common.base.Optional;
import com.jsonfixture.AbstractTestCase;
import com.jsonfixture.parser.Symbol;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeaderParserImplTest extends AbstractTestCase {

    @Test
    public void testHappyPath() {
        HeaderParserImpl instance = injector.getInstance(HeaderParserImpl.class);
        Optional<Symbol> symbolOptional = instance.parseLine("!com.example.Order,order");
        Assert.assertTrue(symbolOptional.isPresent());
    }

    @Test
    public void testNegativeCase() {
        HeaderParserImpl instance = injector.getInstance(HeaderParserImpl.class);
        Optional<Symbol> symbolOptional = instance.parseLine("com.example.Order,order");
        Assert.assertFalse(symbolOptional.isPresent());
    }


}