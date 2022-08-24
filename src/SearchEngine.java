import java.io.*;
import java.util.*;

public class SearchEngine {
    private InvertedIndex indices;
    private File[] searchDocs;

    public SearchEngine (InvertedIndex indices, File searchDir) {
        this.indices = indices;
        this.searchDocs = searchDir.listFiles();
    }

    private String searchDocs (String term) {
        String docLocs = "";
        for (Coordinate loc: indices.getWord(term).getLocs()) {
            File doc = searchDocs[loc.getDoc()];
            docLocs += "Filename: ";
            docLocs += doc.getAbsolutePath() + ", Word Num: " + String.valueOf(loc.getInd());
            docLocs += "\n";
        }
        return docLocs;
    }

    private String searchIndices (String[] search) {
        String result = "";
        for (String term: search) {
            term = term.replaceAll("[^A-Za-b0-9]", "");
            if (!indices.hasWord(term)) {
                result += term + " Serach term not found.\n\n";
            } else {
                result += term + "\n";
                result += searchDocs(term) + "\n";
            }
        }
        return result;
    }

    public void runSearch () {
        System.out.println("Enter your search term. Multi-word searches must be space delimited.");
        System.out.println("Enter .quit to exit serach engine.");
        Scanner searchReader = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter search term: ");
            String searchTerm = searchReader.nextLine();
            if (searchTerm == ".quit") {
                running = false;
            } else {
                String result = searchIndices(searchTerm.split("[ ]"));
                System.out.println(result);
            }
        }
        searchReader.close();
    }
}
