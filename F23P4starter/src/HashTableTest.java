import student.TestCase;

/**
 * Test Hashtable Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-30-2023
 */
public class HashTableTest extends TestCase {

    private HashTable table;

    /**
     * SetUp Constructor
     */
    public void setUp() {
        table = new HashTable(10);
    }


    /**
     * Test Rehash
     */
    public void testRehash() {
        Record temp1 = new Record();
        Record temp2 = new Record();
        Record temp3 = new Record();
        Record temp4 = new Record();
        Record temp5 = new Record();
        Record temp6 = new Record();
        temp1.setKey("Song1");
        temp2.setKey("Song2");
        temp3.setKey("Song3");
        temp4.setKey("Song4");
        temp5.setKey("Song5");
        temp6.setKey("Song6");
        table.insert("song", temp1);
        table.insert("song", temp2);
        table.insert("song", temp3);
        table.insert("song", temp4);
        table.insert("song", temp5);
        table.insert("song", temp6);
        assertEquals(20, table.getSize());
        assertEquals(6, table.getRecordCount());
    }
}
