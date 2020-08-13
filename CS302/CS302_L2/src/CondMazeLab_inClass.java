
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
		if(!puss.isFacingWall() && !puss.isFacingDog() && !puss.isFacingGully() && !puss.isFacingMud())
		{
			puss.forward();
		}
		else if(puss.isFacingDog() || puss.isFacingGully() || puss.isFacingMud())
		{
			puss.right();
		}
		else if(puss.isFacingWall())
		{
			puss.right();
			if(puss.isFacingWall())
			{
				puss.left();
				puss.left();
			}
		}

		//Task 1-3
		/*if(puss.isFacingWall())
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
		else if(puss.isFacingMud())
		{
			puss.putOnBoots();
			puss.forward();
		}
		else if(puss.isFacingDog())
		{
			puss.startTipToe();
			puss.forward();
		}
		else
		{
			puss.forward();
			if(puss.isInBoots())
			{
				puss.takeOffBoots();
			}
			else if(puss.isTipToeing())
			{
				puss.stopTipToe();
			}
		}*/
	}		
	
	public CondMazeLab() { super(true); }
}
