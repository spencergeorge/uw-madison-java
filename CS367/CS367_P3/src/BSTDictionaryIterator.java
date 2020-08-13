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

import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 */
public class BSTDictionaryIterator<K> implements Iterator<K> 
{
	private Stack<BSTnode<K>> bstStack;

	/**
	 * Creates iterator with worst case complexity of O(height of BST)
	 * 
	 * @param root the node to construct the iterator from. 
	 */
	BSTDictionaryIterator(BSTnode<K> root)
	{
		bstStack = new Stack<BSTnode<K>>();
		populateStack(root);
	}
	
    public boolean hasNext() 
    {
    	return !bstStack.isEmpty();  // replace this stub with your code
    }

    public K next() 
    {
    	BSTnode<K> node = bstStack.pop();
    	
    	populateStack(node.getRight());

    	return node.getKey();  // replace this stub with your code
    }
    
    /**
     * Helper method for the next() and constructor. Populates the stack
     * with a worst-case complexity of O(height of BST)
     * 
     * @param node the root node to populate the stack with.
     */
    private void populateStack(BSTnode<K> node)
    {
    	while(node != null)
    	{
    		bstStack.push(node);
    		node = node.getLeft();
    	}
    }
       
    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}

