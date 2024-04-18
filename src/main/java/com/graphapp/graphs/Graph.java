package com.graphapp.graphs;

import org.springframework.lang.Nullable;
import java.util.HashMap;

interface Graph {
    int MAX_NUM_VERTICES = 50;

    void addVertex(Node n);
    void removeVertex(Node n);
    void addEdge(Node n1, Node n2, @Nullable Integer weight);
    void removeEdge(Node n1, Node n2);
    void removeAllEdges();
    void removeAllVertices();
    int getNumVertices();
    int getNumEdges();
    String getNodeLabel(Node n);
    HashMap<Node, Integer> getVertices();
    int[][] getAdjMatrix();


}