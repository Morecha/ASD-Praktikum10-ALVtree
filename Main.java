class AVLTree {
    private Node root;

    private class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private int height(Node node) {     //return ketinggian tree (dibuat balance factor)
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(Node node) {      //melihat balance factornya
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {      //R
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {       //L
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {         //input
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {        //lanjutan input
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        } else {
            // Nilai sudah ada dalam AVL Tree
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        // Jika node tidak seimbang, lakukan rotasi sesuai kasus
        if (balance > 1 && value < node.left.value) {           //RR
            return rotateRight(node);
        }

        if (balance < -1 && value > node.right.value) {         //LL
            return rotateLeft(node);
        }

        if (balance > 1 && value > node.left.value) {           //LR
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && value < node.right.value) {         //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void printPreorder() {
        printPreorderRecursive(root);
        System.out.println();
    }

    private void printPreorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            printPreorderRecursive(node.left);
            printPreorderRecursive(node.right);
        }
    }

    public void printInorder() {
        printInorderRecursive(root);
        System.out.println();
    }

    private void printInorderRecursive(Node node) {
        if (node != null) {
            printInorderRecursive(node.left);
            System.out.print(node.value + " ");
            printInorderRecursive(node.right);
        }
    }

    public void printPostorder() {
        printPostorderRecursive(root);
        System.out.println();
    }

    private void printPostorderRecursive(Node node) {
        if (node != null) {
            printPostorderRecursive(node.left);
            printPostorderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void find(int value){
        if(root!=null){
            int high = 0;
            Node temp = root;
            while(true){
                if(temp!=null){
                    if(value<temp.value){
                        temp = temp.left;
                        high = high+1;
                    }else if(value>temp.value){
                        temp = temp.right;
                        high = high+1;
                    }else if(value==temp.value){
                        high = high+1;
                        System.out.println("Data berada di kedalaman n="+high);
                        return;
                    } 
                }else{
                    System.out.println("Data tidak ditemukan");
                    return;
                }
            }
        }
        System.out.println("AVL tree kosong");
    }
}

public class Main{
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(10);
        tree.insert(7);
        tree.insert(11);
        tree.insert(19);
        tree.insert(8);

    
        tree.printPreorder();
        tree.printPostorder();
        System.out.println();
        tree.find(11);
        tree.find(8);
        tree.find(99);
    }
}


