public class MyObject implements MyObjectADT {

    private int id;
    private int width;
    private int height;
    private String type;
    private Location pos;
    private BNode root;

    public MyObject(int id, int width, int height, String type, Location pos) { // constructor that creates an empty binary search tree 
        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.pos = pos;
        root = new BNode();
    }
        
    public void setType(String type) { // sets the type of this object
        this.type = type;
    }

    public int getWidth() { // returns the width of this object
        return width;
    }

    public int getHeight() { // returns the height of this object
        return height;
    }

    public int getId() { // returns the id of this object
        return id;
    }

    public String getType() { // returns the type of this object
        return type;
    }

    public Location getLocus() { // returns the location of this object
        return pos;
    }

    public void setLocus(Location value) { // changes the location of this object to the given value
        pos = value;
    }

    public void addPel(Pel pix) throws DuplicatedKeyException { // inserts pix into the binary search tree associatied with this myObject. throws duplicatedKeyException if error occurs while inserting the pel object pix into the tree
        put(root, pix);

    }

    public boolean intersects(MyObject otherobject) { // returns true if this object intersects with the given otherobject. Returns false otherwise.
        if (this.getLocus().getX() + this.getWidth() < otherobject.getLocus().getX()) { // if this object is to the left of the other object, return false
            return false;
        }
        if (this.getLocus().getX() > otherobject.getLocus().getX() + otherobject.getWidth()) { // if this object is to the right of the other object, return false
            return false;
        }
        if (this.getLocus().getY() + this.getHeight() < otherobject.getLocus().getY()) { // if this object is above the other object, return false
            return false;
        }
        if (this.getLocus().getY() > otherobject.getLocus().getY() + otherobject.getHeight()) { // if this object is below the other object, return false
            return false;
        }
        return true; // if none of the above conditions are met, return true
    }

    private void put(BNode root2, Pel pix) {
    }

    
    
}
