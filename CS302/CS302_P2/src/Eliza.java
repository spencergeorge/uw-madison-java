///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Title:            Program 2 Eliza
//Files:            Eliza.java, ElizaTests.java
//Semester:         CS302 Spring 2016
//
//Author:           Spencer George
///////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

/**
 * This program is a branch of the original Eliza program that is an early
 * example of primitive natural language processing. The program poses as a 
 * therapists and asks the user to type in descriptions of their problems.
 * The program will look for patterns of words and reply with a prepared 
 * response based on the matched pattern.
 *
 * &lt;p&gt;Bugs: none known
 *
 * @author Spencer George
 */
public class Eliza 
{
	
	/**
	 * This method randomly picks one of the strings from the list. If list 
	 * is null or has 0 elements then null is returned.
	 * 
	 * @param list An array of strings to choose from.
	 * @return One of the strings from the list.
	 */		
	public static String chooseString( String [] list) 
	{
		if(list == null || list.length == 0)
		{
			return null;
		}
		int index = Config.RNG.nextInt(list.length);
		return list[index];
	}
	
	/**
	 * If the word is found in the wordList then the index of the word
	 * is returned, otherwise -1 is returned. If there are multiple matches
	 * the index of the first matching word is returned. If either word or 
	 * wordList is null then -1 is returned.
	 * 
	 * @param word  A word to search for in the list.
	 * @param wordList  The list of Strings in which to search.
	 * @return The index of list where word is found, or -1 if not found.
	 */
	public static int inList( String word, String [] wordList) 
	{
		for(int i = 0; i < wordList.length; i++)
		{
			if(wordList[i].equals(word))
				return i;
		}
		return -1;
	}
	
	/**
	 * Combines the Strings in list together with a space between each
	 * and returns the resulting string. If list is null then null is
	 * returned.
	 * 
	 * @param list An array of words to be combined.
	 * @return A string containing all the words with a space between each.
	 */
	public static String assemblePhrase( String[] list) 
	{
		if(list != null && list.length != 0)
		{
			String assembledString = ""; // Place holder for returned String
			
			for(int i = 0; i < list.length; i++)
			{
				assembledString += list[i];
				
				// Add a space if list is more than one element
				// and not the last element
				if(list.length != 1 && i != (list.length -1))
					assembledString += " ";
			}
			return assembledString;
		}
		return null;
	}
	
	/**
	 * Returns the longest sentence, based on the String length, from 
	 * the array of sentences. Removes spaces from the beginning and
	 * end of each sentence before comparing lengths. If sentences is null
	 * or has a length of 0 then null is returned.
	 * 
	 * Note: String trim method may be useful.
	 * 
	 * @param sentences The array of sentences passed in.
	 * @return The longest sentence without spaces at the beginning or end.
	 */
	public static String findLongest( String [] sentences) 
	{
		if(sentences != null && sentences.length != 0)
		{
			int indexOfLongestSentence = 0; 	// Keeps actually index of array
												// longest sentence element
			
			int lengthOfLongestSentence = 0;	// holds length to be compared 
												// on each consecutive sentence
			
			for(int i = 0; i < sentences.length; i++)
			{
				if(sentences[i] != null)
				{
					sentences[i] = sentences[i].trim();
					
					// Compare against current longest sentence
					if(sentences[i].length() > lengthOfLongestSentence)
					{
						indexOfLongestSentence = i;
						lengthOfLongestSentence = sentences[i].length();
					}
				}
			}
			return sentences[indexOfLongestSentence]; 
		}
		return null;
	}
	
	/**
	 * Counts the number of times the substring is in the str. Does not 
	 * count overlapping substrings separately. If either parameter 
	 * substring or str is null then -1 is returned.
	 * 
	 * Note: String methods indexOf may be useful for finding whether
	 *       substring occurs within str. One of the indexOf methods
	 *       has a parameter that says where to start looking in the str.
	 *       This might be useful to find the substring again in str, 
	 *       once you have found a substring once.
	 * 
	 * @param str The string to be searched.
	 * @param substring The string to be searched for within str.
	 * @return The number of times substring is found within str or -1 if
	 *         either parameter is null parameter.
	 */
	public static int howMany( String substring, String str) 
	{
		if(substring == null || str == null)
		{
			return -1;
		}
		else if(substring == "")
		{
			return str.length();
		}
		else if(str.contains(substring))
		{
			int numberOfSubstrings = 0;
			int currentIndex = 0;
			
			while(str.indexOf(substring, currentIndex) != -1)
			{
				numberOfSubstrings++;
			
				// Sets next iteration to first index after current substring
				currentIndex = str.indexOf(substring, currentIndex) 
						+ substring.length();	
			}
			return numberOfSubstrings;
		}
		return -1;
	}

