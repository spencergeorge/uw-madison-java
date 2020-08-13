
import java.util.Random;

public class PlanetGenerator 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		final int WIDTH = 10;
		final int HEIGHT = 5;	
		final int SEED = 1;
		Random randGenTerrain = new Random(SEED);
		Random randGenHumidity = new Random(SEED);
				
		/**
		 * First loop is the height it only prints a new
		 * line character for each row after the row has
		 * printed in the second loop.
		 */
		double highElevationPoints = 0.0;
		double highHumidityPoints = 0.0;
		double standingWaterPoints = 0.0;
		
		for(int ii = 0; ii < HEIGHT; ii++)
		{
			for(int i = 0; i < WIDTH; i++)
			{
				// Pick a random number from the correct number of values
				int randTerrainValues = randGenTerrain.nextInt(29 - 0 + 1);
				int randTerrainNumber = randTerrainValues + 0;
				
				double randHumidityValues = randGenHumidity.nextDouble();
				double randHumidityNumber = randHumidityValues + 0;
				
				if(randTerrainNumber >= 18)
				{
					System.out.print("^");
					highElevationPoints++;
					if(randHumidityNumber > 0.4)
					{
						highHumidityPoints++;
					}
				}
				else
				{
					//standingWaterPoints++;
					if(randHumidityNumber < 0.4)
					{
						System.out.print("-");
						standingWaterPoints++;
					}
					else
					{
						System.out.print("~");
						highHumidityPoints++;
					}
				}
			}
			System.out.print("\n");
		}
		System.out.println(highElevationPoints);
		System.out.println(highHumidityPoints);
		System.out.println(standingWaterPoints);
		System.out.println("High Elevations: " 
				+ (highElevationPoints / (WIDTH * HEIGHT)) * 100 + "%");
		System.out.println("High Humidity: " 
				+ (highHumidityPoints / (WIDTH * HEIGHT)) * 100 + "%");
		System.out.println("Standing Water: " 
				+ (standingWaterPoints / (WIDTH * HEIGHT)) * 100 + "%");
	}

}
