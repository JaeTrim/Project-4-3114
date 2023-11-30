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
    
    
    @SuppressWarnings("unchecked")
    public Graph(int hashSize) {
        size = hashSize;
        list = new DLList[hashSize];
        numOfNodes = 0;
        compList = new DLList<Integer>();
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

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

    private void expand() {
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

        ConnectedComponent com = this.ConnectedComponent();

        System.out.println("There are " + com.getConnectedComponents()
            + " connected components");
        System.out.println("The largest connected component has " + com
            .getElements() + " elements");
        System.out.println("The diameter of the largest component is " + com
            .getDiameter());
    }

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
                if (d > greatestD)
                {
                    greatestD = d;
                }
            }
        }
        com.setConnectedComponents(numOfComponents);
        com.setElements(greatestCount);
        com.setDiameter(greatestD);
        return com;
    }


    private int diameter(int elements) {
        int[][] D = new int[elements][elements];
        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                if (i == j) {
                    D[i][j] = 0;
                }
                else if (hasEdge(largestTree[i], largestTree[j])) {
                    D[i][j] = 1;
                }
                else {
                    D[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < elements; k++) {
            for (int i = 0; i < elements; i++) {
                for (int j = 0; j < elements; j++) {
                    if (D[i][k] != Integer.MAX_VALUE
                        && D[k][j] != Integer.MAX_VALUE && (D[i][j] > (D[i][k]
                            + D[k][j]))) {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }
        int diameter = 0;
        for (int i = 0; i < elements; i++) {
            for (int j = 0; j < elements; j++) {
                if (D[i][j] != Integer.MAX_VALUE && D[i][j] > diameter) {
                    diameter = D[i][j];
                }
            }
        }
        return diameter;
    }

}
