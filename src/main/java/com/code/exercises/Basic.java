package com.code.exercises;

public class Basic
{
	public static void main(String... args)
	{
		Utils.setDebug(false);

		Utils.execute("lnOfFactorial", n -> lnOfFactorial(n), 20);
		Utils.execute("lnOfFactorialRec", n -> lnOfFactorialRec(n), 20);
		Utils.execute("euclidGCD", (a, b) -> euclidGCD(a, b), 1111111, 1234567);
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
}
