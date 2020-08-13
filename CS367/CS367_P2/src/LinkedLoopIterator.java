//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 2
// Files:            DblListNode.java,EmptyLoopException.java,Image.java
//					 ImageLoopEditor.java, LinkedLoop.java
//					 LinkedLoopIterator.java, LoopADT.java
// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Spencer George
 *
 * @param <E>
 */
class LinkedLoopIterator<E> implements Iterator<E> 
{
	private DblListnode<E> headerNode;
	private DblListnode<E> currentNode;
	private boolean firstCall = true;
	
	/**
	 * Constructor that takes a node used as the current and header nodes.
	 * 
	 * @param node
	 */
	LinkedLoopIterator(DblListnode<E> node)
	{
		this.headerNode = this.currentNode = node;
	}
	
	@Override
	public boolean hasNext() 
	{
		if(firstCall)
		{
			return currentNode != null;
		}
		return currentNode != null && currentNode != headerNode;
	}

	@Override
	public E next()
	{
		E data = null;
		/*
		 * On the first call because this is a doublylink node, the headerNode
		 * and currentNode will be the same otherwise we want the loop to stop
		 * when currentNode equals the first node.
		 */
		if(firstCall && currentNode != null)
		{
			firstCall = false;
			data = currentNode.getData();
			currentNode = currentNode.getPrev();
		}
		// Must have a next() node to iterate.
		else if(!this.hasNext())
		{
            throw new NoSuchElementException();
        }
		// The iterator has reached the last node and pionts back to the start.
		else if(this.currentNode.getPrev() == headerNode)
		{
			data = currentNode.getData();
			this.currentNode = null;
		}
		else
		{
			data = currentNode.getData();
			currentNode = currentNode.getPrev();
		}
		return data;
	}

	@Override
	public void remove() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
}
