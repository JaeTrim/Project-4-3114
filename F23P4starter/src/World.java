
public class World {

    private HashTable songTable;
    private HashTable artistTable;
    private Graph graph;
    private Record record;

    public World(int hashSize, String file) {
        songTable = new HashTable(hashSize);
        artistTable = new HashTable(hashSize);
    }


    public void insert(String artistName, String songName) {
        if (artistTable.search(artistName) == -1) {
            // add node for that artist name to graph
            record = new Record();
            record.setKey(artistName);
            artistTable.insert("Artist", record);
            System.out.println("|" + artistName + "|"
                + " is added to the Artist database");
        }
        else if (songTable.search(songName) == -1) {
            // add node for that artist name to graph
            record = new Record();
            record.setKey(songName);
            artistTable.insert("Song", record);
            System.out.println("|" + songName + "|"
                + " is added to the Song database");
        }
        else if (artistTable.search(artistName) != -1) {
            System.out.println("|" + artistName + "|"
                + " duplicates a record already in the database.");
        }
        else if (songTable.search(songName) != -1) {
            System.out.println("|" + songName + "|"
                + " duplicates a record already in the database.");
        }

    }


    public void remove(String removeType, String typeName) {
        if (removeType.equals("song")) {
            if (songTable.search(typeName) != -1) {
                songTable.delete(typeName);
                System.out.println("|" + typeName + "|"
                    + " is removed from the Song database.");
            }
            else {
                System.out.println("|" + typeName + "|"
                    + " does not exist in the Song database.");
            }
        }
        else if (removeType.equals("artist")) {
            if (artistTable.search(typeName) != -1) {
                artistTable.delete(typeName);
                System.out.println("|" + typeName + "|"
                    + " is removed from the Artist database.");
            }
            else {
                System.out.println("|" + typeName + "|"
                    + " does not exist in the Artist database.");
            }
        }
    }


    public void print(String printType) {

        if (printType.equals("song")) {
            System.out.println("total songs: " + songTable.getRecordCount());
            for (int i = 0; i < songTable.getSize(); i++) {

                if (songTable.getArr()[i] != null) {
                    if (songTable.getArr()[i].getKey() == "TOMBSTONE") {
                        System.out.println(songTable.getArr()[i].getIndex()
                            + ": TOMBSTONE");
                    }
                    else {
                        System.out.println(songTable.getArr()[i].getIndex()
                            + ": " + songTable.getArr()[i].getKey());

                    }
                }
            }
        }
        else if (printType.equals("artist")) {
            System.out.println("total artists: " + artistTable
                .getRecordCount());
            for (int i = 0; i < artistTable.getSize(); i++) {
                if (artistTable.getArr()[i] != null) {
                    if (artistTable.getArr()[i].getKey() == "TOMBSTONE") {
                        System.out.println(artistTable.getArr()[i].getIndex()
                            + ": TOMBSTONE");
                    }
                    else {
                        System.out.println(artistTable.getArr()[i].getIndex()
                            + ": " + artistTable.getArr()[i].getKey());
                    }
                }
            }
        }
        else if (printType.equals("graph")) {

        }

    }
}
