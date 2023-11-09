
/**
 * Record class is a record that holds a key for the name of song or artist, an
 * index of its position, as well as a value for the Node in the graph
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-07-2023
 */
public class Record {

    // the key is a string of the artist or song
    // the value is the corresponding node in the graph

    // Temporarily String for value

    private String key;
    private int index;
    private Node value;

    /**
     * Record constructor
     */
    public Record() {
        key = null;
        index = -1;
        value = null;
    }


    /**
     * Gets the key
     * 
     * @return the key
     */
    public String getKey() {
        return key;
    }


    /**
     * Sets the key to a new key value
     * 
     * @param newKey
     *            is new value
     */
    public void setKey(String newKey) {
        key = newKey;
    }


    /**
     * Gets the index
     * 
     * @return the index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Sets the index to a new index
     * 
     * @param i
     *            is new index
     */
    public void setIndex(int i) {
        index = i;
    }


    /**
     * Gets the value
     * 
     * @return the value
     */
    public Node getValue() {
        return value;
    }


    /**
     * Sets the value to a new value
     * 
     * @param newValue
     *            is new value
     */
    public void setValue(Node newValue) {
        value = newValue;
    }
}
