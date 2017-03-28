import java.io.Serializable;

/**
 * Created by Kyle on 3/27/2017.
 */
public class Pair<Short, Exoplanet> implements Serializable {
    final Short pairShort;
    final Exoplanet pairExoplanet;

    public Pair(Short k, Exoplanet v) {
        this.pairShort = k;
        this.pairExoplanet = v;
    }
}