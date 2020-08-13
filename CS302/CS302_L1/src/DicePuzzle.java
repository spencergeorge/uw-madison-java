import java.util.Random;
import java.util.Scanner;

public class DicePuzzle 
{

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		// Lab01.RunPuzzle("AbracaJava");

		// Create randGen object
		final Random randGen = new Random();
		// Create scanner object
		final Scanner scnr = new Scanner(System.in);

		// Generate random number to use for dice min 1 max 6
		final int ranNumber = randGen.nextInt(6) + 1;

		// Declare and initialize a variable to hold the correct answer to compare
		int correctAnswer = 0;

		// Switch state that runs based on ranNumber (dice)
		switch (ranNumber) {
			case 1:
				correctAnswer = 0;
				System.out.println("/---\\");
				System.out.println("|   |");
				System.out.println("| * |");
				System.out.println("|   |");
				System.out.println("\\---/");
				break;
			case 2:
				correctAnswer = 0;
				System.out.println("/---\\");
				System.out.println("| * |");
				System.out.println("|   |");
				System.out.println("| * |");
				System.out.println("\\---/");
				break;
			case 3:
				correctAnswer = 2;
				System.out.println("/---\\");
				System.out.println("|  *|");
				System.out.println("| * |");
				System.out.println("|*  |");
				System.out.println("\\---/");
				break;
			case 4:
				correctAnswer = 0;
				System.out.println("/---\\");
				System.out.println("|* *|");
				System.out.println("|   |");
				System.out.println("|* *|");
				System.out.println("\\---/");
				break;
			case 5:
				correctAnswer = 4;
				System.out.println("/---\\");
				System.out.println("|* *|");
				System.out.println("| * |");
				System.out.println("|* *|");
				System.out.println("\\---/");
				break;
			case 6:
				correctAnswer = 0;
				System.out.println("/---\\");
				System.out.println("|* *|");
				System.out.println("|* *|");
				System.out.println("|* *|");
				System.out.println("\\---/");
				break;
			default:
				System.out.println("This should never happen");
		}

		System.out.print("How many petals around the rose? ");
		final int userGuess = scnr.nextInt();
		
		// Evaluate if the users guess equals the answer
		if(userGuess == correctAnswer)
		{
			System.out.println("You're correct!");
		}
		else //If users guess is wrong
		{
			System.out.println("The correct answer is: " + correctAnswer);
		}
	}
}
