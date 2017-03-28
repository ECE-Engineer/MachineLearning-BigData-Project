import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner.
 */
public class BTree implements Serializable {
    private RandomAccessFile raf;
    private final short RECORD_SIZE = 82;
    private final short EXOPLANET_SIZE = 157;
    private short DEGREE_TREE_LOCATION = 0;
    private short ROOT_LOCATION = 2;
    private Node root;
    private int order = 5;
    private int t = order / 2;

    private short nodeCounter = 0;
    private short valueCounter = 0;

    public void setOrder(int newOrder) {
        order = newOrder;
    }

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

//    /**
//     * Returns the keys to all the Nodes in the btreecacheNode file
//     * @throws IOException is used for the IO exceptions that might occur
//     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
//     * @return returns the unique hashing value to the unique key that was given
//     */
//    public short[] getKeys() throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
//        short objectCount = (short) (raf.length() / (RECORD_SIZE));
//        short[] keys = new short[objectCount];
//
//        for (int i = 0; i < objectCount; i++) {
//            byte[] objectMask = new byte[RECORD_SIZE];
//            //seek to the position specified
//            raf.seek(i * RECORD_SIZE);
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

//    public void overwriteValue(Node n) throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");
//
//        Exoplanet e = getValue(n.index);
//
//        short objectCount = (short) (raf.length()/(EXOPLANET_SIZE));
//
//        if (n.index < objectCount) {
//            //adjust the pointer
//            raf.seek(n.index * EXOPLANET_SIZE);
//            //overwrite the object
//            raf.write(serialize(e));
//        }
//        //close the file
//        raf.close();
//    }

    //    public Node diskRead(Node x) throws IOException, ClassNotFoundException {/////////////////////////GET ADVICE FOR THIS!!!!!!!!!!!!!!!!!!!!!!!!
//        if (x != null)
//            return x;
//        else
//            return getNode(x);/////////////////////////ALWAYS null
//    }

    //    public Node getNode(Node n) throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
//        byte[] objectMask = new byte[RECORD_SIZE];
//        //seek to the position specified
//        raf.seek(n.index * RECORD_SIZE);
//        //get the key stored there
//        raf.read(objectMask);
//        Node temp = (Node) deserialize(objectMask);
//        //close the file
//        raf.close();
//        //return the key
//        return temp;
//    }

//    /**
//     * Returns all the Exoplanets in the btreecacheValue file
//     * @throws IOException is used for the IO exceptions that might occur
//     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
//     */
//    public Exoplanet[] getValues() throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "r");
//        short objectCount = (short) (raf.length() / (EXOPLANET_SIZE));
//        Exoplanet[] values = new Exoplanet[objectCount];
//        byte[] objectMask = new byte[EXOPLANET_SIZE];
//
//        for (int i = 0; i < objectCount; i++) {
//            //get the object
//            raf.seek(i * EXOPLANET_SIZE);
//            raf.read(objectMask);
//            values[i] = (Exoplanet) deserialize(objectMask);
//        }
//        raf.close();
//
//        return values;
//    }

