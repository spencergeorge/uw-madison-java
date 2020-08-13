///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Eliza.java
// File:             ElizaTests.java
// Semester:         CS 302 Spring 2016
//
// Author:           Spencer George 
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * Implements all methods of the main class Eliza.java and provides feedback 
 * on correct or incorrect tests performed of each method.
 *
 * &lt;p&gt;Bugs: none known.
 *
 * @author Spencer George
 */
public class ElizaTests 
{
	/**
	 * Tests the Eliza.chooseString() method with four tests.
	 * 
	 * &lt;p&gt;Test 1: Is a string chosen?
	 * 
	 * &lt;p&gt;Test 2: Is it random (approximately)?
	 * 
	 * &lt;p&gt;Test 3: Length 1 array has its element chosen
	 * 
	 * &lt;p&gt;Test 4: If null is passed, null is returned
	 */
	public static void testChooseString() 
	{
		// Is one of the 3 strings chosen?
		String [] strList = {"The", "happy", "cat"};
		String choice = Eliza.chooseString( strList);
		if ( choice != null && (choice.equals("The") || choice.equals("happy") 
				|| choice.equals("cat"))) 
		{
			System.out.println("testChooseString 1 passed.");
		} 
		else 
		{
			System.out.println("testChooseString 1 failed.");
		}
		
		// If I call it 100 times, are the choices approximately random?
		int countThe = 0;
		int countHappy = 0;
		int countCat = 0;
		for ( int i = 1; i <= 100; i++) 
		{
			choice = Eliza.chooseString( strList);
			if ( choice != null) 
			{
				if ( choice.equals("The")) 
				{
					countThe++;
				} 
				else if ( choice.equals("happy")) 
				{
					countHappy++;
				} 
				else if ( choice.equals("cat")) 
				{
					countCat++;
				}
			}
		}
		if ( countThe >=20 && countThe <= 45 
				&& countHappy >= 20 && countHappy <= 45
				&& countCat >= 20 && countCat <= 45) 
		{
			System.out.println("testChooseString 2 passed. " + countThe
					+ "," + countHappy + "," + countCat);
		} 
		else 
		{
			System.out.println("testChooseString 2 failed. " + countThe
					+ "," + countHappy + "," + countCat);
		}
		
		// Additional test suggestions: 
		// Q: What should happen when an array with a single string is provided?
		// A: That string is picked 100% of the time.
		String [] strList2 = {"Single"};
		String choice2 = Eliza.chooseString(strList2);
		if(choice2 != null && choice2.equals("Single"))
		{
			System.out.println("testChooseString 3 passed.");
		}
		else
		{
			System.out.println("testChooseString 3 failed.");
		}
		
		// Q: What should happen when null is passed to chooseString?
		// A: null should be returned
		String [] strList3 = null;
		String choice3 = Eliza.chooseString(strList3);
		if(choice3 == null)
		{
			System.out.println("testChooseString 4 passed.");
		}
		else
		{
			System.out.println("testChooseString 4 failed.");
		}
	}
	
	/**
	 * Tests the Eliza.inList() method with four tests.
	 * 
	 * &lt;p&gt;Test 1: Is the correct index is returned?
	 * 
	 * &lt;p&gt;Test 2: A non quit word results in -1 returned.
	 * 
	 * &lt;p&gt;Test 3: Substring of a quit word doesn't count as quit word
	 * 
	 * &lt;p&gt;Test 4: First matching word index is returned if multiple words
	 *  are in quit word list
	 */
	public static void testInList() 
	{
		// Test 1: Is correct index returned?
		String [] quitWords = {"bye","goodbye","quit", "bye"};		
		int index = Eliza.inList( "bye", quitWords);
		if ( index >= 0) 
		{
			System.out.println("testInList 1 passed.");
		}
		else
		{
			System.out.println("testInList 1 failed.");
		}
		
		// Test 2: Non quit word returns -1
		index = Eliza.inList( "seeya", quitWords);
		if ( index == -1) 
		{
			System.out.println("testInList 2 passed.");
		}
		else
		{
			System.out.println("testInList 2 failed.");
		}
		
		// Test suggestion 3: 
		// Q: What should happen if a substring is checked in a string
		// A: It should not be found as it's not the complete word 
		String [] quitWords2 = {"goodbye"};
		index = Eliza.inList("good", quitWords2);
		if(index == -1)
		{
			System.out.println("testInList 3 passed.");
		}
		else
		{
			System.out.println("testInList 3 failed.");
		}
				
		// Q: Which index is returned if a word is listed more than once
		//    in the list?
		// A: The first index should be returned
		String [] quitWords4 = {"bye","goodbye","quit", "bye"};
		index = Eliza.inList("bye", quitWords4);
		if(index == 0)
		{
			System.out.println("testInList 4 passed.");
		}
		else
		{
			System.out.println("testInList 4 failed.");
		}
		
		// Can you think of other tests?
	}
	
