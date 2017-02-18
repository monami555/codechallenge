package com.code.exercises.utils;


import java.time.Duration;
import java.time.LocalTime;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Utils {

	private static boolean debug = true;

	public static void debug(String in, String out) {
		if (debug) {
			System.err.println("  Value for " + in + " is " + out);
		}
	}

	public static void debug(final Object n, final Object log) {
		debug(String.valueOf(n), String.valueOf(log));
	}

	public static void execute(final String name, final Function<Integer, Object> f, final int arg) {
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg);
		if (!debug)
			printTimeSince(start);
		System.out.println("  Result: " + res);
	}

	public static void execute(final String name, final BiFunction<Integer, Integer, Object> f, final int arg1, final int arg2) {
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg1, arg2);
		if (!debug)
			printTimeSince(start);
		System.out.println("  Result: " + res);
	}

	public static void execute(final String name, final TriFunction<int[], int[], Integer, int[]> f, final int[]
			arg1, final int[] arg2, final int arg3) {
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final int[] res = f.apply(arg1, arg2, arg3);
		if (!debug)
			printTimeSince(start);
		System.out.print("  Result: ");
		for (int j = 0; j < res.length; j++) {
			System.out.print(res[j] + ", ");
		}
		System.out.println();
	}

	public static void execute(final String name, final TriFunction<double[], Double, Integer, Object> f, final double[] arg1,
	                           final double arg2, final int arg3) {
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg1, arg2, arg3);
		if (!debug)
			printTimeSince(start);
		System.out.println("  Result: " + res);
	}

	public static void execute(final String name, final TriFunction<Integer, Integer, Double, Object> f, final int arg1,
	                           final int arg2, final double arg3) {
		System.out.println(name);
		final LocalTime start = LocalTime.now();
		final Object res = f.apply(arg1, arg2, arg3);
		if (!debug)
			printTimeSince(start);
		System.out.println("  Result: " + res);
	}

	private static void printTimeSince(final LocalTime start) {
		final double time = Duration.between(start, LocalTime.now())
		                            .getNano();
		System.out.println("  Execution time: " + time / 1000000 + " ms");
	}

	public static void setDebug(final boolean debug) {
		Utils.debug = debug;
	}

	public static void debug(String in, final int[][] a) {
		System.out.println("  " + in + ":");
		for (int i = 0; i < a.length; i++) {
			System.out.print("    ");
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
}