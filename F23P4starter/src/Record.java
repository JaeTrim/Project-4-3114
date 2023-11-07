
public class Record {

    // the key is a string of the artist or song
    // the value is the corresponding node in the graph

    // Temporarily String for value

    private String key;
    private String value;

    public Record() {
        key = null;
        value = null;
    }


    public String getKey() {
        return key;
    }


    public void setKey(String newKey) {
        key = newKey;
    }


    public String getValue() {
        return value;
    }


    public void setSong(String newValue) {
        value = newValue;
    }
}
