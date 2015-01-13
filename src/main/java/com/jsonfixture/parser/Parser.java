package com.jsonfixture.parser;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.jsonfixture.graph.DirectedGraph;
import com.jsonfixture.input.InputSource;
import com.jsonfixture.pluggable.HeaderParser;
import com.jsonfixture.pluggable.Position;
import com.jsonfixture.pluggable.ReferenceDetector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by sgururaj on 1/9/15.
 */
public class Parser {
    private InputSource inputSource;
    private HeaderParser headerParser;
    private ReferenceDetector referenceDetector;
    private DirectedGraph<String> graph;
    private static final Logger log = LogManager.getLogger();

    public Parser(InputSource inputSource, HeaderParser headerParser, ReferenceDetector referenceDetector, DirectedGraph<String> graph) {

        this.inputSource = inputSource;
        this.headerParser = headerParser;
        this.referenceDetector = referenceDetector;
        this.graph = graph;
    }

    /**
     * Constructs the symbol table and dependency graph and calls transform() to do the json transformation
     * @return
     * @throws IOException
     */
    public SymbolTable<String, SymbolData>  parse() throws Exception {
        SymbolTable<String, SymbolData> symbolTable = new SymbolTable<String, SymbolData>();
        String currentJson = "";
        Optional<Symbol> currentSymbol = Optional.absent();
        for (String line : inputSource.readLines()) {
            //log.debug(line);
            Optional<Symbol> symbolHeader = headerParser.parseLine(line);
            // encountered a header line
            if(symbolHeader.isPresent()) {

                // put the accumalated json string in the map.
                if(currentSymbol.isPresent()) {
                    symbolTable.put(currentSymbol.get().getName(), new SymbolData(currentSymbol.get(), currentJson));
                }

                // start with the new header.
                currentSymbol = symbolHeader;
                currentJson = "";
                continue;
            }

            if(!currentSymbol.isPresent()) continue;
            // detect dependencies line by line.
            for (Position dependency : referenceDetector.detectAllReferences(line)) {
                graph.addEdge(currentSymbol.get().getName(), dependency.getReferenceName().substring(1));
            }
            currentJson+=line;
        }

        // any remnant json has to go to the last symbol.
        if(currentSymbol.isPresent()) {
            symbolTable.put(currentSymbol.get().getName(), new SymbolData(currentSymbol.get(), currentJson));
        }



        return transform(symbolTable);

    }

    /**
     * Transforms each nodes json in reverse topological order
     * @param symbolTable
     * @return the final symbol table representing transformed json.
     */
    private SymbolTable<String, SymbolData> transform(SymbolTable<String, SymbolData> symbolTable) throws Exception {
        for (String name : graph.reverseTopologicalSort()) {
            //log.debug(name);
            SymbolData symbolData = symbolTable.get(name);
            String json = symbolData.getJson();
            //log.debug(json);
            while(true) {
                Optional<Position> position = referenceDetector.detectFirstReference(json);
                //log.debug(json + position.isPresent());
                if(!position.isPresent()) break;
                Position pos = position.get();
                String jsonTobeReplaced = symbolTable.get(pos.getReferenceName().substring(1)).getJson();
                //log.debug(jsonTobeReplaced);
                json = json.substring(0, pos.getFrom())+jsonTobeReplaced+json.substring(pos.getTo());
            }
            log.debug(json);
            symbolData.setJson(json);
        }

        return symbolTable;
    }

    public static class Provider {
        private InputSource inputSource;
        private HeaderParser headerParser;
        private ReferenceDetector referenceDetector;
        private DirectedGraph<String> graph;

        @Inject
        public Provider(InputSource inputSource, HeaderParser headerParser, ReferenceDetector referenceDetector, DirectedGraph<String> graph) {
            this.inputSource = inputSource;
            this.headerParser = headerParser;
            this.referenceDetector = referenceDetector;
            this.graph = graph;
        }

        public Parser get() {
            return new Parser(inputSource, headerParser, referenceDetector, graph);
        }
    }

}
