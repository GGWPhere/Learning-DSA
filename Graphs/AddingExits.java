import java.util.*;

public class AddingExits {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;

    static void dfs(int node) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) dfs(neighbor);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); 
            int m = sc.nextInt(); 

            adj = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                adj.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u); 
            }

            visited = new boolean[n + 1];
            int components = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                    components++;
                }
            }

            System.out.println(components);
        }
    }
}
