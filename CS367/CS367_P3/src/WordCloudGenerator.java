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
import java.io.*;

public class WordCloudGenerator {
    /**
     * The main method generates a word cloud as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine where input and 
     * output is done:
     * <ul>
     *   <li>args[0] is the name of the input file</li>
     *   <li>args[1] is the name of the output file</li>
     *   <li>args[2] is the name of the file containing the words to ignore 
     *       when generating the word cloud</li>
     *   <li>args[3] is the maximum number of words to include in the word 
     *       cloud</li> 
     * </ul>
     */
    public static void main(String[] args) 
	{
        Scanner in = null;         // for input from text file
        PrintStream out = null;    // for output to html file
        Scanner inIgnore = null;   // for input from ignore file
        DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();  

        // Check the command-line arguments and set up the input and output
        
        // Check for exactly four arguments from the command line
        if(args.length != 4)
        {
        	System.out.println("Four arguments required: inputFileName "
        			+ "outputFileName ignoreFileName maxWords");
        	
        	System.exit(1);
        }
        
        String inputFileName = args[0];
        String ignoreFileName = args[2];
                
        // Try to access and read from input file
        File inputFile = new File(inputFileName);
        try 
        {
			in = new Scanner(inputFile);
		}
        catch (FileNotFoundException e) 
        {
        	System.out.println("Error: cannot access file " + inputFileName);
        	System.exit(2);
		}
        
        // Try to access and read from ignore file
        File ignoreFile = new File(ignoreFileName);
        try 
        {
			inIgnore = new Scanner(ignoreFile);
		} 
        catch (FileNotFoundException e1) 
        {
        	System.out.println("Error: cannot access file " + ignoreFileName);
        	System.exit(3);
		}

        // Test that maxWords is a positive integer
        int maxWords = 0;
        try
        {
        	maxWords = Integer.parseInt(args[3]);
        	if(maxWords  < 0)
            {
            	System.out.println("Error: maxWords must be a positive integer");
            	System.exit(4);
            }
        }
        catch (NumberFormatException e)
        {
        	System.out.println("Error: maxWords must be a positive integer");
        	System.exit(4);
        }
        
        	
        // Create output file
        try 
        {
			out = new PrintStream(args[1]);
		} 
        catch (FileNotFoundException e2) 
        {
        	System.out.println("Error: cannot create output file " + out);
        	System.exit(5);
		}

        // Create the dictionary of words to ignore
        // You do not need to change this code.
        DictionaryADT<String> ignore = new BSTDictionary<String>();
        while (inIgnore.hasNext()) {
            try {
                ignore.insert(inIgnore.next().toLowerCase());
            } catch (DuplicateException e) {
                // if there is a duplicate, we'll just ignore it
            }
        }
        
        // Process the input file line by line
        // Note: the code below just prints out the words contained in each
        // line.  You will need to replace that code with code to generate
        // the dictionary of KeyWords.
        while (in.hasNext()) {
            String line = in.nextLine();
            List<String> words = parseLine(line);
            
            // Add the word to the dictionary of words to create wordcloud from
            for (String word : words)
            {
            	word = word.toLowerCase();
            	
            	// If word is in ignore list skip, do not add to dictionary
            	if(ignore.lookup(word) == null)
            	{

            		KeyWord tempKeyWord = new KeyWord(word);
					KeyWord keyWordLookup = dictionary.lookup(tempKeyWord);
					
					/*
					 *  If keyWordLookup is null then tempKeyWord is unique
					 *  and can be added to dictionary.
					 *  
					 *  If not null the keyWord is in the dictionary and
					 *  needs to be incremented.
					 */
					if(keyWordLookup != null)
						keyWordLookup.increment();
					else
					{
						try 
						{
							dictionary.insert(tempKeyWord);
							tempKeyWord.increment();
						}
						catch (DuplicateException e) 
						{
							// ignores duplicate
						}
					}
				} 
        	}

        } // end while

        
        ////////////////////////////////////////////////////////////
        // ADD YOUR CODE HERE TO
        // - Print out the information about the dictionary:
        //     - # of keys
        //     - average path length
        //     - linear average path length
        // - Put the dictionary into a priority queue
        // - Use the priority queue to create a list of KeyWords of 
        //   the appropriate length
        // - Generate the html output file
        ////////////////////////////////////////////////////////////
        
        // Maths
        System.out.println("# keys: " + dictionary.size());
        System.out.println("avg path length: " 
        		+ ((float)dictionary.totalPathLength() 
        				/ (float)dictionary.size()));
        System.out.println("linear avg path: " + (1 + dictionary.size()) / 2 );
        
        // Priority Queue to sort dictionary by most occurring words
        ArrayHeap<KeyWord> priorityQueue 
        		= new ArrayHeap<KeyWord>(dictionary.size());
        Iterator<KeyWord> dictionaryIterator = dictionary.iterator();
        
        while(dictionaryIterator.hasNext())
        {
        	priorityQueue.insert(dictionaryIterator.next());
        }

        // Wordcloud list
        DictionaryADT<KeyWord> wordCloud = new BSTDictionary<KeyWord>();
        for(int i = 0; i < maxWords; i++)
        {
        	try 
        	{
				wordCloud.insert(priorityQueue.removeMax());
			}
        	catch (DuplicateException e) 
        	{
        		// Ignore duplicates, shouldn't be any
			}
        }
        
        generateHtml(wordCloud, out);

        // Close everything
        if (in != null) 
            in.close();
        if (inIgnore != null) 
            inIgnore.close();
        if (out != null) 
            out.close();
    }
    
