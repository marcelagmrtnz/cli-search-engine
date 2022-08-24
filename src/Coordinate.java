public class Coordinate {
    private int document;
    private int index;
    private int line;

    public Coordinate (int document, int index, int line) {
        this.document = document;
        this.index = index;
        this.line = line;
    }

    public int getDoc () {
        return document;
    }

    public int getInd () {
        return index;
    }

    public int getLine () {
        return line;
    }

    public String toString () {
        return String.valueOf(document) + "-" + String.valueOf(index) + "-" + String.valueOf(line);
    }
}
