import com.sun.org.apache.xpath.internal.operations.Bool;

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
        x.NKeys = 0;///////////////////////////////////////////////////
        diskWrite(x);
        this.root = x;
    }

    public BTree(BTree T) throws IOException, ClassNotFoundException {
        readTreeDegree();
        readTreeRoot();
    }


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

    public void readTreeRoot() throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheTree", "r");

        //seek to the end of the degree of tree location
        raf.seek(ROOT_LOCATION);


        short temp = raf.readShort();

        System.out.println("INDEX OF ROOT : " + temp);/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //set the root of the BTree
        this.root = getNode(temp);

        //close the file
        raf.close();
    }



    public void setOrder(int newOrder) {
        order = newOrder;
    }

    private int getLength(Object[] o) {
        int counter = 0;
        for (int i = 0; i < o.length; i++)
            if (o[i] != null)
                counter++;
        return counter;
    }

    /**
     * Returns the number count of Nodes in the btreecacheNode file
     * @throws IOException is used for the IO exceptions that might occur
     */
    public short getNumberOfNodes() throws IOException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");
        return (short) (raf.length()/(RECORD_SIZE));
    }

//    public short getKeyCount() throws IOException, ClassNotFoundException {
//        return getKeyCount(this.root);
//    }

//    int loadCounter = 0;
//    public short getKeyCount(Node x) throws IOException, ClassNotFoundException {//////////////////////////////////this does not work
////        finalCounter++;
////        keyCount += r.NKeys;
////        if (!r.isLeaf) {
////            //link the node of the specified child node
////            r.child[1 - 1] = getNode(r.childIndex[1 - 1]);
////            //recursively link you the rest of the BTree
////            loadTree(r.child[1 - 1]);
////        }
////        for (int i = 1; i <= r.NKeys; i++) {
////            //link the node of the specified child node
////            r.child[i - 1] = getNode(r.childIndex[i - 1]);
////            if (!r.isLeaf) {
////                //link the node of the specified child node
////                r.child[i + 1 - 1] = getNode(r.childIndex[i + 1 - 1]);
////                //recursively link you the rest of the BTree
////                loadTree(r.child[i + 1 - 1]);
////            }
////        }
////
//
//
//        loadCounter++;
//        short counter = 0;
//        if (x != null) {
//            if (!x.isLeaf) {
//                if (x.child[1 - 1] == null) {
//                    System.out.println("LOOKED AT THIS MANY KEYS : " + loadCounter);
//                    System.out.println("KEY IS : " + x.key[1 - 1]);
//                }
//                counter += getKeyCount(x.child[1 - 1]);
//            }
//            for (int i = 1; i <= x.NKeys; i++) {
//                counter += x.key[i - 1];
//                if (!x.isLeaf) {
//                    if (x.child[i + 1 - 1] == null) {
//                        System.out.println("LOOKED AT THIS MANY KEYS : " + loadCounter);
//                        System.out.println("KEY IS : " + x.key[i + 1 - 1]);
//                    }
//                    counter += getKeyCount(x.child[i + 1 - 1]);
//                }
//            }
//        }
//        return counter;
//    }

    public ArrayList<Short> getKeys() throws IOException, ClassNotFoundException {
        return getKeys(this.root);
    }

    public ArrayList<Short> getKeys(Node x) throws IOException, ClassNotFoundException {
        ArrayList<Short> keys = new ArrayList<>();
        if (!x.isLeaf)
            for (short eachKey : getKeys(x.child[1 - 1]))///////////////////////////////////////it is running into a null pointer exception at the 2nd level unless I tell it to avoid these!!!!!!!!!!!!!!!
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
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "rw");




//        if (isEmptyArray(n.childIndex)) {
//            System.out.println("THIS NODE "+ n.index + " HAS 0 POINTERS TO IT'S CHILD NODES");///////////////////////////////////////////////////////////////////////this verifies the issue I'm having!!!!!!!!!!!!!!!!!!!!
//        }

