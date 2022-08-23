import java.io.*;

public class Main {
    public static void main (String[] args) {
        // Parsing args
        boolean ref = false;
        boolean load = false;
        String refFile = "";
        String searchDir = "";
        for (String arg: args) {
            // arg is filename for either index or output file
            if (ref) {
                refFile = arg;
                ref = false;
            } else if (arg.charAt(0) == '-') {
                // arg is optional flag
                // arg is index file
                ref = true;
                if (arg.substring(2) == "index") {
                    load = true;
                }
            } else {
                // arg is filename for search directory
                searchDir = arg;
            }
        }

        // Creating InvertedIndex object
        InvertedIndex indices = new InvertedIndex();
        // Building file
        if (!load) {
            indices.buildIndices(new File(searchDir));
            // Intended to load, didn't provide file
            if (refFile == "") {
                System.out.println("No InvertedIndex file provided. Building new one now...");
                indices.outputIndices("./InvertedIndex.out");
            } else {
                // Intended to build
                System.out.println("Building new InvertedIndex file...");
                indices.outputIndices(refFile);
            }
            System.out.println("Done!");
            System.out.println("If you meant to load a file and run the search engine, try now with the --index flag and your new InvertedIndex.out file.");
        } else {
            // Loading file
            System.out.println("Loading InvertedIndex file...");
            indices.loadIndices(new File(refFile));
            System.out.println("Done!");
            // Running search
            System.out.println("Running search...");
            SearchEngine engine = new SearchEngine(indices);
            engine.runSearch(new File(searchDir));
        }
    }
}