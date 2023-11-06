
public class Record {

    // the key is a string of the artist or song
    // the value is the corresponding node in the graph

    private String key;
    private Node value;

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


    public Node getValue() {
        return value;
    }


    public void setSong(Node newValue) {
        value = newValue;
    }
}
