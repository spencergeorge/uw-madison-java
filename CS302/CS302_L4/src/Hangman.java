///////////////////////////////////////////////////////////////////////////////
// Title:            Hangman
// Files:            Hangman.java
//
// Author:           CS302, Jim Skrentny
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * This program implements the word guessing game called Hangman.
 *
 * @author Jim Skrentny
 */
public class Hangman {  

    /**
     * main
     *
     * Play one game of Hangman until the user wins (guesses all of the
     * letters in the secret word) or loses (guesses 7 incorrect letters).
     */
    public static void main(String [] args) {

        //////////////////////////////////////////////////////////////////////
        // 1. "SECRET" WORDS LIST
        //////////////////////////////////////////////////////////////////////
        String [] secretWordsList =   //choose secret word from these
        {"geography", "cat", "yesterday", "java", "truck", "opportunity",
                "fish", "token", "transportation", "bottom", "apple", "cake",
                "remote", "boots", "terminology", "arm", "cranberry", "tool",
                "caterpillar", "spoon", "watermelon", "laptop", "toe", "toad",
                "fundamental", "capitol", "garbage", "anticipate", "pesky"};


        //////////////////////////////////////////////////////////////////////
        // 2. CONSTANT
        //////////////////////////////////////////////////////////////////////
        final int MAX_BAD_GUESSES = 7;  //user can guess up to 7 characters


        //////////////////////////////////////////////////////////////////////
        // 3. VARIABLES
        //////////////////////////////////////////////////////////////////////
        //3A. Choose the secret word (DONE)

        String secretWord;
        //REMOVE LINE BELOW WHEN DONE IMPLEMENTING
        //secretWord = "miscellaneous";
        secretWord = "abcd";

        //Randomly choose a word from list of words
        //UNCOMMENT LINES BELOW TO PLAY WHEN DONE  IMPLEMENTING
        //Random randIndex = new Random();
        //int index = randIndex.nextInt(secretWordsList.length);
        //secretWord = secretWordsList[index];

        //3B. Declare and construct arrays

        //TODO: Finish the line below to construct an array that is
        //      the same length as the secret word.
        char[] correctLetters = new char[secretWord.length()];
        
        //TODO: Initialize this array's elements to '_' underscore characters.
        for(int i = 0; i < correctLetters.length; i++)
        {
        	correctLetters[i] = '_';
        }
        
        //TODO: Finish the line below to construct an array that can
        //      store up to 7 characters for wrong letter guesses.
        //      Hint: Is there a constant that you should use?
        char[] incorrectLetters = new char[MAX_BAD_GUESSES];

        //3C. Other Variables (DONE)
        int    badGuessesCount = 0;  // count of bad guesses
        Scanner stdin = new Scanner(System.in); // for user input



        //////////////////////////////////////////////////////////////////////
        // 4. MAIN LOOP FOR GAME
        //////////////////////////////////////////////////////////////////////

        while (!gameWon(correctLetters) && badGuessesCount < MAX_BAD_GUESSES) {

            //4A. Call method to print the Hangman picture (DONE).
            printHangman(badGuessesCount);

            //4B. Print the correct guesses in the secret word.
            //TODO: Write the code to display the ENTIRE correctLetters array,
            //      that is, display each character in the array separated by
            //      a space character.

            for (int i=0; i<correctLetters.length; i++){
            	System.out.print(correctLetters[i]+" ");
            }

            //4C. Print the incorrect letters that have been guessed.
            //TODO: Write the code to display only the USED PART of the 
            //      incorrectLetters array, that is, display only the bad
            //      guess characters in the array each separated by a space
            //      character.  Note incorrectLetters might only be partially
            //      filled -- DO NOT DISPLAY THE UNUSED ELEMENTS.
            //      Hint: What variable tells you how many bad guesses were made?
            System.out.print("\nWrong guesses: ");

            for (int i=0; i<badGuessesCount; i++){
            	System.out.print(incorrectLetters[i] + " ");
            }


            //4D. Prompt and read the next guess (DONE)
            char guess = ' ';
            boolean enterLetter = false;
            
            do
            {
            	String line = "";
            	System.out.print("\n\n--> Enter a letter for your guess: ");
            	line = stdin.nextLine();
            	if(!(line.length() == 0) && line.matches("[a-zA-Z]")){
            		guess = line.toLowerCase().charAt(0);
            		boolean guessedTwice = guessedTwice(incorrectLetters,guess);
            	
            		if(guessedTwice == true)
            		{
            			System.out.println("Error! That letter has already been guessed!");
            		}
            		else
            		{
            			enterLetter=true;
            		}
        		}else{
	            	System.out.println("Error! Enter a letter");
            	}
            }while(!enterLetter);
            
            //4E. Process the next guess (PARTIALLY DONE)
            //    If the letter "guess" is in the secret word then add it
            //    to the correct guesses array in the correct location(s).
            //    Otherwise, add the letter "guess" to incorrect guesses.
            boolean found = false;  //declaring variable here is okay!

            // go through the secret word character by character
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) { //if guess matches
                    found = true;                    //the guess was correct
                    correctLetters[i] = guess;       //record correct guess
                }
            }

