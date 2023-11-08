import student.TestCase;

public class RecordTest extends TestCase {

    private Record record;

    public void setUp() {
        record = new Record();
    }


    public void testKey() {
        record.setKey("10");
        assertEquals(record.getKey(), "10");
    }


    public void testId() {
        record.setIndex(10);
        assertEquals(record.getIndex(), 10);
    }
    
    public void testValue() {
        record.setValue("10");
        assertEquals(record.getValue(), "10");
    }
}
