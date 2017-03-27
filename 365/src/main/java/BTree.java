import java.io.*;
import java.util.ArrayList;

/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner.
 */
public class BTree implements Serializable {
    private RandomAccessFile raf;
    private final short NODE_SIZE = 182;/////////////this might have to be changed to be MULTIPLIED by a factor of t/////////////right now the NODE-SIZE compensates for the Node array, but I don't know how the overall NODE_SIZE will be changed if the node array actual has values in it????!!!!
    private final short EXOPLANET_SIZE = 157;
    private short DEGREE_TREE_LOCATION = 0;
    private short ROOT_LOCATION = 2;
    private Node root;
    private int order = 5;
    private int t = order / 2;


    private short nodeCounter = 0;
    private short valueCounter = 0;

    /**
     * The constructor calls BTree() to initialize the size of the BTree.//////////////////////////////////////////////////////////////////////////KEYS ARE NOT BEING STORED
     */
    public BTree() throws IOException, ClassNotFoundException {
        Node x = allocateNode();/////////////////////////////////////
        x.isLeaf = true;
        x.NKeys = 0;
        diskWrite(x);
        this.root = x;
        root = x;
    }

    /**
     * Returns the number count of Nodes in the btreecacheNode file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public short getNumberOfNodes() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        return (short) (raf.length()/(NODE_SIZE));
    }

    /**
     * Returns the keys to all the Nodes in the btreecacheNode file
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns the unique hashing value to the unique key that was given
     */
//    public short[] getKeys() throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
//        short objectCount = (short) (raf.length() / (NODE_SIZE));
//        short[] keys = new short[objectCount];
//
//        for (int i = 0; i < objectCount; i++) {
//            byte[] objectMask = new byte[NODE_SIZE];
//            //seek to the position specified
//            raf.seek(i * NODE_SIZE);
//            //get the key stored there
//            raf.read(objectMask);
//            Node temp = (Node) deserialize(objectMask);
//
//
//
//            keys[i] = temp.index;/////////////////////////////////////not sure about this!!!
//        }
//        raf.close();
//
//        return keys;
//    }

    public short getKeyCount(Node x) throws IOException, ClassNotFoundException {
        short counter = 0;
        if (!x.isLeaf)
            counter = (short) (counter + getKeyCount(x.child[1 - 1]));
        for (int i = 1; i <= x.NKeys; i++) {
            counter = (short) (counter + x.key[i - 1]);
            if (!x.isLeaf)
                counter = (short) (counter + getKeyCount(x.child[i + 1 - 1]));
        }
        return counter;
    }

    public ArrayList<Short> getKeys() throws IOException, ClassNotFoundException {
        return getKeys(this.root);
    }

    public ArrayList<Short> getKeys(Node x) throws IOException, ClassNotFoundException {
        ArrayList<Short> keys = new ArrayList<>();
        if (!x.isLeaf)
            for (short eachKey : getKeys(x.child[1 - 1]))
                keys.add(eachKey);
        for (int i = 1; i <= x.NKeys; i++) {
            keys.add(x.key[i - 1]);
            if (!x.isLeaf)
                for (short eachKey : getKeys(x.child[i + 1 - 1]))
                    keys.add(eachKey);
        }
        return keys;
    }