	/**
	 * Tests the Eliza.assemblePhrase() method with two tests.
	 * 
	 * &lt;p&gt;Test 1: Returns expected sentence as string from String array
	 * 
	 * &lt;p&gt;Test 2: If null is passed then null should be returned
	 */
	public static void testAssemblePhrase() 
	{
		// Test 1: Returns expected sentence as string from String array
		String [] words = {"This", "is a", "sentence"};
		String sentence = Eliza.assemblePhrase( words);

		String expectedSentence = "This is a sentence";
		
		if ( sentence.equals( expectedSentence)) 
		{
			System.out.println("testAssembleSentence 1 passed.");
		}
		else 
		{
			System.out.println("testAssembleSentence 1 failed '" 
					+ sentence + "'");
		}
		
		// suggested test: what should happen when null is passed in?
		// A: null should be returned
		sentence = Eliza.assemblePhrase(null);
		if(sentence == null)
		{
			System.out.println("testAssembleSentence 2 passed.");
		}
		else
		{
			System.out.println("testAssembleSentence 2 failed.");
		}
	}
	
	/**
	 * Tests the Eliza.findLongest() method with five tests.
	 * 
	 * &lt;p&gt;Test 1: Returns correct index of longest string
	 * 
	 * &lt;p&gt;Test 2: If two strings are the same length, the first
	 * index is returned.
	 * 
	 * &lt;p&gt;Test 3: If spaces are removed via trim()
	 * 
	 * &lt;p&gt;Test 4: Ignore length on null
	 * 
	 * &lt;p&gt;Test 5: If null return null
	 */
	public static void testFindLongest() 
	{
		// Test 1: Returns correct index of longest string 
		String [] sentences = {"short", "This is longer.", 
				"This is the longest one.", "s"};
		String longest = Eliza.findLongest( sentences);
		if ( longest == sentences[2]) 
		{
			System.out.println("testFindLongest 1 passed.");
		}
		else 
		{
			System.out.println("testFindLongest 1 failed.");
		}
		
		// What additional tests can you create?
		// Q: If two are the same
		// A: Return the first (lowest index) longest sentence
		String [] sentences2 = {"The same length", "The same length"};
		String longest2 = Eliza.findLongest(sentences2);
		if(longest2 == sentences2[0])
		{
			System.out.println("testFindLongest 2 passed.");
		}
		else
		{
			System.out.println("testFindLongest 2 failed:" + longest2);
		}
	
		// Q: Test trim of sentences
		String [] sentences3 = {"   3 Spaces"};
		String longest3 = Eliza.findLongest(sentences3);
		if(longest3.equals("3 Spaces"))
		{
			System.out.println("testFindLongest 3 passed.");
		}
		else
		{
			System.out.println("testFindLongest 3 failed:" + longest3);
		}
		
		// Q: Should findLongest work if sentences contain both null and strings
		// A: Yes ignore length on null
		String [] sentences4 = {"The same length", "The same length", null,
					"", "more nulls", "Longest sentence in here"};
		String longest4 = Eliza.findLongest(sentences4);
		if(longest4 == sentences4[sentences4.length - 1])
		{
			System.out.println("testFindLongest 4 passed.");
		}
		else
		{
			System.out.println("testFindLongest 4 failed:" + longest4);
		}
		
		// Q: If null
		// A: Return null
		String [] sentences5 = {null};
		String longest5 = Eliza.findLongest(sentences5);
		if(longest5 == sentences5[sentences5.length - 1])
		{
			System.out.println("testFindLongest 5 passed.");
		}
		else
		{
			System.out.println("testFindLongest 5 failed:" + longest5);
		}
	}
	