	/**
	 * This method performs the following processing to the userInput.
	 * - substitute spaces for all characters but (alphabetic characters, 
	 *   numbers, and ' , ! ? .).
	 * - Change (,!?.) to (!). Parenthesis not included.
	 * 
	 * @param userInput The characters that the user typed in.
	 * @return The character array containing the valid characters.
	 */	
	public static char [] filterChars( String userInput) 
	{
		char [] replacedChars = 
				userInput.replaceAll("[,!?.]","!")
				.replaceAll("[^a-zA-Z0-9!']", " ")
				.toCharArray();
		return replacedChars; 
	}

	/**
	 * Reduces all sequences of 2 or more spaces to 1 space within the 
	 * characters array. If any spaces are removed then the same number
	 * of Null character '\u0000' will fill the elements at the end of the
	 * array.
	 * 
	 * @param characters The series of characters that may have more than one
	 *     space in a row when calling. On return the array of characters will
	 *     not have more than one space in a row and there may be '\u0000'
	 *     characters at the end of the array.
	 */
	public static void removeDuplicateSpaces( char [] characters) 
	{
		// Iterate through all characters evaluate one at a time.
		for(int i = 0; i < characters.length; i++)
		{
			if(characters[i] == ' ' && characters[i + 1] == ' ')
			{
				// Shift elements left one leaving the current iterated element 
				// i as a space.
				for(int ii = i + 2; ii < characters.length; ii++)
				{
					characters[ii - 1] = characters[ii];
				}
				
				characters[characters.length - 1] = '\u0000'; // Add null to end
				
				i--; 	// Start the next iteration with the current element
						// to check for additional spaces. This is what
						// provides for more than 2 spaces in a row
			}
		}
	}
	
	/**
	 * Looks for each word in words within the wordList. 
	 * If any of the words are found then true is returned, otherwise false.
	 * 
	 * @param words List of words to look for.
	 * @param wordList List of words to look through.
	 * @return Whether one of the words was found in wordList.
	 */
	public static boolean findAnyWords(String[] words, String []wordList ) 
	{
		if(!(words.length == 0) || !(words == null) 
				|| !(wordList.length == 0) || !(wordList == null))
		{
			for(int i = 0; i < words.length; i++)
			{
				if(inList(words[i], wordList) > -1)
				{
					return true;
				}
			}
		}
		
		return false;
	}	
	
	/**
	 * This method performs the following processing to the userInput and 
	 * returns the longest resulting sentence.
	 * 1) Converts all characters to lower case  
	 * 		See String toLowerCase() method.
	 * 2) Removes any extra spaces at the beginning or end of the input
	 * 		See String trim() method.
	 * 3) Substitute spaces for all characters but alphabetic characters, 
	 *    numbers, and ',.!? and then substitute ! for ,.?
	 *      See filterChars method.
	 * 4) Reduce all sequences of 2 or more spaces to be one space.
	 *      See removeDuplicateSpaces method.
	 * 5) Divide input into separate sentences based on !
	 *      Construct a String from a character array with 
	 *      	String str = new String( arrayName);
	 *      See String split method.
	 *      Example: String[] sentences = str.split("!");
	 * 6) Determine which sentence is longest in terms of characters and
	 *    return it. 
	 *      See findLongest method.
	 * 
	 * @param userInput The characters that the user typed in.
	 * @return The longest sentence from the input.
	 */
	public static String initialProcessing( String userInput) 
	{
		userInput = userInput.toLowerCase();
		userInput = userInput.trim();
		char [] filtered = filterChars(userInput);
		removeDuplicateSpaces(filtered);
		String str = new String(filtered);
		String [] sentences = str.split("!");
		
		return findLongest(sentences);
	}
	
	/**
	 * This method creates a new words list replacing any words it finds in
	 * the beforeList with words in the afterList. An array of the resulting
	 * words is returned.  
	 * 
	 * @param words List of words to look through.
	 * @param beforeList List of words to look for.
	 * @param afterList List of words to replace, 
	 * 			if the corresponding word in the before list 
	 * 			is found in the words array.
	 * @return The new list of words with replacements. Null if before and
	 * after list are not the same length.
	 */
	public static String[] replacePairs(String []words, String [] beforeList, 
			String [] afterList) 
	{
		// Checks that both lists have same number of elements, otherwise 
		// there may be a matching element that cannot be replaced
		// else return null
		if(beforeList.length == afterList.length && beforeList.length > 0)
		{
			// Returned array
			String [] arrayReplaced = new String[words.length]; 
			
			int inListIndex = 0;
			
			/*
			 *  Iterate through words list check if in beforeList
			 * if so add afterList[sameIndex of before] 
			 * to arrayReplaced otherwise add word to arrayReplaced
			 */
			for(int i = 0; i < words.length; i++)
			{
				inListIndex = inList(words[i], beforeList);
				if(inListIndex > -1)
				{
					arrayReplaced[i] = afterList[inListIndex];
				}
				else
				{
					arrayReplaced[i] = words[i];
				}
			}
			return assemblePhrase(arrayReplaced).split(" ");
		}
		return null;
	}
	
