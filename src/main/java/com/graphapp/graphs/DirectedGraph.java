package com.graphapp.graphs;

import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.HashMap;

public class DirectedGraph implements Graph{

    private int[][] adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
    private HashMap<Node, Integer> vertices;
    private int numVertices;
    private int numEdges;

    public DirectedGraph() {
        this.numVertices = 0;
        this.numEdges = 0;
    }

    @Override
    public void addVertex(Node n) {
        vertices.put(n, numVertices);
        numVertices++;
    }

    @Override
    public void removeVertex(Node n) {
        var index = vertices.remove(n);
        if (index == null) {
            return;
        }
        vertices.replaceAll((node, ind) -> (ind > index) ? ind - 1 : ind);
        for (int i = 0; i < MAX_NUM_VERTICES; i++) {
            for (int j = index; j < MAX_NUM_VERTICES - 1; j++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1];
                adjMatrix[j][i] = adjMatrix[j + 1][i];
            }
        }
        adjMatrix[index][index] = 0;
        Arrays.fill(adjMatrix[MAX_NUM_VERTICES], 0);
        numVertices--;
    }

    @Override
    public void addEdge(Node n1, Node n2, @Nullable Integer weight) {
        var index1 = vertices.get(n1);
        var index2 = vertices.get(n2);
        if (index1 == null || index2 == null) {
            return;
        }
        if (weight == null) {
            adjMatrix[index1][index2] = 1;
        } else {
            adjMatrix[index1][index2] = weight;
        }
        numEdges++;
    }

    @Override
    public void removeEdge(Node n1, Node n2) {
        var index1 = vertices.get(n1);
        var index2 = vertices.get(n2);
        if (index1 == null || index2 == null) {
            return;
        }
        adjMatrix[index1][index2] = 0;
        numEdges--;
    }

    @Override
    public void removeAllEdges() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
    }

    @Override
    public void removeAllVertices() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public int getNumEdges() {
        return numEdges;
    }

}
