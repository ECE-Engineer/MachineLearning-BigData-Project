import java.io.Serializable;

/**
 * This class provides a way to store all Three API calls necessary to grab all the data from the Kepler API.
 */
public class Triple<String> implements Serializable {
    final String call1;
    final String call2;
    final String call3;

    /**
     *Creates the key
     * @param s1 is the first API call
     * @param s2 is the second API call
     * @param s3 is the third API call
     */
    public Triple(String s1, String s2, String s3) {
        this.call1 = s1;
        this.call2 = s2;
        this.call3 = s3;
    }
}