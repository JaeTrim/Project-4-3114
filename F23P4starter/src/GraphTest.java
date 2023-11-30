import student.TestCase;

/**
 * Test Graph Class
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-30-2023
 */
public class GraphTest extends TestCase {

    private World world;

    /**
     * SetUp Constructor
     */
    public void setUp() {
        world = new World(10, "P4sampleInput.txt");
    }


    /**
     * Test World
     */
    public void testWorld() {
        world.print("song");
        world.insert("Travis", "HYAENA");
        world.print("song");
        world.print("artist");
        world.remove("artist", "Travis");
        world.print("artist");
        world.remove("artist", "AAAAA");
        world.remove("song", "AAAAA");
        world.remove("song", "HYAENA");
        world.insert("Travis", "HYAENA");
        world.insert("HYAENA", "Travis");
        world.remove("song", "Travis");
        world.print("song");
        world.print("artist");
        world.print("graph");
        
        String output = "total songs: 0\r\n"
            + "|Travis| is added to the Artist database.\r\n"
            + "|HYAENA| is added to the Song database.\r\n" + "8: |HYAENA|\r\n"
            + "total songs: 1\r\n" + "3: |Travis|\r\n" + "total artists: 1\r\n"
            + "|Travis| is removed from the Artist database.\r\n"
            + "3: TOMBSTONE\r\n" + "total artists: 0\r\n"
            + "|AAAAA| does not exist in the Artist database.\r\n"
            + "|AAAAA| does not exist in the Song database.\r\n"
            + "|HYAENA| is removed from the Song database.\r\n"
            + "|Travis| is added to the Artist database.\r\n"
            + "|HYAENA| is added to the Song database.\r\n"
            + "|HYAENA| is added to the Artist database.\r\n"
            + "|Travis| is added to the Song database.\r\n"
            + "|Travis| is removed from the Song database.\r\n"
            + "3: TOMBSTONE\r\n" + "8: |HYAENA|\r\n" + "total songs: 1\r\n"
            + "3: |Travis|\r\n" + "8: |HYAENA|\r\n" + "total artists: 2\r\n"
            + "There are 2 connected components\r\n"
            + "The largest connected component has 2 elements\r\n"
            + "The diameter of the largest component is 1";
        assertFuzzyEquals(systemOut().getHistory(), output);
        systemOut().clearHistory();

    }


    /**
     * Test add Node
     */
    public void testAddNode() {
        Graph graph = new Graph(5);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        assertEquals(3, graph.getNumOfNodes());
        graph.print();
        String output = "There are 3 connected components\r\n"
            + "The largest connected component has 1 elements\r\n"
            + "The diameter of the largest component is 0";
        assertFuzzyEquals(systemOut().getHistory(), output);
        systemOut().clearHistory();

    }


    /**
     * test Add Edge
     */
    public void testAddEdge() {
        Graph graph = new Graph(5);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(node1.getIndex(), node2.getIndex());
        graph.addEdge(node2.getIndex(), node3.getIndex());
        assertTrue(graph.hasEdge(node1.getIndex(), node2.getIndex()));
        assertTrue(graph.hasEdge(node2.getIndex(), node3.getIndex()));
        assertFalse(graph.hasEdge(node1.getIndex(), node3.getIndex()));
        graph.print();
        String output = "There are 1 connected components\r\n"
            + "The largest connected component has 3 elements\r\n"
            + "The diameter of the largest component is 2";
        assertFuzzyEquals(systemOut().getHistory(), output);
        systemOut().clearHistory();
    }


    /**
     * test hasEdge
     */
    public void testHasEdge() {
        Graph graph = new Graph(10);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(node1.getIndex(), node2.getIndex());
        graph.addEdge(node2.getIndex(), node3.getIndex());
        graph.addEdge(node3.getIndex(), node4.getIndex());
        assertTrue(graph.hasEdge(node1.getIndex(), node2.getIndex()));
        assertTrue(graph.hasEdge(node2.getIndex(), node3.getIndex()));
        assertTrue(graph.hasEdge(node3.getIndex(), node4.getIndex()));
        assertFalse(graph.hasEdge(node1.getIndex(), node3.getIndex()));
        assertFalse(graph.hasEdge(node2.getIndex(), node4.getIndex()));
        assertFalse(graph.hasEdge(node1.getIndex(), node4.getIndex()));
        assertFalse(graph.hasEdge(0, 5));
        assertFalse(graph.hasEdge(3, 7));
        graph.print();
        String output = "There are 1 connected components\r\n"
            + "The largest connected component has 4 elements\r\n"
            + "The diameter of the largest component is 3";
        assertFuzzyEquals(systemOut().getHistory(), output);
        systemOut().clearHistory();
    }


    /**
     * testRemoveNode
     */
    public void testRemoveNode() {
        Graph graph = new Graph(10);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(node1.getIndex(), node2.getIndex());
        graph.addEdge(node2.getIndex(), node3.getIndex());
        graph.addEdge(node3.getIndex(), node4.getIndex());
        graph.removeNode(node2);
        assertNull(graph.getList()[node2.getIndex()]);
        assertFalse(graph.hasEdge(node1.getIndex(), node2.getIndex()));
        assertFalse(graph.hasEdge(node2.getIndex(), node3.getIndex()));
    }

}
