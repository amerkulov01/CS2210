import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Andrei Merkulov
 * 251145994
 * a class for a hash table, this hash table stores the AI's possible moves
 *
 */
public class Dictionary implements DictionaryADT {

    private int table_s;	//initialising the size of the table
  
    private LinkedList<Record> hashT[];	//intitialising an array of linkedlists

    public Dictionary(int size) {
        this.table_s = size; //setting the length based on the size
        hashT = (LinkedList<Record>[]) new LinkedList[table_s];	//declaring what is going to be in hashT
        for (int i = 0; i < table_s; i++) {	//adding all the entries to our hashT
        	hashT[i] = new LinkedList<Record>();
        }
    }

    private int hashFunction(String key) {	//a private hash value using key
        int result = (int) key.charAt(key.length() - 1);
        for (int i = key.length() - 1; i >= 0; i--) {	// this is a loop which computes the boards hash value
        	result = (result * (143) + (int) key.charAt(i)) % table_s;	//choosing a prime number here
        }
        return result % table_s;
    }

    public int put(Record rec) throws DuplicatedKeyException {	//inserts the given Record object referenced by rec in the dictionary
        int i = hashFunction(rec.getKey());
        ListIterator<Record> list = hashT[i].listIterator();	//iterator to go through the whole list
        Record tempNode = null;	// tempNode to go through iterator
        if (!hashT[i].isEmpty()) {	//if the index at the hash table has a value, a collision occurs
            while (list.hasNext()) {	//loop to check and make sure there are more values in the list
            	tempNode = list.next();	//goes to next node
                if (tempNode.getKey().equals(rec.getKey()))
                    throw new DuplicatedKeyException(null);	//throws DuplicatedKeyException if the string stored in the object referenced by rec is already in dictionary
            }
            list.add(rec);
            return 1;	// since there was collision it returns 1
        } else {	//else, position is empty which mens no collisions occurred
        	hashT[i].add(rec);
            return 0;
        }
    }

    public void remove(String key) throws InexistentKeyException {	//removes Record object containing string key from the dictionary
        int pos = hashFunction(key);
        ListIterator<Record> list = hashT[pos].listIterator();	//same as above
        Record tempNode = null;
        if (!hashT[pos].isEmpty()) {	//if pair exists
            while (list.hasNext()) {	//loops if there are more items ahead
            	tempNode = list.next();	// next Node
                if (tempNode.getKey().equals(key)) {
                	list.remove();	//removing the entry
                    return;
                }
            }
        }
        throw new InexistentKeyException(key);	//throws InexistentKeyException if there is no Record object with the given value
    }

    public Record get(String key) {	//returns Record object stored in hash table containing key value
        int pos = hashFunction(key);
        ListIterator<Record> list = hashT[pos].listIterator();
        Record tempNode = null;
        if (!hashT[pos].isEmpty()) {	//if current position is not empty
            while (list.hasNext()) {	//loops through if there are more items ahead
            	tempNode = list.next();	// next Node
                if (tempNode.getKey().equals(key))	//does pair exist?
                    return tempNode;	//returns Record object
            }
        }
        return null;	// returns null if no matches in hash table
    }

    public int numRecords() {	// returns the number of Record objects stored in the hash table
        int num = 0;	//initialising num for number of spaces that contain entries
        for (int i = 0; i < table_s; i++) {	//loops through whole table
            num += hashT[i].size();	//returns number of Record objects
        }
        return num;
    }

}