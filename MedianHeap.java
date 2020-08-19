
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
		/*
		for (int i = arr.length / 2 - 1; i >= 0; i--)
			minHeapify(arr, 0, arr.length, i);
		*/

		// build backwards max heap
		for (int i = (arr.length + 1) / 2; i < arr.length; i++)
			backMaxHeapify(arr, arr.length - 1, arr.length, i);

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

	


	private static void maxHeapify(int[] arr, int start, int size, int root) {
		int largest = root;
		int l = 2 * (root - start) + 1;
		int r = 2 * (root - start) + 2;

		if (l < start + size && arr[l] > arr[largest])
			largest = l;

		if (r < start + size && arr[r] > arr[largest])
			largest = r;

		if (largest != root) {
			// swap arr[root] and arr[largest]
			int temp = arr[root];
			arr[root] = arr[largest];
			arr[largest] = temp;

			// heapify the subtree
			maxHeapify(arr, start, size, largest);
		}
	}

	private static void minHeapify(int[] arr, int start, int size, int root) {
		int smallest = root;
		int l = 2 * (root - start) + 1;
		int r = 2 * (root - start) + 2;

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
