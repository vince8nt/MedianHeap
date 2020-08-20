
class MedianHeap {
	static final String usage = "Usage: MedianHeap A[0] A[1] ...\n";

	public static void main(String[] args) {
		if (args.length < 2) { // array must be at least 2 long
			System.out.print(usage);
			return;
		}

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
		System.out.printf("Array of length %d was entered:\n", arr.length);
		System.out.println(stringRep(arr));

		// build min heap
		buildMinHeap(arr, 1, arr.length - 1);

		// build backwards max heap
		// buildBackMaxHeap(arr, 0, arr.length - 1);

		// medianHeapSort(arr, 0, arr.length - 1);



		System.out.println("\nAfter heapify:");
		System.out.println(stringRep(arr));
	}

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
			int mid = (begin + end) / 2;
			System.out.println("mid is: " + mid);
			buildBackMaxHeap(arr, begin, mid);
			buildMinHeap(arr, mid + 1, end);
			/*
			while (arr[mid] > arr[mid + 1]) {
				// swap arr[mid] and arr[mid + 1]
				int temp = arr[mid];
				arr[mid] = arr[mid + 1];
				arr[mid + 1] = temp;
				backMaxHeapify(arr, mid, mid - begin + 1, mid);
				minHeapify(arr, mid + 1, end - mid, mid + 1);
			}
			*/

			/*
			medianHeapSort(arr, begin, mid - 1);
			medianHeapSort(arr, mid + 1, end);
			*/
		}
	}

	private static void buildMinHeap(int[] arr, int begin, int end) {
		int n = end - begin + 1;
		for (int i = n / 2 - 1 + begin; i >= begin; i--)
			minHeapify(arr, begin, n, i);
	}

	private static void minHeapify(int[] arr, int start, int size, int root) {
		int smallest = root;
		int l = 2 * root - start + 1;
		int r = 2 * root - start + 2;

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

	private static void buildBackMaxHeap(int[] arr, int begin, int end) {
		for (int i = (end + begin) / 2; i <= end; i++)
			backMaxHeapify(arr, end, end - begin + 1, i);
	}

	private static void backMaxHeapify(int[] arr, int end, int size, int root) {
		int largest = root;
		int l = end - (2 * (end - root) + 1);
		int r = end - (2 * (end - root) + 2);

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
