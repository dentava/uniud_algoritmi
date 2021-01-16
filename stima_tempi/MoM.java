import java.util.Arrays;
import java.util.Scanner;

public class MoM {

    public static void main(String[] args) {

        /*
        Scanner myObj = new Scanner(System.in);

        String str = myObj.nextLine();
        int k = myObj.nextInt();

        String[] sarr = str.split(" ");
        int[] arr = stringToIntegerArray(sarr);

        System.out.println(quickerSelect(arr, k - 1, 0, arr.length - 1));

        myObj.close();
        */

        int[] arr = {756145610, 2018933017, -1452261785, -407034862, 2124844900, 760207428, -1054520709, 494071335,
                543988436, 188060051, 595338197, -1640179596, 353101574, -1652611500, -606519650, -632330942,
                -1503707476, -514038815, -1929501440, 1478943132, -626827202, -1361916583, 695677124, 725141537,
                1345882690, 1960537234, 251181884, 836238619, 1614415821, 500710990, -1551571102, -2095564849,
                711164233, -1820873300, 1159817793, 1164848406, 1978930551, 261083872, -1753811565, 2089766700,
                263945911, 1239574822, 1680484523, 1106065095, -310523769, -329928717, -447206507, -1403647637,
                379476297, 1195223917, 1537169530, 1510240508, 1506497608, 2031384605, -725706935, -503383916,
                -1418815492, -2018007581, 2069647252, 1062199959, -1455825779, -534000087, 1177556151, -1080308733,
                -260589931, 1682364763, 1658351679, -533810932, 2133033514, 1588897686, -380080504, 1711023847,
                1197898266, 483463327, -959524441, -2021073178, 37257080, 1980591894, 179506826, 813745860, 2093438295,
                -1857606934, -402491298, -820094398, 1831818453, -724044017, 2106211888, 549255666, -365109925,
                629126767, 1814109910, -1908843695, 262014242, -1512883171, 560854799, -1486854629, 140529144,
                -1160095024, -1475965463, -1881271589};

        System.out.println(arr.length);

        quickerSelect(arr, 3, 0, arr.length-1);
    }

    public static int[] stringToIntegerArray(String[] arr) {
        int size = arr.length;
        int[] intarr = new int[size];
        for (int i = 0; i < size; i++) {
            intarr[i] = Integer.parseInt(arr[i]);
        }
        return intarr;
    }

    public static int quickerSelect(int[] arr, int k, int p, int q) {

        /*
        for (int g = 0; g <= q; g++)
            System.out.println(arr[g]);
        
        for(int g=0; g<=q; g++) System.out.print(arr[g] + ", ");
        System.out.println("---");
        System.out.println(k + " " + p + " " + q);
        */

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
            mergeSort(array, 0, n - 1);

            if (n % 2 == 0)
                return array[(n / 2) - 1];
            else
                return array[(n / 2)];
        }

        int numeroIndici;

        if (n % 5 == 0)
            numeroIndici = n / 5;
        else
            numeroIndici = (n / 5) + 1;

        int[] vettoreMediane = new int[numeroIndici];

        int indiceInf = 0;
        int indiceSup = 4;

        for (int i = 1; i <= numeroIndici; i++) {
            if (indiceSup >= n)
                indiceSup = n - 1;

            mergeSort(array, indiceInf, indiceSup);

            indiceInf = indiceInf + 5;
            indiceSup = indiceSup + 5;
        }

        for (int i = 2; i < n; i = i + 5) {
            vettoreMediane[i / 5] = array[i];
        }

        if (n % 5 != 0) {
            int r = n % 5;
            vettoreMediane[numeroIndici - 1] = array[n - ((r / 2) + 1)];
        }

        return medianOfMedians(vettoreMediane);
    }

    public static void mergeSort(int[] array, int p, int r) {
        if (p < r) {
            int q = Math.floorDiv((p + r), 2);
            mergeSort(array, p, q);
            mergeSort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + j + 1];
        }

        L[n1] = 1000000000;
        R[n2] = 1000000000;

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
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

        int[] copia = new int[q - p + 1];
        int v = 0;
        for (int i = p; i <= q; i++) {
            copia[v] = array[i];
            v++;
        }

        int posizioneMOM = -1;
        int medianaDelleMediane = medianOfMedians(copia);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == medianaDelleMediane)
                posizioneMOM = i;
        }

        scambia(array, posizioneMOM, q);

        int i = p - 1;
        int x = array[q];
        for (int j = p; j <= q; j++) {
            if (array[j] <= x) {
                i++;
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
