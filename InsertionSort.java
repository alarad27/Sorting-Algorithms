// Author: Alara Dasdan
// Date: Jul 2020

package Sorting;

import java.util.Random;

// Sorting numbers using the insertion sort algorithm
public class InsertionSort {
	static int seed = 12345; // random number generator seed
	static int sz = 10; // size of the array

	// generate a random number in a given range
	public static double randDouble(Random rd, double min, double max) {
		return min + rd.nextDouble() * (max - min + 1);
	}

	// generate an array of random numbers of size sz
	public static double[] getRandNumbers(int sz) {
		Random rd = new Random(seed);
		double[] arr = new double[sz];  // array of random numbers

		// generate random numbers
		for (int i = 0; i < arr.length; i++) {
			double r = randDouble(rd, 0, 10);
			arr[i] = r;
		}

		return arr;
	}

	// print input array arr in given order; also verify if sorted
	public static void printNums(double[] arr, boolean isAscending) {
		System.out.format("\nprint array of size %d:\n", arr.length);
		String sep = "";
		for (int i = 0; i < arr.length; i++) {
			System.out.format(sep + "%.2f", arr[i]);
			sep = ", ";
		}
		System.out.println();
		boolean isSorted = checkSort(arr, isAscending);
		System.out.println("is sorted? " + isSorted);	
	}

	// find x in sorted array arr using sequential search,
	// return its index, if not found, return -1
	public static int find(double[] arr, int len, double x, boolean isAscending) {
		if (isAscending) {
			for (int i = 0; i < len; i++) {
				if (x <= arr[i]) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < len; i++) {
				if (arr[i] <= x) {
					return i;
				}
			}
		}
		return -1;
	}

	// shift array from a point to the right by one location
	public static void shiftRight(double[] arr, int len, int fromInx) {
		if (len <= 0) {
			return;
		}
		for (int i = len - 1; i >= fromInx; i--) {
			arr[i + 1] = arr[i];
		}
	}

	// sort input array in place
	public static void sortHelper(double[] arr, double[] arr2, boolean isAscending) {
		int len = 0;
		if (isAscending) {	
			for (int i = 0; i < arr.length; i++) {
				double x = arr[i];
				int j = find(arr2, len, x, isAscending);
				if (j == -1) {
					arr2[len] = x;
				} else {
					shiftRight(arr2, len, j);
					arr2[j] = x;
				}
				len++;
			}
		} else {
			for (int i = 0; i < arr.length; i++) {
				double x = arr[i];
				int j = find(arr2, len, x, isAscending);
				if (j == -1) {
					arr2[len] = x;
				} else {
					shiftRight(arr2, len, j);
					arr2[j] = x;
				}
				len++;
			}
		}
	}

	// sort input array into a new array and return it
	public static double[] sort(double[] arr, boolean isAscending) {
		double[] arr2 = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr2[i] = 0.0;
		}
		sortHelper(arr, arr2, isAscending);
		return arr2;
	}

	// verify if arr is sorted in the given order
	public static boolean checkSort(double[] arr, boolean isAscending) {
		for (int j = 0; j < (arr.length - 1); j++) {
			if (isAscending) {
				if (arr[j] > arr[j + 1]) {
					return false;
				}
			} else {
				if (arr[j] < arr[j + 1]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		boolean isAscending = true;
		double[] arr = getRandNumbers(sz); 
		printNums(arr, isAscending);

		double[] arr2 = sort(arr, isAscending);
		printNums(arr2, isAscending);

		arr = getRandNumbers(0);
		printNums(arr, isAscending);

		arr2 = sort(arr, isAscending);
		printNums(arr2, isAscending);

		arr = getRandNumbers(1);
		printNums(arr, isAscending);

		arr2 = sort(arr, isAscending);
		printNums(arr2, isAscending);

	}
}
