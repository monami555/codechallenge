package com.code.exercises.trees;

import com.code.exercises.utils.Utils;


public class TreeAlgorithms {
	public static void main(String... args) {
		Utils.setDebug(true);

		int[] array = new int[]{1, 6, 3, 7, 9, 2, 45, 7, 9, 98};

		Utils.executeO("constructHeap", (a) -> construct(a), array);
		Utils.execute("sort", (a) -> sort(construct(a), a.length), array);

		BinaryTree<Integer> tree = construct(array);

		Utils.executeO("add 5", (a, b) -> add(a, b), tree, 5);
		Utils.execute("sort", (a) -> sort(add(construct(a), 5), a.length + 1), array);

		Utils.executeO("remove 9", (a, b) -> remove(a, b), tree, 45);
		Utils.execute("sort", (a) -> sort(remove(construct(a), 45), a.length - 1), array);

	}

	private static BinaryTree<Integer> remove(BinaryTree<Integer> tree, final int b) {
		if (tree == null) {
			return null;
		}
		//check top element
		if (tree.getNode() == b) {
			if (tree.getLeft() != null) {
				int cache = (int) tree.getLeft()
				                      .getNode();
				tree.setLeft(remove(tree.getLeft(), cache));
				tree.setNode(cache);
				return tree;
			} else if (tree.getRight() != null) {
				return tree.getRight();
			} else {
				return null;
			}
		} else {
			//check children
			if (tree.getLeft() != null) {
				tree.setLeft(remove(tree.getLeft(), b));
			}
			if (tree.getRight() != null) {
				tree.setRight(remove(tree.getRight(), b));
			}
			return tree;
		}
	}

	private static BinaryTree<Integer> add(final BinaryTree<Integer> tree, final int b) {
		if (tree == null) {
			BinaryTree<Integer> newTree = new BinaryTree<>();
			newTree.setNode(b);
			return newTree;
		}
		if (tree.getNode() < b) {
			tree.setRight(add(tree.getRight(), b));
		} else {
			tree.setLeft(add(tree.getLeft(), b));
		}
		return tree;
	}

	private static int[] sort(BinaryTree<Integer> tree, int size) {
		int[] a = new int[size];
		int i = 0;
		fill(a, i, tree);
		return a;
	}

	private static int fill(int[] a, int i, final BinaryTree<Integer> tree) {
		if (tree != null) {
			i = fill(a, i, tree.getLeft());
			a[i] = tree.getNode();
			i++;
			i = fill(a, i, tree.getRight());
		}
		return i;
	}

	public static BinaryTree<Integer> construct(int[] numbers) {
		BinaryTree<Integer> res = new BinaryTree<>();
		res.setNode(numbers[0]);
		for (int i = 1; i < numbers.length; i++) {
			res = addNode(numbers[i], res);
		}
		return res;
	}

	private static BinaryTree<Integer> addNode(
			final int number,
			final BinaryTree<Integer> tree) {
		BinaryTree<Integer> subtree;
		if (tree.getNode() < number) {
			if (tree.getRight() != null) {
				subtree = addNode(number, tree.getRight());
			} else {
				subtree = new BinaryTree<>();
				subtree.setNode(number);
			}
			tree.setRight(subtree);
		} else {
			if (tree.getLeft() != null) {
				subtree = addNode(number, tree.getLeft());
			} else {
				subtree = new BinaryTree<>();
				subtree.setNode(number);
			}
			tree.setLeft(subtree);
		}
		return tree;
	}

}
