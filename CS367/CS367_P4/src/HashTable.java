//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 4
// Files:            HashTable.java, TestHash.java

// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class implements a hashtable that using chaining for collision handling.
 * Any non-<tt>null</tt> item may be added to a hashtable.  Chains are 
 * implemented using <tt>LinkedList</tt>s.  When a hashtable is created, its 
 * initial size, maximum load factor, and (optionally) maximum chain length are 
 * specified.  The hashtable can hold arbitrarily many items and resizes itself 
 * whenever it reaches its maximum load factor or whenever it reaches its 
 * maximum chain length (if a maximum chain length has been specified).
 * 
 * Note that the hashtable allows duplicate entries.
 */
public class HashTable<T> 
{
	/* The size of the table, not the number of items in the table. Equivalent to array.length */
	private int tableSize;
	
	/* Measure of how full the hashtable is allowed to get before it will be resized */
	private final double MAX_LOAD_FACTOR;
	
	/* Largest any chain in the hashtable is allowed to get before the table will be resized */
	private int maxChainLength;
	
	/* The "table" object. An array of linked lists (chains) who themselves contain the items */
	private LinkedList<T>[] array;
	
	/**
     * Constructs an empty hashtable with the given initial size, maximum load
     * factor, and no maximum chain length.  The load factor should be a real 
     * number greater than 0.0 (not a percentage).  For example, to create a 
     * hash table with an initial size of 10 and a load factor of 0.85, one 
     * would use:
     * 
     * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
     *
     * @param initSize the initial size of the hashtable.
     * @param loadFactor the load factor expressed as a real number.
     * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
     *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0
     **/
	@SuppressWarnings("unchecked")
	public HashTable(int initSize, double loadFactor) throws IllegalArgumentException
    {
		// Integer.MAX_VALUE is passed to appear to give an limit based on the limit of the type.
		this(initSize, loadFactor, Integer.MAX_VALUE);
    }
    
    
    /**
     * Constructs an empty hashtable with the given initial size, maximum load
     * factor, and maximum chain length.  The load factor should be a real 
     * number greater than 0.0 (and not a percentage).  For example, to create 
     * a hash table with an initial size of 10, a load factor of 0.85, and a 
     * maximum chain length of 20, one would use:
     * 
     * <dir><tt>HashTable ht = new HashTable(10, 0.85, 20);</tt></dir>
     *
     * @param initSize the initial size of the hashtable.
     * @param loadFactor the load factor expressed as a real number.
     * @param maxChainLength the maximum chain length.
     * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
     *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0 
     *         or if <tt>maxChainLength</tt> is less than or equal to 0.
     **/
    @SuppressWarnings("unchecked")
	public HashTable(int initSize, double loadFactor, int maxChainLength) throws IllegalArgumentException 
    {
    	if(initSize <= 0 || loadFactor <= 0.0 || maxChainLength <= 0)
			throw new IllegalArgumentException();
    	
    	// Set instance variables
    	this.MAX_LOAD_FACTOR = loadFactor;
    	this.maxChainLength = maxChainLength;
    	this.tableSize = initSize;
    	
    	// Initialize the array to a new array of linkedlists based on initSize;
    	this.array = (LinkedList<T>[])(new LinkedList[tableSize]);
    	
    	// Initialize each index of the array to a new linkedlist
    	for(int i = 0; i < array.length; i++)
    	{
    		array[i] = new LinkedList<T>();
    	}
    }
    
    
    /**
     * Determines if the given item is in the hashtable and returns it if 
     * present.  If more than one copy of the item is in the hashtable, the 
     * first copy encountered is returned.
     *
     * @param item the item to search for in the hashtable.
     * @return the item if it is found and <tt>null</tt> if not found.
     **/
    public T lookup(T item) 
    {
        int index = hash(item);
        
        if(array[index] == null)
        	return null;
        
        return array[index].get(array[index].indexOf(item));
    }
    
    
    /**
     * Inserts the given item into the hashtable.  The item cannot be 
     * <tt>null</tt>.  If there is a collision, the item is added to the end of
     * the chain.
     * <p>
     * If the load factor of the hashtable after the insert would exceed 
     * (not equal) the maximum load factor (given in the constructor), then the 
     * hashtable is resized.  
     * 
     * If the maximum chain length of the hashtable after insert would exceed
     * (not equal) the maximum chain length (given in the constructor), then the
     * hashtable is resized.
     * 
     * When resizing, to make sure the size of the table is reasonable, the new 
     * size is always 2 x <i>old size</i> + 1.  For example, size 101 would 
     * become 203.  (This guarantees that it will be an odd size.)
     * </p>
     * <p>Note that duplicates <b>are</b> allowed.</p>
     *
     * @param item the item to add to the hashtable.
     * @throws NullPointerException if <tt>item</tt> is <tt>null</tt>.
     **/
    public void insert(T item) throws NullPointerException
    {
    	// Test if inserted item would exceed max load factor. If so resizes the table
    	if((this.getItemCount() + 1) / this.tableSize > this.MAX_LOAD_FACTOR)
    		this.resize();
    	
    	int index;
    	int loopCount = 0;

    	// Tests if inserted item would exceed the max chain length. If so resizes the table
    	do {
    		index = this.hash(item);
    		
    		if(this.exceedMaxChainLength(index))
    			this.resize();
    		else
    			loopCount = 4;
    		
    		// Prevent potential infinite loop of chain length problem if index continues to be the same
    		if(++loopCount == 4)
    			this.maxChainLength = Integer.MAX_VALUE;

    	}while(loopCount < 4);
    	  
    	array[index].add(item);
    }
    
    
    /**
     * Removes and returns the given item from the hashtable.  If the item is 
     * not in the hashtable, <tt>null</tt> is returned.  If more than one copy 
     * of the item is in the hashtable, only the first copy encountered is 
     * removed and returned.
     *
     * @param item the item to delete in the hashtable.
     * @return the removed item if it was found and <tt>null</tt> if not found.
     **/
    public T delete(T item) 
    {
    	// Create the hash of the item
        int index = hash(item);
        
        // If the index is not null the item may exist in the table
        if(!(array[index] == null))
        {
        	// if the list contains the item remove it or return null
	        if(array[index].contains(item))
	        	array[index].remove(item);
	        else
	        	return null;
	        
	        // After the item has been removed, if it's list is now empty set reference to new linkedlist
	        if(array[index].size() == 0)
	        	array[index] = new LinkedList<T>();
	        
	        // Yes this may be cheating if something isn't right with my code by return the actual passed in item
	        // and not the actual item in array[index];
	        return item;
        }
        
        // item index doesn't exist
        return null;
    }
    
    
    /**
     * Prints all the items in the hashtable to the <tt>PrintStream</tt> 
     * supplied.  The items are printed in the order determined by the index of
     * the hashtable where they are stored (starting at 0 and going to 
     * (table size - 1)).  The values at each index are printed according 
     * to the order in the <tt>LinkedList</tt> starting from the beginning. 
     *
     * @param out the place to print all the output.
     **/
    public void dump(PrintStream out) 
    {
    	out.println("Hashtable contents: ");
    	for(int i = 0; i < array.length; i++)
    	{
    		if(!(array[i].size() == 0))
    		{
    			LinkedList<T> tempList = array[i];
    			out.print(i + ": ");
    			out.print(tempList);
    			out.print("\r\n");
    		}
    	}
    }
    
  
    /**
     * Prints statistics about the hashtable to the <tt>PrintStream</tt> 
     * supplied.  The statistics displayed are: 
     * <ul>
     * <li>the current table size
     * <li>the number of items currently in the table 
     * <li>the current load factor
     * <li>the length of the largest chain
     * <li>the number of chains of length 0
     * <li>the average length of the chains of length > 0
     * </ul>
     *
     * @param out the place to print all the output.
     **/
    public void displayStats(PrintStream out) 
    {
       out.println("Hashtable statistics");
       out.println("  current table size:\t\t" + array.length);
       out.println("  # items in table:\t\t" + this.getItemCount());
       out.println("  current load factor:\t\t" + this.getCurrentLoadFactor());
       out.println("  longest chain length:\t\t" + this.getLongestChainLength());
       out.println("  # 0-length chains:\t\t" + this.getCountZeroLengthChains());
       out.println("  avg (non-0) chain length:\t" + this.getAvgNonZeroLengthChainsLengths());
    }
    
