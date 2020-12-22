import java.util.Scanner;

public class Executable {

    public static void main(String[] args) {

        int[] array = new int[] { 5, 7, 11, 8, 3, 8, 6, 9, 1, 78, 34 };

        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();
        int k = myObj.nextInt();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(heapSelect(arr, k));

    }

    public static int[] stringToIntegerArray(String[] arr) {
        int size = arr.length;
        int[] intarr = new int[size];
        for (int i = 0; i < size; i++) {
            intarr[i] = Integer.parseInt(arr[i]);
        }
        return intarr;
    }

    // Se noi cerchiamo il figlio sinistro di un elemento in posizione 1, per la
    // Piazza staremo cercando il figlio di un elemento in posizione 2, per la
    // Piazza figlio six sarebbe in posizione 4, per noi 3
    // 4, 5, 10, 9
    public static void minHeapify(int[] A, int i, int heapsize) {

        // Adattamento a indicizzazione di Java degli array
        int l = ((i + 1) * 2) - 1; // Posizione figlio sx
        int r = (((i + 1) * 2) - 1) + 1; // Posizione figlio dx
        // int ultimaPosizione = A.length-1;

        int minimo = -1; // Posizione del minimo

        if (l <= heapsize && A[l] < A[i]) {
            minimo = l;
        } else {
            minimo = i;
        }

        if (r <= heapsize && A[r] < A[minimo]) {
            minimo = r;
        }

        if (minimo != i) {
            scambia(A, i, minimo);
            minHeapify(A, minimo, heapsize);
        }
    }

    public static void scambia(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void buildMinHeap(int[] vettore) {

        for (int i = Math.floorDiv(vettore.length, 2); i >= 0; i--) {
            minHeapify(vettore, i, vettore.length - 1);
        }
    }

    public static void minHeapExtract(int[] heap, int heapsize) {
        if (heapsize >= 1) {
            scambia(heap, 0, heapsize);
            minHeapify(heap, 0, heapsize - 1);
        }
    }

    public static int heapSelect(int[] A, int k) {

        buildMinHeap(A);
        int[] H1 = A;
        int[] H2 = H1;

        int heapsize = A.length - 1;

        // dopo k-1 iterazioni, la radice di H2 corrisponderà al k-esimo elemento +
        // piccolo
        for (int i = 1; i < k; i++) {
            minHeapExtract(H2, heapsize);
            heapsize--;
        }

        return H2[0];

    }

}
