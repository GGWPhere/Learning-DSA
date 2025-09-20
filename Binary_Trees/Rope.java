import java.util.*;

public class Rope {
    static class Node {
        char c;
        int priority, size;
        Node left, right;

        Node(char c) {
            this.c = c;
            this.priority = new Random().nextInt();
            this.size = 1;
        }
    }

    static Node root;
    static Random rng = new Random();

    // Update size of subtree
    static void update(Node n) {
        if (n == null) return;
        n.size = 1 + size(n.left) + size(n.right);
    }

    static int size(Node n) {
        return (n == null) ? 0 : n.size;
    }

    // Split into [0..k-1] and [k..end]
    static Node[] split(Node n, int k) {
        if (n == null) return new Node[]{null, null};
        if (size(n.left) >= k) {
            Node[] parts = split(n.left, k);
            n.left = parts[1];
            update(n);
            return new Node[]{parts[0], n};
        } else {
            Node[] parts = split(n.right, k - size(n.left) - 1);
            n.right = parts[0];
            update(n);
            return new Node[]{n, parts[1]};
        }
    }

    // Merge two trees (all keys in L < all in R)
    static Node merge(Node L, Node R) {
        if (L == null) return R;
        if (R == null) return L;
        if (L.priority > R.priority) {
            L.right = merge(L.right, R);
            update(L);
            return L;
        } else {
            R.left = merge(L, R.left);
            update(R);
            return R;
        }
    }

    // Build tree from string
    static Node build(String s) {
        Node t = null;
        for (char c : s.toCharArray()) {
            t = merge(t, new Node(c));
        }
        return t;
    }

    // Cut substring [i..j] and insert at position k
    static void process(int i, int j, int k) {
        // Split into three parts: A [0..i-1], B [i..j], C [j+1..end]
        Node[] t1 = split(root, i);
        Node[] t2 = split(t1[1], j - i + 1);
        Node A = t1[0], B = t2[0], C = t2[1];

        // Remaining string = A + C
        root = merge(A, C);

        // Insert B after k
        Node[] t3 = split(root, k);
        root = merge(merge(t3[0], B), t3[1]);
    }

    // Collect string
    static void inorder(Node n, StringBuilder sb) {
        if (n == null) return;
        inorder(n.left, sb);
        sb.append(n.c);
        inorder(n.right, sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        int q = sc.nextInt();
        root = build(S);

        for (int x = 0; x < q; x++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();
            process(i, j, k);
        }

        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        System.out.println(sb.toString());
    }
}