    /**
     * Parses the given line into an array of words.
     * 
     * @param line a line of input to parse
     * @return a list of words extracted from the line of input in the order
     *         they appear in the line
     *         
     * DO NOT CHANGE THIS METHOD.
     */
    private static List<String> parseLine(String line) {
        String[] tokens = line.split("[ ]+");
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {  // for each word
            
            // find index of first digit/letter
              boolean done = false; 
              int first = 0;
            String word = tokens[i];
            while (first < word.length() && !done) {
                if (Character.isDigit(word.charAt(first)) ||
                    Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;
            }
            
            // find index of last digit/letter
            int last = word.length()-1;
            done = false;
            while (last > first && !done) {
                if (Character.isDigit(word.charAt(last)) ||
                        Character.isLetter(word.charAt(last)))
                        done = true;
                    else last--;
            }
            
            // trim from beginning and end of string so that is starts and
            // ends with a letter or digit
            word = word.substring(first, last+1);
  
            // make sure there is at least one letter in the word
            done = false;
            first = 0;
            while (first < word.length() && !done)
                if (Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;           
            if (done)
                words.add(word);
        }
        
        return words;
    }
    
    /**
     * Generates the html file using the given list of words.  The html file
     * is printed to the provided PrintStream.
     * 
     * @param words a list of KeyWords
     * @param out the PrintStream to print the html file to
     * 
     * DO NOT CHANGE THIS METHOD
     */
    private static void generateHtml(DictionaryADT<KeyWord> words, 
                                     PrintStream out) {
           String[] colors = { 
         	"#CD5C5C",      //INDIANRED
		"#5F9EA0",      //CADETBLUE
	 	"#FA8072",      //SALMON
	 	"#E9967A",      //DARKSALMON
	 	"#FF69B4",      //HOTPINK
	 	"#FFA500",      //ORANGE
		"#B22222",	// FIREBRICK
	 	"#E6E6FA",      //LAVENDER
	 	"#8A2BE2",      //BLUEVIOLET
	 	"#6A5ACD",      //SLATEBLUE
	 	"#7FFF00",      //CHARTREUSE
	 	"#32CD32",      //LIMEGREEN
	 	"#228B22",      //FORESTGREEN
	 	"#66CDAA",      //MEDIUMAQUAMARINE
	 	"#00FFFF",      //CYAN
	 	"#1E90FF",      //DODGERBLUE
	 	"#FFE4C4",      //BISQUE
	 	"#8B4513",      //SADDLEBROWN
	 	"#F5F5DC",      //BEIGE
	 	"#C0C0C0"       //Silver       
	     };
           int initFontSize = 100;
   	   String fontFamily = "Cursive";
           
        // Print the header information including the styles
        out.println("<head>\n<title>Word Cloud</title>");
        out.println("<style type=\"text/css\">");
        out.println("body { font-family: "+fontFamily+" }");
        
        // Each style is of the form:
        // .styleN {
        //      font-size: X%;
        //      color: #YYYYYY;

        // }
        //  where N and X are integers and Y is a hexadecimal digit
        for (int i = 0; i < colors.length; i++)
            out.println(".style" + i + 
                    " {\n    font-size: " + (initFontSize + i*20)
                    + "%;\n    color: " + colors[i] + ";\n}");

        
        out.println("</style>\n</head>\n<body><p>");        
        
        // Find the minimum and maximum values in the collection of words
        int min = Integer.MAX_VALUE, max = 0;
        for (KeyWord word : words) {
            int occur = word.getOccurrences();
            if (occur > max)
                max = occur;
            if (occur < min)
                min = occur;
        }

        double slope = (colors.length - 1.0)/(max - min);
        
        for (KeyWord word : words) {
            out.print("<span class=\"style");
            
            // Determine the appropriate style for this value using
            // linear interpolation
            // y = slope *(x - min) (rounded to nearest integer)
            // where y = the style number
            // and x = number of occurrences
            int index = (int)Math.round(slope*(word.getOccurrences() - min));
            
            out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
        }
        
        // Print the closing tags
        out.println("</p></body>\n</html>");
    }
 }