public static int medianOfMedians(int[] array) {​​

  int n = array.length;

  if (n <= 5) {​​
    mergeSort(array, 0, n-1);

    if (n % 2 == 0) {​​
      return array[(n/2) -1];
    }​​ else {​​
      return array[(n/2)];
    }​​
  }​

  int numeroIndici;

  if (n % 5 == 0) {​​
    numeroIndici = n/5;
  }​​ else {​​
    numeroIndici = (n/5) + 1;
  }​​

  int[] vettoreMediane = new int[numeroIndici];

  int indiceInf = 0;
  int indiceSup = 4;

  for (int i=1; i<=numeroIndici; i++) {​​
    if (indiceSup >= n) {​​
      indiceSup = n-1;
    }​​

    mergeSort(array, indiceInf, indiceSup);

    indiceInf = indiceInf + 5;
    indiceSup = indiceSup + 5;
  }​​

  for (int i=2; i < n; i=i+5) {​​
    vettoreMediane[i/5] = array[i];
  }​​

  if (n % 5 != 0) {​​
    int r = n % 5;
    vettoreMediane[numeroIndici-1] = array[n - ((r/2)+1)];
  }​​

  return medianOfMedians(vettoreMediane);
}​​
