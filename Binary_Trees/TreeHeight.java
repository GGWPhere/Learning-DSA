import java.util.*;

public class TreeHeight {
    // BFS (level-order traversal)
    public static int treeHeightBFS(List<List<Integer>> children, int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        int height = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            height++;
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                for (int child : children.get(node)) {
                    q.add(child);
                }
            }
        }
        return height;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = sc.nextInt();
        }

        // build adjacency list
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                root = i;
            } else {
                children.get(parent[i]).add(i);
            }
        }

        // compute height
        int height = treeHeightBFS(children, root);
        System.out.println(height);
    }
}
