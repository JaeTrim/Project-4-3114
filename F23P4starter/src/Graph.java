/**
 * Graph class that creates and manages the graph
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-08-2023
 */
public class Graph {

    private DLList<Node>[] list;
    private int numOfNodes;

    @SuppressWarnings("unchecked")
    public Graph(int hashSize) {
        list = new DLList[hashSize];
        numOfNodes = 0;

    }
    
    public int getNumOfNodes() {
        return numOfNodes;
    }


    public void addNode(Node node) {
        DLList<Node> temp = new DLList<Node>();
        temp.add(node);
        list[numOfNodes] = temp;
        numOfNodes++;
    }


    public void addEdge(int start, int end) {
        DLList<Node> temp = list[start];
        Node destNode = list[end].get(0);
        temp.add(destNode);
    }


    public boolean hasEdge(int start, int end) {
        DLList<Node> temp = list[start];
        Node destNode = list[end].get(0);
        for (int i = 0; i < temp.size(); i++) {
            Node node = temp.get(i);
            if (node == destNode) {
                return true;
            }
        }
        return false;
    }


    public void removeEdge() {

    }


    public void removeNode() {

    }


    public void expand() {

    }


    public void print() {

    }


    public void union() {

    }


    public void connectedComponent() {

    }


    public void diameter() {

    }
}
