import java.util.Scanner;

class AVL {

    class Node {
        int key, height;
        Node left, right;
        String alphanumeric;

        Node(int d, String alphanumeric) {
            key = d;
            this.alphanumeric = alphanumeric;
            height = 1;
        }
    }

    Node root;

    public String find(Node nodo, int keyToFind) { // k: chiave che stai cercando; x: nodo da cui parto (per cercare

        if (nodo == null || nodo.key == keyToFind) { // (nodo.key == keyToFind &&
                                                     // nodo.alphanumeric.equals(alphanumericToFind))
            return nodo.alphanumeric;
        } else {
            if (nodo.key > keyToFind) {
                return find(nodo.left, keyToFind);
            } else {
                return find(nodo.right, keyToFind);
            }
        }
    }

    int altezzaAlbero(Node N) {
        if (N == null) {
            return 0;
        } else {
            return N.height;
        }
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotation(Node y) { // nodo y è il perno
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(altezzaAlbero(y.left), altezzaAlbero(y.right)) + 1;
        x.height = max(altezzaAlbero(x.left), altezzaAlbero(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotation(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(altezzaAlbero(x.left), altezzaAlbero(x.right)) + 1;
        y.height = max(altezzaAlbero(y.left), altezzaAlbero(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return altezzaAlbero(N.left) - altezzaAlbero(N.right);
    }

    void insert(int key, String alphanumeric) {
        root = insertSup(root, key, alphanumeric);
    }

    Node insertSup(Node node, int key, String alphanumeric) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new Node(key, alphanumeric));

        if (key < node.key)
            node.left = insertSup(node.left, key, alphanumeric);
        else if (key > node.key)
            node.right = insertSup(node.right, key, alphanumeric);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(altezzaAlbero(node.left), altezzaAlbero(node.right));

        /*
         * 3. Get the balance factor of this ancestor node to check whether this node
         * became unbalanced
         */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotation(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotation(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void printRec(Node n) {
        if (n == null) {
            System.out.print("NULL ");
        } else {
            System.out.print(n.key + ":" + n.alphanumeric + ":" + n.height + " ");
            printRec(n.left);
            printRec(n.right);
        }
    }

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        AVL tree = new AVL();

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
