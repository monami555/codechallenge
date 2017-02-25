package com.code.exercises.trees;

public class BinaryTree<T>
{
	private BinaryTree left;
	private BinaryTree right;
	private T node;

	public BinaryTree getLeft()
	{
		return left;
	}

	public BinaryTree getRight()
	{
		return right;
	}

	public T getNode()
	{
		return node;
	}

	public void setLeft(final BinaryTree left)
	{
		this.left = left;
	}

	public void setNode(final T node)
	{
		this.node = node;
	}

	public void setRight(final BinaryTree right)
	{
		this.right = right;
	}

	public int getHeight()
	{
		return 1 + Math.max(left != null ? left.getHeight() : 0, right != null ? right.getHeight() : 0);
	}

	@Override
	public String toString()
	{
		if (left == null && right == null)
			return this.node.toString();
		else
			return this.node.toString() + " -> ( " + left + ", " + right + " )";
	}
}
