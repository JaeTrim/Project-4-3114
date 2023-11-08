
/**
 * HashTable class deals with the hash table that stores records of a song or
 * artist and a node
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-07-2023
 */
public class HashTable {

    private int hashSize;
    private int recordCount;
    private Record[] hashTable;

    /**
     * HashTable Constructor
     * 
     * @param hashSize
     *            is the initial hash size
     */
    public HashTable(int hashSize) {
        this.hashSize = hashSize;
        hashTable = new Record[hashSize];
        recordCount = 0;
    }


    /**
     * Gets the amount of records in the table
     * 
     * @return the recordCount
     */
    public int getRecordCount() {
        return recordCount;
    }


    /**
     * Returns the hash table as an array of records
     * 
     * @return the hashtable
     */
    public Record[] getArr() {
        return hashTable;
    }


    /**
     * Gets the hashSize
     * 
     * @return the hashSize
     */
    public int getSize() {
        return hashSize;
    }


    /**
     * Inserts into the hash table
     * 
     * @param recordType
     *            is whether it is a song or an artist for sake of printing
     * @param record
     *            is the record that is passed containing the key and value
     */
    public void insert(String recordType, Record record) {
        if (recordCount >= (hashSize / 2)) {
            rehash();
            System.out.println(recordType + " hash table size doubled");
        }
        int index = Hash.h(record.getKey(), hashSize);
        int step = 1;
        while ((hashTable[index] != null) && (hashTable[index]
            .getIndex() != -1)) {
            index = index + (step * step);
            step++;
        }
        record.setIndex(index);
        // record.setKey(key);
        hashTable[record.getIndex()] = record;
        recordCount++;

    }


    /**
     * Deletes from the hash table
     * 
     * @param recordType
     *            is whether it is a song or an artist for sake of printing
     * @param title
     *            is the name of the artist or song
     */
    public void delete(String removeType, String title) {
        int found = this.search(title);
        if (found != -1) {
            Record temp = new Record();
            temp.setKey("TOMBSTONE");
            temp.setIndex(found);
            hashTable[found] = temp;
            System.out.println("|" + title + "|" + " is removed from the "
                + removeType + " database.");
            recordCount--;
        }
        else {
            System.out.println("|" + title + "|" + " does not exist in the "
                + removeType + " database.");
        }

    }


    /**
     * Searches for a song or an artist in the table
     * 
     * @param title
     *            is the name of the artist or song
     * @return whether or not the title is found and returns -1 or the index
     */
    public int search(String title) {
        int found = -1;
        int index = Hash.h(title, hashSize);
        int step = 1;
        while (hashTable[index] != null) {
            if (hashTable[index].getKey().equals(title)) {
                found = index;
                return found;
            }
            else {
                index = index + (step * step);
                step++;
            }
        }
        return found;
    }


    /**
     * Resizes the hash table
     */
    private void rehash() {
        hashSize = hashSize * 2;
        HashTable updatedHashTable = new HashTable(hashSize);

        for (int i = 0; i < hashTable.length; i++) {
            if ((hashTable[i] != null) && !(hashTable[i].getKey().equals(
                "TOMBSTONE"))) {
                // String tempId = hashTable[i].getKey();
                updatedHashTable.insert("", hashTable[i]);
            }
        }
        hashTable = updatedHashTable.getArr();
    }
}
