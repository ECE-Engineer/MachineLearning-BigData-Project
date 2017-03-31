import java.io.Serializable;

/**
 * This class provides a way to store all the nodes as on disk with a fixed size.
 */
public class Record implements Serializable {
    final short[] key;
    final short index;
    final short[] childIndex;
    final short[] valIndex;
    final int NKeys;
    final boolean isLeaf;

    /**
     *Creates the key / value pairs
     * @param ka is the key array.
     * @param i is the node index on disk.
     * @param ni is the node index array.
     * @param vi is the value index array.
     * @param nk is the number of keys currently in the node.
     * @param il is the boolean value of whether or not the current node is a leaf.
     */
    public Record(short[] ka, short i, short[] ni, short[] vi, int nk, boolean il) {
        key = ka;
        index = i;
        childIndex = ni;
        valIndex = vi;
        NKeys = nk;
        isLeaf = il;
    }
}