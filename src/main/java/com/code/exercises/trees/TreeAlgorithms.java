package com.code.exercises.trees;

import com.code.exercises.utils.Utils;


public class TreeAlgorithms
{
	public static void main(String... args)
	{
		Utils.setDebug(true);

		int[] array = new int[]{1, 6, 3, 7, 9, 2, 45, 7, 9, 98};

		Utils.executeO("construct", (a) -> construct(a), array);
		Utils.execute("sort", (a) -> sort(construct(a), a.length), array);

		BinaryTree<Integer> tree = construct(array);

		Utils.executeO("add 5", (a, b) -> add(a, b), tree, 5);
		Utils.execute("sort", (a) -> sort(add(construct(a), 5), a.length + 1), array);

		Utils.executeO("remove 9", (a, b) -> remove(a, b), tree, 45);
		Utils.execute("remove", (a) -> sort(remove(construct(a), 45), a.length - 1), array);

		Utils.execute("preOrder", (a) -> preOrder(construct(a), a.length), array);
		Utils.execute("inOrder", (a) -> inOrder(construct(a), a.length), array);
		Utils.execute("postOrder", (a) -> postOrder(construct(a), a.length), array);
	}

	public static BinaryTree<Integer> construct(int[] numbers)
	{
		BinaryTree<Integer> res = new BinaryTree<>();
		res.setNode(numbers[0]);
		for (int i = 1; i < numbers.length; i++)
		{
			res = addNode(numbers[i], res);
		}
		return res;
	}

	private static BinaryTree<Integer> addNode(
			final int number,
			final BinaryTree<Integer> tree)
	{
		BinaryTree<Integer> subtree;
		if (tree.getNode() < number)
		{
			if (tree.getRight() != null)
			{
				subtree = addNode(number, tree.getRight());
			}
			else
			{
				subtree = new BinaryTree<>();
				subtree.setNode(number);
			}
			tree.setRight(subtree);
		}
		else
		{
			if (tree.getLeft() != null)
			{
				subtree = addNode(number, tree.getLeft());
			}
			else
			{
				subtree = new BinaryTree<>();
				subtree.setNode(number);
			}
			tree.setLeft(subtree);
		}
		return tree;
	}

	public static BinaryTree<Integer> add(final BinaryTree<Integer> tree, final int b)
	{
		if (tree == null)
		{
			BinaryTree<Integer> newTree = new BinaryTree<>();
			newTree.setNode(b);
			return newTree;
		}
		else if (tree.getNode() == null)
		{
			tree.setNode(b);
		}
		else if (tree.getNode() < b)
		{
			tree.setRight(add(tree.getRight(), b));
		}
		else
		{
			tree.setLeft(add(tree.getLeft(), b));
		}
		return tree;
	}

	private static BinaryTree<Integer> remove(BinaryTree<Integer> tree, final int b)
	{
		if (tree == null)
		{
			return null;
		}
		// check top element
		if (tree.getNode() == b)
		{
			if (tree.getLeft() != null)
			{
				int tmp = (int) tree.getLeft().getNode();
				tree.setLeft(remove(tree.getLeft(), tmp));
				tree.setNode(tmp);
				return tree;
			}
			else if (tree.getRight() != null)
			{
				return tree.getRight();
			}
			else
			{
				return null;
			}
		}
		else
		{
			// check children
			if (tree.getLeft() != null)
			{
				tree.setLeft(remove(tree.getLeft(), b));
			}
			if (tree.getRight() != null)
			{
				tree.setRight(remove(tree.getRight(), b));
			}
			return tree;
		}
	}

	private static int[] sort(BinaryTree<Integer> tree, int size)
	{
		return inOrder(tree, size);
	}

	private static int[] inOrder(BinaryTree<Integer> tree, int size)
	{
		int[] array = new int[size];
		fillArrayInOrder(array, 0, tree);
		return array;
	}

	private static int fillArrayInOrder(int[] array, int fromIdx, final BinaryTree<Integer> tree)
	{
		if (tree != null)
		{
			fromIdx = fillArrayInOrder(array, fromIdx, tree.getLeft());
			array[fromIdx] = tree.getNode();
			fromIdx++;
			fromIdx = fillArrayInOrder(array, fromIdx, tree.getRight());
		}
		return fromIdx;
	}

	private static int[] preOrder(BinaryTree<Integer> tree, int size)
	{
		int[] array = new int[size];
		fillArrayPreOrder(array, 0, tree);
		return array;
	}

	private static int fillArrayPreOrder(int[] array, int fromIdx, final BinaryTree<Integer> tree)
	{
		if (tree != null)
		{
			array[fromIdx] = tree.getNode();
			fromIdx++;
			fromIdx = fillArrayPreOrder(array, fromIdx, tree.getLeft());
			fromIdx = fillArrayPreOrder(array, fromIdx, tree.getRight());
		}
		return fromIdx;
	}

	private static int[] postOrder(BinaryTree<Integer> tree, int size)
	{
		int[] array = new int[size];
		fillArrayPostOrder(array, 0, tree);
		return array;
	}

	private static int fillArrayPostOrder(int[] array, int fromIdx, final BinaryTree<Integer> tree)
	{
		if (tree != null)
		{
			fromIdx = fillArrayPostOrder(array, fromIdx, tree.getLeft());
			fromIdx = fillArrayPostOrder(array, fromIdx, tree.getRight());
			array[fromIdx] = tree.getNode();
			fromIdx++;
		}
		return fromIdx;
	}

}
