//BST
/* Left Subtree: node contiaining only keys less than node's key
 * Right Subtree contains only keys greater than node's key 
 */
class Node{
    int data;
    Node left, right;
    Node(int data){
        this.data = data;
        left = right = null;
    }
}

public class BSTDemo{
/*Recursive 
 * if tree is empty -> new node becomes root 
 * if 'key < root.data' -> insert into left subtree
 * if 'key > root.data' -> insert into right subtree
 */
    static Node insert(Node root, int key) {
    if (root == null) {
        return new Node(key);
    }
    if (key < root.data) {
        root.left = insert(root.left, key);
    } else if (key > root.data) {
        root.right = insert(root.right, key);
    }
    // if equal, we skip or handle duplicates as you like
    return root;
    }

    //search (skip half of the tree (why?))
    static boolean searchBST(Node root, int key){
        if(root == null) return false;
        if(root.data == key) return true;
        if(key < root.data)
            return searchBST(root.left, key);
        else   
            return searchBST(root.right, key);
    }

    //inorder = sorted order
    static void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        System.out.println(root.data + " ");
        inorder(root.right);
    }

    //finding Minimum is leftmost node
    static int findMin(Node root){
        if(root == null) throw new IllegalArgumentException("Tree is empty");
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }

    //finding Maximum is the rightmost node
    static int findMax(Node root){
        if(root == null) throw new IllegalArgumentException("Tree is Empty");
        while(root.right != null){
            root = root.right;
        }
        return root.data;
    }
    
    //delete a node in BST
    static Node deleteNode(Node root, int key){
        if(root == null) return null;
        if(key < root.data){
            root.left = deleteNode(root.left, key);
        }else if(key > root.data){
            root.right = deleteNode(root.right, key);
        }else{
            //no child (leaf): just remove the node
            if (root.left == null && root.right == null){
                return null;
            }
            //one child: Replace node with its single child
            else if(root.left == null){
                return  root.right;
            }else if(root.right == null){
                return root.left;
            }
            //Two children: find inorder successor ( min in right subtree),
            //replace node's value with successor's value,
            //then delete successor from right subtree
            else{
                int successorValue = findMin(root.right);
                root.data = successorValue;
                root.right = deleteNode(root.right, successorValue);
            }
        }
        return root;
    }

    public static void main(String[] args){
        Node root = null;

        int[] values = {10, 5, 15, 2, 7, 20};
        for (int v : values) {
            root = insert(root, v);
        }

        System.out.print("Inorder (sorted): ");
        inorder(root);           // 2 5 7 10 15 20
        System.out.println();

        System.out.println("Search 7: " + searchBST(root, 7));  // true
        System.out.println("Search 42: " + searchBST(root, 42)); // false

        System.out.println("Min: " + findMin(root)); // 2
        System.out.println("Max: " + findMax(root)); // 20

        System.out.print("Before delete inorder: ");
        inorder(root); // 2 5 7 10 15 20
        System.out.println();

        root = deleteNode(root, 5);  // delete node with two children

        System.out.print("After delete inorder: ");
        inorder(root);
        System.out.println();


    }
}