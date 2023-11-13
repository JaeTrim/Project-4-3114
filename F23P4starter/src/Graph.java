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
    private int[] nodeArray;
    private int size;

    @SuppressWarnings("unchecked")
    public Graph(int hashSize) {
        size = hashSize;
        list = new DLList[hashSize];
        numOfNodes = 0;
        nodeArray = new int[hashSize];
        for (int i = 0; i < hashSize; i++) {
            nodeArray[i] = -1;
        }

    }


    public int getNumOfNodes() {
        return numOfNodes;
    }


    public void addNode(Node node) {
        if (numOfNodes == list.length) {
            size = size * 2;
            @SuppressWarnings("unchecked")
            DLList<Node>[] temp = new DLList[size];
            System.arraycopy(list, 0, temp, 0, numOfNodes);
        }
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


    public void print() {

    }


    public void union(int i, int j) {
        int firstRoot = find(i);
        int secondRoot = find(j);
        if (firstRoot != secondRoot) {
            nodeArray[firstRoot] = secondRoot;
        }
    }


    private int find(int curr) {
        while (nodeArray[curr] != -1) {
            curr = nodeArray[curr];
        }
        return curr;
    }


    public void connectedComponent() {

    }


    public int diameter(int largestComponentNodes) {
        int[][] D = new int[largestComponentNodes][largestComponentNodes];
        for (int i = 0; i < largestComponentNodes; i++) {
            for (int j = 0; j < largestComponentNodes; j++) {
                if (i == j) {
                    D[i][j] = 0;
                }
                else if (hasEdge(i, j)) {
                    D[i][j] = 1;
                }
                else {
                    D[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < largestComponentNodes; k++) {
            for (int i = 0; i < largestComponentNodes; i++) {
                for (int j = 0; j < largestComponentNodes; j++) {
                    if (D[i][k] != Integer.MAX_VALUE
                        && D[k][j] != Integer.MAX_VALUE && D[i][j] > (D[i][k]
                            + D[k][j])) {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }
        int diameter = 0;
        for (int i = 0; i < largestComponentNodes; i++) {
            for (int j = 0; j < largestComponentNodes; j++) {
                if (D[i][j] != Integer.MAX_VALUE && D[i][j] > diameter) {
                    diameter = D[i][j];
                }
            }
        }

        return diameter;
    }
}
