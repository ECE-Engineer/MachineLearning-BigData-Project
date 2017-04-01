import java.io.*;
import java.util.ArrayList;

/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner using a BTree.
 */
public class BTree implements Serializable {
    private final short RECORD_SIZE = 173;
    private final short EXOPLANET_SIZE = 157;
    private short DEGREE_TREE_LOCATION = 0;
    private short ROOT_LOCATION = 2;
    private Node root;
    private int order = 5;
    private int t = order / 2;

    private short nodeCounter = 0;
    private short valueCounter = 0;

    /**
     * Creates the root node of the BTree.
     */
    public BTree() throws IOException, ClassNotFoundException {
        Node x = allocateNode();
        x.isLeaf = true;
        x.NKeys = 0;
        diskWrite(x);
        this.root = x;
    }

    /**
     * Loads the root node of the BTree.
     * @param T is an empty BTree.
     */
    public BTree(BTree T) throws IOException, ClassNotFoundException {
        readTreeDegree();
        readTreeRoot();
    }

    /**
     * Sets the order of the the Btree.
     * @param newOrder is the new order of the BTree.
     */
    public void setOrder(int newOrder) {
        order = newOrder;
    }

    /**
     * Reads the degree of the BTree from file and sets the order of the BTree.
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void readTreeDegree() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "r");

        //seek to the start of the file
        raf.seek(DEGREE_TREE_LOCATION);
        //get the degree of the tree
        this.order = raf.readShort();
        //close the file
        raf.close();
    }

    /**
     * Reads the root of the BTree from file and sets the root of the BTree.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public void readTreeRoot() throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "r");

        //seek to the end of the degree of tree location
        raf.seek(ROOT_LOCATION);

        short temp = raf.readShort();

        //set the root of the BTree
        this.root = getNode(temp);

        //close the file
        raf.close();
    }

    /**
     * Returns all the keys from the persistant BTree.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns all the keys from the persistant BTree.
     */
    public ArrayList<Short> getKeys() throws IOException, ClassNotFoundException {
        return getKeys(this.root);
    }

    /**
     * Returns all the keys from the persistant BTree.
     * @param x is a root node of a tree or subtree.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns all the keys from the persistant BTree.
     */
    public ArrayList<Short> getKeys(Node x) throws IOException, ClassNotFoundException {
        ArrayList<Short> keys = new ArrayList<>();
        if (!x.isLeaf)
            for (short eachKey : getKeys(getNode(x.childIndex[1 - 1])))
                keys.add(eachKey);
        for (int i = 1; i <= x.NKeys; i++) {
            keys.add(x.key[i - 1]);
            if (!x.isLeaf)
                for (short eachKey : getKeys(getNode(x.childIndex[i + 1 - 1])))
                    keys.add(eachKey);
        }
        return keys;
    }

    /**
     * Returns all the values from the persistant BTree.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return all the values from the persistant BTree.
     */
    public ArrayList<Exoplanet> getValues() throws IOException, ClassNotFoundException {
        return getValues(this.root);
    }

    /**
     * Returns all the values from the persistant BTree.
     * @param x is a root node of a tree or subtree.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns all the values from the persistant BTree.
     */
    public ArrayList<Exoplanet> getValues(Node x) throws IOException, ClassNotFoundException {
        ArrayList<Exoplanet> values = new ArrayList<>();
        if (!x.isLeaf)
            for (Exoplanet eachValue : getValues(getNode(x.childIndex[1 - 1])))
                values.add(eachValue);
        for (int i = 1; i <= x.NKeys; i++) {
            values.add(getValue(x.valIndex[i - 1]));
            if (!x.isLeaf)
                for (Exoplanet eachValue : getValues(getNode(x.childIndex[i + 1 - 1])))
                    values.add(eachValue);
        }
        return values;
    }

    /**
     * Creates space for a Node on disk, then returns the Node
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns a deserialized object given by a byte array.
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
     * Overwrites the node on disk.
     * @param n is the specified index to find the node in the btreecacheNode file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void overwriteNode(Node n) throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");

        //adjust the pointer
        raf.seek(n.index * RECORD_SIZE);
        //overwrite the object
        //write everything except for the node array to file & instead of the node array, write the array of pointers to the nodes in memory
        raf.write(serialize(new Record(n.key, n.index, n.childIndex, n.valIndex, n.NKeys, n.isLeaf)));

        //close the file
        raf.close();
    }

    /**
     * Returns the index corresponding to a specific value.
     * @param e is the Exoplanet to overwrite the current value found at its respective position on file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns the index corresponding to a specific value.
     */
    public short overwriteValue(Exoplanet e) throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
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

