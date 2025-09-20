//Binary Tree Traversal
import java.util.*;

public class TreeTraversal {
    static class Node {
        int key;
        int left;
        int right;

        Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();
            tree[i] = new Node(key, left, right);
        }

        // root is always node 0 (given in problem)
        int root = 0;

        // Perform traversals
        List<Integer> inorder = new ArrayList<>();
        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        inorder(root, inorder);
        preorder(root, preorder);
        postorder(root, postorder);

        printList(inorder);
        printList(preorder);
        printList(postorder);
    }

    static void inorder(int node, List<Integer> result) {
        if (node == -1) return;
        inorder(tree[node].left, result);
        result.add(tree[node].key);
        inorder(tree[node].right, result);
    }

    static void preorder(int node, List<Integer> result) {
        if (node == -1) return;
        result.add(tree[node].key);
        preorder(tree[node].left, result);
        preorder(tree[node].right, result);
    }

    static void postorder(int node, List<Integer> result) {
        if (node == -1) return;
        postorder(tree[node].left, result);
        postorder(tree[node].right, result);
        result.add(tree[node].key);
    }

    static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(list.get(i));
        }
        System.out.println();
    }
}
