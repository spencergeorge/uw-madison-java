public class Quilt 
{

    public static void main(String[] args) 
    {

        /*TODO: Task 1 */
	    char [][] myBlock = { 	{'x', '.', '.', '.', '.'},
				                {'x', '+', '.', '.', '.'},
				                {'x', '+', '+', '.', '.'},
				                {'x', 'x', 'x', 'x', 'x'} };
        char [][] myQuilt = new char[myBlock.length * 3][myBlock[0].length * 4];
	    //char [][] myQuilt = new char[(myBlock.length * 3) + 2]
        //		[(myBlock[0].length * 4) + 9]; 

        for(int row = 0; row < myQuilt.length; row++)
        {
        	for(int column = 0; column < myQuilt[row].length; column++)
        	{
        		//myQuilt[row][column] = '?';
        		
        		//if(row == 4 || row == 9)
        		/*if(row == myBlock.length || row == myBlock.length * 2 + 1)
        		{
        			myQuilt[row][column] = '-';
        		}
        		else
        		{
        			if(column == 5 || column == 7 || column == 13 
        					|| column == 15 || column == 21 || column == 23)
        			{
        				myQuilt[row][column] = ' ';
        			}
        			else if(column == 6 || column == 14 || column == 22)
        			{
        				myQuilt[row][column] = '|';
        			}
        			else
        			{
        				myQuilt[row][column] = '?';
        			}
        		}*/
        	}
        }
        
        createQuilt(myQuilt,myBlock);
        displayPattern(myQuilt);
    }

    public static void displayPattern(char[][] myArray) 
    {
        /*TODO Task 2 */
    	
    	for(int row = 0; row < myArray.length; row++)
    	{
    		for(int column = 0; column < myArray[row].length; column++)
    		{
    			System.out.print(myArray[row][column]);
    		}
    		System.out.print("\n");
    	}
    }  

    public static void createQuilt(char[][] quilt, char[][] block) 
    {
        /*TODO Tasks 4, 5, and 6 */
    	boolean doFlip = false;
    	for(int qRow = 0; qRow < quilt.length; qRow += 4)
    	{
    		for(int qColumn = 0; qColumn < quilt[qRow].length; qColumn += 5)
    		{
    			if(!doFlip)
				{
    				// Non flipped
    				placeBlock(quilt, block, qRow, qColumn);
    				doFlip = true;
				}
    			else
				{
    				// Flipped
					placeBlock(quilt, (createFlipped(block)), qRow, qColumn);
					doFlip = false;
				}
      		}
    	}
    }

    /**
     * Places the 2-D character array block into the 2-D character array quilt
     * starting with the upper left hand corner of the block placed into 
     * position in the quilt at startRow, startCol
     *
     * Note: This is implemented for you, DO NOT CHANGE THIS METHOD.
     */
    public static void placeBlock(char[][] quilt, char[][] block,
            int startRow, int startCol){
        for (int r = 0; r < block.length; r++) {
            for (int c = 0; c < block[r].length; c++) {
                quilt[r+startRow][c+startCol] = block[r][c];
            }
        }
    }

    /**
     * Returns a 2-D array which is the horizontally flipped version
     * of the block parameter.
     */
    public static char[][] createFlipped(char[][] block) 
    {
        /*TODO Task 5 */
        char [][] flippedBlock = new char[block.length][block[0].length];
    	
        int blockIndex = block.length - 1;
        for(int i = 0; i < block.length; i++)
    	{
        	flippedBlock[i] = block[blockIndex];
        	blockIndex--;
    	}
    	
    	return flippedBlock;
    }
}
