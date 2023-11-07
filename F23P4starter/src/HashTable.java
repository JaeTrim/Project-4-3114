
public class HashTable {

    private int hashSize;
    private int recordCount;
    private Record[] hashTable;

    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        hashTable = new Record[hashSize];
        recordCount = 0;
    }


    public int getRecordCount() {
        return recordCount;
    }


    public Record[] getArr() {
        return hashTable;
    }


    public int getSize() {
        return hashSize;
    }


    public void insert(String recordType, Record record) {
        if (recordCount >= (hashSize / 2)) {
            rehash();
            System.out.println(recordType + " hash table size doubled");
        }
        int index = Hash.h(record.getKey(), hashSize);
        while ((hashTable[index] != null) && (hashTable[index]
            .getIndex() != -1)) {
            index = Hash.h(record.getKey(), hashSize);
        }
        record.setIndex(index);
        // record.setKey(key);
        hashTable[record.getIndex()] = record;
        recordCount++;

    }


    public void delete(String title) {
        int found = this.search(title);

        if (found != -1) {
            Record temp = new Record();
            temp.setKey("tombstone");
            temp.setIndex(found);
            hashTable[found] = temp;
            System.out.println("Record with ID " + title
                + " successfully deleted from the database");
            recordCount--;
        }
        else {
            System.out.println("Delete FAILED -- There is no record with ID "
                + title);
        }
    }


    public int search(String title) {
        int found = -1;
        int index = Hash.h(title, hashSize);
        while (hashTable[index] != null) {
            if (hashTable[index].getKey().equals(title)) {
                found = index;
                return found;
            }
            else {
                index = index + Hash.h(title, hashSize);
            }
        }
        return found;
    }


    private void rehash() {
        hashSize = hashSize * 2;
        HashTable updatedHashTable = new HashTable(hashSize);

        for (int i = 0; i < hashTable.length; i++) {
            if ((hashTable[i] != null) && !(hashTable[i].getKey().equals(
                "tombstone"))) {
                // String tempId = hashTable[i].getKey();
                updatedHashTable.insert("", hashTable[i]);
            }
        }
        hashTable = updatedHashTable.getArr();
    }
}
