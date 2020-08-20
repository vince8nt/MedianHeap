# Median Heap Sort
A sorting algorithm invented by me.
Uses divide and conquer by finding the median of a given sub-array
and recursively calling itself with the sub-arrays on each side of
the median.
The median is discovered by making 2 equally sized (or off by 1)
heaps within the subarray.
The heap on the left is a backwards max heap, and on the right is
a min heap.
Then, while the root of the backwards max heap is greater than the
root of the min heap:
	- swap the two roots
	- heapify max heap
	- heapify min heap

At this point, the root of the backwards max heap is the median of
the sub-array. It is also in its permanent final position.
Now it recursively calls itself with the sub-arrays on each side of
the median.

How to use:
program takes 2 arguments, and input file and an output file.
The input file should be space seperated integers.
The first integer, n, is the length of the array.
The next n integers are the values.

The sorted array will be printed to the output file.
