package com.graphapp.graphs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class UndirectedGraphTests {
    private UndirectedGraph graph;

    @Test
    void addVertex() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");

        graph.addVertex(n1);
        assertEquals(1, graph.getNumVertices());

        graph.addVertex(n2);
        assertEquals(2, graph.getNumVertices());

        var vertices = graph.getVertices();
        var indices = graph.getIndices();
        assertEquals(2, vertices.size());

        assertEquals(n1 , graph.getVertex(graph.getIndex(n1)));
    }

    @Test
    void addRepeatedVertex() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");

        graph.addVertex(n1);
        graph.addVertex(n1);
        assertEquals(1, graph.getNumVertices());

        graph.addVertex(n2);
        graph.addVertex(n1);
        assertEquals(2, graph.getNumVertices());

        graph.addVertex(n3);
        graph.addVertex(n2);
        graph.addVertex(n1);
        assertEquals(3, graph.getNumVertices());
    }

    @Test
    void removeVertex() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);

        graph.addEdge(n1,n2, null);
        graph.addEdge(n1,n3, null);
        graph.addEdge(n2,n3, null);

        int[][] actual = graph.getAdjMatrix();
        var unusedIndices = graph.getUnusedIndices();

        int[][] expected = new int[50][50];
        expected[0][1] = 1;
        expected[1][0] = 1;

        expected[0][2] = 1;
        expected[2][0] = 1;

        expected[1][2] = 1;
        expected[2][1] = 1;


        assertEquals(3, graph.getNumVertices());
        assertEquals(3, graph.getNumEdges());
        assertArrayEquals(expected, actual);
        assertEquals(0, unusedIndices.size());


        graph.removeVertex(n1);
        actual = graph.getAdjMatrix();
        unusedIndices = graph.getUnusedIndices();

        expected[0][1] = 0;
        expected[1][0] = 0;
        expected[0][2] = 0;
        expected[2][0] = 0;

        assertEquals(2, graph.getNumVertices());
        assertEquals(1, graph.getNumEdges());
        assertArrayEquals(expected, actual);
        assertEquals(1, unusedIndices.size());

        var vertices = graph.getVertices();
        var indices = graph.getIndices();

        assertNull(graph.getVertex(graph.getIndex(n1))); // not found since removed
        assertEquals(n2, graph.getVertex(graph.getIndex(n2)));

    }

    @Test
    void addEdge() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D"); // not in graph

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);

        graph.addEdge(n1,n2, null);
        assertEquals(1, graph.getNumEdges());
        assertEquals(1, graph.getEdgeWeight(n1,n2));

        graph.addEdge(n2,n1, null);
        assertEquals(1, graph.getNumEdges());
        assertEquals(1, graph.getEdgeWeight(n2,n1));


        graph.addEdge(n1,n3, null);
        assertEquals(2, graph.getNumEdges());
        assertEquals(1, graph.getEdgeWeight(n1,n3));

        graph.addEdge(n2,n3, 2);
        assertEquals(3, graph.getNumEdges());
        assertEquals(2, graph.getEdgeWeight(n2,n3));

        graph.addEdge(n2,n3, 3);
        assertEquals(3, graph.getNumEdges()); // edge already present, so no change
        assertEquals(3, graph.getEdgeWeight(n2,n3)); // updates edge weight

        graph.addEdge(n4, n1, null);
        assertEquals(3, graph.getNumEdges()); // unchanged when one vertex isn't in graph
        assertEquals(-1, graph.getEdgeWeight(n4,n2)); // edge weight returns -1 when one (or both) edge not in graph
    }

    @Test
    void removeEdge() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.addEdge(n1,n2, null);
        graph.addEdge(n1,n3, null);

        graph.removeEdge(n1,n4);
        assertEquals(2, graph.getNumEdges()); // removed non-existent edge

        graph.removeEdge(n1,n2);
        assertEquals(1, graph.getNumEdges());

        graph.removeEdge(n3,n1);
        assertEquals(0, graph.getNumEdges());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeAllEdges() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.addEdge(n1,n2, null);
        graph.addEdge(n1,n3, null);
        graph.addEdge(n1,n4, null);
        graph.addEdge(n2,n3, null);
        graph.addEdge(n3,n4, null);

        assertEquals(5, graph.getNumEdges());

        graph.removeAllEdges();
        assertEquals(0, graph.getNumEdges());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeAllVertices() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);

        graph.addEdge(n1,n2, null);
        graph.addEdge(n1,n3, null);
        graph.addEdge(n1,n4, null);

        graph.removeAllVertices();
        assertEquals(0, graph.getNumVertices());
        assertEquals(0, graph.getNumEdges());
        assertEquals(0, graph.getVertices().size());
        assertEquals(0, graph.getIndices().size());

        int[][] expected = new int[50][50];
        int[][] actual = graph.getAdjMatrix();
        assertArrayEquals(expected, actual);
    }

    @Test
    void getNumVertices() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        assertEquals(0, graph.getNumVertices());

        graph.addVertex(n1);
        graph.addVertex(n2);
        assertEquals(2, graph.getNumVertices());

        graph.removeVertex(n1);
        assertEquals(1, graph.getNumVertices());

        graph.addVertex(n3);
        graph.addVertex(n4);
        assertEquals(3, graph.getNumVertices());
    }

    @Test
    void getNumEdges() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        assertEquals(0, graph.getNumEdges());

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);
        graph.addVertex(n4);
        assertEquals(0, graph.getNumEdges());

        graph.addEdge(n1,n1, null);
        assertEquals(1, graph.getNumEdges()); // allows self-edges

        graph.addEdge(n1,n2, null);
        graph.addEdge(n1,n3, null);
        assertEquals(3, graph.getNumEdges());
    }

    @Test
    void getEdgeWeight() {
        graph = new UndirectedGraph();

        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");

        graph.addVertex(n1);
        graph.addVertex(n2);
        graph.addVertex(n3);

        graph.addEdge(n1,n1, null);
        graph.addEdge(n1,n2, 2);
        graph.addEdge(n1,n3, 3);
        assertEquals(1, graph.getEdgeWeight(n1,n1));
        assertEquals(2, graph.getEdgeWeight(n1,n2));
        assertEquals(3, graph.getEdgeWeight(n1,n3));
        assertEquals(-1, graph.getEdgeWeight(new Node("D"),n1));

        graph.addEdge(n1,n1, 4);
        graph.addEdge(n1,n2, 5);
        assertEquals(4, graph.getEdgeWeight(n1,n1));
        assertEquals(5, graph.getEdgeWeight(n1,n2));

    }

}
