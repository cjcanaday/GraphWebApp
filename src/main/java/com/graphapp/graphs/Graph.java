interface Graph {
    int MAXNUMVERTICES;
    int MAXNUMEDGES;

    void addVertex(String label);
    void removeVertex(String label);
    void addEdge(String label1, String label2);
    void removeEdge(String label1, String label2);
    void removeAllEdges();
    void removeAllVertices(String label);
}