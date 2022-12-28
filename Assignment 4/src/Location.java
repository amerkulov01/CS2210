
public class Location {
	
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { // returns the x coordinate of this location
		return x;
	}

	public int getY() { // returns the y coordinate of this location
		return y;
	}

	public int compareTo(Location p) {
		if (this.x == p.getX() && this.y == p.getY()) return 0;
		else if ((this.x > p.getX() || this.x == p.getX()) && this.y > p.getY()) return 1;
		else if ((this.x < p.getX() || this.x == p.getX()) && this.y < p.getY()) return -1;
		return 2;
	}
	
}
