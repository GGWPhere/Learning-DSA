import java.util.*;

public class DetectAnomalies {
    static class Edge {
        int from, to;
        int weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); 
            int m = sc.nextInt(); 

            ArrayList<Edge> edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                edges.add(new Edge(u, v, w));
            }

            long[] dist = new long[n + 1];
            Arrays.fill(dist, 0); 

            boolean hasNegativeCycle = false;

            for (int i = 0; i < n - 1; i++) {
                for (Edge e : edges) {
                    if (dist[e.to] > dist[e.from] + e.weight) {
                        dist[e.to] = dist[e.from] + e.weight;
                    }
                }
            }

            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.weight) {
                    hasNegativeCycle = true;
                    break;
                }
            }

            System.out.println(hasNegativeCycle ? 1 : 0);
        }
    }
}
