package com.graphapp.graphs;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DirectedGraph implements Graph{

    private int[][] adjMatrix;
    private final ArrayList<Integer> emptyIndices;
    private final HashMap<Node, Integer> vertices;
    private final HashMap<Integer, Node> indices;

    public DirectedGraph() {
        this.adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
        this.emptyIndices = new ArrayList<>();
        this.vertices = new HashMap<>();
        this.indices = new HashMap<>();
    }

    @Override
    public void addVertex(Node n) {
        if (getNumVertices() == MAX_NUM_VERTICES) {
            return;
        }
        if (emptyIndices.isEmpty()) {
            int numVertices = getNumVertices(); // set to variable so indices and vertices match up
            vertices.put(n, numVertices);
            indices.put(numVertices, n);
        } else {
            vertices.put(n, emptyIndices.get(0));
            indices.put(emptyIndices.get(0), n);
            emptyIndices.remove(0);
        }
    }

    @Override
    public void removeVertex(Node n) {
        var index = vertices.remove(n);
        indices.remove(index);
        if (index == null) {
            return;
        }
        emptyIndices.add(index);
        // making row and column of adj matrix 0
        Arrays.fill(adjMatrix[index], 0);
        for (int i = 0; i < MAX_NUM_VERTICES; i++) {
            adjMatrix[i][index] = 0;
        }
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
    }

    @Override
    public void removeEdge(Node n1, Node n2) {
        var index1 = vertices.get(n1);
        var index2 = vertices.get(n2);
        if (index1 == null || index2 == null) {
            return;
        }
        adjMatrix[index1][index2] = 0;
    }

    @Override
    public void removeAllEdges() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
    }

    @Override
    public void removeAllVertices() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
        vertices.clear();
        emptyIndices.clear();
        indices.clear();
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public int getNumEdges() {
        var numEdges = 0;
        for (int i = 0; i < MAX_NUM_VERTICES; i++) {
            for (int j = 0; j < MAX_NUM_VERTICES; j++) {
                if (adjMatrix[i][j] > 0) numEdges++;
            }
        }
        return numEdges;
    }

    @Override
    public String getNodeLabel(Node n) {
        return n.getLabel();
    }

    @Override
    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    @Override
    public HashMap<Node, Integer> getVertices() {
        return vertices;
    }

    @Override
    public HashMap<Integer, Node> getIndices() {
        return indices;
    }

    @Override
    public Node getVertex(int v) {
        var result = indices.get(v);
        if(result == null) {
            return null;
        }
        return indices.get(v);
    }

    @Override
    public int getIndex(Node n) {
        var result = vertices.get(n);
        if(result == null) {
            return -1;
        }

        return vertices.get(n);
    }

    public ArrayList<Integer> getEmptyIndices() {
        return emptyIndices;
    }

}
