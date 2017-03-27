import java.io.Serializable;

/**
 * Created by Kyle on 3/22/2017.
 */
public class Node implements Serializable {
    short[] key;
    short index;
    short[] nodeIndex;
    short[] valIndex;
    Node[] child;/////////////////////////////////////////////////
    int NKeys;
    boolean isLeaf;

    Node(int order) {
        key = new short[order - 1];
        index = 0;
        nodeIndex = null;/**ASK CHRIS WHAT TO DO WITH THIS*/
        valIndex = new short[order - 1];
        child = new Node[order];
        NKeys = 0;
        isLeaf = false;
    }
}