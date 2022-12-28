/**
 * @author Andrei Merkulov
 * 251145994
 *represents the records that will be stored in the hash table
 *
 */
public class Record {
	
	private String key;
	private int score;
	private int level;
	
	public Record(String key, int score, int level) {	//constructor for the class
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
		public String getKey() {	//returns the string stored in this record object
			return key;
	}
	
	public int getScore() {		//returns first integer stored in this record object
		return score;
	}
	
	public int getLevel() {		//returns second integer stored in this record object
		return level;
	}
}
