package com.graphapp.graphs;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.ArrayList;

public class UndirectedGraph implements Graph {
    ArrayList<Integer> unusedIndices;
    private int[][] adjMatrix = new int[MAX_NUM_VERTICES][MAX_NUM_VERTICES];
    private HashMap<Node, Integer> vertices = new HashMap<>();
    private int numVertices;
    private int numEdges;

    public UndirectedGraph() {
        this.numVertices = 0;
        this.numEdges = 0;
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
            unusedIndices.remove(0);
        }

        else {
            vertices.put(n, numVertices);
        }
    }

    @Override
    public void removeVertex(Node n) {
        var index = vertices.remove(n);
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
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public int getNumEdges() {
        for(int i = 0; i < adjMatrix.length; i++) {
            for(int j = i; j < adjMatrix.length; j++) {
                if(adjMatrix[i][j] > 0) {
                    numEdges++;
                }
            }
        }

        return numEdges;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public HashMap<Node, Integer> getVertices() {
        return vertices;
    }

    public ArrayList<Integer> getUnusedIndices() {
        return unusedIndices;
    }

}