	/**
	 * Checks to see if the pattern matches the sentence. In other words, 
	 * checks to see that all the words in the pattern are in the sentence 
	 * in the same order as the pattern. If the pattern matches then this
	 * method returns the phrases before, between and after the 
	 * pattern words. If the pattern does not match then null is returned.
	 * 
	 * @param pattern The words to match, in order, in the sentence.
	 * @param sentence Each word in the sentence.
	 * @return The phrases before, between and after the pattern words
	 * 		or null if the pattern does not match the sentence.
	 */	
	public static String [] findPatternInSentence( String [] pattern, 
			String [] sentence) 
	{
		// Stores userPhrase and is returned unless conditions aren't met
		String [] userPhrase = new String[pattern.length + 1];
		
		// Initialize all elements to "" 
		for(int i = 0; i < userPhrase.length; i++)
		{
			userPhrase[i] = "";
		}
		
		int patternIndex = 0;	// Increments when a pattern word is found
							  	// so next sentence element is checked against
								// new pattern element.
		
		int phraseIndex = 0;	// Increments when pattern word is found so that
								// the next sentence element is added to the 
								// next userPhrase element, skipping pattern 
								// word that was found
		
		boolean firstConcat = true; // Is used to determine when to remove the
									// initial "" from the user phrase
									// Probably could have used trim but ohwell
		
		boolean patternMatched = false; // When true runs loop to add any 
										// remaining sentence elements to 
										// last userPhrase element
		
		/*
		 * Iterate through each word in sentence. Will return userPhrase
		 * if pattern words are all found in order in sentence. 
		 * 
		 * If pattern index == pattern.length, for loop will return before 
		 * next item therefore an array index out of bounds will not occur 
		 */
		for(int i = 0; i < sentence.length; i++)
		{
			/*
			 * Test if pattern matches sentence.  
			 * 
			 * If so increment patternIndex and phraseIndex to check next 
			 * pattern on next element.
			 * 
			 * Else add current word to userPhrase current index.
			 */
			if(pattern[patternIndex].equals(sentence[i]))
			{
				patternIndex++;

				// Testing if the patterns last element has been matched
				if(patternIndex == pattern.length)
				{
					patternMatched = true;
				}
				phraseIndex++;
				firstConcat = true; 
			}
			else // Add current word to userPhrase current index.
			{
				// If this is the first element added to the userPhrase then
				// overwrite the "" that was set on initialization
				if(firstConcat)
				{
					userPhrase[phraseIndex] = sentence[i];
					firstConcat = false;	
				}
				else
				{
					userPhrase[phraseIndex] += (" " + sentence[i]);
				}
			}
			
			// When pattern has been matched will add any remaining sentence 
			// elements to the last userPhrase
			if(patternMatched == true)
			{
				i++;	// Increment to not add the current  
						// word that was matched to userPhrase
		
				
				/*
				 *  If false: no more sentence items remain therefore
				 *  userPhrase is complete and no additional elements need to 
				 *  be added.
				 *  Otherwise: add remaining elements before returning 
				 *  userPhrase 
				 */
				if(i < sentence.length)
				{
					while(i < sentence.length)
					{
						// If this is the first element added to the userPhrase
						// then overwrite the "" that was set on initialization
						if(firstConcat)
						{
							userPhrase[phraseIndex] = sentence[i];
							firstConcat = false;	
						}
						else
						{
							userPhrase[phraseIndex] += (" " + sentence[i]);
						}
						
						i++;
					}
				}
				return userPhrase;
			} // End if pattern matched
		} // End for loop for sentence array
		
		return null;
	} // End findPatternInSentence

	/**
	 * Replaces words in the phrase if they are in the
	 * Config.POST_PROCESS_BEFORE with the corresponding words
	 * from Config.POST_PROCESS_AFTER.
	 * 
	 * @param phrase One or more words separated by spaces.
	 * @return A string composed of the words from phrase with replacements.
	 */
	public static String prepareUserPhrase( String phrase) 
	{
		String [] preparedPhrase = replacePairs(phrase.split(" "),
				Config.POST_PROCESS_BEFORE, Config.POST_PROCESS_AFTER);
		
		return assemblePhrase(preparedPhrase);
	}
	
