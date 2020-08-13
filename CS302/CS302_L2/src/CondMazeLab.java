
public class CondMazeLab extends Maze 
{

	public static void main(String[] args) 
	{ 
		// Step 1: Debugging BEGINS HERE //////////////////////////////////////
		int ageOfAlice = 23;
		int ageOfRob = 17;
		if(ageOfAlice > ageOfRob);
		{
			System.out.println("Alice is older than Rob.");
		}		
		// Step 1: Debugging ENDS HERE ////////////////////////////////////////

		
		// Step 1: Run Maze Simulation
		CondMazeLab myMaze = new CondMazeLab();

	}
	
	public void step() 
	{			
		// Steps 2 and 3: Implement HERE
		if(puss.isFacingWall())
		{
			puss.right();
			if(puss.isFacingWall())
			{
				puss.left();
				puss.left();
			}
		}
		else if(puss.isFacingGully())
		{
			puss.jump();
		}
		else if(puss.isFacingDog())
		{
			puss.startTipToe();
			if(!puss.isFacingDog())
			{
				puss.stopTipToe();
			}
		}
		else if(puss.isFacingMud())
		{
			puss.putOnBoots();
			puss.forward();
			if(!puss.isFacingMud())
			{
				puss.takeOffBoots();
			}
		}
		else
		{
			puss.forward();
		}
		
	}		
	
	public CondMazeLab() { super(true); }
}
