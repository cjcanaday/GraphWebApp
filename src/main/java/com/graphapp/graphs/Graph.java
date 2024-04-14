interface Graph {
    int MAX_NUM_VERTICES;
    int MAX_NUM_EDGES;

    void addVertex(String label);
    void removeVertex(String label);
    void addEdge(String label1, String label2);
    void removeEdge(String label1, String label2);
    void removeAllEdges();
    void removeAllVertices(String label);
}