    /**
     * Returns the value corresponding to a specific index
     * @param k is the specified index to find the value in the btreecacheValue file
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public Exoplanet getValue(short k) throws IOException, ClassNotFoundException {
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

    /**
     * Returns all the Exoplanets in the btreecacheValue file
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
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

    /**
     * Creates space for a Node on disk, then returns the Node
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public Node allocateNode() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //create a new node
        Node n = new Node(order);
        //set the index of the new node
        n.index = nodeCounter;
//        //seek to the end of the file
//        raf.seek(raf.length());
//
//        System.out.println("NODE allocation space: " + serialize(n).length);
//
//        //write the value to the file
//        raf.write(serialize(n));
//        //seek to the node just created
//        raf.seek(NODE_SIZE * n.index);
//        //get the object
//        byte[] objectMask = new byte[NODE_SIZE];
//        raf.read(objectMask);
//        Node temp = (Node) deserialize(objectMask);/////////////////////////////////
        //close the file
//        raf.close();
        //increment the nodeCounter
        nodeCounter++;/////////////////////////////////this might become a problem
        //return the node
        return n;
    }

    /**
     * Overw
     * @param n is the specified index to find the value in the btreecacheValue file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void overwriteNode(Node n) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //adjust the pointer
        raf.seek(n.index * NODE_SIZE);
        //overwrite the object
        raf.write(serialize(n));
        //close the file
        raf.close();
    }

    public short allocateValue(Exoplanet e) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");

        //seek to the end of the file
        raf.seek(raf.length());
        //write the value to the file
        raf.write(serialize(e));
        //close the file
        raf.close();

        valueCounter++;

        return (short) (valueCounter - 1);
    }

    public void overwriteValue(Node n) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");

        Exoplanet e = getValue(n.index);

        short objectCount = (short) (raf.length()/(EXOPLANET_SIZE));

        if (n.index < objectCount) {
            //adjust the pointer
            raf.seek(n.index * EXOPLANET_SIZE);
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
        raf.write(serialize(this.root));//////////////////////////////////////////////////NO SERIALIZE THE MEMORY LOCATION OF THE ROOT IN THE NODE FILE
        //close the file
        raf.close();
    }

    public Node getNode(Node n) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        byte[] objectMask = new byte[NODE_SIZE];
        //seek to the position specified
        raf.seek(n.index * NODE_SIZE);////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get the key stored there
        raf.read(objectMask);
        Node temp = (Node) deserialize(objectMask);//////////////////////////////////////
        //close the file
        raf.close();
        //return the key
        return temp;
    }

//    public Node diskRead(Node x) throws IOException, ClassNotFoundException {/////////////////////////GET ADVICE FOR THIS!!!!!!!!!!!!!!!!!!!!!!!!
//        if (x != null)
//            return x;
//        else
//            return getNode(x);/////////////////////////ALWAYS null
//    }

    public void diskWrite(Node n) throws IOException, ClassNotFoundException {/////////////////////////GET ADVICE FOR THIS!!!!!!!!!!!!!!!!!!!!!!!!
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
            try(ObjectInputStream o = new ObjectInputStream(b)){////////////////////////////////////////////
                return o.readObject();
            }
        }
    }

    public Exoplanet BTreeSearch(short k) throws IOException, ClassNotFoundException {
        return BTreeSearch(this.root, k);
    }

    public Exoplanet BTreeSearch(Node x, short k) throws IOException, ClassNotFoundException {
        int i = 1;
        while (i <= x.NKeys && k > x.key[i - 1])
            i = i + 1;
        if (i <= x.NKeys && k == x.key[i - 1])
            return getValue(x.valIndex[i - 1]);
        else if (x.isLeaf)
            return null;
        else {
            /**x.child[i - 1] = diskRead(x.child[i - 1]);*/
            return BTreeSearch(x.child[i - 1], k);
        }
    }

    public void BTreeSplitChild(Node x, short i) throws IOException, ClassNotFoundException {
        Node z = allocateNode();
        Node y = x.child[i - 1];
        z.isLeaf = y.isLeaf;
        z.NKeys = t - 1;
        for (int j = 1; j <= t - 1; j++){/**not running*/
            z.key[j - 1] = y.key[j + t - 1];/////////////////////////////////////////////////THINGS ARE BEING OVERWRITTEN HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            z.valIndex[j - 1] = y.valIndex[j + t - 1];
        }
        if (!y.isLeaf)
            for (int j = 1; j <= t; j++)
                z.child[j - 1] = y.child[j + t - 1];
        y.NKeys = t - 1;
        for (int j = x.NKeys + 1; j >= i + 1; j--)
            x.child[j + 1 - 1] = x.child[j - 1];
        x.child[i + 1 - 1] = z;
        for (int j = x.NKeys; j >= i; j--){
            x.key[j + 1 - 1] = x.key[j - 1];
            x.valIndex[j + 1 - 1] = x.valIndex[j - 1];
        }
        x.key[i - 1] = y.key[t - 1];
        x.valIndex[i - 1] = y.valIndex[t - 1];
        x.NKeys = x.NKeys + 1;
        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void BTreeInsert(short k, Exoplanet e) throws IOException, ClassNotFoundException {
        short valIndex = allocateValue(e);
        Node r = this.root;

        if (r.NKeys == 2 * t - 1) {
            Node s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.NKeys = 0;
            s.child[1 - 1] = r;
            BTreeSplitChild(s, (short) 1);/**ASK CHRIS ABOUT INDEXING HERE*/
            BTreeInsertNonFull(s, k, valIndex);
        } else
            BTreeInsertNonFull(r, k, valIndex);/////////////////////////////null pointer exception/////////////////////////////////////////
    }

    public void BTreeInsertNonFull(Node x, short k, short v) throws IOException, ClassNotFoundException {////////////////////////////////////////////////////////////////////////////////this is the problem
        int i = x.NKeys;
        if (x.isLeaf) {/// && i < x.key.length//////////////this second condition was added
            while (i >= 1 && k < x.key[i - 1]) {
                x.key[i + 1 - 1] = x.key[i - 1];///////////////////out of bounds
                x.valIndex[i + 1 - 1] = x.valIndex[i - 1];
                i = i - 1;
            }
            x.key[i + 1 - 1] = k;
            x.valIndex[i + 1 - 1] = v;
            x.NKeys = x.NKeys + 1;
            ///x.child[i] = x;//////////////////////MAKE SURE THAT THIS CHILD IS BEING FILLED ------ i think there is a memory issue going on here b/c more nodes are being added to this one ----->>>> maybe the node size should actually be the number of nodes that can be stored in the node TIMES the CURRENT node size!!!!!!!!!!
            diskWrite(x);///////////////////////////////
        } else {
            while (i >= 1 && k < x.key[i - 1])/**ASK CHRIS ABOUT THE INDENTATION AND BRACKET SETUP HERE*//////////////////////////////////////////////////////////////////////////-1 OUT OF BOUNDS EXCEPTION!!!!!!!!!
                i = i - 1;
            i = i + 1;////////////////////////another -1 OUT OF BOUNDS EXCEPTION
            /**x.child[i - 1] = diskRead(x.child[i - 1]);/////////////////////////////////////////////null----->>>>> points to a piece of memory with nothing there*/
            if (x.child[i - 1].NKeys == 2 * t - 1) {////////////////////////////////////////////////
                BTreeSplitChild(x, (short) i);
                if (k > x.key[i - 1])
                    i = i + 1;
            }
            BTreeInsertNonFull(x.child[i - 1], k, v);//////////////////////////
        }
    }
}