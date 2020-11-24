package quickselect;

import java.util.Scanner;

public class Executable {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();
        int k = myObj.nextInt();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(quickSelect(arr, k-1, 0, arr.length - 1));

    }

    public static int[] stringToIntegerArray(String[] arr) {
        int size = arr.length;
        int[] intarr = new int[size];
        for (int i = 0; i < size; i++) {
            intarr[i] = Integer.parseInt(arr[i]);
        }
        return intarr;
    }
    

    public static int quickSelect(int[] arr, int k, int p, int q) {
        if (p <= q) {
            int g = partition(arr, p, q);
            if (g == k)
                return arr[g];
            else if (k < g)
                return quickSelect(arr, k, p, g - 1);
            else
                return quickSelect(arr, k, g + 1, q);
        }
        return 0;
    }

    public static int partition(int[] array, int p, int q) {

        int i = p - 1;
        int x = array[q];
        for (int j = p; j <= q; j++) {
            if (array[j] <= x) {
                i = i + 1;
                scambia(array, i, j);
            }
        }
        return i;
    }

    public static void scambia(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
