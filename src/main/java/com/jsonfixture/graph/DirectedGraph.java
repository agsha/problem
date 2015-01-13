package com.jsonfixture.graph;

import com.google.common.base.MoreObjects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by sgururaj on 1/9/15.
 */
public class DirectedGraph<T> {
    Map<Node<T>, AdjacencyList> graph = new HashMap<Node<T>, AdjacencyList>();
    private static final Logger log = LogManager.getLogger();

    public void addEdge(T from, T to) {
        Node<T> fromNode = new Node<T>(from);
        Node<T> toNode = new Node<T>(to);
        if(!graph.containsKey(fromNode)) {
            log.debug(fromNode);
            graph.put(fromNode, new AdjacencyList(fromNode));
        }
        if(!graph.containsKey(toNode)) {
            log.debug(toNode);
            graph.put(toNode, new AdjacencyList(toNode));
        }
        graph.get(fromNode).addToAdjacencyList(toNode);
    }

    public Iterable<T> reverseTopologicalSort() throws Exception {
        List<T> result = new ArrayList<T>();
        Map<Node<T>, Color> nodeToColorMap = new HashMap<Node<T>, Color>();
        for (Node<T> node : graph.keySet()) {
            depthFirstSearch(node, nodeToColorMap, result);
        }
        return result;
    }

    public List<T> adjacencyListFor(T nodeData) {
        List<Node<T>> adjacencyList = graph.get(new Node<T>(nodeData)).getAdjacencyList();
        List<T> unwrapped = new ArrayList<T>();
        for (Node<T> node : adjacencyList) {
            unwrapped.add(node.getData());
        }
        return unwrapped;

    }

    private void depthFirstSearch(Node<T> node, Map<Node<T>, Color> nodeToColorMap, List<T> result) throws Exception {
        if(nodeToColorMap.containsKey(node) && nodeToColorMap.get(node).equals(Color.GREY)) {
            throw new Exception("Cycle detected in graph");
        };
        if(nodeToColorMap.containsKey(node)&&nodeToColorMap.get(node).equals(Color.BLACK)) return;
        nodeToColorMap.put(node, Color.GREY);
        for (Node<T> nextNode : graph.get(node).getAdjacencyList()) {
            depthFirstSearch(nextNode, nodeToColorMap, result);
        }
        nodeToColorMap.put(node, Color.BLACK);
        result.add(node.getData());
    }


    class AdjacencyList {
        Node<T> fromNode;
        List<Node<T>> adjacencyList = new ArrayList<Node<T>>();

        AdjacencyList(Node<T> fromNode) {
            this.fromNode = fromNode;
        }

        public Node<T> getFromNode() {
            return fromNode;
        }

        public List<Node<T>> getAdjacencyList() {
            return adjacencyList;
        }

        public void addToAdjacencyList(Node<T> toNode) {
            adjacencyList.add(toNode);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("fromNode", fromNode).add("adjacencyList", adjacencyList).toString();
        }
    }

    private static enum Color {
        WHITE,
        GREY,
        BLACK;
    }
    @Override
    public String toString() {
        return graph.toString();
    }
}
