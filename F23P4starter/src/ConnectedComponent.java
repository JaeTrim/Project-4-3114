import java.util.Arrays;

public class ConnectedComponent {

    private int numOfConnectedComponents;
    private int numOfElements;
    private int diameter;

    public ConnectedComponent() {
        numOfConnectedComponents = 0;
        numOfElements = 0;
        diameter = 0;
    }


    public int getConnectedComponents() {
        return numOfConnectedComponents;
    }


    public int getElements() {
        return numOfElements;
    }


    public void setConnectedComponents(int c) {
        numOfConnectedComponents = c;
    }


    public void setElements(int c) {

        numOfElements = c;
    }
    
    
    public void setDiameter(int d)
    {
        diameter = d;
    }
    
    public int getDiameter()
    {
        return diameter;
    }
}
