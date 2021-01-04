import java.util.Arrays;
import java.util.Scanner;

public class Executable {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();
        int k = myObj.nextInt();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(quickerSelect(arr, k-1, 0, arr.length-1));

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

    public static int quickerSelect(int[] arr, int k, int p, int q) { //Controllare
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

    public static int medianOfMedians(int[] array) {
      int n = array.length;

      if (n <= 5) {
        mergeSort(array, 0, n-1);

        if (n % 2 == 0) return array[(n/2) -1];
        else return array[(n/2)];
      }

      int numeroIndici;

      if (n % 5 == 0) numeroIndici = n/5;
      else numeroIndici = (n/5) + 1;

      int[] vettoreMediane = new int[numeroIndici];

      int indiceInf = 0;
      int indiceSup = 4;

      for (int i=1; i<=numeroIndici; i++){
        if (indiceSup >= n) indiceSup = n-1;

        mergeSort(array, indiceInf, indiceSup);

        indiceInf = indiceInf + 5;
        indiceSup = indiceSup + 5;
      }

      for (int i=2; i < n; i=i+5) {
        vettoreMediane[i/5] = array[i];
      }

      if (n % 5 != 0) {
        int r = n % 5;
        vettoreMediane[numeroIndici-1] = array[n - ((r/2)+1)];
      }

      return medianOfMedians(vettoreMediane);
    }

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

        int[] L = new int[n1+1];
        int[] R = new int[n2+1];

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

    public static int partition(int[] array, int p, int q) {

    //for (int c : array) { System.out.println(c); }
    //System.out.println("---");

    int[] copia = new int[q-p+1];
    int v=0;
    for(int i=p; i<=q; i++) {
        copia[v] = array[i];
        v++;
    }

    //System.out.println("Copia:");
    //for (int c : copia) { System.out.println(c); }
    //System.out.println("---");


    int posizioneMOM = -1;
    int medianaDelleMediane = medianOfMedians(copia); //Qui c'è errore

    //System.out.println("MOM:" + medianaDelleMediane);


    //System.out.println("Array pre ricerca posizione MOM:");
    //for (int c : array) { System.out.println(c); }
    //System.out.println("---");


    for (int i=0; i<array.length; i++) {
        if (array[i] == medianaDelleMediane) posizioneMOM=i;
    }

    //System.out.println("Posizone MOM in array iniziale:" + posizioneMOM);

    //for (int c : array) { System.out.println(c); }
    //System.out.println("---");

    scambia(array, posizioneMOM, q);

    int i = p - 1;
    int x = array[q];
    for (int j = p; j <= q; j++) {
        if (array[j] <= x) {
            i++;
            scambia(array, i, j);
        }
    }



    //System.out.println("Inizio array dopo esecuzione partition");
    //for (int z: array) { System.out.println(z);}
    //System.out.println("Fine array dopo esecuzione partition");

    //System.out.println("Fine esecuzione Partition");

    //System.out.println("i:" + i);

    return i;
}

    public static void scambia(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
