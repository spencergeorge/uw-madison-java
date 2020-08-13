import java.util.ArrayList;
import java.util.Collections;

public class PlaceList 
{
	private ArrayList<Place> placeList = new ArrayList<>();
	
	public void add(Place place) 
	{
		placeList.add(place);
	}
	
	public void remove(int index)
	{
		if(placeList.get(index) == Place.currentPlace)
		{
			Place.currentPlace = null;
		}
		
		placeList.remove(index);
	}
	
	public Place get(int index)
	{
		return placeList.get(index);
	}
	
	public boolean exists(String name, String address)
	{
		for(int i = 0; i < placeList.size(); i++)
		{
			if(placeList.get(i).equals(name, address))
				return true;
		}
		return false;
	}
	
	public int size()
	{
		return placeList.size();
	}
	
	public String toString()
	{
		return placeList.toString();
	}
	
	public void sort()
	{
		Collections.sort(placeList);
	}
}
