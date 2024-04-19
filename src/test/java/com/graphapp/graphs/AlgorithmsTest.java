package com.graphapp.graphs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsTest {
    private DirectedGraph directedGraph;
    private UndirectedGraph undirectedGraph;
    private Algorithms algs;

    @Test
    void bfsUndirected() {
        undirectedGraph = new UndirectedGraph();
        algs = new Algorithms();


        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        Node n5 = new Node("E");
        Node n6 = new Node("F");
        Node n7 = new Node("G");

        undirectedGraph.addVertex(n1);
        undirectedGraph.addVertex(n2);
        undirectedGraph.addVertex(n3);
        undirectedGraph.addVertex(n4);
        undirectedGraph.addVertex(n5);

        undirectedGraph.addEdge(n1,n2,null);
        undirectedGraph.addEdge(n2,n4,null);
        undirectedGraph.addEdge(n2,n5,null);
        undirectedGraph.addEdge(n4,n3,null);
        undirectedGraph.addEdge(n5,n3,null);


        ArrayList<String> result = algs.bfs(undirectedGraph, n1);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("D");
        expected.add("E");
        expected.add("C");


        assertEquals(expected, result);
    }
}
