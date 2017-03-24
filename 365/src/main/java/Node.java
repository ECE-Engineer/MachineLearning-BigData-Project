import java.io.Serializable;

/**
 * Created by Kyle on 3/22/2017.
 */
public class Node implements Serializable {
    short[] key;
    short index;
    short[] valIndex;
    Node[] child;
    int NKeys;
    boolean isLeaf;

    Node(int t) {
        key = new short[t - 1]; // WAS null
        index = 0;
        valIndex = null;
        child = new Node[t];
        NKeys = 0;
        isLeaf = false;
    }
}