///////////////////////////////////////////////////////////////////////////////
// Config.java for Program1: Math Trainer (CS302, Spring 2016)
//
// This file contains constants that will be used to control parameters used by
// your MathTrainer program.  This file will not be handed in, because our own
// Config.java files will be used to test your program.  For this reason, your
// code must reference these constant values by the names defined below.
//
// DO NOT EDIT THE VARIABLE NAMES OR TYPES, OR ADD VARIABLES TO THIS FILE.
//
// Your program may assume that the constant values used to test your code are
// all of the correct type, in addition to the following:
//     MIN VALUE is less than or equal to MAX_VALUE
//     CLOSE_ENOUGH is a positive value that is less than 1.0
//     NUMBER_OF_QUESTIONS is a positive value
// 
///////////////////////////////////////////////////////////////////////////////

public class Config 
{
	// Use RANDOM_SEED to initialize your pseudo-random number generator, so
	// that it generates a predictable sequence of "random" values.
	public static final int RANDOM_SEED = 211;
	// If you want your program to generate different "random" values each 
	// time it is run, you can set this seed to something that is different
	// each time the program runs: like the current time in milliseconds.	
	//public static final int RANDOM_SEED = (int)System.currentTimeMillis();
	
	// MIN_VALUE and MAX_VALUE define the range of values that your program
	// should generate math problem operands within. 
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = 9;
	
	// Use CLOSE_ENOUGH to determine whether the user's answer is close enough
	// to the computer generated solution to be considered correct.
	public static final double CLOSE_ENOUGH = 0.1;
	
	// Use NUMBER_OF_QUESTIONS to determine the total number of math problems 
	// that generated and presented to the user during a single execution.
	public static final int NUMBER_OF_QUESTIONS = 3;
}
