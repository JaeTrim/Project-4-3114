
public class HashTable {

    private int hashSize;
    public Record[] table;

    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        table = new Record[hashSize];
    }


    public Record[] getArr() {
        return table;
    }


    public int getSize() {
        return hashSize;
    }
}
