
public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);

        g.printGraph();

        g.bfs(0);  // uses Graph's own bfs()
        g.dfs(0);  // uses Graph's own dfs()
    }
}
