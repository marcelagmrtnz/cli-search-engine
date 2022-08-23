import java.util.*;

public class Word {
    public String value;
    public int frequency;
    public List<Coordinate> locations;

    public Word (String value) {
        this.value = value;
        this.frequency = 0;
        this.locations = new ArrayList<> ();
    }

    public String toString () {
        String locationString = value + " ";
        for (Coordinate coord: locations) {
            locationString += coord.toString() + ";";
        }
        return locationString + " (" + String.valueOf(frequency) + ")";
    }

    public void updateWord (int document, int location) {
        frequency++;
        locations.add(new Coordinate(document, location));
    }
}
