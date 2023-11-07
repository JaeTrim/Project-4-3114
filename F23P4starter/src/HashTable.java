
public class HashTable {

    private int hashSize;
    private int recordCount;
    public Record[] table;

    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        table = new Record[hashSize];
        recordCount = 0;
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
        if (seminarCount >= (hashSize / 2)) {
            rehash();
            System.out.println("Hash table expanded to " + hashSize
                + " records");
        }
        record.setId(id);
        int key = keyFinder1(id);
        while ((hashTable[key] != null) && (hashTable[key].getId() != -1)) {
            key = keyFinder2(id, key);
        }
        record.setKey(key);
        hashTable[record.getKey()] = record;
        seminarCount++;
        }


    public void delete(String title) {
        int found = this.search(id);

        if (found != -1) {
            Record temp = new Record();
            temp.setId(-1);
            temp.setKey(found);
            hashTable[found] = temp;
            System.out.println("Record with ID " + id
                + " successfully deleted from the database");
            seminarCount--;
        }
        else {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
        }
    }


    public void search(String title) {
        int found = -1;
        int key = keyFinder1(id);
        while (hashTable[key] != null) {
            if (hashTable[key].getId() == id) {
                found = key;
                return found;
            }
            else {
                key = keyFinder2(id, key);
            }
        }
        return found;

    }
    
    private void rehash() {
        hashSize = hashSize * 2;
        HashTable updatedHashTable = new HashTable(hashSize);

        for (int i = 0; i < hashTable.length; i++) {
            if ((hashTable[i] != null) && (hashTable[i].getId() != -1)) {
                int tempId = hashTable[i].getId();
                updatedHashTable.insert(tempId, hashTable[i]);
            }
        }
        hashTable = updatedHashTable.getArr();
    }

}
