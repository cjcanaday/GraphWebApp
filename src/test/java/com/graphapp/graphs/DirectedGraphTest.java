package com.graphapp.graphs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    private DirectedGraph graph;

    @Test
    void addVertex() {
        graph = new DirectedGraph();

        graph.addVertex(new Node("1"));
        assertEquals(1, graph.getNumVertices());

        graph.addVertex(new Node("2"));
        assertEquals(2, graph.getNumVertices());

        var vertices = graph.getVertices();
        assertEquals(2, vertices.size());
    }

    @Test
    void removeVertex() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        graph.addEdge(n1, n2, null);
        graph.addEdge(n2, n3, null);
        graph.addEdge(n3, n1, null);
        assertEquals(4, graph.getNumVertices());

        graph.removeVertex(n1);
        assertEquals(3, graph.getNumVertices());
        int[][] expected = new int[50][50];
        expected[1][2] = 1;
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);

        var emptyIndices = graph.getEmptyIndices();
        assertEquals(1, emptyIndices.size());

        graph.removeVertex(n2);
        expected = new int[50][50];
        actual = graph.getAdjMatrix();
        assertEquals(2, graph.getNumVertices());
        assertArrayEquals(expected, actual);
    }

    @Test
    void addEdge() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.addEdge(n1, n2, null);
        assertEquals(1, graph.getNumEdges());

        graph.addEdge(n2, n3, null);
        assertEquals(2, graph.getNumEdges());

        // adding duplicate edge
        graph.addEdge(n2, n3, null);
        assertEquals(2, graph.getNumEdges());

        graph.addEdge(new Node("not in graph"), n1, null);
        assertEquals(2, graph.getNumEdges());
    }

    @Test
    void removeEdge() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        graph.addEdge(n1, n2, null);
        graph.addEdge(n2, n3, null);

        // removing non-existent edge
        graph.removeEdge(n2, n4);
        assertEquals(2, graph.getNumEdges());

        // removing edge between non-existent vertex
        graph.removeEdge(new Node("not in graph"), n1);
        assertEquals(2, graph.getNumEdges());

        graph.removeEdge(n1, n2);
        assertEquals(1, graph.getNumEdges());

        graph.removeEdge(n2, n3);
        assertEquals(0, graph.getNumEdges());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeAllEdges() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        graph.addEdge(n1, n2, null);
        graph.addEdge(n2, n3, null);

        graph.removeAllEdges();
        assertEquals(0, graph.getNumEdges());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeAllVertices() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.removeAllVertices();
        assertEquals(0, graph.getVertices().size());
        assertEquals(0, graph.getNumVertices());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void getNumVertices() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        assertEquals(0, graph.getNumVertices());

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        assertEquals(4, graph.getNumVertices());
    }

    @Test
    void getNumEdges() {
        graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        assertEquals(0, graph.getNumEdges());

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        assertEquals(0, graph.getNumEdges());

        graph.addEdge(n1, n2, null);
        graph.addEdge(n1, n3, null);
        assertEquals(2, graph.getNumEdges());
    }
}