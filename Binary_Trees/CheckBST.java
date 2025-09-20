import java.util.*;

public class CheckBST {
    static class Node {
        long key;
        int left;
        int right;
        Node(long key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("CORRECT");
            return;
        }

        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            long key = sc.nextLong();
            int left = sc.nextInt();
            int right = sc.nextInt();
            tree[i] = new Node(key, left, right);
        }

        if (isBST(0, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
    //long min and max
    static boolean isBST(int index, long min, long max) {
        if (index == -1) return true;

        Node node = tree[index];
        if (node.key <= min || node.key >= max) return false;

        return isBST(node.left, min, node.key) &&
               isBST(node.right, node.key, max);
    }
}
