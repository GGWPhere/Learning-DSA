import java.util.*;

public class FlightCost {
    static class Edge {
        int to;
        long weight;
        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        long distance;
        Node(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.distance, other.distance);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); 
            int m = sc.nextInt(); 

            ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                adj.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                long weight = sc.nextLong();
                adj.get(from).add(new Edge(to, weight)); 
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            long[] dist = new long[n + 1];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[start] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                if (current.distance > dist[current.vertex]) continue;
                for (Edge edge : adj.get(current.vertex)) {
                    if (dist[edge.to] > dist[current.vertex] + edge.weight) {
                        dist[edge.to] = dist[current.vertex] + edge.weight;
                        pq.add(new Node(edge.to, dist[edge.to]));
                    }
                }
            }

            System.out.println(dist[end] == Long.MAX_VALUE ? -1 : dist[end]);
        }
    }
}
