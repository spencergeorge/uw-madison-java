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

/**
 * This is an implementation of the LoopADT. Specifically this is a circular,
 * doubly-linked chain of nodes.
 * 
 * @author Spencer George
 *
 * @param <E>
 */
public class LinkedLoop<E> implements LoopADT<E>
{
	private int itemCount;
	private DblListnode<E> current;
		
	/**
	 * Creates an empty loop.
	 */
	public LinkedLoop()
	{
		this.current = new DblListnode<E>(null);
		this.itemCount = 0;
	}

	@Override
	public void add(E item) 
	{
		
		if(itemCount == 0)
		{
			this.current.setData(item);
		}
		else if(itemCount == 1)
		{
			/*
			 *  Change data of current from null to item
			 */
			DblListnode<E> newNode = new DblListnode<E>(item);
			DblListnode<E> tmp = this.current;
			
			// Set currents previous node to the new node
			this.current.setPrev(newNode);
			
			// Set currents next node to the new node
			this.current.setNext(newNode);
			
			// Set current to the new node
			this.current = newNode;
			
			// Set current's next to original node
			this.current.setNext(tmp);
			
			// Set current's previous to original node
			this.current.setPrev(tmp);
		}
		else
		{
			/*
			 *  Create new node with item data.
			 *  Set next to be current as it is added before current.
			 *  Set previous node to be currents previous node.
			 */
			DblListnode<E> newNode = new DblListnode<E>(this.current.getPrev(), 
					item, this.current);
			
			// Get currents previous and change next to the new node
			this.current.getPrev().setNext(newNode);
			
			// Set currents previous to the new node
			this.current.setPrev(newNode);
			
			// Set current to the new node
			this.current = newNode;
		}
		this.itemCount++;
	}

	@Override
	public E getCurrent() throws EmptyLoopException 
	{
		if(isEmpty())
			throw new EmptyLoopException();
		
		return this.current.getData();
	}

	@Override
	public E removeCurrent() throws EmptyLoopException 
	{
		if(isEmpty())
			throw new EmptyLoopException();
		
		// Bypass current by settings it's previous with it's next node
		this.current.getPrev().setNext(this.current.getNext());
		
		// Finish bypass by settings current's next's previous to current's previous
		this.current.getNext().setPrev(this.current.getPrev());
		
		// Create tmp with current for returning data
		DblListnode<E> tmp = this.current;
		
		// Set new current to current's next
		this.current = this.current.getPrev();
		
		// Decrement item count
		this.itemCount--;
		
		return tmp.getData();
	}
	
	@Override
	public void next() 
	{
		this.current = this.current.getNext();
	}

	@Override
	public void previous() 
	{
		this.current = this.current.getPrev();
	}

	@Override
	public boolean isEmpty() 
	{
		if(itemCount == 0)
			return true;
		
		return false;
	}

	@Override
	public int size() 
	{
		return itemCount;
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new LinkedLoopIterator<E>(current); 
	}
}
