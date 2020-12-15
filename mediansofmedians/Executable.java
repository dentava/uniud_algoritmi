import java.util.Arrays;
import java.util.Scanner;

public class Executable {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(quickerSelectCaller(arr, 4));

    }

    public static int[] stringToIntegerArray(String[] arr) {
        int size = arr.length;
        int[] intarr = new int[size];
        for (int i = 0; i < size; i++) {
            intarr[i] = Integer.parseInt(arr[i]);
        }
        return intarr;
    }

    public static int quickerSelectCaller(int[] array, int indice) {

        quickerSelect(array, indice, 0, array.length);
        
    }


    public static int quickerSelect(int[] arr, int k, int p, int q) {
        if (p <= q) {
            int posizioneMedianOfMedians = partition(arr, p, q);
            if (posizioneMedianOfMedians == k)
                return arr[posizioneMedianOfMedians];
            else if (k < posizioneMedianOfMedians)
                return quickerSelect(arr, k, p, posizioneMedianOfMedians - 1);
            else
                return quickerSelect(arr, k, posizioneMedianOfMedians + 1, q);
        }
        return 0;
    }

    public static int partition(int[] array, int p, int q) {

        //1. Cerco medianOfMedians in array e restituisco la sua posizione
        int posizioneInizialeMOM = 0;
        for (int b=0; b < array.length; b++) {
            if (array[b] == medianOfMedians(array)) {
                posizioneInizialeMOM = b;
            }
        }

        scambia(array, posizioneInizialeMOM, array.length-1); //2. Scambio medianOfMedians con l'ultimo elemento

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


    public static int medianOfMedians(int[] array) {

        if (array.length <= 5) {
            MergeSort.mergeSort(array, 0, array.length-1);

            return array[(int) Math.ceil(array.length/2)];
        }

        int n = array.length;
        int numeroIndici = (int) Math.ceil(array.length/5);
        int[] vettoreMediane = new int[numeroIndici];

        int indiceInf = 0;
        int indiceSup = 4;

        for (int i=1; i<numeroIndici; i++) {
            MergeSort.mergeSort(array, indiceInf, indiceSup);
            indiceInf = indiceInf +5;
            indiceSup = indiceSup +5;
        }

        if (n % 5 != 0) {
            int indiceUltimoBlocco = (numeroIndici-1)*5;
            int r = n % 5;
            MergeSort.mergeSort(array, indiceUltimoBlocco, indiceUltimoBlocco+r);
        }

        int v = 0;
        for (int i=2; i<n - (n%5); i=i+5) {
            vettoreMediane[v] = array[i];
            v++;
        }

        if (n % 5 != 0) {
            int r = n % 5;
            vettoreMediane[vettoreMediane.length -1] = array[(int) (n - Math.ceil(r/2))];
        }
        return medianOfMedians(vettoreMediane);
    }


}
