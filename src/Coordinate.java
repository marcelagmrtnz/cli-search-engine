public class Coordinate {
    public int document;
    public int index;

    public Coordinate (int document, int index) {
        this.document = document;
        this.index = index;
    }

    public String toString () {
        return String.valueOf(document) + " " + String.valueOf(index);
    }
}