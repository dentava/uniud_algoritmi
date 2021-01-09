public class MergeSort {

    public static void mergeSort(int[] array, int p, int r) {
        if (p<r) {
            int q = Math.floorDiv((p+r), 2);
            mergeSort(array, p, q);
            mergeSort(array, q+1, r);
            merge(array, p, q, r);
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;

        int[] L = new int[n1+1]; //abbiamo tolto la pos della sentinella
        int[] R = new int[n2+1]; //abbiamo tolto la pos della sentinella

        for (int i=0; i<n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j=0; j<n2; j++) {
            R[j] = arr[q+j+1];
        }

        L[n1] = 1000000000;
        R[n2] = 1000000000;

        int i=0;
        int j=0;

        for (int k=p; k<=r; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
    }
}
