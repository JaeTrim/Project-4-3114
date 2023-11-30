import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Command Processor Class that reads the input file
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-07-2023
 */
public class CommandProcessorTest extends TestCase {

    /**
     * setUp Constructor 
     */
    public void setUp() throws FileNotFoundException {
        int hash = 10;
        String file = "P4sampleInput.txt";
        new CommandProcessor(hash, file);
    }

    /**
     * test the reader
     * @throws Exception
     */
    public void testReader() throws Exception {
        String output = "|Drake| is added to the Artist database.\r\n"
            + "|Fair Trade| is added to the Song database.\r\n"
            + "|Hours| is added to the Song database.\r\n"
            + "|One Dance| is added to the Song database.\r\n"
            + "|Trav| is added to the Artist database.\r\n"
            + "|Mafia| is added to the Song database.\r\n"
            + "|IKNOW?| is added to the Song database.\r\n"
            + "Song hash table size doubled.\r\n"
            + "|God Country| is added to the Song database.\r\n"
            + "|Kanye| is added to the Artist database.\r\n"
            + "|Moon| is added to the Song database.\r\n"
            + "|Feel No Ways| is added to the Song database.\r\n"
            + "|Keem| is added to the Artist database.\r\n"
            + "|16| is added to the Song database.\r\n"
            + "|Kendrick| is added to the Artist database.\r\n"
            + "|Range Brothers| is added to the Song database.\r\n"
            + "Song hash table size doubled.\r\n"
            + "|Father Time| is added to the Song database.\r\n"
            + "Artist hash table size doubled.\r\n"
            + "|Future| is added to the Artist database.\r\n"
            + "|Hard to Choose One| is added to the Song database.\r\n"
            + "|Telekenesis| is added to the Song database.\r\n"
            + "|SZA| is added to the Artist database.\r\n"
            + "|Broken Clocks| is added to the Song database.\r\n"
            + "|KenCarson| is added to the Artist database.\r\n"
            + "|ineedu| is added to the Song database.\r\n"
            + "|Uzi| is added to the Artist database.\r\n"
            + "|Lifes Good| is added to the Song database.\r\n"
            + "|20 Min| is added to the Song database.\r\n"
            + "|Taylor| is added to the Artist database.\r\n"
            + "|Flowers| is added to the Song database.\r\n"
            + "Artist hash table size doubled.\r\n"
            + "|Adele| is added to the Artist database.\r\n"
            + "|Skyfall| is added to the Song database.\r\n"
            + "|KEYGLOCK| is added to the Artist database.\r\n"
            + "|GLOCKOMA| is added to the Song database.\r\n"
            + "|Metro| is added to the Artist database.\r\n"
            + "Song hash table size doubled.\r\n"
            + "|Beats| is added to the Song database.\r\n"
            + "|Kenny| is added to the Artist database.\r\n"
            + "|Beats 2| is added to the Song database.\r\n"
            + "|Pierre| is added to the Artist database.\r\n"
            + "|Beats 3| is added to the Song database.\r\n"
            + "|fly| is added to the Song database.\r\n"
            + "|Telekenesis| is removed from the Song database.\r\n"
            + "|20 Min| is removed from the Song database.\r\n"
            + "|Mafia| is removed from the Song database.\r\n"
            + "|Drake| is removed from the Artist database.\r\n"
            + "|Hard to Choose One| is removed from the Song database.\r\n"
            + "|Adele| is removed from the Artist database.\r\n"
            + "|God Country| is removed from the Song database.\r\n"
            + "|Kenny| is removed from the Artist database.\r\n"
            + "There are 5 connected components\r\n"
            + "The largest connected component has 13 elements\r\n"
            + "The diameter of the largest component is 9\r\n" + "";
        assertFuzzyEquals(systemOut().getHistory(), output);
    }
}
