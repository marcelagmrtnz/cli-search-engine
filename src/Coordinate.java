public class Coordinate {
    private int document;
    private int index;

    public Coordinate (int document, int index) {
        this.document = document;
        this.index = index;
    }

    public String toString () {
        return String.valueOf(document) + " " + String.valueOf(index);
    }
}
