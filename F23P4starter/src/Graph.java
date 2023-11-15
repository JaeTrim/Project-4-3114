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

    @SuppressWarnings("unchecked")
    public Graph(int hashSize) {
        size = hashSize;
        list = new DLList[hashSize];
        numOfNodes = 0;
    }


    public int getNumOfNodes() {
        return numOfNodes;
    }


    public void addNode(Node node) {
        if (numOfNodes >= (size - 1)) {
            expand();
        }
        
        DLList<Node> temp = new DLList<Node>();
        temp.add(node);
        
        int firstEmpty = 0;
        for (int i = 0; i < list.length; i++)
        {
            if (list[i] == null)
            {
                firstEmpty = i;
                break;
            }
        }
        
        list[firstEmpty] = temp;
        node.setIndex(firstEmpty);
        numOfNodes++;
    }
    
    private void expand()
    {
        size = size * 2;
        @SuppressWarnings("unchecked")
        DLList<Node>[] temp = new DLList[size];
        System.arraycopy(list, 0, temp, 0, numOfNodes);
        list = temp;
    }


    public void addEdge(int start, int end) {
        DLList<Node> temp = list[start];
        Node destNode = list[end].get(0);
        temp.add(destNode);
    }


    public boolean hasEdge(int start, int end) {
        DLList<Node> temp = list[start];
        if (list[end] == null)
        {
            return false;
        }
        Node destNode = list[end].get(0);
        if (temp != null)
        {
            for (int i = 0; i < temp.size(); i++) {
                Node node = temp.get(i);
                if (node == destNode) {
                    return true;
                }
            }
        }
        return false;
    }


    public void removeNode(Node node) {
        DLList<Node> tempList = list[node.getIndex()];
        if (tempList.size() != 0) {
            for (int i = 1; i < tempList.size(); i++) {
                list[tempList.get(i).getIndex()].remove(node);
            }
            list[node.getIndex()] = null;
        }
    }


    public void print() {
        parent = new int[size];
        for (int n = 0; n < size; n++) {
//            if (list[n] != null)
//            {
                parent[n] = -1;
//            }
//            else 
//            {
//                parent[n] = -2; 
//            }
        }

        count = new int[size];
        for (int n = 0; n < list.length; n++) {
            if (list[n] != null) {
                count[n] = list[n].size();
            }
        }

        ConnectedComponent com = this.ConnectedComponent();
        System.out.println("There are " + com.getConnectedComponents()
            + " connected components");
        System.out.println("The largest connected component has " + com
            .getElements() + " elements");
        System.out.println("The diameter of the largest component is " + this
            .diameter(com.getElements()));
    }


    public void union(int i, int j) {

        int firstRoot = find(i);
        int secondRoot = find(j);
        if (firstRoot != secondRoot) {
            parent[secondRoot] = firstRoot;
        }
    }


    private int find(int curr) {
        while (parent[curr] != -1) {
            curr = parent[curr];
        }
        return curr;
    }


    public ConnectedComponent ConnectedComponent() {
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
        int greatestIndex = 0;
        int currentCount = 0;
        for (int i = 0; i < size; i++) {
            currentCount = 0;
            for (int j = 0; j < size; j++) {
                if (parent[j] == i) {
                    currentCount++;
                }
            }
            if (currentCount > greatestCount) {
                currentCount++;
                greatestIndex = i;
                greatestCount = currentCount;
            }
            if (currentCount > 0) {
                numOfComponents++;
            }
        }

        com.setConnectedComponents(numOfComponents);
        com.setElements(greatestCount);
        return com;
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
