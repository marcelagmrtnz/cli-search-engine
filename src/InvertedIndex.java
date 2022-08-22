import java.util.*;
import java.io.*;

public class InvertedIndex {
    public Map<String, Word> words;

    public InvertedIndex () {
        this.words = new HashMap<String, Word> ();
    }

    public void buildIndices (File trainDir) {
        File[] trainFiles = trainDir.listFiles();
        int document = 0;
        for (File trainFile : trainFiles) {
            try {
                Scanner textReader = new Scanner(trainFile);
                textReader.useDelimiter(" ");
                int location = 0;
                while (textReader.hasNext()){
                    String word = textReader.next();
                    word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

                    if (!words.containsKey(word)) {
                        words.put(word, new Word(word));
                    }
                    words.get(word).updateWord(document, location);
                    location++;

                    textReader.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                e.printStackTrace();
            }
            document++;
        }   
    }

    public void outputIndices (String filename) {
        try {
            File outputFile = new File(filename);
            if (!outputFile.createNewFile()) {
                if (!outputFile.delete()) {
                    System.out.println("File could not be created! Try a new filename.");
                    return;
                }
                outputFile.createNewFile();
            }

            try {
                FileWriter outputWriter = new FileWriter(filename);
                String wordsString = "word doc loc;...doc loc; (freq)\n";
                for (Word word: words.values()) {
                    wordsString += word.value;
                    wordsString += " " + word.locations.toString() + "\n";
                }
                outputWriter.write(wordsString);
                outputWriter.close();
            } catch (IOException e) {
                System.out.println("Inverted Index data could not be output!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("File could not be created!");
            e.printStackTrace();
        }
    }
}