            if (found) {
                System.out.println("*** Your guess was correct!");
            }
            else {
   	         	//TODO: Write the code to store the incorrect guess and update
				//      the count of bad guesses.

				incorrectLetters[badGuessesCount] = guess ;
				badGuessesCount++;
                System.out.println("*** Sorry, that letter is not in the secret word");
            }
        }

        //////////////////////////////////////////////////////////////////////
        // 5. END OF GAME MESSAGE (DONE)
        //////////////////////////////////////////////////////////////////////
        System.out.println("\n********************************************");
        System.out.println("The secret word was: " + secretWord);
        if (gameWon(correctLetters)) {
            System.out.println("Congratulations, you won!");
        } else {
            System.out.println("Sorry, you lost.");
            printHangman(badGuessesCount);
        }
    }



    //////////////////////////////////////////////////////////////////////
    // 6. ADDITIONAL STATIC METHODS
    //////////////////////////////////////////////////////////////////////

    //6A. Method to determine if the game has been won.
    /**
     * gameWon
     *
     * Return true if the user has won the game;
     * otherwise, return false.
     *
     * @return true if the user has won, false otherwise
     */
    private static boolean gameWon(char[] correctLetters) {

        //TODO: Complete the method below so that it returns true if there are
        //      NO '_' characters in the correctLetters array, otherwise return
        //      false.

        for(int i=0; i<correctLetters.length; i++){
        	if(correctLetters[i]=='_'){
        		return false;
        	}
       
        }

        // NOTE: THE LINE BELOW IS SO THE CODE WILL COMPILE
        // REPLACE IT with appropriate code for your implementation
        return true;
    }


    //6B. Method to display the hangman image (DONE).
    /**
     * printHangman
     *
     * Print the Hangman that corresponds to the given number of
     * bad guesses so far.
     *
     * @param badGuessesCount count of bad guesses so far
     */
    private static void printHangman(int badGuessesCount) {
        int poleLines = 6;   // number of lines for hanging pole
        System.out.println("  ____");
        System.out.println("  |  |");

        if (badGuessesCount == 7) {
            System.out.println("  |  |");
            System.out.println("  |  |");
        }

        if (badGuessesCount > 0) {              
            System.out.println("  |  O");
            poleLines = 5;
        }
        
		if (badGuessesCount > 1) {
            poleLines = 4;
            if (badGuessesCount == 2) {
                System.out.println("  |  |");
            } else if (badGuessesCount == 3) {
                System.out.println("  | \\|");
            } else if (badGuessesCount >= 4) {
                System.out.println("  | \\|/");
            }
        }
        
		if (badGuessesCount > 4) {
            poleLines = 3;
			System.out.println("  |  |");
		}
        
		if (badGuessesCount == 6) {
		    poleLines = 2;
            System.out.println("  | /");
        } else if (badGuessesCount >= 7) {
		    poleLines = 0;
            System.out.println("  | / \\");
        }

        for (int k = 0; k < poleLines; k++) {
            System.out.println("  |");
        }
        System.out.println("__|__");
        System.out.println();
    }
    
  //6C. Method to determine if guess has already been guessed.
    /**
     * guessedTwice
     *
     * Return true if the user has guessed the same letter twice;
     * otherwise, return false.
     *
     * @param guess: char that the user guessed
     * @return true if the user has guessed the letter twice, false otherwise
     */
    private static boolean guessedTwice(char[] incorrectLetters, char guess) {

        for(int i=0; i < incorrectLetters.length; i++){
        	if(incorrectLetters[i] == guess){
        		return true;
        	}
       
        }

        // NOTE: THE LINE BELOW IS SO THE CODE WILL COMPILE
        // REPLACE IT with appropriate code for your implementation
        return false;
    }

}

