import java.io.*;

public class SearchEngine {
    private InvertedIndex indices;

    public SearchEngine (InvertedIndex indices) {
        this.indices = indices;
    }

    public void runSearch(File searchDir) {
        System.out.println("Working");
    }
}
