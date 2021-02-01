import java.util.Scanner;

class Heap {

    int[] heap;
    int size;

    Heap(int space) {
        heap = new int[space];
        size = 0;
    }

    Heap(int[] IntArray) {
        heap = IntArray;
        size = IntArray.length;
    }

    int size() {
        return size;
    }

    int key(int i) {
        return heap[i];
    }

    int parent(int i) {
        return ((i - 1) / 2);
    }

    int left(int i) {
        return (2 * i) + 1;
    }

    int right(int i) {
        return (2 * i) + 2;
    }

    int root() {
        return heap[0];
    }

    // versione modificata di insert: valida solo per H2
    void insertInH2(Heap H1, int index) {
        if (size() == heap.length)
            return;
        size += 1;
        int i = size() - 1;
        while (i > 0 && H1.key(key(parent(i))) >= H1.key(index)) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = index;
    }

    // versione modificata di removeRoot: valida solo per H2
    void removeRootInH2(Heap H1) {
        if (size() == 0)
            return;
        swap(0, size() - 1);
        size -= 1;
        heapifyMinH2(H1, 0);
    }

    // versione modificata di heapifyMin: valida solo per H2
    void heapifyMinH2(Heap H1, int i) {
        if (size() <= 1)
            return;
        int left = left(i);
        int right = right(i);
        int smallest;
        if (left < size() && H1.key(key(left)) <= H1.key(key(i)))
            smallest = left;
        else
            smallest = i;
        if (right < size() && H1.key(key(right)) <= H1.key(key(smallest)))
            smallest = right;
        if (smallest != i) {
            swap(i, smallest);
            heapifyMinH2(H1, smallest);
        }
    }

    void heapifyMin(int i) {
        if (size() <= 1)
            return;
        int left = left(i);
        int right = right(i);
        int smallest;
        if (left < size() && key(left) <= key(i))
            smallest = left;
        else
            smallest = i;
        if (right < size() && key(right) <= key(smallest))
            smallest = right;
        if (smallest != i) {
            swap(i, smallest);
            heapifyMin(smallest);
        }
    }

    void buildMinHeap() {
        for (int i = this.size() / 2 - 1; i >= 0; i--)
            heapifyMin(i);
    }

    void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // riceve un array disordinato e il k da trovare
    static int heapSelect(int[] array, int k) {
        Heap H1 = new Heap(array);
        Heap H2 = new Heap(k);
        H1.buildMinHeap();
        H2.insertInH2(H1, 0);
        int left, right;
        for (int i = 1; i <= k - 1; i++) {
            int j = H2.root();
            H2.removeRootInH2(H1);
            left = 2 * j + 1;
            right = 2 * j + 2;
            if (left < H1.size())
                H2.insertInH2(H1, left);
            if (right < H1.size())
                H2.insertInH2(H1, right);
        }
        return H1.key(H2.root());
    }

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();
        int k = myObj.nextInt();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(heapSelect(arr, k));

        myObj.close();

    }

    public static int[] stringToIntegerArray(String[] arr) {
        int size = arr.length;
        int[] intarr = new int[size];
        for (int i = 0; i < size; i++) {
            intarr[i] = Integer.parseInt(arr[i]);
        }
        return intarr;
    }

}