	/**
	 * Tests the Eliza.howMany() method with three tests.
	 * 
	 * &lt;p&gt;Test 1: Correct count of spaces
	 * 
	 * &lt;p&gt;Test 2: Test non whitespace characters
	 * 
	 * &lt;p&gt;Test 3: If empty return length
	 */
	public static void testHowMany() 
	{
		// Test 1 count how many spaces are in the string
		int countSpaces = Eliza.howMany( " ", " you me ");
		if ( countSpaces == 3) 
		{
			System.out.println( "testHowMany 1 passed.");
		} 
		else 
		{
			System.out.println( "testHowMany 1 failed.  countSpaces == " 
					+ countSpaces);
		}
		
		// Test 2
		int countNum = Eliza.howMany("<2>", "What makes you think I am <2>?");
		if ( countNum == 1) 
		{
			System.out.println( "testHowMany 2 passed.");
		} 
		else 
		{
			System.out.println( "testHowMany 2 failed.  countNum == " 
					+ countNum);
		}
		
		//additional tests
		
		// Test08_TestHowMany.text empty string return length
		int test08 = Eliza.howMany("", "three");
		if(test08 == 5)
		{
			System.out.println("testHowMany 3 passed.");
		}
		else
		{
			System.out.println("testHowMany 3 failed. test08 == " + test08);
		}
	}		

	/**
	 * Tests the Eliza.filterChars() method with two tests.
	 * 
	 * &lt;p&gt;Test 1: Correct length is returned
	 * 
	 * &lt;p&gt;Test 2: Correct characters are returned
	 */
	public static void testFilterChars() 
	{
		// Test 1
		String userInput = "w? #t   i't e   4t m-s!";
		char [] expectedChars = {'w','!',' ',' ','t',' ',' ',' ','i','\'','t',
				' ','e',' ',' ',' ','4','t',' ','m',' ','s','!'};
		char [] characters = Eliza.filterChars( userInput);
		
		if ( userInput.length() == characters.length) 
		{
			System.out.println("testFilterChars 1 passed.");
		} 
		else 
		{
			System.out.println("testFilterChars 1 failed.");
		}
		
		// Test 2
		boolean error = false;
		for ( int i = 0; i < expectedChars.length; i++) 
		{
			if ( expectedChars[i] != characters[i]) 
			{
				System.out.print( 
						String.format("characters[%d] '%c' expected '%c'\n",
								i, characters[i], expectedChars[i]));
				error = true;
			}
		}
		if ( error) 
		{
			System.out.println("testFilterChars 2 failed.");
		} 
		else 
		{
			System.out.println("testFilterChars 2 passed.");
		}
		
		// additional tests
	}
	
