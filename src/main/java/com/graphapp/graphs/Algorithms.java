package com.graphapp.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Algorithms {

    public ArrayList<String> bfs(Graph g, Node start) {

        if(g.getIndex(start) == -1) {
            return null; // need error handling
        }
        ArrayList<String> result = new ArrayList<>();
        boolean[] visitedVertices = new boolean[g.getNumVertices()];
        int[][] adj = g.getAdjMatrix();
        Arrays.fill(visitedVertices,false); // set all visited to False

        ArrayList<Node> queue = new ArrayList<>();

        queue.add(start);
        visitedVertices[g.getIndex(start)] = true;

        int currIdx;
        while (!queue.isEmpty()) {
            Node current = queue.get(0);
            currIdx = g.getIndex(current);

            result.add(current.getLabel()); // add found node to result

            queue.remove(0);

            for(int i = 0; i < adj.length; i++) {
                if(adj[currIdx][i] == 1 && !visitedVertices[i]) {
                    queue.add(g.getVertex(i));
                    visitedVertices[i] = true;
                }
            }
        }

        return result;
    }

    public ArrayList<String> dfs(Graph g, Node start) {
        return null;
    }

    public ArrayList<String> djikstra(Graph g, Node start) {
        return null;
    }
}
