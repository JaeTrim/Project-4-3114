import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Command Processor Class that reads the input file
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-07-2023
 */
public class CommandProcessor {

    private World world;

    /**
     * Command Processor Constructor
     * 
     * @param hashSize
     *            is the inital hash size for the hashtable
     * @param file
     *            is the input file
     * @throws FileNotFoundException
     */
    public CommandProcessor(int hashSize, String file)
        throws FileNotFoundException {
        world = new World(hashSize, file);
        readFile(file);
    }


    /**
     * readFile method that reads the input file and stores data and sends it to
     * the world
     * 
     * @param name
     *            is the name of the input file
     * @throws FileNotFoundException
     */
    private void readFile(String name) throws FileNotFoundException {
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.contains("remove")) {
                String[] removeLine = currentLine.split("\\s+");
                String removeType = removeLine[1];
                String typeName = null;
                if (removeType.equals("song")) {
                    typeName = currentLine.substring(12, currentLine.length());
                }
                else {
                    typeName = currentLine.substring(14, currentLine.length());
                }

                world.remove(removeType, typeName);
            }
            else if (currentLine.contains("insert")) {
                currentLine = currentLine.substring("insert".length()).trim();
                Scanner tempScanner = new Scanner(currentLine).useDelimiter(
                    "<SEP>");
                String insertType = tempScanner.next().trim();
                String typeName = tempScanner.next().trim();
                world.insert(insertType, typeName);
                tempScanner.close();
            }
            else {
                String[] printLine = currentLine.split("\\s+");
                String typeName = printLine[1];
                world.print(typeName);
            }
        }
        scanner.close();
    }

}
