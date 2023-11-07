
public class World {

    private HashTable songTable;
    private HashTable artistTable;
    private Graph graph;

    public World(int hashSize, String file) {
        songTable = new HashTable(hashSize);
        artistTable = new HashTable(hashSize);
    }


    public void insert(String artistName, String songName) {
        
    }


    public void remove(String removeType, String typeName) {
        if (removeType.equals("song")) {
            if (songTable.search(typeName) != -1) {
                System.out.println("|" + typeName + "|"
                    + " is removed from the Song database.");
            }
            else {
                System.out.println(typeName
                    + " does not exist in the Song database.");
            }
        }
        else if (removeType.equals("artist")) {
            if (artistTable.search(typeName) != -1) {
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
            for (int i = 0; i < songTable.getSize(); i++) {
                
            }
        }
        else if (printType.equals("artist")) {
            
        }
        else if (printType.equals("graph")) {
            
        }
        System.out.println("total songs: ");
    }
}