	/**
	 * Tests the Eliza.removeDuplicateSpaces() method with three tests.
	 * 
	 * &lt;p&gt;Test 1: Removing of two spaces and leaving single spaces
	 * 
	 * &lt;p&gt;Test 2: If 3 spaces are reduced to 1
	 * 
	 * &lt;p&gt;Test 3: If all elements are spaces should return 1 space
	 */
	public static void testRemoveDuplicateSpaces() 
	{
		// Test 1: 
		char [] chars1 = {'a', 't', ' ', '.', ' ', ' ', 'g', ' ',
				' ', 'h', ' '};
		Eliza.removeDuplicateSpaces( chars1);
		char [] expectedResult = {'a', 't', ' ', '.', ' ', 'g', ' ', 'h', ' ',
				'\u0000', '\u0000'};
		
		boolean error = false;
		String errorStr = "";
		for ( int i = 0; i < expectedResult.length; i++) 
		{
			if ( chars1[i] != expectedResult[i]) 
			{
				errorStr += String.format("chars1[%d] '%c' expected '%c'\n",
						i, chars1[i], expectedResult[i]);
				error = true;
			}
		}
		if ( error) 
		{
			System.out.println("testRemoveDuplicateSpaces 1 failed. " 
					+ errorStr);
		} else 
		{
			System.out.println("testRemoveDuplicateSpaces 1 passed.");
		}
		
		// additional tests
		// Q: If 3 or more in a row, reduced to 1
		char [] chars3Space = {'a', ' ', ' ', ' ', 'b', 'c', ' ', ' ', 'd'};
		char [] chars3Expected = {'a', ' ', 'b', 'c', ' ', 'd'};
		error = false;
		for(int i = 0; i < chars3Expected.length; i++)
		{
			if(chars3Space[i] != chars3Expected[i])
			{
				errorStr += String.format("chars3Space[%d] '%c' expect '%c'\n",
						i, chars3Space[i], chars3Expected[i]);
			}
		}
		if ( error) 
		{
			System.out.println("testRemoveDuplicateSpaces 2 failed. " 
					+ errorStr);
		} else 
		{
			System.out.println("testRemoveDuplicateSpaces 2 passed.");
		}
		
		// Q: All spaces
		// A: Only 1 returned remaining elements are null
		char [] charsAllSpace = {' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '};
		char [] charsAllExpected = {' '};
		error = false;
		for(int i = 0; i < charsAllExpected.length; i++)
		{
			if(charsAllSpace[i] != charsAllExpected[i])
			{
				errorStr += 
						String.format("charsAllSpace[%d] '%c' expect '%c'\n", 
								i, charsAllSpace[i], charsAllExpected[i]);
			}
		}
		if ( error) 
		{
			System.out.println("testRemoveDuplicateSpaces 3 failed. " 
					+ errorStr);
		} else 
		{
			System.out.println("testRemoveDuplicateSpaces 3 passed.");
		}
	}
	
	/**
	 * Tests the Eliza.findAnyWords() method with two tests.
	 * 
	 * &lt;p&gt;Test 1: If a quit word was found
	 * 
	 * &lt;p&gt;Test 2: If a quit word was not found
	 */
	public static void testFindAnyWords() 
	{
		// Test 1:
		String[] someWords = {"Going", "now", "goodbye"};
		boolean found = Eliza.findAnyWords( someWords, Config.QUIT_WORDS);
		if ( found) 
		{
			System.out.println("testFindAnyWords 1 passed.");
		}
		else 
		{
			System.out.println("testFindAnyWords 1 failed.");
		}
		
		// Test 2: 
		String[] someMoreWords = {"Hello", "how", "are", "you"};
		found = Eliza.findAnyWords( someMoreWords, Config.QUIT_WORDS);
		if ( !found) 
		{
			System.out.println("testFindAnyWords 2 passed.");
		}
		else 
		{
			System.out.println("testFindAnyWords 2 failed.");
		}
		
		//additional tests
	}

	/**
	 * Tests the Eliza.initialProcessing() method with two tests.
	 * 
	 * &lt;p&gt;Test 1: Test if cleaned up the string based on replacing
	 * characters and extra spaces.
	 * 
	 * &lt;p&gt;Test 2: Empty string, return false
	 */
	public static void testInitialProcessing() 
	{
		// Test 1:
		String sentence = Eliza.initialProcessing("What? This isn't the "
					+ "    4th messy-sentence!");
		if ( sentence != null 
				&& sentence.equals( "this isn't the 4th messy sentence"))
		{
			System.out.println("testInitialProcessing 1 passed.");
		}
		else 
		{
			System.out.println("testInitialProcessing 1 failed:" + sentence);
		}
		
		// Q: String empty ""
		// A: should return ""
		String sentence2 = Eliza.initialProcessing("");
		if ( sentence2 != null 
				&& sentence2.equals( ""))
		{
			System.out.println("testInitialProcessing 2 passed.");
		}
		else 
		{
			System.out.println("testInitialProcessing 2 failed:" + sentence);
		}
	}
	
