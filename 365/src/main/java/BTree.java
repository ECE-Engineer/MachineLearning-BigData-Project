/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner.
 */
public class BTree {
    Node root;
    static final int t = 32;

    /**
     * The constructor calls BTree() to initialize the size of the BTree.
     */
    public BTree() {
        Node x = allocateNode();
        x.isLeaf = true;
        x.NKeys = 0;
        diskWrite(x);
        this.root = x;
        root = x;
    }

    public class Node {
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

    public class Pair<Node, Integer> {
        final Node pairNode;
        final Integer pairInteger;

        public Pair(Node x, Integer k) {
            this.pairNode = x;
            this.pairInteger = k;
        }
    }

    //get keys>>>>>>>>>>>>>>>>>>>>traversal method

    //get values>>>>>>>>>>>>>>>>>>traversal method

    public Node allocateNode() {
        //allocate a page of disk space to a node

    }

    public Node diskRead(Node x) {////////////////////////////////////////////////parameter and result

    }

    public void diskWrite(Node n) {

    }

    public Pair<Node, Integer> BTreeSearch(Node x, short k) {
        int i = 0;
        while (i <= x.NKeys && k > x.key[i - 1])
            i = i + 1;
        if (i <= x.NKeys && k == x.key[i - 1])
            return new Pair<Node, Integer>(x, i);
        else if (x.isLeaf)
            return null;
        else {
            diskRead(x.child[i - 1]);
            return BTreeSearch(x.child[i - 1], k);
        }
    }

    public void BTreeSplitChild(Node x, short i) {
        Node z = allocateNode();
        Node y = x.child[i - 1];
        z.isLeaf = y.isLeaf;
        z.NKeys = t - 1;
        for (int j = 1; i < t - 1; i++)
            z.key[j - 1] = y.key[j + t - 1];
        if (!y.isLeaf)
            for (int j = 1; i < t; i++)
                z.child[j - 1] = y.child[j + t - 1];
        y.NKeys = t - 1;
        for (int j = x.NKeys + 1; j > i + 1; j--)
            x.child[j + 1 - 1] = x.child[j - 1];
        x.child[i + 1 - 1] = z;
        for (int j = x.NKeys; j > i; j--)
            x.key[j + 1 - 1] = x.key[j - 1];
        x.key[i - 1] = y.key[t - 1];
        x.NKeys = x.NKeys + 1;
        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void BTreeInsert(BTree T, short k) {
        Node r = T.root;
        if (r.NKeys == 2 * t - 1) {
            Node s = allocateNode();
            T.root = s;
            s.isLeaf = false;
            s.NKeys = 0;
            s.child[1 - 1] = r;
            BTreeSplitChild(s, (short) 1);
            BTreeInsertNonFull(s, k);
        } else
            BTreeInsertNonFull(r, k);
    }

    public void BTreeInsertNonFull(Node x, short k) {
        int i = x.NKeys;
        if (x.isLeaf) {
            while (i >= 1 && k < x.key[i - 1]) {
                x.key[i + 1 - 1] = x.key[i - 1];
                i = i - 1;
            }
            x.key[i + 1 - 1] = k;
            x.NKeys = x.NKeys + 1;
            diskWrite(x);
        } else {
            while (i >= 1 && k < x.key[i - 1])
                i = i - 1;
            i = i + 1;
            diskRead(x.child[i - 1]);
            if (x.child[i - 1].NKeys == 2 * t - 1) {
                BTreeSplitChild(x, (short) i);
                if (k > x.key[i - 1])
                    i = i + 1;
            }
            BTreeInsertNonFull(x.child[i - 1], k);
        }
    }
}