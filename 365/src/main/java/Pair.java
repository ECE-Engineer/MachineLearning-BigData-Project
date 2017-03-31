import java.io.Serializable;

/**
 * This class provides a way to store a key value pair (Short, Exoplanet).
 */
public class Pair<Short, Exoplanet> implements Serializable {
    final Short pairShort;
    final Exoplanet pairExoplanet;

    /**
     *Creates the key / value pairs
     * @param k is the unique identification value that the object has
     * @param v is the Exoplanet object
     */
    public Pair(Short k, Exoplanet v) {
        this.pairShort = k;
        this.pairExoplanet = v;
    }
}