    /**
     * Returns the number count of Nodes in the btreecacheNode file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public short getNumberOfNodes() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        return (short) (raf.length()/(RECORD_SIZE));
    }

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
     * Creates space for a Node on disk, then returns the Node
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public Node allocateNode() throws IOException, ClassNotFoundException {
        //create a new node
        Node n = new Node(order);
        //set the index of the new node
        n.index = nodeCounter;
        //increment the counter
        nodeCounter++;
        //return the node
        return n;
    }

    /**
     * Overwrite
     * @param n is the specified index to find the value in the btreecacheValue file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void overwriteNode(Node n) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //adjust the pointer
        raf.seek(n.index * RECORD_SIZE);
        //overwrite the object
        //write everything except for the node array to file & instead of the node array, write the array of pointers to the nodes in memory
        raf.write(serialize(new Record<Short, Integer, Boolean>(n.key, n.index, n.nodeIndex, n.valIndex, n.NKeys, n.isLeaf)));

        //close the file
        raf.close();
    }

    public short overwriteValue(Exoplanet e) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheValue", "rw");

        //seek to the current end of the file
        raf.seek(valueCounter * EXOPLANET_SIZE);
        //write the value to the file
        raf.write(serialize(e));
        //close the file
        raf.close();

        valueCounter++;

        return (short) (valueCounter - 1);
    }

    public void overwriteDegreeToFile() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the start of the file
        raf.seek(DEGREE_TREE_LOCATION);
        //write the degree of the tree
        raf.writeShort(this.t);
        //close the file
        raf.close();
    }

    public void overwriteRootToFile() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the end of the degree of tree location
        raf.seek(ROOT_LOCATION);
        //write the root of the tree
        raf.writeShort(this.root.index);
        //close the file
        raf.close();
    }

    public void diskWrite(Node n) throws IOException, ClassNotFoundException {
        overwriteNode(n);
    }

    public void readTreeDegree() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the start of the file
        raf.seek(DEGREE_TREE_LOCATION);
        //get the degree of the tree
        this.t = raf.readShort();
        //close the file
        raf.close();
    }

    public void readTreeRoot() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the end of the degree of tree location
        raf.seek(ROOT_LOCATION);
        this.root = getRoot(raf.readShort());/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////issues 2
        //close the file
        raf.close();
    }

    public Node getRoot(short pos) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //adjust the pointer
        raf.seek(pos * RECORD_SIZE);

        //read in all the parts and create the BTree OR CREATE THE ROOT >>>>>>>> then write a method to create the BTree using its node >>>>>>>_____________ NOTE that the NODE array is EMPTY TO START IF YOU DO THIS & WHEN YOU CREATE THE BTree, >>>>> REALLY YOU WILL JUST BE RECURSIVELY FILLING THE node arrays >>>> starting with the root





        byte[] objectMask = new byte[RECORD_SIZE];
        raf.read(objectMask);

        //close the file
        raf.close();

        //create a temporary record
        Record<Short, Integer, Boolean> temp = (Record<Short, Integer, Boolean>) deserialize(objectMask);

        //create an empty node
        Node node = new Node();

        //set all the features of the node
        node.key = temp.key;
        node.index = temp.index;
        node.nodeIndex = temp.nodeIndex;
        node.valIndex = temp.valIndex;
        node.NKeys = temp.NKeys;
        node.isLeaf = temp.isLeaf;

        return node;////////////////////////////////REMEMBER THAT THE NODE ARRAY IS EMPTY & THAT YOU WILL HAVE TO CREATE A BTREE CREATE METHOD THAT FILLS THIS ARRAY APPROPRIATELY!!!!!!!!!!!!!!!
    }

//    public void readNode(Node r) throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");
//
//        //seek to the node specified
//        raf.seek(ROOT_LOCATION);
//        this.root = getRoot(raf.readShort());/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////issues 2
//        //close the file
//        raf.close();
//    }
//
//    public Node readNode(short pos) throws IOException, ClassNotFoundException {
//        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");
//
//        //adjust the pointer
//        raf.seek(pos * RECORD_SIZE);
//        byte[] objectMask = new byte[RECORD_SIZE];
//        raf.read(objectMask);
//
//        //close the file
//        raf.close();
//
//        return (Node) deserialize(objectMask);/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////issues start here 1
//    }



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
                return o.readObject();/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////issues
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
            return BTreeSearch(x.child[i - 1], k);
        }
    }

    public void BTreeSplitChild(Node x, short i) throws IOException, ClassNotFoundException {
        Node z = allocateNode();
        Node y = x.child[i - 1];
        z.isLeaf = y.isLeaf;
        z.NKeys = t - 1;
        for (int j = 1; j <= t - 1; j++){
            z.key[j - 1] = y.key[j + t - 1];
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
        short valIndex = overwriteValue(e);
        Node r = this.root;

        if (r.NKeys == 2 * t - 1) {
            Node s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.NKeys = 0;
            s.child[1 - 1] = r;
            BTreeSplitChild(s, (short) 1);
            BTreeInsertNonFull(s, k, valIndex);
        } else
            BTreeInsertNonFull(r, k, valIndex);
    }

    public void BTreeInsertNonFull(Node x, short k, short v) throws IOException, ClassNotFoundException {
        int i = x.NKeys;
        if (x.isLeaf) {
            while (i >= 1 && k < x.key[i - 1]) {
                x.key[i + 1 - 1] = x.key[i - 1];
                x.valIndex[i + 1 - 1] = x.valIndex[i - 1];
                i = i - 1;
            }
            x.key[i + 1 - 1] = k;
            x.valIndex[i + 1 - 1] = v;
            x.NKeys = x.NKeys + 1;
            diskWrite(x);
        } else {
            while (i >= 1 && k < x.key[i - 1])
                i = i - 1;
            i = i + 1;
            if (x.child[i - 1].NKeys == 2 * t - 1) {
                BTreeSplitChild(x, (short) i);
                if (k > x.key[i - 1])
                    i = i + 1;
            }
            BTreeInsertNonFull(x.child[i - 1], k, v);
        }
    }
}