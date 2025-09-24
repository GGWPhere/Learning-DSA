/* 
import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left = right = null;
    }
}

public class BinaryTreeDemo {
    //Traversals (recursive)
    //Pre-order: Root -> left -> Right
    static void preOrder(Node root){
        if(root == null) return;
        System.err.println(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //InOrder: left -> Root -> Right (Depth first traversal)
    static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.println(root.data + " ");
        inOrder(root.right);
    }

    //PostOrder: Left -> Right -> Root
    static void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data + " ");
    }

    //level-order using queue (BFS)
    static void levelOrder(Node root){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node temp = q.poll();
            System.out.println(temp.data + " ");
            if (temp.left != null) q.add(temp.left); 
            if (temp.right != null) q.add(temp.right);
        } 
    }

    //Find height
    static int height(Node root){
        if(root == null) return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    //check if value exist
    static boolean search(Node root, int key){
        if(root == null) return false;
        if(root.data == key) return true;
        return search(root.left, key) || search(root.right, key);
    }

    //count nodes
    static int countNodes(Node root){
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //count leaves
    static int countLeaves(Node root){
        if (root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }
*/
/* 
    public static void main(String[] args) {
        // manually build a small tree:
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.right = new Node(20);
        System.out.println("Level order: ");
        levelOrder(root);
        System.out.println();

        System.out.print("Inorder: ");
        inOrder(root);      // 2 5 7 10 15 20
        System.out.println();

        System.out.print("Preorder: ");
        preOrder(root);     // 10 5 2 7 15 20
        System.out.println();

        System.out.print("Postorder: ");
        postOrder(root);    // 2 7 5 20 15 10
        System.out.println();

        System.out.println("Height: " + height(root));
        System.out.println();

        int key = 7;
        System.out.println("Does " + key + " exist? " + search(root, key));

        System.out.println("Total nodes: " + countNodes(root));
        System.out.println("Leaf nodes: " + countLeaves(root));

    }
}
*/