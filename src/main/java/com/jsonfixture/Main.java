package com.jsonfixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jsonfixture.parser.Parser;
import com.jsonfixture.parser.SymbolData;
import com.jsonfixture.parser.SymbolTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import problem.App;

import java.io.IOException;

/**
 * Created by sgururaj on 1/9/15.
 */
public class Main {
    private Parser parser;
    private static final Logger log = LogManager.getLogger();

    SymbolTable<String, SymbolData> symbolTable;
    public void initialize() throws Exception {
        Injector injector = Guice.createInjector(new Module());
        symbolTable = injector.getInstance(Parser.class).parse();
    }

    public String getJsonString(String qualifiedName, String name) throws Exception {
        SymbolData symbolData = symbolTable.get(name);
        if(!symbolData.getSymbol().getQualifiedClassName().equals(qualifiedName)) {
            throw new Exception("No such class found");
        }
        return symbolData.getJson();

    }
}
