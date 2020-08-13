import java.util.Random;

public class HumidityGenerator {

	public static void main(String[] args) {
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
		double highHumidityPoints = 0.0; 
		for(int ii = 0; ii < HEIGHT; ii++)
		{
			for(int i = 0; i < WIDTH; i++)
			{
				// Pick a random number from the correct number of values
				double randValues = randomGenerator.nextDouble();
				// Shift whatever number is picked into the correct range
				double randNumber = randValues + 0;
				
				if(randNumber < 0.4)
				{
					System.out.print(".");
				}
				else
				{
					System.out.print("~");
					highHumidityPoints++;
				}
				
			}
			System.out.print("\n");
		}
		System.out.println(highHumidityPoints);
		System.out.println("High Humidity: " 
				+ (highHumidityPoints / (WIDTH * HEIGHT)) * 100 + "%");
	}

}