    /**
     * Overwrites the current tree degree to file.
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void overwriteDegreeToFile() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the start of the file
        raf.seek(DEGREE_TREE_LOCATION);
        //write the degree of the tree
        raf.writeShort(this.order);
        //close the file
        raf.close();
    }

    /**
     * Overwrites the current tree root to file.
     * @throws IOException is used for the IO exceptions that might occur
     */
    public void overwriteRootToFile() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "rw");

        //seek to the end of the degree of tree location
        raf.seek(ROOT_LOCATION);
        //write the root of the tree
        raf.writeShort(this.root.index);
        //close the file
        raf.close();
    }

    /**
     * Returns the value corresponding to a specific index.
     * @param k is the specified index to find the value in the btreecacheValue file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns a deserialized object given by a byte array.
     */
    public Exoplanet getValue(short k) throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
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
     * Writes a given node to disk and updates the pointer to the root.
     * @param n is the node that will be written to disk.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public void diskWrite(Node n) throws IOException, ClassNotFoundException {
        if (n == this.root) {
            n.index = root.index;
            overwriteRootToFile();
        }
        overwriteNode(n);
    }

    /**
     * Returns the node at the specified position.
     * @param pos is the specified index to find the node in the btreecacheNode file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns the node at the specified position.
     */
    public Node getNode(short pos) throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");

        //adjust the pointer
        raf.seek(pos * RECORD_SIZE);

        //read in all the parts and create the root
        byte[] objectMask = new byte[RECORD_SIZE];
        raf.read(objectMask);

        //close the file
        raf.close();

        //create a temporary record
        Record temp = (Record) deserialize(objectMask);

        //create an empty node
        Node node = new Node();

        //set all the features of the node
        node.key = temp.key;
        node.index = temp.index;
        node.childIndex = temp.childIndex;
        node.valIndex = temp.valIndex;
        node.child = new Node[this.order];
        node.NKeys = temp.NKeys;
        node.isLeaf = temp.isLeaf;

        return node;
    }

    /**
     * Returns the value corresponding to a specific index.
     * @param k is the specified key to find the value corresponding to it.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns a deserialized object given by a byte array.
     */
    public Exoplanet BTreeSearch(short k) throws IOException, ClassNotFoundException {
        return BTreeSearch(this.root, k);
    }

    /**
     * Returns the value corresponding to a specific index.
     * @param x is a root node of a tree or subtree.
     * @param k is the specified key to find the value corresponding to it.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns a deserialized object given by a byte array.
     */
    public Exoplanet BTreeSearch(Node x, short k) throws IOException, ClassNotFoundException {
        int i = 1;
        while (i <= x.NKeys && k > x.key[i - 1])
            i = i + 1;
        if (i <= x.NKeys && k == x.key[i - 1])
            return getValue(x.valIndex[i - 1]);
        else if (x.isLeaf)
            return null;
        else {
            return BTreeSearch(getNode(x.childIndex[i - 1]), k);
        }
    }

    /**
     * Splits the nodes when they become full & readjusts the BTree afterwards.
     * @param x is the node being split and readjusted.
     * @param i is the key value being stored in the node.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
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
            for (int j = 1; j <= t; j++) {
                z.child[j - 1] = y.child[j + t - 1];
                z.childIndex[j - 1] = y.childIndex[j + t - 1];
            }
        y.NKeys = t - 1;
        for (int j = x.NKeys + 1; j >= i + 1; j--) {
            x.child[j + 1 - 1] = x.child[j - 1];
            x.childIndex[j + 1 - 1] = x.childIndex[j - 1];
        }
        x.child[i + 1 - 1] = z;
        x.childIndex[i + 1 - 1] = z.index;
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

    /**
     * Inserts a key and corresponding value into the BTree.
     * @param k is the key value being stored in the node.
     * @param e is the Exoplanet being stored in file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public void BTreeInsert(short k, Exoplanet e) throws IOException, ClassNotFoundException {
        short valIndex = overwriteValue(e);
        Node r = this.root;

        if (r.NKeys == 2 * t - 1) {
            Node s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.NKeys = 0;
            s.child[1 - 1] = r;
            s.childIndex[1 - 1] = r.index;
            BTreeSplitChild(s, (short) 1);
            BTreeInsertNonFull(s, k, valIndex);
        } else
            BTreeInsertNonFull(r, k, valIndex);
    }

    /**
     * Inserts a key and corresponding value into the BTree when the node is non-full.
     * @param x is a root node of a tree or subtree.
     * @param k is the key value being stored in the node.
     * @param v is the value index corresponding to the Exoplanet being stored in file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
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

    /**
     * Creates an array of bytes after serializing a given object.
     * @throws IOException is used for the IO exceptions that might occur
     * @param obj is the object you are serializing.
     */
    private byte[] serialize(Object obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    /**
     * Returns a deserialized object given by a byte array.
     * @throws IOException is used for the IO exceptions that might occur
     * @param bytes is your object in bytes.
     * @return returns a deserialized object given by a byte array.
     */
    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }
}