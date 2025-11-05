import java.util.*;

public class BuildingRoads {

    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(sc.nextDouble(), sc.nextDouble());
            }

            boolean[] visited = new boolean[n];
            double[] minDist = new double[n];
            Arrays.fill(minDist, Double.POSITIVE_INFINITY);
            minDist[0] = 0.0;

            double totalCost = 0.0;

            for (int i = 0; i < n; i++) {
                int u = -1;
                for (int j = 0; j < n; j++) {
                    if (!visited[j] && (u == -1 || minDist[j] < minDist[u])) {
                        u = j;
                    }
                }

                visited[u] = true;
                totalCost += minDist[u];

                for (int v = 0; v < n; v++) {
                    if (!visited[v]) {
                        double dist = distance(points[u], points[v]);
                        if (dist < minDist[v]) {
                            minDist[v] = dist;
                        }
                    }
                }
            }

            System.out.printf("%.9f%n", totalCost);
        }
    }
}
