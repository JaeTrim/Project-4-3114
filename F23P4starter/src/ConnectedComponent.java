/**
 * Connected Component Class that is used to set and get the number of elements
 * and connected components
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-30-2023
 */
public class ConnectedComponent {

    private int numOfConnectedComponents;
    private int numOfElements;
    private int diameter;

    /**
     * Connected Component Constructor
     */
    public ConnectedComponent() {
        numOfConnectedComponents = 0;
        numOfElements = 0;
        diameter = 0;
    }


    /**
     * Get the number of connected components
     * 
     * @return the number of connected components
     */
    public int getConnectedComponents() {
        return numOfConnectedComponents;
    }


    /**
     * Gets the number of elements
     * 
     * @return the number of elements
     */
    public int getElements() {
        return numOfElements;
    }


    /**
     * Sets the amount of connected components to a new value
     * 
     * @param c
     *            is the new amount of connected componenets
     */
    public void setConnectedComponents(int c) {
        numOfConnectedComponents = c;
    }


    /**
     * Sets the amount of elements to a new value
     * 
     * @param c
     *            is the new amount of elements
     */
    public void setElements(int c) {

        numOfElements = c;
    }


    /**
     * Sets the diameter to a new value
     * 
     * @param d
     *            is the new diameter value
     */
    public void setDiameter(int d) {
        diameter = d;
    }


    /**
     * Gets the diameter value
     * 
     * @return the diameter value
     */
    public int getDiameter() {
        return diameter;
    }
}
