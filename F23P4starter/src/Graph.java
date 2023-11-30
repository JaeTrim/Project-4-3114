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
    private int[] parent;
    private int[] count;
    private int size;
    private int[] largestTree;
    private DLList<Integer> compList;

    /**
     * Graph Constructor
     * 
     * @param hashSize
     *            is the initial hashSize
     */
    @SuppressWarnings("unchecked")
    public Graph(int hashSize) {
        size = hashSize;
        list = new DLList[hashSize];
        numOfNodes = 0;
        compList = new DLList<Integer>();
    }


    /**
     * Gets the list
     * 
     * @return the list
     */
    public DLList<Node>[] getList() {
        return list;
    }


    /**
     * Gets the number of nodes
     * 
     * @return the num of ndoes
     */
    public int getNumOfNodes() {
        return numOfNodes;
    }


    /**
     * Adds a node to the graph
     * 
     * @param node
     *            is the node to be added
     */
    public void addNode(Node node) {
        if (numOfNodes >= (size)) {
            expand();
        }

        int firstEmpty = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                firstEmpty = i;
                break;
            }
        }

        DLList<Node> temp = new DLList<Node>();
        node.setIndex(firstEmpty);
        temp.add(node);
        list[firstEmpty] = temp;
        numOfNodes++;
    }


    /**
     * Expands the list by creating a new list and using an arraycopy to copy
     * the values over
     */
    private void expand() {
        size = size * 2;
        @SuppressWarnings("unchecked")
        DLList<Node>[] temp = new DLList[size];
        System.arraycopy(list, 0, temp, 0, numOfNodes);
        list = temp;
    }


    /**
     * Adds an edge to the graph between two nodes
     * 
     * @param start
     *            is the start index
     * @param end
     *            is the end index
     */
    public void addEdge(int start, int end) {
        DLList<Node> temp = list[start];
        Node destNode = list[end].get(0);
        temp.add(destNode);
    }


    /**
     * Checks if an edge exists between two nodes in the graph
     * 
     * @param start
     *            is the start index of the first node
     * @param end
     *            is the end index of the second node
     * @return true or false if there is an edge
     */
    public boolean hasEdge(int start, int end) {
        DLList<Node> temp = list[start];
        if (list[end] == null) {
            return false;
        }
        Node destNode = list[end].get(0);
        if (temp != null) {
            for (int i = 1; i < temp.size(); i++) {
                Node node = temp.get(i);
                if (node == destNode) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Removes a node from the graph
     * 
     * @param node
     *            is the node to be removed
     */
    public void removeNode(Node node) {
        int index = node.getIndex();
        DLList<Node> tempList = list[index];
        if (tempList.size() != 0) {
            for (int i = 1; i < tempList.size(); i++) {
                list[tempList.get(i).getIndex()].remove(node);
            }
            list[index] = null;
            numOfNodes--;
        }
    }


    /**
     * Prints information about the graph
     */
    public void print() {
        parent = new int[size];
        for (int n = 0; n < size; n++) {
            if (list[n] != null) {
                parent[n] = -1;
            }
            else {
                parent[n] = -2;
            }
        }
        count = new int[size];
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                count[i] = list[i].size();
            }
            else {
                count[i] = -1;
            }
        }

        ConnectedComponent com = this.connectedComponent();

        System.out.println("There are " + com.getConnectedComponents()
            + " connected components");
        System.out.println("The largest connected component has " + com
            .getElements() + " elements");
        System.out.println("The diameter of the largest component is " + com
            .getDiameter());
    }


    /**
     * Uses the union-find algorithm to determine connected components
     * 
     * @param i
     *            is the first index
     * @param j
     *            is the se cond index
     */
    public void union(int i, int j) {
        int firstRoot = find(i);
        int secondRoot = find(j);
        if (firstRoot != secondRoot) {
            if (count[secondRoot] > count[firstRoot]) {
                parent[firstRoot] = secondRoot;
                count[secondRoot] += firstRoot;
                for (int n = 0; n < parent.length; n++) {
                    if (parent[n] == firstRoot) {
                        parent[n] = secondRoot;
                    }
                }
            }
            else {
                parent[secondRoot] = firstRoot;
                count[firstRoot] += secondRoot;
                for (int n = 0; n < parent.length; n++) {
                    if (parent[n] == secondRoot) {
                        parent[n] = firstRoot;
                    }
                }
            }
        }
    }


    /**
     * Uses the union-find algorithm to determine connected components
     * 
     * @param curr
     *            is the curr index
     */
    private int find(int curr) {
        while (parent[curr] != -1) {
            curr = parent[curr];
        }
        return curr;
    }


    /**
     * Determines the connected components and extracts information about the
     * largest connected components
     * 
     * @return a connected component object with the num of connected
     *         components, the size, and diameter
     */
    public ConnectedComponent connectedComponent() {
        ConnectedComponent com = new ConnectedComponent();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                for (int j = 0; j < list[i].size() - 1; j++) {
                    this.union(list[i].get(j).getIndex(), list[i].get(j + 1)
                        .getIndex());
                }
            }
        }
        int greatestCount = 0;
        int numOfComponents = 0;
        int currentCount = 0;
        for (int i = 0; i < size; i++) {
            if (parent[i] == -1) {
                currentCount = 1;
            }
            else {
                currentCount = 0;
            }
            for (int j = 0; j < size; j++) {
                if (parent[j] == i) {
                    currentCount++;
                }
            }
            if (currentCount > greatestCount) {
                greatestCount = currentCount;
                compList.clear();
                compList.add(i);
            }
            else if (currentCount == greatestCount && currentCount > 0) {
                compList.add(i);
            }
            if (currentCount > 0) {
                numOfComponents++;
            }
        }
        int greatestD = 0;
        if (compList.size() > 0) {
            for (int i = 0; i < compList.size(); i++) {
                largestTree = new int[greatestCount];
                int treeCount = 0;
                for (int j = 0; j < parent.length; j++) {
                    if (parent[j] == compList.get(i)) {
                        largestTree[treeCount] = j;
                        treeCount++;
                    }
                }
                largestTree[greatestCount - 1] = compList.get(i);
                int d = this.diameter(greatestCount);
                if (d > greatestD) {
                    greatestD = d;
                }
            }
        }
        com.setConnectedComponents(numOfComponents);
        com.setElements(greatestCount);
        com.setDiameter(greatestD);
        return com;
    }


    /**
     * Uses Floyd's algorithm for finding shortest paths in the graph and the
     * calculates the diameter from this information
     * 
     * @param elements
     *            is the number of elements in the largest connected component
     * @return the diamater value of the largest connected component
     */
    private int diameter(int elements) {
        int[][] dArray = new int[elements][elements];
        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                if (i == j) {
                    dArray[i][j] = 0;
                }
                else if (hasEdge(largestTree[i], largestTree[j])) {
                    dArray[i][j] = 1;
                }
                else {
                    dArray[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < elements; k++) {
            for (int i = 0; i < elements; i++) {
                for (int j = 0; j < elements; j++) {
                    if (dArray[i][k] != Integer.MAX_VALUE
                        && dArray[k][j] != Integer.MAX_VALUE
                        && (dArray[i][j] > (dArray[i][k] + dArray[k][j]))) {
                        dArray[i][j] = dArray[i][k] + dArray[k][j];
                    }
                }
            }
        }
        int diameter = 0;
        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                if (dArray[i][j] != Integer.MAX_VALUE
                    && dArray[i][j] > diameter) {
                    diameter = dArray[i][j];
                }
            }
        }
        return diameter;
    }

}
