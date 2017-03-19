package com.code.exercises.cracking;

import java.util.Stack;


public class SortStack {
	public static void main(String... args) {
		Stack s = new Stack<Integer>();
		s.push(4);
		s.push(3);
		s.push(7);
		s.push(4);
		s.push(6);
		s.push(5);
		s.push(1);
		System.out.println(new SortStack().sort(s));
	}

	public Stack<Integer> sort(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack();
		while (!stack.isEmpty()) {
			int item = stack.pop();
			if (temp.isEmpty() || item < temp.peek()) {
				temp.push(item);
			} else {
				while (!temp.isEmpty() && item > temp.peek()) {
					stack.push(temp.pop());
				}
				temp.push(item);
			}
		}
		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
		return stack;
	}

}
