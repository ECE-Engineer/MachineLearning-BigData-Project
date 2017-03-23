import java.io.Serializable;

/**
 * Created by Kyle on 3/22/2017.
 */
public class Node implements Serializable {
    short[] key;
    long index;
    Node[] child;
    int NKeys;
    boolean isLeaf;

    Node() {
        key = null;
        index = 0;
        child = null;
        NKeys = 0;
        isLeaf = false;
    }
}