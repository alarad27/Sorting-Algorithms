// Author: Alara Dasdan
// Date: Jul 2020

package Sorting;

import java.util.Random;

// Sorting numbers using the quicksort algorithm
public class QuickSort {
	static int seed = 12345; // random number generator seed
	static int sz = 10; // size of the array

	// generate a random num in a given range
	public static double randDouble(Random rd, double min, double max) {
		return min + rd.nextDouble() * (max - min + 1);
	}
	
	// generate array of random numbers of size sz
	public static double[] getRandNumbers(int sz) {
		Random rd = new Random(seed);
		double[] arr = new double[sz];  // array of random numbers

		// generate random numbers
		for (int i = 0; i < arr.length; i++) {
			double r = (int) (10 *  randDouble(rd, 0, 10));
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
	
	// swap arr[x] and arr[y]
	private static void swap(double[] arr, int x, int y) {
		double tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	// sort input array arr[l..r] using quicksort; partition is embedded in
	public static void sortRecur(double[] arr, int l, int r, boolean isAscending) {
		if (l >= r) {
			return;
		}
		int p = l;
		int li = l;
		int ri = r;
		if (isAscending) {
			while (li <= ri) {
				if (arr[li] <= arr[p]) {
					li++;
				}
				if (arr[p] < arr[ri]) {
					ri--;
				}
				if ((li < ri) && (arr[li] > arr[p]) && (arr[p] > arr[ri])) {
					swap(arr, li, ri);
					li++;
					ri--;
				}
			}
			if (ri > 0) {
				swap(arr, p, ri);
			}
		} else {
			while (li <= ri) {
				if (arr[li] >= arr[p]) {
					li++;
				}
				if (arr[p] > arr[ri]) {
					ri--;
				}
				if ((li < ri) && (arr[li] < arr[p]) && (arr[p] < arr[ri])) {
					swap(arr, li, ri);
					li++;
					ri--;
				}
			}
			if (ri > 0) {
				swap(arr, p, ri);
			}

		}
		sortRecur(arr, l, ri - 1, isAscending);
		sortRecur(arr, ri + 1, r, isAscending);
	}
	
	
	// sort input array into a new array and return it
	public static double[] sort(double[] arr, boolean isAscending) {
		double[] arr2 = arr;
		sortRecur(arr2, 0, arr.length - 1, isAscending);
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
		boolean isAscending = false;
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