	/**
	 * Prepares a response based on the draftResponse. If draftResponse
	 * contains <1>, <2>, <3> or <4> then the corresponding userPhrase
	 * is substituted for the <1>, <2>, etc.  The userPhrase however must
	 * be prepared by exchanging words that are in Config.POST_PROCESS_BEFORE
	 * with the corresponding words from Config.POST_PROCESS_AFTER.
	 * 
	 * @param draftResponse A response sentence potentially containing <1>, 
	 *             <2> etc.
	 * @param userPhrases An array of phrases from the user input.
	 * @return A string composed of the words from sentence with replacements.
	 */
	public static String prepareResponse( String draftResponse,
			String []userPhrases) 
	{
		// Searches by userPhrases index if corresponding <i+1> matches in 
		// draftResponse.
		for(int i = 0; i < userPhrases.length; i++)
		{
			if(draftResponse.contains("<" + (i + 1) + ">") 
					&& userPhrases.length > 0)
			{
				draftResponse = draftResponse.replaceFirst("<" + (i + 1) + ">", 
						prepareUserPhrase(userPhrases[i]));
			}
		}
		return draftResponse;
	}
	
	/**
	 * Looks through Config.RESPONSE_TABLE to find the first pattern 
	 * that matches the words. When a pattern is matched then a response 
	 * is randomly chosen from the corresponding list of responses.
	 * If the response has a parameter denoted with <1>, <2> 
	 * etc. the parameter is replaced with the 0th, 1st, etc String
	 * from the user phrases returned by the findPatternInSentence method.
	 * 
	 * @param words The words of a sentence.
	 * @return The completed response ready to be shown to the user.
	 */
	public static String matchResponse( String [] words) 
	{
		for(int i = 0; i < Config.RESPONSE_TABLE.length; i++)
		{
			/*
			 *  Create an array for each response set and split the pattern
			 *  array from the response array into individual arrays to be
			 *  searched through (pattern) and used to replace with (response)
			 */
			String [][] responseSet = Config.RESPONSE_TABLE[i];
			String [] pattern = responseSet[0];
			String [] response = responseSet[1];
			
			String [] patternMatch = findPatternInSentence(pattern, words);
			
			// If pattern was found in findPatternInSentence prepareResponse
			if(patternMatch != null)
			{
				return prepareResponse(chooseString(response), patternMatch);
			}
		}
		return chooseString(Config.NO_MATCH_RESPONSES); 
	}

	/**
	 * Takes the input as a parameter and returns a response. If any of the
	 * QUIT_WORDS are found then null is returned. 
	 * 
	 * @param userInput The problem sentence(s) the user types in.
	 * @return A response string to be shown to the user or null if a QUIT_WORD
	 *         is found.
	 */
	public static String processInput(String userInput) 
	{
		// For the case that a user submits an empty line in interactive
		if(userInput.length() > 0)
		{
			userInput = initialProcessing(userInput);
			// If no QUIT_WORDS were found proceed with method
			if(!findAnyWords(userInput.split(" "), Config.QUIT_WORDS))
			{
				/*
				 *  Replaces words if found in PRE_PROCESS_BEFORE with 
				 *  corresponding index in PRE_PROCESS_AFTER
				 */
				String [] preparedPhrase = replacePairs(userInput.split(" "),
						Config.PRE_PROCESS_BEFORE, Config.PRE_PROCESS_AFTER);
				
				return matchResponse(preparedPhrase);
			}
		}
		return null;
	}

	/**
	 * This method displays an INITIAL_MESSAGE, accepts typed input, calls 
	 * the processInput method, and prints out the response (of processInput)
	 * until the response is null at which point the FINAL_MESSAGE is shown
	 * and the program terminates.
	 */
	public static void interactive() 
	{
		Scanner readConsole = new Scanner(System.in);
		
		System.out.println("Eliza: " + Config.INITIAL_MESSAGE);
		
		String response = "";	// Response holds ELIZA's response
		
		/*
		 * Continues taking input and giving a response until a quit word
		 * or user presses the ENTER key and submits not text (a blank line)
		 */
		do
		{
			System.out.print("Patient: ");

			response = processInput(readConsole.nextLine());

			if(response == null)
			{
				System.out.println(Config.FINAL_MESSAGE);
			}
			else
			{
				System.out.println("Eliza: " + response);
			}
		}while(response != null);
		
		readConsole.close(); // Question loop has finished therefore program
							 // will terminate and the scanner can be closed

	}
	
	/**
	 * Program execution starts here.
	 * @param args unused
	 */  	
	public static void main(String[] args) 
	{
		interactive();
		
	}
}
