
public class Record {

    // the key is a string of the artist or song
    // the value is the corresponding node in the graph

    // Temporarily String for value

    private String key;
    private int index;
    private String value;

    public Record() {
        key = null;
        index = -1;
        value = null;
    }


    public String getKey() {
        return key;
    }


    public void setKey(String newKey) {
        key = newKey;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public void setIndex(int i)
    {
        index = i;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String newValue) {
        value = newValue;
    }
}
