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
            try {
                File doc = searchDocs[loc.getDoc()];
                docLocs += "Filename: ";
                docLocs += doc.getAbsolutePath() + ", Line Num: " + String.valueOf(loc.getLine());
                docLocs +=  ", Word Num: " + String.valueOf(loc.getInd()) + "\n";
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error retrieving indexed document. Files may have been deleted or permissions changed. Try rebuilding index file.");
                System.exit(1);
            }
        }
        return docLocs;
    }

    private String searchIndices (String[] search) {
        String result = "\n";
        for (String term: search) {
            term = term.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            if (!indices.hasWord(term)) {
                result += term + "\nSearch term not found.\n\n";
            } else {
                result += term + "\n";
                result += searchDocs(term) + "\n";
            }
        }
        return result;
    }

    public void runSearch () {
        System.out.println("Enter your search term. Multi-word searches must be space delimited.");
        Scanner searchReader = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter search term or .quit to exit: ");
            String searchTerm = searchReader.nextLine();
            searchTerm = searchTerm.replaceAll("[^.A-Za-z0-9 ]", "");
            if (searchTerm.equals(".quit")) {
                running = false;
            } else {
                String result = searchIndices(searchTerm.split("[ ]"));
                System.out.println(result);
            }
        }
        searchReader.close();
    }
}
