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

import java.util.Iterator;

/**
 * Creates a BST of BSTnodes
 * 
 * @author Spencer George
 *
 * @param <K>
 */
public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> 
{
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    public BSTDictionary()
    {
    	this.root = null;
    	this.numItems = 0;
    }
    
    public void insert(K key) throws DuplicateException 
    {
        this.root = insert(this.root, key);
        this.numItems++;
    }
    
    /**
     * This is a helper method for the implemented insert method. This is a 
     * recursive method.
     * 
     * @param n the root node which will be used to cascade down the tree
     * @param key the value to be inserted
     * @return the node inserted
     * @throws DuplicateException if node exists
     */
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException
    {
    	// create key at n
    	if(n == null)
    		return new BSTnode<K>(key, null, null);
    	
    	// key exists already
    	if(n.getKey().equals(key))
    		throw new DuplicateException();

    	// key is less than n traverse left children
    	if(key.compareTo(n.getKey()) < 0)
    	{
    		n.setLeft(insert(n.getLeft(), key));
    		return n;
    	}
    	// key is greater than n traverse right children
    	else
    	{
    		n.setRight(insert(n.getRight(), key));
    		return n;
    	}
    }

    public boolean delete(K key) 
    {
        BSTnode<K> removed = delete(this.root, key);  // replace this stub with your code
        if(removed == null)
        	return false;
        
        numItems--;
        return true;
    }
    
    /**
     * Helper method for the implemented delete method. This is a recursive
     * method.
     * 
     * @param n the root node to start searching the tree to delete
     * @param key the key to delete from the Dictionary
     * @return the key from the Dictionary, if the key is in the Dictionary; 
     * null if the key is not in the Dictionary
     */
    private BSTnode<K> delete(BSTnode<K> n, K key) 
    {
    	// root node is null therefore the tree cannot be traversed
        if (n == null) 
            return null;
        
        // the key to be deleted is the root tree
        if (key.equals(n.getKey())) 
        {
        	// There are no children to move up
        	if (n.getLeft() == null && n.getRight() == null) 
                return null;

            if (n.getLeft() == null) 
                return n.getRight();

            if (n.getRight() == null) 
                return n.getLeft();
            
            K smallVal = smallest(n.getRight());
            n.setKey(smallVal);
            n.setRight( delete(n.getRight(), smallVal) );
            return n; 
        }
        // Key is less then root so traverse left nodes
        else if (key.compareTo(n.getKey()) < 0) 
        {
            n.setLeft( delete(n.getLeft(), key) );
            return n;
        }
        // Key is greater than root traverse right nodes
        else 
        {
            n.setRight( delete(n.getRight(), key) );
            return n;
        }
    }
    
    /**
     * Returns the smallest node in the tree with root n.
     * 
     * @param n the root node
     * 
     * @return the smallest node
     */
    private K smallest(BSTnode<K> n)	
	{
    	if (n.getLeft() == null) 
    		return n.getKey();
    	else 
    		return smallest(n.getLeft());
	}

    public K lookup(K key) 
    {
    	return lookup(root, key);
    }
    
    /**
     * This is a helper method for the implemented lookup method. This is a 
     * recursive method.
     * 
     * @param n the root node to start the search
     * 
     * @param key the key to search for
     * 
     * @return the key if found, null otherwise
     */
    private K lookup(BSTnode<K> n, K key)
    {
    	if(n == null)
    		return null;
    	
    	if(n.getKey().equals(key))
    		return n.getKey();
    	
        if(key.compareTo(n.getKey()) < 0)
	        return lookup(n.getLeft(), key);
        else
        	return lookup(n.getRight(), key);
    }

    public boolean isEmpty() 
    {
        if(this.numItems == 0)
        	return true;
        
    	return false;
    }

    public int size() 
    {
        return this.numItems; 
    }
    
    public int totalPathLength() 
    {
    	return totalPathLength(this.root, 1);
    }
    
    /**
     * Returns The total path length which is the sum of the lengths of the paths to each 
     * key in the dictionary. This a recursive method
     * 
     * @param n the root node to start counting the path lengths from
     * @param depth This is the starting depth where the root node (n) 
     * resides
     * @return the totalPathLength
     */
    private int totalPathLength(BSTnode<K> n, int depth)
    {
    	if(n == null)
    		return 0;
    	
    	if(n.getLeft() == null && n.getRight() == null)
    		return depth;
    	
    	int leftLength = totalPathLength(n.getLeft(), depth + 1);
    	depth += totalPathLength(n.getRight(), depth + 1);
    	
    	return depth + leftLength;
    }
    
    public Iterator<K> iterator() 
    {
        return new BSTDictionaryIterator<K>(this.root);  // replace this stub with your code
    }
    
    
}
