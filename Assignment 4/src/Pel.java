
public class Pel {
	
	private int color;
	private Location p;
	
	public Pel(Location p, int color) { 
		this.p = p;
		this.color = color;
	}
	
	public Location getLocus() { // returns the location of this pel
		return p;
	}
	
	public int getColor() { // returns the color of this pel
		return color;
	}
}
