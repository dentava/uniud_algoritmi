import java.util.Scanner;

class RedBlackTree {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        RedBlackTree rbTree = new RedBlackTree();
        
        String[] words;

        do {
            String str = myObj.nextLine();
            words = str.split(" ");
            if (words[0].equals("insert")) {
                rbTree.insert(Integer.parseInt(words[1]), words[2]);
            } else if (words[0].equals("show")) {
                rbTree.printRec(rbTree.root);
                System.out.println();
            } else if (words[0].equals("find")) {
                System.out.println(rbTree.find(rbTree.root, Integer.parseInt(words[1])));
            }
        } while (!(words[0].equals("exit")));
        
        myObj.close();
    }
 
    class Node {
        int key; 
        Node left ;
        Node right; 
        Node father; 
        String alphanumeric;
        String color;
    
        public Node(int key, String alphanumeric, Node father, Node left, Node right, String color) { 
            this.key = key; 
            this.alphanumeric = alphanumeric;
            this.father = father;
            this.left = left;
            this.right = right;
            this.color = color;
        } 

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }
    }
    
    Node root; 
    final Node NULL_NODE = new Node(-1, null, null, null, null, "black");
    
    public RedBlackTree() {
        root = NULL_NODE;
    }
    
    public String find(Node nodo, int keyToFind) {
        if (nodo == null || nodo.key == keyToFind) return nodo.alphanumeric;
        else if (nodo.key > keyToFind) return find(nodo.left, keyToFind);
        else return find(nodo.right, keyToFind);   
    }

    void preOrder(Node node) { 
        if (node != NULL_NODE) { 
            System.out.print(node.key + ":" + node.alphanumeric + ":" + node.color + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } else {
            System.out.println("NULL ");
        }
    } 

    void printRec(Node n) {
        if (n == null || n.key == 0) System.out.print("NULL ");
        else {
            System.out.print(n.key + ":" + n.alphanumeric + ":" + n.color + " ");
            printRec(n.left);
            printRec(n.right);
        }
    }

    public void leftRotate(Node nodo) {
        Node y = nodo.right;
        nodo.right = y.left;
        
        if (y.left != NULL_NODE) y.left.father = nodo;

        y.father = nodo.father;

        if (nodo.father == null) this.root = y;
        else if (nodo == nodo.father.left) nodo.father.left = y;
        else nodo.father.right = y;

        y.left = nodo;
        nodo.father = y;
    }

    public void rightRotate(Node nodo) {
        Node y = nodo.left;
        nodo.left = y.right;
        
        if (y.right != NULL_NODE) y.right.father = nodo;

        y.father = nodo.father;

        if (nodo.father == null) this.root = y;
        else if (nodo == nodo.father.right) nodo.father.right = y;
        else nodo.father.left = y;

        y.right = nodo;
        nodo.father = y;
    }

    public void insert(int key, String alphanumeric) {

        Node z = new Node(key, alphanumeric, null, NULL_NODE, NULL_NODE, "red");
        Node y = null;
        Node x = this.root;

        while (x != NULL_NODE) {
            y = x;
            if (z.key < x.key) x = x.left;
            else x = x.right;
        }

        z.father = y;

        if (y == null) root = z;
        else if (z.key < y.key) y.left = z;
        else y.right = z;
        
        if (z.father == null) {
            z.setColor("black");
            return;
        }
        if (z.father.father == null) return;

        fixUp(z);
    }

    public void fixUp(Node k) {
        Node u;

        while (k.father.getColor().equals("red")) {

            if (k.father == k.father.father.right) {
                u = k.father.father.left;
                if (u.getColor().equals("red")) {
                    u.setColor("black");
                    k.father.setColor("black");
                    k.father.father.setColor("red");
                    k = k.father.father;
                } else {
                    if (k == k.father.left) {
                        k = k.father;
                        rightRotate(k);
                    }
                    k.father.setColor("black");
                    k.father.father.setColor("red");
                    leftRotate(k.father.father);
                }
            } else {
                u = k.father.father.right;
                if (u.getColor().equals("red")) {
                    u.setColor("black");
                    k.father.setColor("black");
                    k.father.father.setColor("red");
                    k = k.father.father;
                } else {
                    if (k == k.father.right) {
                        k = k.father;
                        leftRotate(k);
                    }
                    k.father.setColor("black");
                    k.father.father.setColor("red");
                    rightRotate(k.father.father);
                }
            }
            if (k == root) break;
        }
        root.setColor("black");
    }
}