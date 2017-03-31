import java.io.Serializable;

/**
 * This class provides a way to store all the data necessary to create a node in a BTree.
 */
public class Node implements Serializable {
    short[] key;
    short index;
    short[] childIndex;
    short[] valIndex;
    Node[] child;
    int NKeys;
    boolean isLeaf;

    /**
     *Creates the key / value pairs
     * @param order is the degree order of the BTree
     */
    Node(int order) {
        key = new short[order - 1];
        index = 0;
        childIndex = new short[order];
        valIndex = new short[order - 1];
        child = new Node[order];
        NKeys = 0;
        isLeaf = false;
    }

    /**
     *Creates node with empty arrays
     */
    Node() {
        key = null;
        index = 0;
        childIndex = null;
        valIndex = null;
        child = null;
        NKeys = 0;
        isLeaf = false;
    }
}