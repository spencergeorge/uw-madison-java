import java.util.Random;

public class IntelligenceExplorer {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		final int WIDTH = 10;
		final int HEIGHT = 5;	
		
		/**
		 *	Used to determine planet capable of sustaining
		 *  with intelligent life in percentages 
		 */
		final double PLANET_ELEVATION = 0.2;
		final double PLANET_WATER_LOW = 0.4;
		final double PLANET_WATER_HIGH = 0.6;
		final double PLANET_HUMIDITY = 0.7;
		
		System.out.println("Planets cabable of life: ");
		
		for(int seed = 0; seed < 100000; seed++)
		{
			Random randGenTerrain = new Random(seed);
			Random randGenHumidity = new Random(seed);
					
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
					//	System.out.print("^");
						highElevationPoints++;
						if(randHumidityNumber > 0.4)
						{
							highHumidityPoints++;
						}
					}
					else
					{
						if(randHumidityNumber < 0.4)
						{
							//System.out.print("-");
							standingWaterPoints++;
						}
						else
						{
							//System.out.print("~");
							highHumidityPoints++;
						}
					}
				}
			}
			double elevationPercent = highElevationPoints / (WIDTH * HEIGHT);
			double humidityPercent = highHumidityPoints / (WIDTH * HEIGHT);
			double waterPercent = standingWaterPoints / (WIDTH * HEIGHT);
			
			//System.out.println("\nElevation: " + elevationPercent);
			//System.out.println("Humidity: " + humidityPercent);
			//System.out.println(PLANET_HUMIDITY);
			//System.out.println("Water: " + waterPercent);
			if(//elevationPercent < PLANET_ELEVATION  && 
					//humidityPercent > PLANET_HUMIDITY  &&
					(waterPercent > PLANET_WATER_LOW 
							&& waterPercent < PLANET_WATER_HIGH))
			{
				System.out.println(seed);
			}
			else
			{
				//System.out.print("out");
			}
		}
	}

}
