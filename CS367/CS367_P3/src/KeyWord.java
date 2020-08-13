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

/**
 * A KeyWord consists of a word and an integer (representing the number of 
 * occurrences of the word). A word is a non-empty sequence of characters whose
 * letters are all lower-case.
 * 
 * @author Spencer George
 *
 */
public class KeyWord implements java.lang.Comparable<KeyWord>, Prioritizable 
{
	/** The word value stored in the keyword */
	private String word;
	
	/** Can be used to count number of times a keyword is used */
	private int occurrences;
	
	/**
	 * Constructs a KeyWord with the given word (converted to lower-case) 
	 * and zero occurrences.
	 * 
	 * @param word
	 */
	public KeyWord(String word)
	{
		this.word = word;
		this.occurrences = 0;
	}
	
	
	/**
	 * Compares the KeyWord with the one given. Two KeyWords are compared by 
	 * comparing the word associated with the two KeyWords, ignoring case 
	 * differences in the names.
	 * 
	 * @param other - the KeyWord with which to compare this KeyWord
	 */
	@Override
	public int compareTo(KeyWord other) 
	{
		return this.getWord().compareToIgnoreCase(other.getWord());
	}
	
	/**
	 * Compares this KeyWord to the specified object. The result is true if and
	 * only if the argument is not null and is a KeyWord object whose word is 
	 * the same as the word of this KeyWord, ignoring case differences.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof KeyWord)
		{
			if(this == obj || 
					this.getWord().equalsIgnoreCase(((KeyWord) obj).getWord()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the number of occurrences for this KeyWord.
	 * 
	 * @return the number of occurrences for this KeyWord
	 */
	public int getOccurrences()
	{
		return this.occurrences;
	}
	
	/**
	 * Returns the priority for this KeyWord. The priority of a KeyWord is the
	 * number of occurrences it has.
	 * 
	 * @return the priority for this item.
	 */
	public int getPriority()
	{
		return this.occurrences;
	}
	
	/**
	 * Returns the word for this KeyWord.
	 * 
	 * @return the word for this KeyWord
	 */
	public String getWord()
	{
		return this.word;
	}
	
	/**
	 * Adds one to the number of occurrences for this KeyWord.
	 */
	public void increment()
	{
		this.occurrences++;
	}
}
