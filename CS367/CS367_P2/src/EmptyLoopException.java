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

@SuppressWarnings("serial")
public class EmptyLoopException extends Exception
{
	public EmptyLoopException()
	{
		super();
	}
	
	public EmptyLoopException(String message)
	{
		super(message);
	}
}
