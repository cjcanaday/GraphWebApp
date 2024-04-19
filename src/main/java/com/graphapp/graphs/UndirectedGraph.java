package com.graphapp.graphs;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.ArrayList;

public class UndirectedGraph implements Graph {
    private final ArrayList<Integer> unusedIndices;
    private int[][] adjMatrix;
    private final HashMap<Node, Integer> vertices;
    private final HashMap<Integer, Node> indices;

    public UndirectedGraph() {
        this.adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
        this.unusedIndices = new ArrayList<>();
        this.vertices = new HashMap<>();
        this.indices = new HashMap<>();
    }


    @Override
    public void addVertex(Node n) {
        if(getNumVertices() == MAX_NUM_VERTICES) {
            return;
        }

        if(vertices.containsKey(n)) { // check if already in vertices
            return;
        }

        if(!unusedIndices.isEmpty()) {
            vertices.put(n, unusedIndices.get(0)); // if there are unused indices, use those
            indices.put(unusedIndices.get(0), n);
            unusedIndices.remove(0);
        }

        else {
            int numVertices = getNumVertices(); // set to variable so indices and vertices match up
            vertices.put(n, numVertices);
            indices.put(numVertices, n);
        }
    }

    @Override
    public void removeVertex(Node n) {
        var index = vertices.remove(n);
        indices.remove(index);
        if(index == null) {
            return;
        }

        unusedIndices.add(index); // add newly empty index to list

        for(int i = 0; i < adjMatrix.length; i++) {

            adjMatrix[i][index] = 0;
            adjMatrix[index][i] = 0;
        }
    }

    @Override
    public void addEdge(Node n1, Node n2, @Nullable Integer weight) {
        var n1Idx = vertices.get(n1);
        var n2Idx = vertices.get(n2);

        if(n1Idx == null || n2Idx == null) {
            return;
        }

        if(weight == null) {
            adjMatrix[n1Idx][n2Idx] = 1;
            adjMatrix[n2Idx][n1Idx] = 1;
        }

        else {
            adjMatrix[n1Idx][n2Idx] = weight;
            adjMatrix[n2Idx][n1Idx] = weight;
        }
    }

    @Override
    public void removeEdge(Node n1, Node n2) {
        var n1Idx = vertices.get(n1);
        var n2Idx = vertices.get(n2);

        if(n1Idx == null || n2Idx == null) {
            return;
        }

        adjMatrix[n1Idx][n2Idx] = 0;
        adjMatrix[n2Idx][n1Idx] = 0;
    }

    @Override
    public void removeAllEdges() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];

    }

    @Override
    public void removeAllVertices() {
        adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
        unusedIndices.clear();
        vertices.clear();
        indices.clear();
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public int getNumEdges() {
        int numEdges = 0;
        for(int i = 0; i < adjMatrix.length; i++) {
            for(int j = i; j < adjMatrix.length; j++) {
                if(adjMatrix[i][j] > 0) {
                    numEdges++;
                }
            }
        }

        return numEdges;
    }

    @Override
    public String getNodeLabel(Node n) {
        return n.getLabel();
    }

    public int getEdgeWeight(Node n1, Node n2) {
        var index1 = vertices.get(n1);
        var index2 = vertices.get(n2);
        if (index1 == null || index2 == null) {
            return -1;
        }

        return adjMatrix[index1][index2];
    }

    @Override
    public int[][] getAdjMatrix() {
        return adjMatrix;
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

    @Override
    public HashMap<Node, Integer> getVertices() {
        return vertices;
    }

    @Override
    public HashMap<Integer, Node> getIndices() {
        return indices;
    }

    public ArrayList<Integer> getUnusedIndices() {
        return unusedIndices;
    }

}