	/**
	 * Tests the Eliza.replacePairs() method with four tests.
	 * 
	 * &lt;p&gt;Test 1: Replace some words not all that match before with after
	 * and return completed array with non matching words still intact.
	 * 
	 * &lt;p&gt;Test 2: Return null if beforelist and afterlist are of 
	 * different lengths
	 * 
	 * &lt;p&gt;Test 3: Return zero length array if all elements are ""
	 * 
	 * &lt;p&gt;Test 4: Return null if zero length array of beforeList is sent 
	 */
	public static void testReplacePairs() 
	{
		// Test 1: Does it replaces words it matches with correct after word
		String [] someWords = {"i'm", "cant", "recollect"};
		String [] beforeList = {"dont", "cant", "wont", "recollect", "i'm"};
		String [] afterList = {"don't", "can't", "won't", "remember", "i am"};
		String [] result = Eliza.replacePairs( someWords, 
					beforeList, afterList);
		if ( result != null && result[0].equals("i") && result[1].equals("am") 
				&& result[2].equals("can't") && result[3].equals("remember")) 
		{
			System.out.println("testReplacePairs 1 passed.");
		}
		else
		{
			System.out.println("testReplacePairs 1 failed.");
		}
		
		//additional tests
		// Q: If beforelist and afterlist are different lengths
		// A: return null (I created this did not ask instructors)
		String [] someWords2 = {"i'm", "cant", "recollect"};
		String [] beforeList2 = {"length", "is"};
		String [] afterList2 = {"different"};
		String [] result2 = Eliza.replacePairs( someWords2, 
				beforeList2, afterList2);
		if(result2 == null)
		{
			System.out.println("testReplacePairs 2 passed.");
		}
		else
		{
			System.out.println("testReplacePairs 2 failed.");
		}
		
		// Q: All ""
		// A: Length will be zero after assemble and split
		String [] someWords3 = {"", "", ""};
		String [] beforeList3 = {"length", "is"};
		String [] afterList3 = {"not", "different"};
		String [] result3 = Eliza.replacePairs( someWords3, 
				beforeList3, afterList3);
		if(result3.length == 0)
		{
			System.out.println("testReplacePairs 3 passed.");
		}
		else
		{
			System.out.println("testReplacePairs 3 failed.");
		}
		
		// Q: If beforeList doesn't have anything to match
		// A: Return null
		String [] someWords4 = {"One", "Two", "Three"};
		String [] beforeList4 = new String[0];
		String [] afterList4 = {""};
		String [] result4 = Eliza.replacePairs( someWords4, 
				beforeList4, afterList4);
		if(result4 == null)
		{
			System.out.println("testReplacePairs 4 passed.");
		}
		else
		{
			System.out.println("testReplacePairs 4 failed.");
		}
	}

