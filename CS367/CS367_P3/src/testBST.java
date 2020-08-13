import java.util.Iterator;

public class testBST {

	public static void main(String[] args) 
	{

		ArrayHeap heap = new ArrayHeap(10);		
		
		BSTDictionary<Integer> myBST = new BSTDictionary<Integer>();
    	
    	try 
    	{
			myBST.insert(10);
			myBST.insert(9);
			myBST.insert(12);
			myBST.insert(11);
			myBST.insert(13);
			
		} 
    	catch (DuplicateException e) 
    	{
			// TODO Auto-generated catch block
			//e.printStackTrace();
    		System.out.println("Duplicate Item Exception");
		}
    	finally
    	{
    	
    		System.out.println("TPL: " + myBST.totalPathLength());
    		System.out.println("Size: " + myBST.size());
    		System.out.println("Lkup: " + myBST.lookup(12));
    	}
    	
    	Iterator<Integer> myIterator = myBST.iterator();
    	while(myIterator.hasNext())
    	{
    		System.out.println(myIterator.next());
    	}
    	
    	myBST.delete(9);
    	System.out.println(" ");
    	
    	myIterator = myBST.iterator();
    	while(myIterator.hasNext())
    	{
    		System.out.println(myIterator.next());
    	}
	}

}
