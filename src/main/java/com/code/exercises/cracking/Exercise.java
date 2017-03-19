package com.code.exercises.cracking;

public class Exercise {
	public static void main(String... args) {
		System.out.println(replaceWithSpaces("d d  "));
	}

	public static String replaceWithSpaces(String input) {
		char[] ch = input.toCharArray();
		int r = ch.length - 1;
		int e = -1;
		for (int i = ch.length - 1; i > 0; i--) {
			if (ch[i] != ' ') {
				e = i;
				break;
			}
		}
		while (r > e) {
			System.out.println(new String(ch));
			for (int i = e; i > 0; i--) {
				if (ch[i] == ' ') {
					r = replace(ch, r, i + 1, e);
					e = i - 1;
				}
			}
		}
		return new String(ch);
	}

	private static int replace(final char[] ch, int targetEnd, final int from, final int to) {
		for (int i = to; i >= from; i--) {
			ch[targetEnd] = ch[i];
			targetEnd--;
		}
		ch[targetEnd] = '0';
		ch[targetEnd - 1] = '2';
		ch[targetEnd - 2] = '%';
		return targetEnd - 3;
	}

}
