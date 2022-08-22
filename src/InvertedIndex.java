import java.util.*;
import java.io.*;

public class InvertedIndex {
    public Map<String, Word> words;

    public InvertedIndex () {
        this.words = new HashMap<String, Word> ();
    }

    public void buildIndices (File trainFile) {
        try {
            Scanner textReader = new Scanner(trainFile);
            textReader.useDelimiter(" ");
            int location = 0;
            while (textReader.hasNext()){
                String word = textReader.next();

                if (!words.containsKey(word)) {
                    words.put(word, new Word(word));
                }
                words.get(word).updateWord(location);
                location++;

                textReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }
}
