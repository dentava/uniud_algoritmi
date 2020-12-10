public class Main {

	public static void main(String[] args) {
		
		int[] array = new int[] {5, 7, 11, 3, 8, 6, 9};
		
		System.out.println(heapSelect(array, 1));
		
		System.out.println(heapSelect(array, 2));
		
		System.out.println(heapSelect(array, 3));
		
		System.out.println(heapSelect(array, 4));
		
		System.out.println(heapSelect(array, 5));
		
		System.out.println(heapSelect(array, 6));
		
		System.out.println(heapSelect(array, 7));
	}
	
	// Giusta, intoccabile
	public static void minHeapify(int[] A, int i) {
		
		int l;
		int r;
		
		if (i==0) {
			 l = 1; //posizione figlio sx
			 r = 2; //posizione figlio dx
		} else {
			 l = i*2; //posizione figlio sx
			 r = i*2+1; //posizione figlio dx
		}
		
		int heapsize = A.length-1;
		
		int minimo;
		
		if (l < heapsize && A[l] < A[i]) {
			minimo = l;
		} else {
			minimo = i;
		}
		
		if (r < heapsize && A[r] < A[minimo]) {
			minimo = r;
		}
		
		if (minimo != i) {
			scambia(A, i, minimo);
			minHeapify(A, minimo);
		}
	}
	
	// Giusta, intoccabile
	public static void scambia(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Giusta, intoccabile
	public static int[] buildMinHeap(int[] A) {
		for (int i=Math.floorDiv(A.length, 2); i>=0; i--) {
			minHeapify(A,i);
		}
		return A;
	}

	//c'è l'errore??
	//qua possibile errore porco dio 
	public static int minHeapExtract(int[] minHeap) { //O(logn)
		
		int heapsizeA = minHeap.length;
		
		if (heapsizeA > 1) {
			scambia(minHeap, 0, heapsizeA-1);
			heapsizeA--;
			
			minHeapify(minHeap, 0); //(to do something?)
			return minHeap[heapsizeA];
		} else {
			return -1;
		}
	}

	public static int heapSelect(int[] A, int k) {
		
		int[] H1 = buildMinHeap(A);
		int[] H2 = H1;
		
		//dopo k-1 iterazioni, la radice di H2 corrisponderà al k-esimo elemento + piccolo 
		for (int i=1; i<k; i++) {
			minHeapExtract(H2);
		}
		
		return H2[k-1];
	}
}