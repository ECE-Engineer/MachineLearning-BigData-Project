import java.io.Serializable;

/**
 * Created by Kyle on 3/28/2017.
 */
public class Record<Short, Integer, Boolean> implements Serializable {
    final short[] key;
    final short index;
    final short[] nodeIndex;
    final short[] valIndex;
    final int NKeys;
    final boolean isLeaf;

    public Record(short[] ka, short i, short[] ni, short[] vi, int nk, boolean il) {
        key = ka;
        index = i;
        nodeIndex = ni;
        valIndex = vi;
        NKeys = nk;
        isLeaf = il;
    }
}