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

    public String find(Node nodo, int keyToFind) {
        if (nodo == null || nodo.key == keyToFind) return nodo.alphanumeric;
        else {
            if (nodo.key > keyToFind) return find(nodo.left, keyToFind);
            else return find(nodo.right, keyToFind);
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

    Node rightRotation(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(altezzaAlbero(y.left), altezzaAlbero(y.right)) + 1;
        x.height = max(altezzaAlbero(x.left), altezzaAlbero(x.right)) + 1;

        return x;
    }

    Node leftRotation(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(altezzaAlbero(x.left), altezzaAlbero(x.right)) + 1;
        y.height = max(altezzaAlbero(y.left), altezzaAlbero(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {
        if (N == null) return 0;
        return altezzaAlbero(N.left) - altezzaAlbero(N.right);
    }

    void insert(int key, String alphanumeric) {
        root = insertSup(root, key, alphanumeric);
    }

    Node insertSup(Node node, int key, String alphanumeric) {

        if (node == null) return (new Node(key, alphanumeric));
        if (key < node.key) node.left = insertSup(node.left, key, alphanumeric);
        else if (key > node.key) node.right = insertSup(node.right, key, alphanumeric);
        else return node;

        node.height = 1 + max(altezzaAlbero(node.left), altezzaAlbero(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) return rightRotation(node);

        if (balance < -1 && key > node.right.key) return leftRotation(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

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
