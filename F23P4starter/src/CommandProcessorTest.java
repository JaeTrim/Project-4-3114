import java.io.FileNotFoundException;
import student.TestCase;

public class CommandProcessorTest extends TestCase {

    public void setUp() throws FileNotFoundException {
        int hash = 10;
        String file = "P4sampleInput.txt";
        new CommandProcessor(hash, file);
    }


    public void testReader() throws Exception {
        String output =
            "|When Summer's Through| does not exist in the Song database.\r\n"
                + "total songs: 0\r\n" + "total artists: 0\r\n"
                + "|Blind Lemon Jefferson| is added to the Artist database.\r\n"
                + "|Long Lonesome Blues| is added to the Song database.\r\n"
                + "|Long   Lonesome Blues| is added to the Song database.\r\n"
                + "|long Lonesome Blues| is added to the Song database.\r\n"
                + "|Ma Rainey| is added to the Artist database.\r\n"
                + "|Ma Rainey's Black Bottom| is added to the Song database.\r\n"
                + "|Mississippi Boweavil Blues| is added to the Song database.\r\n"
                + "Song hash table size doubled\r\n"
                + "|Fixin' To Die Blues| is added to the Song database.\r\n"
                + "0: |Blind Lemon Jefferson|\r\n" + "7: |Ma Rainey|\r\n"
                + "total artists: 2\r\n" + "1: |Fixin' To Die Blues|\r\n"
                + "2: |Mississippi Boweavil Blues|\r\n"
                + "7: |long Lonesome Blues|\r\n"
                + "15: |Long Lonesome Blues|\r\n"
                + "16: |Ma Rainey's Black Bottom|\r\n"
                + "19: |Long   Lonesome Blues|\r\n" + "total songs: 6\r\n"
                + "|Sleepy| does not exist in the Song database.\r\n"
                + "|ma rainey| does not exist in the Artist database.\r\n"
                + "|Ma Rainey| is removed from the Artist database.\r\n"
                + "0: |Blind Lemon Jefferson|\r\n" + "7: TOMBSTONE\r\n"
                + "total artists: 1\r\n" + "";
        assertFuzzyEquals(systemOut().getHistory(), output);
    }
}
