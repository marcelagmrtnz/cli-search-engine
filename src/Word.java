import java.util.*;

public class Word {
    public String value;
    public int frequency;
    public List<Integer> locations;

    public Word (String value) {
        this.value = value;
        this.frequency = 0;
        this.locations = new ArrayList<> ();
    }

    public void updateWord (int location) {
        frequency++;
        locations.add(location);
    }
}
