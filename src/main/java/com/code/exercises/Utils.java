package com.code.exercises;


import java.time.Duration;
import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Utils
{

	private static boolean debug = true;

	public static void debug(String in, String out)
	{
		if (debug)
		{
			System.err.println("  Value for " + in + " is " + out);
		}
	}

	public static void debug(final Object n, final Object log)
	{
		debug(String.valueOf(n), String.valueOf(log));
	}

	public static void execute(final String name, final Function<Integer, Object> f, final int arg)
	{
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg);
		if (!debug) System.out.println("  Execution time: " + Duration.between(start, LocalTime.now()).getNano());
		System.out.println("  Result: " + res);
	}


	public static void execute(final String name, final BiFunction<Integer, Integer, Object> f, final int arg1, final int arg2)
	{
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg1, arg2);
		if (!debug) System.out.println("  Execution time: " + Duration.between(start, LocalTime.now()).getNano());
		System.out.println("  Result: " + res);
	}

	public static void setDebug(final boolean debug)
	{
		Utils.debug = debug;
	}
}
