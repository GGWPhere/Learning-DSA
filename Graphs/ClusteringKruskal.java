import java.util.*;

public class ClusteringKruskal {

    static class Edge implements Comparable<Edge> {
        int a, b;
        double d;
        Edge(int a, int b, double d) { this.a = a; this.b = b; this.d = d; }
        public int compareTo(Edge o) { return Double.compare(this.d, o.d); }
    }

    static class DSU {
        int[] p, r;
        DSU(int n) {
            p = new int[n];
            r = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }
        int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (r[a] < r[b]) { int t = a; a = b; b = t; }
            p[b] = a;
            if (r[a] == r[b]) r[a]++;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextDouble();
            y[i] = sc.nextDouble();
        }
        int k = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dx = x[i] - x[j];
                double dy = y[i] - y[j];
                double dist = Math.hypot(dx, dy);
                edges.add(new Edge(i, j, dist));
            }
        }
        Collections.sort(edges);

        DSU dsu = new DSU(n);
        int components = n;
        int idx = 0;

        while (components > k && idx < edges.size()) {
            Edge e = edges.get(idx++);
            if (dsu.union(e.a, e.b)) {
                components--;
            }
        }

        double answer = 0.0;
        while (idx < edges.size()) {
            Edge e = edges.get(idx++);
            if (dsu.find(e.a) != dsu.find(e.b)) {
                answer = e.d;
                break;
            }
        }

        System.out.printf(Locale.US, "%.12f%n", answer);
    }
}
