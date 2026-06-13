public class BinarySearchTree {
    private static class Node {
        int data;
        Node left, right;
        
        Node(int data) {
            this.data = data;
        }
    }
    
    private Node root;
    
    public void insert(int data) {
        root = insert(root, data);
    }
    
    private Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) node.left = insert(node.left, data);
        else if (data > node.data) node.right = insert(node.right, data);
        return node;
    }
    
    public boolean search(int data) {
        return search(root, data);
    }
    
    private boolean search(Node node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return search(node.left, data);
        return search(node.right, data);
    }
    
    public void inorder() {
        inorder(root);
        System.out.println();
    }
    
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        
        bst.inorder();  // 20 30 40 50 70
        System.out.println(bst.search(40));  // true
        System.out.println(bst.search(100)); // false
    }
}
