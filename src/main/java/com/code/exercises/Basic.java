package com.code.exercises;

import com.code.exercises.utils.Utils;

import java.util.Arrays;


public class Basic
{
	public static void main(String... args)
	{
		Utils.setDebug(true);

		// Utils.execute("lnOfFactorial", n -> lnOfFactorial(n), 20);
		// Utils.execute("lnOfFactorialRec", n -> lnOfFactorialRec(n), 20);
		// Utils.execute("euclidGCD", (a, b) -> euclidGCD(a, b), 1111111, 1234567);
		// Utils.execute("binomial", (a, b, c) -> binomial(a, b, c), 35, 15, 0.25);
		// Utils.execute("binomialOpt", (a, b, c) -> binomialOpt(a, b, c), 35, 15, 0.25);
		Utils.execute("binarySearch", (a, b, c) -> binarySearch(a, b, c),
				new double[]{1.0, 2.0, 5.0, 5.6, 7.8, 8.9}, 4, 0);
		Utils.execute("binarySearchBetter", (a, b) -> binarySearchBetter(a, b),
				new double[]{1.0, 2.0, 5.0, 5.6, 7.8, 8.9}, 4.0);
	}

	// ln(n!)
	public static double lnOfFactorial(int n)
	{
		long factorial = n;
		while (n > 1)
		{
			Utils.debug(n, factorial);
			n = n - 1;
			factorial = factorial * n;
		}
		return Math.log(factorial);
	}

	public static double lnOfFactorialRec(int n)
	{
		if (n > 1)
		{
			return Math.log(n) + lnOfFactorialRec(n - 1);
		}
		else
		{
			return Math.log(n);
		}
	}

	// 2322 = 654·3 + 360 -> gcd(2322, 654) = gcd(654, 360)
	public static double euclidGCD(int a, int b)
	{
		Utils.debug(a, b);
		if (a < b)
		{// swap a and b so that a is bigger
			final int c = a;
			a = b;
			b = c;
		}
		if (b == 0)
		{
			return -1;
		}
		final int multiply = a / b;
		final int remainder = a - b * multiply;
		if (remainder > 0)
		{
			return euclidGCD(b, remainder);
		}
		else
		{
			return b;
		}
	}

	// prove this by math induction TODO

	// copy pasted; how many recursive -> 2^n ?
	public static double binomial(int n, int k, double p)
	{
		if (n == 0 && k == 0)
			return 1.0;
		if (n < 0 || k < 0)
			return 0.0;
		return (1 - p) * binomial(n - 1, k, p) + p * binomial(n - 1, k - 1, p);
	}

	// 2n ? : seems not, seems 2^(n-1)
	// 99 50, 99 49 - 2
	// 98 50, 98 49, 98 49, 98 48 - 3
	// 97 50, 97 49, 97 49, 97 48, 97 49, 97 48, 97 48, 97 47 - 4
	public static double binomialOpt(int n, int k, double p)
	{
		final double cache[][] = new double[n][k];
		for (int i = 0; i < cache.length; i++)
		{
			for (int j = 0; j < cache[i].length; j++)
			{
				cache[i][j] = -1;
			}
		}
		if (n == 0 && k == 0)
			return 1.0;
		if (n < 0 || k < 0)
			return 0.0;

		if (cache[n - 2][k - 1] == -1)
			cache[n - 2][k - 1] = binomial(n - 1, k, p);

		if (cache[n - 2][k - 2] == -1)
			cache[n - 2][k - 2] = binomial(n - 1, k - 1, p);

		return (1 - p) * cache[n - 2][k - 1] + p * cache[n - 2][k - 2];
	}

	public static int binarySearch(double[] sortedArrayAsc, double searchedElement, int currentIndex)
	{
		if (sortedArrayAsc.length == 1)
		{
			return currentIndex;
		}
		final int middle = sortedArrayAsc.length / 2;
		if (sortedArrayAsc[middle] < searchedElement)
		{
			Utils.debug(currentIndex, Arrays.toString(Arrays.copyOfRange(sortedArrayAsc, middle, sortedArrayAsc.length)));
			return binarySearch(Arrays.copyOfRange(sortedArrayAsc, middle, sortedArrayAsc.length), searchedElement,
					currentIndex + middle);
		}
		else
		{
			Utils.debug(currentIndex, Arrays.toString(Arrays.copyOfRange(sortedArrayAsc, 0, middle)));
			return binarySearch(Arrays.copyOfRange(sortedArrayAsc, 0, middle), searchedElement, currentIndex);
		}
	}

	public static int binarySearchBetter(double[] sortedArrayAsc, double searchedElement)
	{
		return binarySearchBetterInternal(sortedArrayAsc, searchedElement, 0, sortedArrayAsc.length);
	}

	public static int binarySearchBetterInternal(double[] sortedArrayAsc, double searchedElement, int startIncl, int endExcl)
	{
		Utils.debug(startIncl,endExcl);
		if (endExcl - startIncl == 1)
		{
			return startIncl;
		}
		final int middle = startIncl + (endExcl - startIncl) / 2;
		if (sortedArrayAsc[middle] < searchedElement)
		{
			return binarySearchBetterInternal(sortedArrayAsc, searchedElement, middle, endExcl);
		}
		else
		{
			return binarySearchBetterInternal(sortedArrayAsc, searchedElement, startIncl, middle);
		}
	}

}
