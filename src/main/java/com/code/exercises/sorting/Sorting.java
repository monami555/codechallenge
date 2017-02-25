package com.code.exercises.sorting;

import com.code.exercises.utils.Utils;


public class Sorting
{
	public static void main(String... args)
	{
		Utils.setDebug(true);

		Utils.execute("selection", (a) -> selectionSort(a),
				new int[]{1, 6, 3, 7, 9, 2, 45, 7, 9});
		Utils.execute("insertion", (a) -> insertionSort(a),
				new int[]{1, 6, 3, 7, 9, 2, 45, 7, 9});
	}

	// n^2, in place
	public static int[] selectionSort(int[] array)
	{
		for (int j = 0; j < array.length; j++)
		{
			int minIdx = j;
			for (int i = j + 1; i < array.length; i++)
			{
				if (array[i] < array[minIdx])
				{
					minIdx = i;
				}
			}
			int temp = array[j];
			array[j] = array[minIdx];
			array[minIdx] = temp;
		}
		return array;
	}

	// n^2, in place
	public static int[] insertionSort(int[] array)
	{
		for (int j = 0; j < array.length; j++)
		{
			for (int i = j + 1; i < array.length; i++)
			{
				if (array[i] < array[j])
				{
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
		}
		return array;
	}

}
