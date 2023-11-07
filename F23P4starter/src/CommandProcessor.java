import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Command Processor Class that reads the input file
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
                String typeName = "";
                for (int i = 2; i < removeLine.length; i++) {
                    typeName += removeLine[i] + " ";
                }
                typeName = typeName.trim();

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
            else if (currentLine.contains("print")) {
                String[] printLine = currentLine.split("\\s+");
                String typeName = printLine[1];
                world.print(typeName);
            }
        }
        scanner.close();
    }

}
