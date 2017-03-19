package com.code.exercises.sorting;

import com.code.exercises.utils.Utils;


public class MergeSort
{
	public static void main(String... args)
	{
		Utils.setDebug(true);
		int[] array = new int[]{6, 1, 5, 0, 9, 5, 7, 82, 0};
		Utils.execute("mergeSort", (a) -> mergeSort(a), array);
	}

	private static int[] mergeSort(final int[] array)
	{
		return mergeSortInternal(array, 0, array.length);
	}

	private static int[] mergeSortInternal(int[] array, int start, int end)
	{
		Utils.debug("", array);
		final int length = end - start;

		if (length < 2)
		{
			return array;
		}
		else
		{
			int[] merged = new int[array.length];
			for (int k = 0; k < array.length; k++)
			{
				merged[k] = array[k];
			}

			int middle = start + length / 2;

			array = mergeSortInternal(array, start, middle);
			array = mergeSortInternal(array, middle, end);

			int j1 = start;
			int j2 = middle;
			for (int i = start; i < end; i++)
			{
				if (j1 < middle && j2 < end)
				{
					if (array[j1] > array[j2])
						j2 = placeElement(array, j2, merged, i);
					else
						j1 = placeElement(array, j1, merged, i);
				}
				else if (j1 < middle)
					j1 = placeElement(array, j1, merged, i);
				else
					j2 = placeElement(array, j2, merged, i);
			}
			return merged;
		}
	}

	private static int placeElement(final int[] from, int fromIdx, final int[] to, final int toIdx)
	{
		to[toIdx] = from[fromIdx];
		fromIdx++;
		return fromIdx;
	}


}
