package com.code.exercises.sorting;

import com.code.exercises.utils.Utils;


public class HeapSort
{
	public static void main(String... args)
	{
		Utils.setDebug(true);
		int[] array = new int[]{1, 6, 3, 7, 9, 2, 45, 7, 9, 98};
		Utils.execute("constructHeap", (a) -> constructHeap(a), array);
		Utils.execute("heapSort", (a) -> heapSort(constructHeap(a)), array);
	}

	private static int[] heapSort(final int[] heap)
	{
		int[] result = new int[heap.length];
		for (int i = 0; i < heap.length; i++)
		{
			result[i] = heap[0];
			
			heap[0] = heap[heap.length - i - 1];
			int j = 0;
			int a = 2 * (j + 1) - 1;
			int b = 2 * (j + 1);
			while ((a < heap.length - i && (heap[j] < heap[a])) ||
					(b < heap.length - i && heap[j] < heap[b]))
			{
				if (heap[a] > heap[b])
				{
					int temp = heap[a];
					heap[a] = heap[j];
					heap[j] = temp;
					j = a;
				}
				else
				{
					int temp = heap[b];
					heap[b] = heap[j];
					heap[j] = temp;
					j = b;
				}
				a = 2 * (j + 1) - 1;
				b = 2 * (j + 1);
			}
		}
		return result;
	}

	public static int[] constructHeap(int[] numbers)
	{
		for (int i = 0; i < numbers.length; i++)
		{
			numbers = pullUp(numbers, i);
		}
		return numbers;
	}

	private static int[] pullUp(final int[] numbers, int i)
	{
		while (numbers[i] > numbers[i / 2] && i > 0)
		{
			int temp = numbers[i / 2];
			numbers[i / 2] = numbers[i];
			numbers[i] = temp;
			i = i / 2;
		}
		return numbers;
	}


}
