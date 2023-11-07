
public class HashTable {

    private int hashSize;
    private int recordCount;
    public Record[] table;

    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        table = new Record[hashSize];
        recordCount = 0;z
    }


    public int getRecordCount() {
        return recordCount;
    }


    public Record[] getArr() {
        return table;
    }


    public int getSize() {
        return hashSize;
    }


    public void insert(Record record) {
        if (recordCount >= (hashSize / 2)) {
            rehash();
            System.out.println()
        }
    }


    public void delete(String title) {

    }


    public void search(String title) {

    }

}
