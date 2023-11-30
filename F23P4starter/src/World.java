
/**
 * World Class that deals with changing HashTable, graph, and reading from
 * Command Prcoessor
 * 
 * @author Jae Trimboli (jaetrim)
 * @author Mohammad Mian (mohammadm21)
 * @version 11-07-2023
 */
public class World {

    private HashTable songTable;
    private HashTable artistTable;
    private Record record;
    private Graph graph;

    /**
     * World Constructor
     * 
     * @param hashSize
     *            is initial hash size
     * @param file
     *            is the input file
     */
    public World(int hashSize, String file) {
        songTable = new HashTable(hashSize);
        artistTable = new HashTable(hashSize);
        graph = new Graph(hashSize);
    }


    /**
     * Inserts an artist name and a song name into the hashtable and graph
     * 
     * @param artistName
     *            is the name of the artist
     * @param songName
     *            is the name of the song
     */
    public void insert(String artistName, String songName) {
        if (artistTable.search(artistName) == -1) {
            helpInsert(artistName, "artist");
        }
        if (songTable.search(songName) == -1) {
            helpInsert(songName, "song");
        }

        int artistIndex = artistTable.search(artistName);
        int songIndex = songTable.search(songName);
        Node artistNode = artistTable.getArr()[artistIndex].getValue();
        Node songNode = songTable.getArr()[songIndex].getValue();

        if (!graph.hasEdge(artistNode.getIndex(), songNode.getIndex())) {
            graph.addEdge(artistNode.getIndex(), songNode.getIndex());
        }
        if (!graph.hasEdge(songNode.getIndex(), artistNode.getIndex())) {
            graph.addEdge(songNode.getIndex(), artistNode.getIndex());
        }
        else {
            System.out.println("|" + artistName + "<SEP>" + songName
                + "| duplicates a record already in the database.");

        }

    }


    private void helpInsert(String name, String type) {
        record = new Record();
        record.setKey(name);

        Node node = new Node();
        graph.addNode(node);
        record.setValue(node);

        if (type.equals("song")) {
            songTable.insert("Song", record);
            System.out.println("|" + name + "|"
                + " is added to the Song database.");
        }

        else {
            artistTable.insert("Artist", record);
            System.out.println("|" + name + "|"
                + " is added to the Artist database.");
        }
    }


    /**
     * Removes a song or an artist from its respective hashTable
     * 
     * @param removeType
     *            is whether it is a song or artist
     * @param typeName
     *            is the name of the song or artist
     */
    public void remove(String removeType, String typeName) {
        if (removeType.equals("song")) {
            if (songTable.search(typeName) == -1) {
                System.out.println("|" + typeName + "|"
                    + " does not exist in the Song database.");
            }
            else {
                graph.removeNode(songTable.getArr()[songTable.search(typeName)]
                    .getValue());
                songTable.delete("Song", typeName);
            }
        }
        else {
            if (artistTable.search(typeName) == -1) {
                System.out.println("|" + typeName + "|"
                    + " does not exist in the Artist database.");
            }
            else {
                graph.removeNode(artistTable.getArr()[artistTable.search(
                    typeName)].getValue());
                artistTable.delete("Artist", typeName);
            }
        }
    }


    /**
     * Prints out the songs or artists and the total songs or artists
     * 
     * @param printType
     *            is whether it is a song or artist
     */
    public void print(String printType) {

        if (printType.equals("song")) {

            for (int i = 0; i < songTable.getSize(); i++) {

                if (songTable.getArr()[i] != null) {
                    if (songTable.getArr()[i].getKey().equals("TOMBSTONE")) {
                        System.out.println(songTable.getArr()[i].getIndex()
                            + ": TOMBSTONE");
                    }
                    else {
                        System.out.println(songTable.getArr()[i].getIndex()
                            + ": |" + songTable.getArr()[i].getKey() + "|");

                    }
                }
            }
            System.out.println("total songs: " + songTable.getRecordCount());
        }
        else if (printType.equals("artist")) {

            for (int i = 0; i < artistTable.getSize(); i++) {
                if (artistTable.getArr()[i] != null) {
                    if (artistTable.getArr()[i].getKey().equals("TOMBSTONE")) {
                        System.out.println(artistTable.getArr()[i].getIndex()
                            + ": TOMBSTONE");
                    }
                    else {
                        System.out.println(artistTable.getArr()[i].getIndex()
                            + ": |" + artistTable.getArr()[i].getKey() + "|");
                    }
                }
            }
            System.out.println("total artists: " + artistTable
                .getRecordCount());
        }
        else {
            graph.print();
        }

    }

}
