//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 3
// Files:            ArrayHeap.java BSTDictionary.java 
//					 BSTDictionaryIterator.java KeyWord.java
//					 WordCloudGenerator.java
// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

// Was I supposed to use this? I do know where but did so with a basic if
import java.util.NoSuchElementException;

public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> 
{

    /** default number of items the heap can hold before expanding */
    private static final int INIT_SIZE = 100;
    
    /** The array that stores the heap items */
    private E[] array;
    
    /** Number of items in the heap */
    private int heapSize;

    /**
     * Add a no-argument constructor that constructs a heap whose underlying
     * array has enough space to store INIT_SIZE items before needing to 
     * expand.
     */
    @SuppressWarnings("unchecked")
	public ArrayHeap()
    {
    	heapSize = 0;
    	array = (E[])(new Prioritizable[INIT_SIZE + 1]);
    }
    
    
    /**
     * Add a 1-argument constructor that takes an integer parameter and 
     * constructs a heap whose underlying array has enough space to store the 
     * number of items given in the parameter before needing to expand.  If
     * the parameter value is less 0, an IllegalArgumentException is thrown.
     * 
     * @param items initial size of the heap
     * @throws IllegalArgumentException if items is less than 0
     */
    @SuppressWarnings("unchecked")
	public ArrayHeap(int items) throws IllegalArgumentException
    {
    	if(items < 0)
	    	throw new IllegalArgumentException();
    	
    	heapSize = 0;
    	
    	array = (E[])(new Prioritizable[items + 1]);
    }
    
    public boolean isEmpty() 
    {
    	if(array.length < 2)
    		return true;
    	
    	return false;
    }

    public void insert(E item) 
    {
    	array[++heapSize] = item;
    	swapUp(heapSize);
	}
    
    /**
     * This is a help method for the insert method. It swaps up children with 
     * parents if the childrens priority if great than its parent.
     * 
     * @param childIndex the index of the child item used to check against it's
     * parent
     */
    private void swapUp(int childIndex)
    {
    	if(childIndex == 1)
    		return;
    	
    	int parentIndex = childIndex / 2;
    	
    	if(array[childIndex].getPriority() > array[parentIndex].getPriority())
    	{
    		swap(parentIndex, childIndex);
    		swapUp(parentIndex);
    	}
    }
    
    public E removeMax() 
    {
        E tempItem = array[1];
        array[1] = array[heapSize];
        array[heapSize--] = null;
        swapDown(1);
        
    	return tempItem;  // replace this stub with your code
    }
    
    /**
     * Helper method for the removeMax method. This method swaps parent items
     * with the largest child if a child's priority is larger than it's parent
     * 
     * @param parentIndex the parent to cascade down the heap
     */
    private void swapDown(int parentIndex)
    {
    	
    	int leftChildIndex = parentIndex * 2;
    	int rightChildIndex = parentIndex * 2 + 1;
    	int largestChildIndex = 0;
    	
    	/*
    	 * If statement (and subsequent else if) are used to determine
    	 * the child with the largest priority if there are children.
    	 */
    	// No children to swap down with so return
    	if(leftChildIndex > heapSize && rightChildIndex > heapSize)
    		return;
    	// Only left child exists, by default is largest
    	else if(leftChildIndex <= heapSize && rightChildIndex > heapSize)
    		largestChildIndex = leftChildIndex;
    	// Only right child. This should never happen but we return if so
    	// because I'm too lazy to fix the heap
    	else if(rightChildIndex <= heapSize && leftChildIndex > heapSize)
    		return;
    	// Left and right children exist
    	else
    	{
    		int leftChildPriority = array[leftChildIndex].getPriority();
       		int rightChildPriority = array[rightChildIndex].getPriority();
       		
       		// Determine priority between children
       		// Left has higher priority
       		if(leftChildPriority > rightChildPriority)
       			largestChildIndex = leftChildIndex;
       		// Right has larger priority
       		else if(rightChildPriority > leftChildPriority)
       			largestChildIndex = rightChildIndex;
       		// Priorities are equal determine based on lexicographical comparison
       		else 
       		{
       			// Safety check to perform casting
       			if(array[leftChildIndex] instanceof KeyWord 
       					&& array[rightChildIndex] instanceof KeyWord)
        		{
       				// Get actually word values from children keys
        			KeyWord leftChild = (KeyWord) array[leftChildIndex];
        			KeyWord rightChild = (KeyWord) array[rightChildIndex];
        			
        			// Left child is alphabetically first
        			if(leftChild.compareTo(rightChild) < 0)
        				largestChildIndex = leftChildIndex;
        			// Right child is first
        			else
        				largestChildIndex = rightChildIndex;
        		}
       		}
    	}
    	
    	// Grab the largest priority 
    	int largestChildPriority = array[largestChildIndex].getPriority();
    	int parentPriority = array[parentIndex].getPriority();
    	
    	// If child has high priority than parent perform swap. Recurse.
    	if(largestChildPriority > parentPriority)
    	{
    		swap(largestChildIndex, parentIndex);
    		swapDown(largestChildIndex);
    	}
	}
    
    /**
     * Swaps two items in the array
     * 
     * @param index1 first item to swap
     * @param index2 second item to swap
     */
    private void swap(int index1, int index2)
    {
    	E tempItem = array[index1];
    	array[index1] = array[index2];
    	array[index2] = tempItem;
	}
    
    public E getMax() 
    {
        return array[1];  // replace this stub with your code
    }

    public int size() 
    {
        return heapSize;  // replace this stub with your code
    }
}
