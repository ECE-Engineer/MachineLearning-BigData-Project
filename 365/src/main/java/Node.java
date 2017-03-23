import java.io.Serializable;

/**
 * Created by Kyle on 3/22/2017.
 */
public class Node implements Serializable {
    short[] key;
    Node[] child;
    int NKeys;
    boolean isLeaf;

    Node() {
        key = null;
        child = null;
        NKeys = 0;
        isLeaf = false;
    }
}