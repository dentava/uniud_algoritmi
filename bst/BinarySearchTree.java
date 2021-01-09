import java.util.Scanner;

public class BinarySearchTree {

    class Node {
        int key;
        String alphanumeric;
        Node left, right;

        public Node(int item, String valueAlphanumeric) {
            key = item;
            alphanumeric = valueAlphanumeric;
            left = right = null;
        }

        public int getKey() {
            return key;
        }

        public String getAlphanumeric() {
            return alphanumeric;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    public String find(Node nodo, int keyToFind) {
        if (nodo == null || nodo.key == keyToFind) return nodo.alphanumeric;
        else {
            if (nodo.key > keyToFind) return find(nodo.left, keyToFind);
            else return find(nodo.right, keyToFind);
        }
    }

    void insert(int key, String alphanumeric) root = insertRec(root, key, alphanumeric);

    Node insertRec(Node root, int key, String alphanumeric) {

        if (root == null) return new Node(key, alphanumeric);

        if (key < root.key) root.left = insertRec(root.left, key, alphanumeric);
        else if (key > root.key) root.right = insertRec(root.right, key, alphanumeric);
        return root;
    }

    void BSTinorder() {
        BSTinorderRec(root);
    }

    void BSTinorderRec(Node root) {
        if (root != null) {
            BSTinorderRec(root.left);
            System.out.println(root.key);
            BSTinorderRec(root.right);
        }
    }

    void BSTPreOrder() {
        BSTPreOrderRec(root);
    }

    void BSTPreOrderRec(Node n) {
        if (n != null) {
            System.out.println(n.key + " " + n.alphanumeric);
            BSTPreOrderRec(n.left);
            BSTPreOrderRec(n.right);
        }
    }

    void printRec(Node n) {
        if (n == null) {
            System.out.print("NULL ");
        } else {
            System.out.print(n.key + ":" + n.alphanumeric + " ");
            printRec(n.left);
            printRec(n.right);
        }
    }

    public static int contaAltezza(Node nodo) {
        if (nodo == null) return 0;

        int hleft = contaAltezza(nodo.left);
        int hright = contaAltezza(nodo.right);

        if (hleft >= hright) return 1 + hleft;
        else return 1 + hright;
    }

    // Driver Code
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        
        String[] words;
        do {
            String str = myObj.nextLine();
            words = str.split(" ");
            if (words[0].equals("insert")) {
                tree.insert(Integer.parseInt(words[1]), words[2]);
            } else if (words[0].equals("show")) {
                tree.printRec(tree.root);
                System.out.println();
            } else if (words[0].equals("find")) {
                System.out.println(tree.find(tree.root, Integer.parseInt(words[1])));
            }
        } while (!(words[0].equals("exit")));
        
        myObj.close();
    }
}