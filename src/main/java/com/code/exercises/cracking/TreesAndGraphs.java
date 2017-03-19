package com.code.exercises.cracking;

import com.code.exercises.graph.Graph;
import com.code.exercises.trees.BinaryTree;
import com.code.exercises.trees.TreeAlgorithms;
import com.code.exercises.utils.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class TreesAndGraphs
{
	public static void main(String... args)
	{
		TreesAndGraphs thiz = new TreesAndGraphs();
		int[] array = new int[]{5, 3, 7, 9, 2, 45, 6, 900};
		BinaryTree<Integer> t = TreeAlgorithms.construct(array);
		System.out.println(t);
		System.out.println(thiz.isBalanced(t));
		System.out.println(t);

		int[] vertices = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[][] edges = new int[][]{
				{1, 2}, {3, 10},
				{4, 6}, {4, 5},
				{6, 8}, {1, 3},
				{3, 4}, {8, 7},
				{7, 9}, {9, 10},
				{4, 7}};
		LinkedList[] graph = Graph.constructUniDir(edges, vertices);
		Utils.execute("hasPath", (a, b) -> thiz.hasPath(graph, a, b), 1, 10);

		BinaryTree<Integer> tree = new BinaryTree<>();
		int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		thiz.processSortedArray(tree, sortedArray, 0, sortedArray.length);
		System.out.println(
				"Height=" + tree.calculateHeight() + ", expected=" + Math.log(sortedArray.length));
	}

	public boolean isBalanced(BinaryTree<Integer> tree)
	{
		if (tree == null)
		{
			return true;
		} // O(n) //O(n)
		else if (!isBalanced(tree.getLeft()) || !isBalanced(tree.getRight()))
		{
			return false;
		}
		else
		{
			int leftHeight = tree.getLeft() != null ? tree.getLeft().getHeight() : 0;
			int rightHeight = tree.getRight() != null ? tree.getRight().getHeight() : 0;

			if (Math.abs(leftHeight - rightHeight) > 1)
			{
				System.out.print(tree.getNode());
				return false;
			}
			else
			{
				tree.setHeight(Math.max(leftHeight, rightHeight) + 1);
				return true;
			}
		}
	}

	public boolean hasPath(LinkedList<Integer>[] graph, int x, int y)
	{
		if (x == y)
		{
			return true;
		}
		else
		{
			Queue<Integer> queue = new LinkedList<>();
			Set<Integer> visited = new HashSet<>();
			visited.add(x);
			queue.add(x);

			while (!queue.isEmpty())
			{
				int element = queue.poll();
				for (int e : graph[element - 1])
				{
					if (!visited.contains(e))
					{
						if (y == e)
						{
							return true;
						}
						visited.add(e);
						queue.add(e);
					}
				}

			}
		}
		return false;
	}

	void processSortedArray(BinaryTree<Integer> tree, int[] array, int start, int end)
	{
		if (start >= end)
		{
			return;
		}
		else
		{
			int median = (end - start) / 2 + start;
			TreeAlgorithms.add(tree, array[median]);
			processSortedArray(tree, array, start, median);
			processSortedArray(tree, array, median + 1, end);
		}
	}
}
