package com.code.exercises.graph;

import com.code.exercises.utils.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Graph
{
	public static void main(String... args)
	{
		Utils.setDebug(true);
		int[] vertices = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[][] edges = new int[][]{
				{1, 2}, {3, 10},
				{4, 6}, {4, 5},
				{6, 8}, {1, 3},
				{3, 4}, {8, 7},
				{7, 9}, {9, 10},
				{4, 7}};
		Utils.execute("construct", (a, b) -> construct(a, b), edges, vertices);
		LinkedList[] graph = construct(edges, vertices);
		Utils.execute("breadthFirst", (a, b, c) -> breadthFirst(a, b, c), graph, 1, vertices);
		Utils.execute("breadthFirstPaper", (a, b, c) -> breadthFirstPaper(a, b, c), graph, 1, vertices);
		Utils.execute("depthFirst", (a, b, c) -> depthFirst(a, b, c), graph, 1, vertices);
		int[] res = new int[vertices.length];
		depthFirstRec(graph, 1, new HashSet<>(), res, 0);
		Utils.debug("depthFirstRec", res);
	}

	public static LinkedList[] construct(final int[][] edges, int[] vertices)
	{
		LinkedList[] res = new LinkedList[vertices[vertices.length - 1]];
		for (int vertice : vertices)
		{
			res[vertice - 1] = new LinkedList();
		}

		for (int[] edge : edges)
		{
			res[edge[0] - 1].add(edge[1]);
			res[edge[1] - 1].add(edge[0]);
		}
		return res;
	}

	public static LinkedList[] constructUniDir(final int[][] edges, int[] vertices)
	{
		LinkedList[] res = new LinkedList[vertices[vertices.length - 1]];
		for (int vertice : vertices)
		{
			res[vertice - 1] = new LinkedList();
		}

		for (int[] edge : edges)
		{
			res[edge[0] - 1].add(edge[1]);
		}
		return res;
	}

	private static int[] breadthFirstPaper(final LinkedList<Integer>[] graph, int start, int[] vertices)
	{
		int[] res = new int[vertices.length];
		int resPointer = 0;

		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();

		queue.add(start);
		visited.add(start);

		while (!queue.isEmpty())
		{
			int elem = queue.poll();
			resPointer = processNode(res, resPointer, elem);

			for (int e : graph[elem - 1])
			{
				if (!visited.contains(e))
				{
					queue.add(e);
					visited.add(e);
				}
			}
		}

		return res;
	}


	private static int[] breadthFirst(final LinkedList<Integer>[] graph, int start, int[] vertices)
	{
		int[] res = new int[vertices.length];
		int resPointer = 0;
		boolean[] visited = new boolean[vertices.length];
		boolean[] processed = new boolean[vertices.length];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);

		while (queue.size() > 0)
		{
			Utils.debug("res", res);

			int current = queue.remove();
			if (!visited[current - 1])
			{
				resPointer = processNode(res, resPointer, current);
			}
			processed[current - 1] = true;

			for (int vert : graph[current - 1])
			{
				visited[current - 1] = true;
				if (!processed[vert - 1])
				{
					queue.add(vert);
				}
			}
		}
		return res;
	}

	private static int[] depthFirst(final LinkedList<Integer>[] graph, int start, int[] vertices)
	{
		int[] res = new int[vertices.length];
		int resPointer = 0;

		Set<Integer> visited = new HashSet<>();
		Stack<Integer> stack = new Stack<>();

		stack.add(start);
		visited.add(start);
		resPointer = processNode(res, resPointer, start);

		while (!stack.isEmpty())
		{
			int elem = stack.peek();
			boolean hadNewNodes = false;
			for (int e : graph[elem - 1])
			{
				if (!visited.contains(e))
				{
					stack.push(e);
					visited.add(e);
					resPointer = processNode(res, resPointer, e);
					hadNewNodes = true;
					break;
				}
			}
			if (!hadNewNodes)
			{
				stack.pop();
			}
		}

		return res;
	}

	private static int[] depthFirstRec(
			final LinkedList<Integer>[] graph,
			int start,
			Set<Integer> visited,
			int[] res,
			int resPointer)
	{
		visited.add(start);
		processNode(res, resPointer, start);
		for (int e : graph[start - 1])
		{
			if (!visited.contains(e))
			{
				resPointer++;
				depthFirstRec(graph, e, visited, res, resPointer);
			}
		}
		return res;
	}

	private static int processNode(final int[] res, int resPointer, final int e)
	{
		res[resPointer] = e;
		resPointer++;
		return resPointer;
	}

}
