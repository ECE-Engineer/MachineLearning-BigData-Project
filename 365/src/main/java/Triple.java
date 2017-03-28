import java.io.Serializable;

/**
 * Created by Kyle on 3/27/2017.
 */
public class Triple<String> implements Serializable {
    final String call1;
    final String call2;
    final String call3;

    public Triple(String s1, String s2, String s3) {
        this.call1 = s1;
        this.call2 = s2;
        this.call3 = s3;
    }
}