import java.util.*;

public class MergingTables {

    static int[] parent;
    static int[] rank; 
    static long[] rows;
    static long maxSize;

    static int find(int i) {
        if (i != parent[i])
            parent[i] = find(parent[i]); // path compression
        return parent[i];
    }

    static void union(int dest, int src) {
        int realDest = find(dest);
        int realSrc = find(src);

        if (realDest == realSrc)
            return; // already merged

        // Union by rank (optional optimization)
        if (rank[realDest] < rank[realSrc]) {
            int temp = realDest;
            realDest = realSrc;
            realSrc = temp;
        }

        parent[realSrc] = realDest;
        rows[realDest] += rows[realSrc];
        rows[realSrc] = 0;

        if (rank[realDest] == rank[realSrc])
            rank[realDest]++;

        maxSize = Math.max(maxSize, rows[realDest]);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); // number of tables
            int m = sc.nextInt(); // number of merge operations

            parent = new int[n + 1];
            rank = new int[n + 1];
            rows = new long[n + 1];
            maxSize = 0;

            for (int i = 1; i <= n; i++) {
                rows[i] = sc.nextLong();
                parent[i] = i;
                maxSize = Math.max(maxSize, rows[i]);
            }

            for (int i = 0; i < m; i++) {
                int dest = sc.nextInt();
                int src  = sc.nextInt();
                union(dest, src);
                System.out.println(maxSize);
            }
        }
    }
}
