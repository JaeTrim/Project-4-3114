import student.TestCase;

/**
 * Record test class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-30-2023
 */
public class RecordTest extends TestCase {

    private Record record;

    /**
     * setUp Constructor
     */
    public void setUp() {
        record = new Record();
    }


    /**
     * testKey method
     */
    public void testKey() {
        record.setKey("10");
        assertEquals(record.getKey(), "10");
    }


    /**
     * testId method
     */
    public void testId() {
        record.setIndex(10);
        assertEquals(record.getIndex(), 10);
    }


    /**
     * testId method
     */
    public void testValue() {
        Node temp = new Node();
        record.setValue(temp);
        assertEquals(record.getValue(), temp);
    }
}
