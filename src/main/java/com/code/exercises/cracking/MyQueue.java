package com.code.exercises.cracking;

import java.util.Stack;


public class MyQueue
{
	public static void main(String... args)
	{
		MyQueue queue = new MyQueue();
		queue.enqueue(5).enqueue(6).enqueue(2).enqueue(0).enqueue(7);
		while(!queue.isEmpty()){
			System.out.print(queue.dequeue()+", ");
		}
	}

	private Stack<Integer> enqueuingStack = new Stack<>();

	private Stack<Integer> dequeuingStack = new Stack<>();

	private boolean isEnqueuingModeOn = true;

	public MyQueue enqueue(int element)
	{
		if (!isEnqueuingModeOn)
		{
			slide();
		}
		enqueuingStack.push(element);
		return this;
	}

	public int dequeue()
	{
		if (isEnqueuingModeOn)
		{
			slide();
		}
		return dequeuingStack.pop();
	}

	private void slide()
	{
		Stack from = isEnqueuingModeOn ? enqueuingStack : dequeuingStack;
		Stack to = isEnqueuingModeOn ? dequeuingStack : enqueuingStack;
		while (!from.isEmpty())
		{
			to.push(from.pop());
		}
	}

	public boolean isEmpty() {
		return enqueuingStack.isEmpty()&&dequeuingStack.isEmpty();
	}
}