//        if (!isEmptyArray(n.childIndex)) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.THIS NODE "+ n.index + " HAS POINTERS TO IT'S CHILD NODES");
//        }


        //////////////////////////////////////////////////////THE STUFF ABOVE HAS TOLD ME THAT NOT ALL THE NODES HAVE POINTERS TO CHILD NODES AND THIS IS POSSIBLY THE CAUSE OF THE ISSUE I'M HAVING!!!!!!!!



        //adjust the pointer
        raf.seek(n.index * RECORD_SIZE);
        //overwrite the object
        //write everything except for the node array to file & instead of the node array, write the array of pointers to the nodes in memory
        raf.write(serialize(new Record(n.key, n.index, n.childIndex, n.valIndex, n.NKeys, n.isLeaf)));

        //close the file
        raf.close();
    }










    public void printProperties() throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");

        System.out.println("NUMBER OF NODES : " + (raf.length() / RECORD_SIZE));

        int tempcounter = 0;
        int morecounter = 0;
        for (int i = 0; i < (raf.length() / RECORD_SIZE); i++) {
            morecounter++;
            raf.seek(i*RECORD_SIZE);
            byte[] mask = new byte[RECORD_SIZE];
            raf.read(mask);
            Record temp = (Record) deserialize(mask);
            if (isEmptyArray(temp.childIndex)) {
                System.out.println("THIS NODE "+ temp.index + " HAS 0 POINTERS TO IT'S CHILD NODES");///////////////////////////////////////////////////////////////////////this verifies the issue I'm having!!!!!!!!!!!!!!!!!!!!
            }
            if (!isEmptyArray(temp.childIndex)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.THIS NODE "+ temp.index + " HAS POINTERS TO IT'S CHILD NODES");
            }
            tempcounter += temp.NKeys;
        }
        raf.close();
        System.out.println("# keys : " + tempcounter);////////////////NUMBER OF KEYS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("# nodes : " + morecounter);
    }

    public void printAt(int i) throws IOException, ClassNotFoundException {
        RandomAccessFile raf;
        raf = new RandomAccessFile(".\\Cache\\btreecacheNode", "r");

        raf.seek(i*RECORD_SIZE);
        byte[] mask = new byte[RECORD_SIZE];
        raf.read(mask);
        Record temp = (Record) deserialize(mask);
        raf.close();

        System.out.println("NKEYS IS : " + temp.NKeys);/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }



























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










    public void loadTree() throws IOException, ClassNotFoundException {
        loadTree(this.root);
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////THIS MUST BE WHAT IS WRONG////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int finalCounter = 0;
    int keyCount = 0;
    public void loadTree(Node r) throws IOException, ClassNotFoundException {
        finalCounter++;
        keyCount += r.NKeys;
//        if (isEmptyArray(r.childIndex)) {
//            System.out.println("THIS NODE "+ r.index + " HAS 0 POINTERS TO IT'S CHILD NODES");
//        }
        if (!r.isLeaf) {
            //link the node of the specified child node
            r.child[1 - 1] = getNode(r.childIndex[1 - 1]);/////////////////////////////////////because it is going through all the nodes and all the data does seem intact-----------
            //recursively link you the rest of the BTree
            loadTree(r.child[1 - 1]);
        }
        for (int i = 1; i <= r.NKeys; i++) {
            //link the node of the specified child node
            r.child[i - 1] = getNode(r.childIndex[i - 1]);////////////////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>it's almost like the data isn't attaching itself properly!!!!!!!!!!!!!!!!!!
            if (!r.isLeaf) {
                //link the node of the specified child node
                r.child[i + 1 - 1] = getNode(r.childIndex[i + 1 - 1]);///////////////////////////////
                //recursively link you the rest of the BTree
                loadTree(r.child[i + 1 - 1]);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////it is going through everything///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isEmptyArray(short[] objects) {
        for (int i = 0; i < objects.length; i++)
            if (objects[i] != 0)
                return false;
        return true;
    }



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
     * Returns the value corresponding to a specific index
     * @param k is the specified index to find the value in the btreecacheValue file
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
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















    public void diskWrite(Node n) throws IOException, ClassNotFoundException {
        //is node root = n >>> if so >>set the root's index to this index;n.index = root.index;>>>>>>>>>>>>>>>>>>>>>>>then update root!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (n == this.root) {
            n.index = root.index;
            System.out.print("ROOT NODE IS " + n.index);///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            for (int i = 0; i < n.NKeys; i++)
                System.out.print("\t" + n.key[i]);
            System.out.println();
            overwriteRootToFile();
        }
        overwriteNode(n);
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
            for (int j = 1; j <= t; j++) {
                z.child[j - 1] = y.child[j + t - 1];
                z.childIndex[j - 1] = y.childIndex[j + t - 1];///////////////////////////////DOES ORDER MATTER??? SHOULD I HAVE THIS COME BEFORE THE CHILD's assignment????
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

    public void BTreeInsert(short k, Exoplanet e) throws IOException, ClassNotFoundException {
        short valIndex = overwriteValue(e);
        Node r = this.root;

        if (r.NKeys == 2 * t - 1) {
            Node s = allocateNode();
            this.root = s;
            s.isLeaf = false;
            s.NKeys = 0;
            s.child[1 - 1] = r;
            s.childIndex[1 - 1] = r.index;//////////////////////////////////////////////////////////////////////////////////////////////this is the issue >>>>>>>>> i must have to do this somewhere else here to fix the problem BECAUSE THE ISSUE IS THAT the pointer ARRAY to the children nodes is empty
            BTreeSplitChild(s, (short) 1);
            BTreeInsertNonFull(s, k, valIndex);///////////DO I NEED TO PASS THE CHILDINDEX AROUND LIKE I DID FOR THE VALUEINDEX?????
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