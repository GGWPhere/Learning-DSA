//unweighted and undirected graph
import java.util.*;

//for now think of HaskMap as a simple Java collection that stores key-value pairs
/*Graph: a set of Vertices (a.k.a nodes) connected by edges
 *Directed vs Undirected: edges have a direction or not
 *Weighted vs Unweighted: edges carry a cost/weight or not
 *Paths and cycles: Sequences of vertices and edges; cycle starts and ends at the same vertex
 */

public class Graph{
    private final Map<Integer, List<Integer>> adj;
    public Graph(){
        adj = new HashMap<>();
    }
    public void addVertex(int v){
        adj.putIfAbsent(v, new ArrayList<>());
    }
    public void addEdge(int v, int w){
        adj.putIfAbsent(v, new ArrayList<>());
        adj.putIfAbsent(w, new ArrayList<>());
        adj.get(v).add(w);
        adj.get(w).add(v); //remove if directed 
    }
    public List<Integer> getNeighbors(int v){
        return adj.getOrDefault(v, Collections.emptyList());
    }
    public void printGraph(){
        for(var entry : adj.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}