package com.code.exercises.sorting;

import com.code.exercises.utils.Utils;


public class QuickSort
{
	public static void main(String... args)
	{
		Utils.setDebug(true);
		int[] array = new int[]{6, 5, 1, 3, 8, 4, 7, 9, 2};
		Utils.execute("quickSort", (a) -> quickSort(a), array);
	}

	private static int[] quickSort(final int[] array)
	{
		return quickSortInternal(array, 0, array.length);
	}

	private static int[] quickSortInternal(int[] array, int start, int end)
	{
		if (start < end)
		{
			int p = partition(array, start, end);
			quickSortInternal(array, start, p);
			quickSortInternal(array, p + 1, end);
		}
		return array;
	}

	private static int partition(final int[] array, final int start, final int end)
	{
		Utils.debug(start + "," + end, array);
		int pivot = end - 1;

		int j = start - 1;
		for (int i = start; i < end - 1; i++)
		{
			if (array[i] < array[pivot])
			{
				j++;
				swap(array, j, i);
			}
		}
		j++;
		swap(array, j, pivot);
		Utils.debug("-->" + start + "," + end, array);
		return j;
	}

	private static void swap(final int[] array, final int j, final int i)
	{
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}


	// algorithm quicksort(A, lo, hi) is
	// if lo < hi then
	// p := partition(A, lo, hi)
	// quicksort(A, lo, p – 1)
	// quicksort(A, p + 1, hi)
	//
	// algorithm partition(A, lo, hi) is
	// pivot := A[hi]
	// i := lo - 1
	// for j := lo to hi - 1 do
	// if A[j] ≤ pivot then
	// i := i + 1
	// swap A[i] with A[j]
	// swap A[i+1] with A[hi]
	// return i + 1
}
