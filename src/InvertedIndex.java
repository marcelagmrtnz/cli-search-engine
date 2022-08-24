import java.util.*;
import java.io.*;

public class InvertedIndex {
    private Map<String, Word> words;

    public InvertedIndex () {
        this.words = new HashMap<String, Word> ();
    }

    public Word getWord (String word) {
        return words.get(word);
    }

    public boolean hasWord (String word) {
        return words.containsKey(word);
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
                }
                textReader.close();
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
                    wordsString += word.toString() + "\n";
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

    public void loadIndices (File inputFile) {
        try {
            Scanner indexReader = new Scanner(inputFile);
            boolean header = true;
            while (indexReader.hasNext()) {
                // Ignoring file header
                if (header) {
                    indexReader.next();
                    header = false;
                } else {
                    // Pulling word info
                    String wordLine = indexReader.next();
                    String[] lineSections = wordLine.split("[ ]");
                    // Initializing word object
                    Word curWord = new Word(lineSections[0]);
                    // Pulling location info and adding locations to word
                    String[] locStrings = lineSections[1].split("[;]");
                    for (String loc: locStrings) {
                        String[] splitLoc = loc.split("[ ]");
                        curWord.updateWord(Integer.parseInt(splitLoc[0]), Integer.parseInt(splitLoc[1]));
                    }
                    // Adding word to InvertedIndex object
                    words.put(lineSections[0], curWord);
                }
            }
            indexReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file!");
            e.printStackTrace();
        }
    }
}