	/**
	 * Tests the Eliza.findPatternInSentence() method with seven tests.
	 * 
	 * &lt;p&gt;Test 1: Remove single word from sentence found at end
	 * 
	 * &lt;p&gt;Test 2: Remove single word from sentence found at beginning
	 * 
	 * &lt;p&gt;Test 3: Remove single word from sentence found in middle
	 * 
	 * &lt;p&gt;Test 4: Remove two words found at middle and end
	 * 
	 * &lt;p&gt;Test 5: Don't remove anything return null
	 * 
	 * &lt;p&gt;Test 6: Remove more than two words
	 * 
	 * &lt;p&gt;Test 7: Multiple words neither are first or last in sentence
	 */
	public static void testFindPatternInSentence() 
	{
		//Test 1: Remove single word from sentence found at end		
		String [] pattern1 = { "computer"};
		String [] sentence1 = {"are", "you", "a", "computer"};
		
		String [] matches = Eliza.findPatternInSentence( pattern1, sentence1);
		if ( matches != null && matches.length == 2 
				&& matches[0].equals( "are you a") && matches[1].equals("")) {
			System.out.println("testFindPatternInSentence 1 passed.");
		} else {
			System.out.println("testFindPatternInSentence 1 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
		// Test 2: Remove single word from sentence found at beginning
		String [] pattern2 = { "computer"};
		String [] sentence2 = {"computer", "are", "you"};
		
		matches = Eliza.findPatternInSentence( pattern2, sentence2);
		if ( matches != null && matches.length == 2 && matches[0].equals("") 
				&& matches[1].equals( "are you")) {
			System.out.println("testFindPatternInSentence 2 passed.");
		} else {
			System.out.println("testFindPatternInSentence 2 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
		// Test 3: Remove single word from sentence found in middle
		String [] pattern5 = { "computer"};
		String [] sentence5 = {"does", "that", "computer", "on", "your", 
					"desk", "work"};
		matches = Eliza.findPatternInSentence( pattern5, sentence5);
		if ( matches != null && matches.length == 2 
				&& matches[0].equals( "does that") 
				&& matches[1].equals( "on your desk work")) {
			System.out.println("testFindPatternInSentence 3 passed.");
		} else {
			System.out.println("testFindPatternInSentence 3 failed.");
			System.out.println( Arrays.toString(matches));
		}

		// Test 4: Remove two words found at middle and end
		String [] pattern6 = {"you", "me"};
		String [] sentence6 = {"why", "don't", "you", "like",  "me"};
		matches = Eliza.findPatternInSentence( pattern6, sentence6);
		if ( matches != null && matches.length == 3 
				&& matches[0].equals( "why don't") 
				&& matches[1].equals( "like") && matches[2].equals("")) {
			System.out.println("testFindPatternInSentence 4 passed.");
		} else {
			System.out.println("testFindPatternInSentence 4 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
		// Test 5: Don't remove anything return null
		String [] pattern7 = {"you", "me"};
		String [] sentence7 = {"me", "don't", "like", "you"};
		matches = Eliza.findPatternInSentence( pattern7, sentence7);
		if ( matches == null) {
			System.out.println("testFindPatternInSentence 5 passed.");
		} else {
			System.out.println("testFindPatternInSentence 5 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
		//additional tests
		// Test 6: Remove more than two words
		String [] pattern8 = { "computer", "your", "work", "for", "you"};
		String [] sentence8 = {"does", "that", "computer", "on", "your", 
					"desk", "work", "at", "all", "for", "either", "you",
					"or", "me"};
		matches = Eliza.findPatternInSentence( pattern8, sentence8);
		if ( matches != null && matches.length == 6 
				&& matches[0].equals( "does that") 
				&& matches[1].equals( "on")
				&& matches[2].equals( "desk")
				&& matches[3].equals( "at all")
				&& matches[4].equals( "either")
				&& matches[5].equals( "or me")) {
			System.out.println("testFindPatternInSentence 6 passed.");
		} else {
			System.out.println("testFindPatternInSentence 6 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
		// Test 7: Multiple words neither are first or last in sentence
		String [] pattern9 = {"i", "dreamed"};
		String [] sentence9 = {"do", "you", "know", "that", "i", "have",
				"dreamed", "of", "being", "an", "astronaut"};
		
		matches = Eliza.findPatternInSentence(pattern9, sentence9);
		if(matches != null && matches.length == 3
				&& matches[0].equals("do you know that")
				&& matches[1].equals("have")
				&& matches[2].equals("of being an astronaut"))
		{
			System.out.println("testFindPatternInSentence 7 passed.");
		}
		else
		{
			System.out.println("testFindPatternInSentence 7 failed.");
			System.out.println( Arrays.toString(matches));
		}
		
	}
	
	/**
	 * Tests the Eliza.prepareUserPhrase() method with one test.
	 * 
	 * &lt;p&gt;Test 1: Replace match word with random chosen response
	 */
	public static void testPrepareUserPhrase()  
	{
		String someWords = "i'm happy";
		String result = Eliza.prepareUserPhrase( someWords);
		if ( result != null && result.equals("you are happy"))
		{
			System.out.println("testPrepareUserPhrase 1 passed.");
		}
		else
		{
			System.out.println("testPrepareUserPhrase 1 failed. '" 
					+ result + "'");
		}
		
		//additional tests - should have added more
	}
	
	/**
	 * Tests the Eliza.replacePairs() method with three tests.
	 * 
	 * &lt;p&gt;Test 1: Replace single instance of <a number>
	 * 
	 * &lt;p&gt;Test 2: No <a number> returns same string unmodified
	 * 
	 * &lt;p&gt;Test 3: If <a number><a number> are back to back return 
	 * string with appropriate responses without space
	 */
	public static void testPrepareResponse() 
	{
		// Test 1: Replace single instance of <a number>
		String draftResponse = "Really, <3>?";
		String []userPhrases = {"", "", "about my dog"};
		String response = Eliza.prepareResponse( draftResponse, userPhrases);
		
		String expectedResponse = "Really, about your dog?";
		
		if ( response.equals( expectedResponse)) 
		{
			System.out.println("testPrepareResponse 1 passed.");
		}
		else
		{
			System.out.println("testPrepareResponse 1 failed. '" 
					+ response + "'");
		}
		
		//additional tests
		// Test 2: 
		// Q: No <2> etc in string
		// A: should return same string unmodified
		String draftResponse2 = "There is nothing to replace here.";
		String [] userPhrases2 = {"Does", "not", "matter"};
		String response2 = Eliza.prepareResponse( draftResponse2, userPhrases2);
		
		String expectedResponse2 = "There is nothing to replace here.";
		
		if ( response2.equals( expectedResponse2)) 
		{
			System.out.println("testPrepareResponse 2 passed.");
		}
		else
		{
			System.out.println("testPrepareResponse 2 failed. '" 
					+ response2 + "'");
		}
		
		// Test 3: 
		// Q: Back to back <2><3> etc
		// A: return string with userphrases in correct position
		String draftResponse3 = "I love <2><4> with syrup.";
		String [] userPhrases3 = {"Does", "pan", "than", "cakes"};
		String response3 = Eliza.prepareResponse( draftResponse3, userPhrases3);
		
		String expectedResponse3 = "I love pancakes with syrup.";
		
		if ( response3.equals( expectedResponse3)) 
		{
			System.out.println("testPrepareResponse 3 passed.");
		}
		else
		{
			System.out.println("testPrepareResponse 3 failed. '" 
					+ response3 + "'");
		}
		
	}
	
	/**
	 * Tests the Eliza.matchResponse() method with three tests.
	 * 
	 * &lt;p&gt;Test 1: Match computer as first match in pattern of 
	 * RESPONSE_TABLE
	 * 
	 * &lt;p&gt;Test 2: Match double word pattern and choose response
	 * 
	 * &lt;p&gt;Test 3: No pattern is matched return a match from 
	 * NO_MATCH_RESPONSES
	 */
	public static void testMatchResponse() 
	{
		// Test 1: Match computer as first match in pattern of RESPONSE_TABLE
		String []words1 = {"are", "you", "a", "computer"};
		String response1 = Eliza.matchResponse( words1);
		if ( Eliza.inList( response1, Config.RESPONSE_TABLE[0][1]) >= 0) 
		{
			System.out.println("testMatchResponse 1 passed.");
		}
		else
		{
			System.out.println("testMatchResponse 1 failed.");
		}
		
		// Test 2: Match double word patter you are
		String []words2 = {"you", "are", "like", "my", "father"};
		String response2 = Eliza.matchResponse( words2);
		if ( response2 != null && response2.contains( "like your father")) 
		{
			System.out.println("testMatchResponse 2 passed.");
		}
		else 
		{
			System.out.println("testMatchResponse 2 failed.");
		}
		
		//additional tests
		// Test 3:
		// Q: No pattern matched
		// A: Pattern from Config.NO_MATCH_RESPONSES IS RETURNED
		String [] test3 = {"blaahaha", "thiscannotpossible", "match23"};
		String response3 = Eliza.matchResponse(test3);
		if(Eliza.inList(response3, Config.NO_MATCH_RESPONSES) >= 0)
		{
			System.out.println("testMatchResponse 3 passed.");
		}
		else
		{
			System.out.println("testMatchResponse 3 failed.");
		}
		
	}
	
	/**
	 * This is a helper method for the testProcessInput() method. It simply 
	 * takes the users question / phrase as string and calls the
	 * Eliza.processInput() method and displays its response.
	 *
	 * @param problem Question posed to Eliza
	 */
	private static void testProblem(String problem) 
	{
		//supporting method for testProcessInput
		System.out.println("Patient:  " + problem);
		System.out.println("Eliza: " + Eliza.processInput( problem));
	}
	
	/**
	 * This method tests the processInput() method with the help method of
	 * testProblem(). It has four tests.
	 * 
	 * &lt;p&gt;Test 1: A list of phrases taken from the Stanford universities
	 * test site of dialogues.
	 * http://web.stanford.edu/group/SHR/4-2/text/dialogues.html
	 * 
	 * &lt;p&gt;Test 2: A string containing a single quit word is sent
	 * should return null.
	 * 
	 * &lt;p&gt;Test 3: Return null if length of string is zero or empty
	 * 
	 * &lt;p&gt;Test 4: Return null if non quit words are mixed with quit words
	 *
	 * @param problem Question posed to Eliza
	 */
	public static void testProcessInput() 
	{
		//note: the responses may vary as they are randomly selected and the 
		//random generator results will vary based on the previous times it 
		//has been called. Therefore, see if each response is appropriate.
		
		//The following are selected phrases from: 
		//http://web.stanford.edu/group/SHR/4-2/text/dialogues.html
		testProblem("How are you today?");
		testProblem("Men are all alike.");
		testProblem("They're always bugging us about something specific" 
			+ " or other.");
		testProblem("Well, my boyfriend made me come here.");
		testProblem("He says I'm depressed much of the time.");
		testProblem("It's true. I'm unhappy.");
		testProblem("I need some help, that much seems certain.");
		testProblem("Perhaps I could learn to get along with my mother.");
		testProblem("My mother takes care of me.");
		testProblem("My father.");
		testProblem("You are like my father in some ways.");
		testProblem("You are not very aggressive but I think you don't want me" 
				+ " to notice that.");
		testProblem("You don't argue with me.");
		testProblem("You are afraid of me.");
		testProblem("My father is afraid of everybody.");
		testProblem("Bullies.");
		
		// Test 2:
		// Should make this more elaborate and loop through all quit words
		// Q: Quit word
		// A: should return null
		String test2 = "goodbye";
		if(Eliza.processInput(test2) == null)
		{
			System.out.println("testProcessInput 2 passed.");
		}
		else
		{
			System.out.println("testProcessInput 2 failed.");
		}
		
		// Test 3:
		// Q: Length zero or empty line
		// A: return null
		String test3 = new String();
		if(Eliza.processInput(test3) == null)
		{
			System.out.println("testProcessInput 3 passed.");
		}
		else
		{
			System.out.println("testProcessInput 3 failed.");
		}
		
		// Test 4:
		// Q: Quit word mixed with other words
		// A: return null
		String test4 = new String("My other word is computer and goodbye");
		if(Eliza.processInput(test4) == null)
		{
			System.out.println("testProcessInput 4 passed.");
		}
		else
		{
			System.out.println("testProcessInput 4 failed.");
		}
	}
	
	/**
	 * Program execution starts here. 
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		//feel free to comment out tests that are not of current interest
		//in order to focus on certain tests.  Eventually, all the tests
		//should run successfully.

		testChooseString();
		
		testInList();
		testAssemblePhrase();
		testFindLongest();
		testHowMany();

		testFilterChars();
		testRemoveDuplicateSpaces();
		testFindAnyWords();
		testInitialProcessing();

		testReplacePairs();
		testFindPatternInSentence();
		testPrepareUserPhrase();
		testPrepareResponse();

		testMatchResponse();
		testProcessInput();

	}
}
