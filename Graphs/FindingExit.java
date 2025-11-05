import java.util.*;

public class FindingExit {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;

    static boolean dfs(int node, int target) {
        visited[node] = true;
        if (node == target) return true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor] && dfs(neighbor, target))
                return true;
        }
        return false;
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

            int u = sc.nextInt(); 
            int v = sc.nextInt(); 

            visited = new boolean[n + 1];

            boolean pathExists = dfs(u, v);
            System.out.println(pathExists ? 1 : 0);
        }
    }
}
