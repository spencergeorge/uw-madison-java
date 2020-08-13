import java.util.Random;

public class TerrainGenerator 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		final int WIDTH = 10;
		final int HEIGHT = 5;	
		final int SEED = 1;
		Random randomGenerator = new Random(SEED);
				
		/**
		 * First loop is the height it only prints a new
		 * line character for each row after the row has
		 * printed in the second loop.
		 */
		double highElevationPoints = 0.0; 
		for(int ii = 0; ii < HEIGHT; ii++)
		{
			for(int i = 0; i < WIDTH; i++)
			{
				// Pick a random number from the correct number of values
				double randValues = randomGenerator.nextInt(29 - 0 + 1);
				// Shift whatever number is picked into the correct range
				double randNumber = randValues + 0;
				if(randNumber >= 18)
				{
					System.out.print("^");
					highElevationPoints++;
				}
				else
				{
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
		System.out.println("High Elevations: " + highElevationPoints / (WIDTH * HEIGHT));
		
	}

}
