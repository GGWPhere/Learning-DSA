import java.util.*;

/**
 * Single-source shortest paths with negative cycle detection.
 * Outputs for each vertex:
 *  - "*" if vertex unreachable from source
 *  - "-" if reachable but distance is -infinity (affected by negative cycle)
 *  - otherwise the shortest distance value
 */
public class ExchangingMoneyOptimally {

    static class Edge {
        int from, to;
        long weight;
        Edge(int f, int t, long w) { from = f; to = t; weight = w; }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<Edge> edges = new ArrayList<>(m);
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                long w = sc.nextLong();
                edges.add(new Edge(u, v, w));
                graph[u].add(v); 
            }

            int s = sc.nextInt();

            final long INF = (long)9e18;
            long[] dist = new long[n + 1];
            Arrays.fill(dist, INF);
            dist[s] = 0;

            for (int iter = 0; iter < n - 1; iter++) {
                boolean updated = false;
                for (Edge e : edges) {
                    if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight) {
                        dist[e.to] = dist[e.from] + e.weight;
                        updated = true;
                    }
                }
                if (!updated) break;
            }

            boolean[] negInf = new boolean[n + 1];

            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight) {
                    negInf[e.to] = true;
                }
            }

            Deque<Integer> dq = new ArrayDeque<>();
            for (int v = 1; v <= n; v++) {
                if (negInf[v]) dq.addLast(v);
            }
            while (!dq.isEmpty()) {
                int u = dq.pollFirst();
                for (int nb : graph[u]) {
                    if (!negInf[nb]) {
                        negInf[nb] = true;
                        dq.addLast(nb);
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for (int v = 1; v <= n; v++) {
                if (dist[v] == INF) {
                    sb.append("*\n");
                } else if (negInf[v]) {
                    sb.append("-\n");
                } else {
                    sb.append(dist[v]).append('\n');
                }
            }
            System.out.print(sb.toString());
        }
    }
}
