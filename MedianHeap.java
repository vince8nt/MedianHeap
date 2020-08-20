
class MedianHeap {
	static final String usage = "Usage: MedianHeap A[0] A[1] ...\n";

	public static void main(String[] args) {
		if (args.length < 2) { // array must be at least 2 long
			System.out.print(usage);
			return;
		}

		// setup arr
		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			try {
				arr[i] = Integer.parseInt(args[i]);
			}
			catch (Exception e) { // non-integer arg is found
				System.out.printf("\"%s\" is not an integer\n%s", args[i], usage);
				return;
			}
		}

		// print arr as entered to console
		System.out.printf("Array of length %d was entered:\n", arr.length);
		System.out.println(stringRep(arr));

		// sort arr
		medianHeapSort(arr, 0, arr.length - 1);

		// print sorted arr to console
		System.out.println("\nAfter median heap sort:");
		System.out.println(stringRep(arr));
	}

	// returns a string representation of arr
	private static String stringRep(int[] arr) {
		String temp = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i > 0)
				temp += ", ";
			temp += arr[i];
		}
		temp += "]";
		return temp;
	}

	private static void medianHeapSort(int[] arr, int begin, int end) {
		if (end > begin) {
			// split sub-array in half and make each side a heap
			int mid = (begin + end) / 2;
			buildBackMaxHeap(arr, begin, mid);
			buildMinHeap(arr, mid + 1, end);

			// make elements of the left heap <= elements of the right heap
			while (arr[mid] > arr[mid + 1]) {
				// swap arr[mid] and arr[mid + 1] (the roots of the two heaps)
				int temp = arr[mid];
				arr[mid] = arr[mid + 1];
				arr[mid + 1] = temp;
				// heapify both heaps
				backMaxHeapify(arr, mid, mid - begin + 1, mid);
				minHeapify(arr, mid + 1, end - mid, mid + 1);
			}
			// arr[mid] is the median of the sub-array and is on the correct final index

			// recursively call medianHeapSort() on each side of the sub-array
			medianHeapSort(arr, begin, mid - 1);
			medianHeapSort(arr, mid + 1, end);
		}
	}

	// creates a min heap on the sub-array of arr
	private static void buildMinHeap(int[] arr, int begin, int end) {
		int n = end - begin + 1;
		for (int i = n / 2 - 1 + begin; i >= begin; i--)
			minHeapify(arr, begin, n, i);
	}

	// heapify the sub-tree at root of the sub-array
	private static void minHeapify(int[] arr, int start, int size, int root) {
		int smallest = root;
		int l = 2 * root - start + 1; // left child of root
		int r = 2 * root - start + 2; // right child of root

		// set smallest to the smallest of the root and its 2 children (l and r)
		if (l < start + size && arr[l] < arr[smallest])
			smallest = l;
		if (r < start + size && arr[r] < arr[smallest])
			smallest = r;

		if (smallest != root) {
			// swap arr[root] and arr[smallest]
			int temp = arr[root];
			arr[root] = arr[smallest];
			arr[smallest] = temp;
			// heapify the subtree
			minHeapify(arr, start, size, smallest);
		}
	}

	// creates a backwards max heap on the sub-array of arr
	private static void buildBackMaxHeap(int[] arr, int begin, int end) {
		int n = end - begin + 1;
		for (int i = (n + 1) / 2 + begin; i <= end; i++)
			backMaxHeapify(arr, end, n, i);
	}

	// heapify the sub-tree at root of the sub-array
	private static void backMaxHeapify(int[] arr, int end, int size, int root) {
		int largest = root;
		int l = 2 * root - end - 1; // left child of root
		int r = 2 * root - end - 2; // right child of root

		// set largest to the largest of the root and its 2 children (l and r)
		if (l > end - size && arr[l] > arr[largest])
			largest = l;
		if (r > end - size && arr[r] > arr[largest])
			largest = r;

		if (largest != root) {
			// swap arr[root] and arr[largest]
			int temp = arr[root];
			arr[root] = arr[largest];
			arr[largest] = temp;
			// heapify the subtree
			backMaxHeapify(arr, end, size, largest);
		}
	}
}
