package com.jsonfixture.graph;

import com.google.common.base.Joiner;
import com.jsonfixture.AbstractTestCase;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DirectedGraphTest extends AbstractTestCase {
    private static final Logger log = LogManager.getLogger();

    @Test
    public void testReverseTopologicalSort() throws Exception {

        DirectedGraph<String> graph = new DirectedGraph<String>();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        log.debug(graph);
        String result = Joiner.on("").join(graph.reverseTopologicalSort());
        Assert.assertEquals("dcba", result);
    }

    @Test
    public void testCycleDetection() {
        DirectedGraph<String> graph = new DirectedGraph<String>();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "a");
        boolean pass = false;
        try {
            graph.reverseTopologicalSort();
        } catch (Exception e) {
            Assert.assertEquals("Cycle detected in graph", e.getMessage());
            pass = true;
        }
        if(!pass) Assert.fail();


    }
}