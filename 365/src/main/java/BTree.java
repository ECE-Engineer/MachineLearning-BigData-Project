import java.io.*;

/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner.
 */
public class BTree implements Serializable {
    private RandomAccessFile raf;
    private final short NODE_SIZE = 78;
    private final short EXOPLANET_SIZE = 157;
    private short DEGREE_TREE_LOCATION = 0;
    private short ROOT_LOCATION = 2;
    private Node root;
    private int t = 32;

    private short counter = 0;

    /**
     * The constructor calls BTree() to initialize the size of the BTree.
     */
    public BTree() throws IOException, ClassNotFoundException {
        Node x = allocateNode();
        x.isLeaf = true;
        x.NKeys = 0;
        diskWrite(x);
        this.root = x;
        root = x;
    }

    public class Pair<Node, Integer> {
        final Node pairNode;
        final Integer pairInteger;

        public Pair(Node x, Integer k) {
            this.pairNode = x;
            this.pairInteger = k;
        }
    }

    public short getNumberOfNodes() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        return (short) (raf.length()/(NODE_SIZE));
    }

    public short[] getKeys() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        short objectCount = (short) (raf.length() / (NODE_SIZE));
        short[] keys = new short[objectCount];

        for (int i = 0; i < objectCount; i++) {
            raf.seek(i * NODE_SIZE);
            keys[i] = raf.readShort();
        }
        raf.close();

        return keys;
    }

    public Exoplanet getValue(Short k) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "r");
        byte[] objectMask = new byte[EXOPLANET_SIZE];

        //get the object
        raf.seek(k * EXOPLANET_SIZE);
        raf.read(objectMask);
        Exoplanet temp = (Exoplanet) deserialize(objectMask);
        //close the file
        raf.close();

        return temp;
    }

    public Exoplanet[] getValues() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "r");
        short objectCount = (short) (raf.length() / (EXOPLANET_SIZE));
        Exoplanet[] values = new Exoplanet[objectCount];
        byte[] objectMask = new byte[EXOPLANET_SIZE];

        for (int i = 0; i < objectCount; i++) {
            //get the object
            raf.seek(i * EXOPLANET_SIZE);
            raf.read(objectMask);
            values[i] = (Exoplanet) deserialize(objectMask);
        }
        raf.close();

        return values;
    }

    public Node allocateNode() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //create a new node
        Node n = new Node();
        //seek to the end of the file
        raf.seek(raf.length());
        //write the value to the file
        raf.write(serialize(n));
        //seek to the node just created
        raf.seek(NODE_SIZE * counter);
        //get the object
        byte[] objectMask = new byte[NODE_SIZE];
        raf.read(objectMask);
        Node temp = (Node) deserialize(objectMask);
        //close the file
        raf.close();
        //increment the counter
        counter++;
        //return the node
        return temp;
    }

    public void overwriteNode(Node n) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        short objectCount = (short) (raf.length()/(NODE_SIZE));

        if (counter-1 < objectCount) {
            //adjust the pointer
            raf.seek(counter * NODE_SIZE - NODE_SIZE);
            //overwrite the object
            raf.write(serialize(n));
        }
        //close the file
        raf.close();
    }

    public void allocateValue(Exoplanet e) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");

        //seek to the end of the file
        raf.seek(raf.length());
        //write the value to the file
        raf.write(serialize(e));
        //seek to the node just created
        raf.seek(EXOPLANET_SIZE * counter);
        //close the file
        raf.close();
    }

    public void overwriteValue(Exoplanet e) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");

        short objectCount = (short) (raf.length()/(EXOPLANET_SIZE));

        if (counter-1 < objectCount) {
            //adjust the pointer
            raf.seek(counter * EXOPLANET_SIZE - EXOPLANET_SIZE);
            //overwrite the object
            raf.write(serialize(e));
        }
        //close the file
        raf.close();
    }

    public void degreeToFile() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //write the degree of the tree
        raf.writeShort(this.t);
        //close the file
        raf.close();
    }

    public void rootToFile() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //write the root of the tree
        raf.write(serialize(this.root));
        //close the file
        raf.close();
    }

    public Node getNode() throws IOException, ClassNotFoundException {////////////////////////////////////////////////////I will seek advice for this part B/C I'm uncertain of HOW TO ADJUST THE COUNTER when I'm moving up and down the tree!????????????????????????
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        byte[] objectMask = new byte[NODE_SIZE];
        //seek to the position specified
        raf.seek(counter * NODE_SIZE - NODE_SIZE);
        //get the key stored there
        raf.read(objectMask);
        Node temp = (Node) deserialize(objectMask);
        //close the file
        raf.close();
        //return the key
        return temp;
    }

    public Node diskRead(Node x) throws IOException, ClassNotFoundException {
        if (x != null)
            return x;
        else
            return getNode();////////////////////////////////////////////////////I will seek advice for this part B/C I'm uncertain of HOW TO ADJUST THE COUNTER when I'm moving up and down the tree!????????????????????????
    }

    public void diskWrite(Node n) throws IOException, ClassNotFoundException {
        if (n == getNode())////////////////////////////////////////////////////I will seek advice for this part B/C I'm uncertain of HOW TO ADJUST THE COUNTER when I'm moving up and down the tree!????????????????????????
            return;
        else
            overwriteNode(n);
    }

    public void setTreeDegree() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the end of the file
        raf.seek(DEGREE_TREE_LOCATION);
        //get the degree of the tree
        this.t = raf.readShort();
        //close the file
        raf.close();
    }

    public void setTreeRoot() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the end of the file
        raf.seek(ROOT_LOCATION);
        byte[] objectMask = new byte[NODE_SIZE];
        raf.read(objectMask);
        this.root = (Node) deserialize(objectMask);
        //close the file
        raf.close();
    }

    private byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }

    public Pair<Node, Integer> BTreeSearch(Node x, short k) throws IOException, ClassNotFoundException {
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

    public void BTreeSplitChild(Node x, short i) throws IOException, ClassNotFoundException {
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

    public void BTreeInsert(BTree T, short k) throws IOException, ClassNotFoundException {
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
            BTreeInsertNonFull(r, k);/////////////////////////////null pointer exception
    }

    public void BTreeInsertNonFull(Node x, short k) throws IOException, ClassNotFoundException {
        int i = x.NKeys;
        if (x.isLeaf) {
            while (i >= 1 && k < x.key[i - 1]) {
                x.key[i + 1 - 1] = x.key[i - 1];
                i = i - 1;
            }
            x.key[i + 1 - 1] = k;/////////////////////////////null pointer exception
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