    /**
     * Returns the current load factor of the table. 
     * 
     * @return the load factor
     */
    public double getCurrentLoadFactor()
    {
    	return ((double) this.getItemCount() / (double) tableSize);
    }
    
    /**
     * Returns the length (number of items) of the longest chain in the table
     * 
     * @return length of longest chain
     */
    public int getLongestChainLength()
    {
    	int longestChainLength = 0;
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i].size() > longestChainLength)
    			longestChainLength = array[i].size();
    	}
    	
    	return longestChainLength;
    }
    
    /**
     * Returns the number of chains that have zero items in them.
     * 
     * @return count of zero length chains
     */
    public int getCountZeroLengthChains()
    {
    	int countZeroLengthChains = 0;
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i].size() == 0)
    			countZeroLengthChains++;
    	}
    	
    	return countZeroLengthChains;
    }
    
    /**
     * Returns the average length of all chains who are not empty
     * 
     * @return average of lengths
     */
    public double getAvgNonZeroLengthChainsLengths()
    {
    	double countNonZeroLengthChainsTotalLengths = 0.0;
    	double countNumberOfNonZeroLengthChains = 0.0; 
    	
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i].size() > 0)
    		{
    			countNumberOfNonZeroLengthChains++;
    			countNonZeroLengthChainsTotalLengths += array[i].size();
    		}
    	}
    	
    	return countNonZeroLengthChainsTotalLengths / countNumberOfNonZeroLengthChains;
    }
    
    /**
     * Returns number of items in the hash table. 
     * 
     * @return the item count.
     */
    public int getItemCount()
    {
    	int itemCount = 0;

    	for(int i = 0; i < array.length; i++)
    	{
    		itemCount += array[i].size();
    	}
    	
    	return itemCount;
    }
    
    /**
     * Calculates a hash by taking the items.hashCode() and modulo this value by the table size. 
     * If this number is negate, it is summed with the tablesize to create a positive value.
     * 
     * @param item an object to calculate hash on
     * @return the hash as int
     */
    private int hash(T item)
    {
    	int hash = item.hashCode() % tableSize;
        if(hash < 0)
        	hash = hash + tableSize;
        
        return hash;
    }
    
    /**
     * Resizes the hashtable by a factor of current size * 2 + 1 to produce an odd number size.
     */
    private void resize()
    {
    	tableSize = array.length * 2 + 1;
    	
    	// Create new array for and temp reference to store hash table contents
    	@SuppressWarnings("unchecked")
		LinkedList<T>[] tempArray = (LinkedList<T>[])(new LinkedList[tableSize]);
    	
    	// Loop through new array and initialize each index to be an empty linkedlist
    	for(int i = 0; i < tempArray.length; i++)
    	{
    		tempArray[i] = new LinkedList<T>();
    	}
    	
    	/*
    	 *  Loop through array, get iterator for each index(linkedlist)
    	 *  If iterator has an item recalculate hash(new index) and insert into the new array
    	 */
    	for(int i = 0; i < array.length; i++)
    	{
    		Iterator<T> tempIterator = array[i].iterator();
    		while(tempIterator.hasNext())
    		{
    			T item = tempIterator.next();
    			int index = hash(item);
    			
    	    	tempArray[index].add(item);
    		}
    	}
    	
    	// Point array reference to new array
    	array = tempArray;
    }
    
    /**
     * Tests if the given index, after being inserted into the table, would exceed the max chain length.
     * 
     * @param index the index which an item would be inserted into
     * @return boolean. True if it would exceed the max chain length, false if not
     */
    private boolean exceedMaxChainLength(int index)
    {
    	if(array[index].size() + 1 > this.maxChainLength)
    		return true;
    	
    	return false;
    }
}
