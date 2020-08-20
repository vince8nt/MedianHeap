import java.io.*;
import java.util.Scanner;

class MedianHeap {
	static final String usage = "Usage: MedianHeap <input file> <output file>";

	public static void main(String[] args) throws IOException {
		// make sure 2 args are given
		if (args.length != 2) {
			System.err.println(usage);
			System.exit(1);
		}

		// setup <in> and <out>
		Scanner in;
		PrintWriter out;
		try {
			new Scanner(new File(args[0]));
		}
		catch (Exception e) {
			System.err.println(args[0] + " (No such file or directory)");
			System.err.println(usage);
			System.exit(1);
		}
		in = new Scanner(new File(args[0]));

		// scan <in> for arr length
		int length = 0;
		try {
			length = in.nextInt();
		}
		catch (Exception e) {
			System.err.println(args[0] + " (Incorrect formatting of length)");
			System.err.println(usage);
			System.exit(1);
		}

		// scan <in> for arr values
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			try {
				arr[i] = in.nextInt();
			}
			catch (Exception e) { // non-integer is found
				System.err.printf("%s (Incorrect formatting at index %d)\n", args[0], i);
				System.err.println(usage);
				System.exit(1);
			}
		}
		in.close();

		// print arr as entered to console
		System.out.printf("Array of length %d was entered:\n", arr.length);
		System.out.println(stringRep(arr));

		// sort arr
		medianHeapSort(arr, 0, arr.length - 1);

		// print sorted arr to console
		System.out.println("\nAfter median heap sort:");
		System.out.println(stringRep(arr));

		// print sorted arr to <out>
		out = new PrintWriter(new FileWriter(args[1]));
		out.println(length);
		out.println(stringRep(arr));
		out.close();
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
