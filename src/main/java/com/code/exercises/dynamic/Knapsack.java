package com.code.exercises.dynamic;

import com.code.exercises.utils.Utils;


public class Knapsack
{
	public static void main(String... args)
	{
		Utils.setDebug(true);

		Utils.execute("knapsack", (a, b, c) -> knapsackWeightsValues(a, b, c),
				new int[]{1, 1, 1, 1, 1, 2, 2, 3},
				new int[]{1, 1, 1, 1, 1, 4, 4, 9}, 5);

	}

	/*
	 * w{3, 2, 1}, v{5, 3, 4}, 5
	 *
	 * 0 1 2 3 4 5 <-- j
	 *
	 * 0 0 0 0 0 0 i=0
	 * 0 0 0 5 5 5 i=1
	 * 0 0 3 5 5 8 i=2
	 * 0 4 4 7 9 9 i=3
	 *
	 * 0 0 0 0 0 0 i=0
	 * 0 0 0 1 1 1 i=1
	 * 0 0 1 0 0 1 i=2
	 * 0 1 1 1 1 1 i=3
	 *
	 */
	public static int[] knapsackWeightsValues(int[] weights, int[] values, int capacity)
	{
		final int itemCount = values.length;// =>3

		int[][] v_knapsacks = new int[itemCount + 1][capacity + 1];// [vertical][horizontal]
		int[][] v_keep = new int[itemCount + 1][capacity + 1];

		for (int i = 1; i <= itemCount; i++)// for 0 items we would have 0 value anyway..
		{
			int currentWeight = weights[i - 1];
			int currentValue = values[i - 1];

			for (int j = 1; j <= capacity; j++)
			{
				int currentCapacity = j;

				if (currentWeight > currentCapacity) // this item won't fit
				{
					v_knapsacks[i][j] = v_knapsacks[i - 1][j];
				}
				else // it fits
				{
					v_knapsacks[i][j] = Math.max(
							v_knapsacks[i - 1][j],
							currentValue + v_knapsacks[i - 1][currentCapacity - currentWeight]);

					if (v_knapsacks[i][j] > v_knapsacks[i - 1][j])
					{
						v_keep[i][j] = 1;
					}
				}
			}
		}

		Utils.debug("v_knapsacks", v_knapsacks);
		Utils.debug("v_keep", v_keep);

		// finding the items:
		int[] result = new int[values.length];
		int takenWeight = 0;

		for (int i = itemCount; i > 0; i--)
		{
			if (v_keep[i][capacity - takenWeight] == 1)
			{
				result[i - 1] = 1;
				takenWeight = takenWeight + weights[i - 1];
			}
		}

		return result;
	}
}


