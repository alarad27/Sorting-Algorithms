// Author: Alara Dasdan
// Date: Jul 2020

package Sorting;

import java.util.Random;

// Sorting numbers using the merge sort algorithm
public class MergeSort {
	static int seed = 12345; // random number generator seed
	static int sz = 10; // size of the array
	
	// generate a random int in a given range
	public static int randInt(Random rd, int min, int max) {
		return min + rd.nextInt(max - min + 1);
	}

	// generate a random number in a given range
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
	
	// merge the two sides into a sorted list arr[l..r]
	public static void merge(double[] arr, int l, int r, boolean isAscending) {
		double[] arr2 = new double[r - l + 1];
		int m = (l + r) / 2;
		int li = l;
		int ri = m + 1;
		int i = 0;
		if (isAscending) {
			while ((li <= m) && (ri <= r)) {
				if (arr[li] < arr[ri]) {
					arr2[i] = arr[li];
					li++;
					i++;
				} else if (arr[li] > arr[ri]) {
					arr2[i] = arr[ri];
					ri++;
					i++;
				} else {
					arr2[i] = arr[li];
					li++;
					i++;
					arr2[i] = arr[ri];
					ri++;
					i++;
				}
			}
		} else {
			while ((li <= m) && (ri <= r)) {
				if (arr[li] < arr[ri]) {
					arr2[i] = arr[ri];
					ri++;
					i++;
				} else if (arr[li] > arr[ri]) {
					arr2[i] = arr[li];
					li++;
					i++;
				} else {
					arr2[i] = arr[li];
					li++;
					i++;
					arr2[i] = arr[ri];
					ri++;
					i++;
				}
			}
		}
		for ( ; li <= m; li++) {
			arr2[i] = arr[li];
			i++;
		}
		for ( ; ri <= r; ri++) {
			arr2[i] = arr[ri];
			i++;
		}
		i = 0;
		for (int j = l; j <= r; j++) {
			arr[j] = arr2[i];
			i++;
		}
	}
				
	// sort input array arr[l..r] using merge sort
	public static void sortRecur(double[] arr, int l, int r, boolean isAscending) {
		if (l >= r) {
			return;
		}
		int m = (l + r) / 2;
		sortRecur(arr, l, m, isAscending);
		sortRecur(arr, m + 1, r, isAscending);
		merge(arr, l, r, isAscending);
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
