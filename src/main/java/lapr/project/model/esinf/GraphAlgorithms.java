package lapr.project.model.esinf;

import lapr.project.model.Place;
import lapr.project.utils.Graph;


import java.util.ArrayList;
import java.util.Comparator;

import java.util.LinkedList;
import java.util.function.BinaryOperator;

public class GraphAlgorithms {


    /** Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g initial graph
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V,E> AdjacencyMatrixGraph <V,E> minDistGraph(Graph <V,E> g, Comparator<E> ce, BinaryOperator<E> sum) {
        int nV = g.numVertices();
        int i;
        int j;
        int k;

        if (nV == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        E[][] mat = (E[][]) new Object[nV][nV];

        // preencher a matriz com as ligações já presentes no grafo
        for (i = 0; i < nV; i++) {
            for (j = 0; j < nV; j++) {
                Edge<E, V> edge = (Edge<E, V>) g.edge(i, j);
                if (edge != null) {
                    mat[i][j] = (E) edge.getWeight();
                }
            }
        }

        //preencher com os caminhos mais curtos
        for(k=0; k < nV; k++){
            for(i=0; i<nV; i++){
                if(i!=k && mat[i][k]!=null){
                    for(j=0; j<nV; j++){
                        if(i!=j && k!=j && mat[k][j]!=null){
                            E s = sum.apply(mat[i][k],mat[k][j]);

                            if(mat[i][j] == null || ce.compare(mat[i][j],s) > 0){
                                mat[i][j] =s;
                            }
                        }
                    }
                }
            }
        }

        return new AdjacencyMatrixGraph<>(g.isDirected(), g.vertices(), mat);
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {

        int vKey = g.key(vOrig);
        dist[vKey] = zero;
        pathKeys[vKey] = vOrig;

        while(vOrig != null){
            vKey = g.key(vOrig);
            visited[vKey] = true;
            for(Edge<V,E> edge : g.outgoingEdges(vOrig)){
                int vKeyAdj = g.key(edge.getVDest());
                if(!visited[vKeyAdj]){
                    E s = sum.apply(dist[vKey], edge.getWeight());
                    if(dist[vKeyAdj] == null || ce.compare(dist[vKeyAdj],s) > 0){
                        dist[vKeyAdj] = s;
                        pathKeys[vKeyAdj] = vOrig;
                    }
                }
            }

            E minDist = null;
            vOrig = null;
            for(V vert : g.vertices()){
                int i = g.key(vert);
                if(!visited[i] && dist[i] != null && (minDist == null || ce.compare(dist[i],minDist) < 0)){
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }


    /** Shortest-path between two vertices
     *
     * @param g graph
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {

        if(!g.validVertex(vOrig) || !g.validVertex(vDest)){
            return null;
        }

        shortPath.clear();
        int numVerts = g.numVertices();

        boolean[] visited = new boolean[numVerts];

        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];

        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];

        for(int i=0; i < numVerts; i++){
            dist[i] = null;
            pathKeys[i] = null;
        }

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        E lengthPath = dist[g.key(vDest)];

        if(lengthPath == null){
            return null;
        }

        getPath(g, vOrig, vDest, pathKeys, shortPath);
        return lengthPath;
    }

    /** Shortest-path between a vertex and all other vertices
     *
     * @param g graph
     * @param vOrig start vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        int numVerts = g.numVertices();

        boolean[] visited = new boolean[numVerts];

        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];

        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];

        for(int i=0; i < numVerts; i++){
            dist[i] = null;
            pathKeys[i] = null;
        }

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        dists.clear();
        paths.clear();

        for(int i=0; i<numVerts; i++){
            dists.add(null);
            paths.add(null);
        }

        for(V vDest : g.vertices()){
            int v = g.key(vDest);

            if(dist[v] != null){
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDest, pathKeys, shortPath);
                paths.set(v, shortPath);
                dists.set(v, dist[v]);
            }
        }

        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V [] pathKeys, LinkedList<V> path) {

        if(vOrig.equals(vDest)){
            path.push(vOrig);
        }

        else{
            path.push(vDest);
            int vKey = g.key(vDest);
            vDest = pathKeys[vKey];
            getPath(g,vOrig,vDest,pathKeys,path);
        }
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vOrig vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        visited[g.key(vOrig)] = true;

        for (V vertex : g.adjVertices(vOrig)) {
            if (! visited[g.key(vertex)]) {
                qdfs.add(vertex);
                DepthFirstSearch(g, vertex, visited, qdfs);
            }
        }
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex of graph g that will be the source of the search

     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> resultQueue = new LinkedList<>();
        resultQueue.add(vert);
        boolean[] knownVertices = new boolean[g.numVertices()];

        DepthFirstSearch(g, vert, knownVertices, resultQueue);

        return resultQueue;
    }

    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if(!g.validVertex(vert))
            return null;

        LinkedList<V> quaux = new LinkedList<>();
        LinkedList<V> qbls = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];
        quaux.add(vert); qbls.add(vert);
        int vkey = g.key(vert);
        visited[vkey] = true;

        while(!quaux.isEmpty()) {
            vert = quaux.remove();
            for(V vAdj : g.adjVertices(vert)) {
                vkey = g.key(vAdj);
                if(!visited[vkey]) {
                    quaux.add(vAdj);
                    visited[vkey] = true;
                    qbls.add(vAdj);
                }
            }
        }
        return qbls;
    }




}
