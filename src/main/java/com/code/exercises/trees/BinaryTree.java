package com.code.exercises.trees;

public class BinaryTree<T>
{
	private BinaryTree left;
	private BinaryTree right;
	private T node;
	private Integer height;

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

	public int calculateHeight()
	{
		return 1 + Math.max(left != null ? left.calculateHeight() : 0, right != null ? right.calculateHeight() : 0);
	}

	@Override
	public String toString()
	{
		if (left == null && right == null)
			return String.valueOf(this.node);
		else
			return String.valueOf(this.node) + "/" + this.getHeight() + " -> ( " + left + ", " + right + " )";
	}

	public Integer getHeight()
	{
		return height;
	}

	public void setHeight(final Integer height)
	{
		this.height = height;
	}
}
