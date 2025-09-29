//unweighted and undirected graph
import java.util.*;

//for now think of HaskMap as a simple Java collection that stores key-value pairs
/*Graph: a set of Vertices (a.k.a nodes) connected by edges
 *Directed vs Undirected: edges have a direction or not
 *Weighted vs Unweighted: edges carry a cost/weight or not
 *Paths and cycles: Sequences of vertices and edges; cycle starts and ends at the same vertex
 */

     /* 
    public void addVertex(int v){
        adj.putIfAbsent(v, new ArrayList<>());
    }
    */

public class Graph{
    int V;
    List<List<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // undirected
    }

    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.println(i + " -> " + adj.get(i));
        }
    }

    //Level 1 Traversal
    //BFS: Breath-First Search 
    /*the idea is to traverse level by level: pick a start vertex
     *Mark visited and put in queue
     *While queue not empty:
     *  -Dequeue vertex 'u' and each unvisited neighbor 'v' of 'u'
     *  -Mark 'v' visited and enqueue it
     */
    //Uses Queue FIFO and is good for finding shortest paths in unweighted
    void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.offer(start);

        System.out.print("BFS from " + start + ": ");
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        System.out.println();
    }

    //DFS: Depth-First Search (recursive)
    /*Go as deep as possible along branch before backtracking
     *Visit vertex 'u' and mark visited
     *For each unvisited neighbor 'v' call DFS(v)
     */
    //uses Recursion Stack or explicit Stack: good for checking connectivity, detecting cycles, topological sorting
    void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS from " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int u, boolean[] visited) {
        visited[u] = true;
        System.out.print(u + " ");
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsHelper(v, visited);
            }
        }
    }
}