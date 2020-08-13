import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Place implements Comparable<Place>
{
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private final String URI_BASE = "www.google.com/maps/place/";;
	private final String URL_PROTOCOL = "https://";
	private String url;
	private String addressEncoded;

	// int range between 3 to 21
	// For URL a lowercase z is appended.
	private int mapZoom = 17;
	private static final int MAP_ZOOM_MAX = 21;
	private static final int MAP_ZOOM_MIN = 3;
	private static final String CHARSET = "UTF-8";
	public static Place currentPlace;

	
	public Place(String name, String address, String latitude, String longitude)
			throws UnsupportedEncodingException
	{
		this.name = name;
		this.address = address;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		
		addressEncoded = URLEncoder.encode(address, CHARSET);
		generateUrl();
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(String address) throws UnsupportedEncodingException
	{
		this.address = address;
		
		addressEncoded = URLEncoder.encode(address, CHARSET);
		generateUrl();
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
		
		generateUrl();
	}
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
		
		generateUrl();
	}
	
	public boolean equals(String name, String address)
	{
		if(this.name.equals(name) 
				&& this.address.equals(address))
			return true;
		
		return false;
	}
	
	public boolean equals(Place other)
	{
		if(this == other)
			return true;
		else if(this.name.equals(other.name) 
				&& this.address.equals(other.address))
			return true;
		
		return false;
	}
	
	private String buildString(String seperator)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(name);
		sb.append(seperator);
		sb.append(address);
		sb.append(seperator);
		sb.append(latitude);
		sb.append(',');
		sb.append(longitude);
		
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(buildString("\n"));
		sb.append("\n");
		sb.append(url);
		
		return sb.toString(); 
	}
	
	public String toLine()
	{
		return buildString(";");
	}
	
	private void generateUrl()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(URL_PROTOCOL);
		sb.append(URI_BASE);
		sb.append(addressEncoded);
		sb.append("/@");
		sb.append(latitude);
		sb.append(',');
		sb.append(longitude);
		sb.append(',');
		sb.append(mapZoom);
		sb.append('z');
		
		url = sb.toString();
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setMapZoom(int mapZoom)
	{
		if(mapZoom >= MAP_ZOOM_MIN && mapZoom <= MAP_ZOOM_MAX)
			this.mapZoom = mapZoom;
	}
	
	public int getMapZoom()
	{
		return mapZoom;
	}
	
	public double getDistance()
	{
		return Geocoding.distance(latitude, longitude, 
				currentPlace.getLatitude(), currentPlace.getLongitude());
	}
	
	public int compareTo(Place otherPlace)
	{
		if(currentPlace == null)
			return this.name.compareTo(otherPlace.getName());
		
		Double distance = getDistance();
		return distance.compareTo(otherPlace.getDistance());
	}
	
}
