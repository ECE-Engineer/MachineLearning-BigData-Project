import java.io.*;

/**
 * @author Kyle Zeller
 * This class provides a way to store the keys of the hashtable and its related exoplanet object.
 * It also provides a way to look up an item very quickly.
 */

public class HashCache implements Serializable {
    private RandomAccessFile raf;
    private final short KEY_SIZE = 2;
    private final short OBJECT_SIZE = 157;
    private final short RECORD_SIZE = 159;

    public short getNumberOfObjects() throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        return (short) (raf.length()/(KEY_SIZE + OBJECT_SIZE));
    }

    public boolean isEmpty() throws IOException {
        return getNumberOfObjects() == 0;
    }

    public boolean exist() {
        File f = new File(".\\Cache\\hashcache");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    public void append(short k, Exoplanet v) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");

        //seek to the end of the file
        raf.seek(raf.length());
        //write the key to the file
        raf.writeShort(k);
        //write the value to the file
        raf.write(serialize(v));
        //close the file
        raf.close();
    }

    public void overwrite(short k, Exoplanet v) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");
        short objectCount = (short) (raf.length()/(KEY_SIZE + OBJECT_SIZE));
        byte[] objectMask = new byte[OBJECT_SIZE];

        for (int i = 0; i < objectCount; i++) {
            raf.seek(i*RECORD_SIZE);
            if (raf.readShort() == k) {
                //adjust the pointer
                raf.seek(i * RECORD_SIZE + KEY_SIZE);
                //overwrite the object
                raf.write(serialize(v));
                //break out of the loop
                break;
            }
        }
        //close the file
        raf.close();
    }

    public short[] getKeys() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        short objectCount = (short) (raf.length()/(KEY_SIZE + OBJECT_SIZE));
        short[] keys = new short[objectCount];

        for (int i = 0; i < objectCount; i++) {
            raf.seek(i*RECORD_SIZE);
            keys[i] = raf.readShort();
        }
        raf.close();

        return keys;
    }

    public boolean contains(short k) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        short objectCount = (short) (raf.length()/(KEY_SIZE + OBJECT_SIZE));

        for (int i = 0; i < objectCount; i++) {
            raf.seek(i*RECORD_SIZE);
            if (raf.readShort() == k) {
                //close the file
                raf.close();
                //return true
                return true;
            }
        }
        raf.close();

        return false;
    }

    public short getKey(int pos) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        //seek to the position specified
        raf.seek(pos);
        //get the key stored there
        short temp = raf.readShort();
        //close the file
        raf.close();
        //return the key
        return temp;
    }

    public Exoplanet getExoplanet(int pos) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        byte[] objectMask = new byte[OBJECT_SIZE];
        //seek to the position specified
        raf.seek(pos);
        //get the key stored there
        raf.read(objectMask);
        Exoplanet temp = (Exoplanet) deserialize(objectMask);
        //close the file
        raf.close();
        //return the key
        return temp;
    }

    public Exoplanet search(short k) throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        short objectCount = (short) (raf.length()/(KEY_SIZE + OBJECT_SIZE));
        byte[] objectMask = new byte[OBJECT_SIZE];

        for (int i = 0; i < objectCount; i++) {
            raf.seek(i*RECORD_SIZE);
            if (raf.readShort() == k) {
                //adjust the pointer
                raf.seek(i * RECORD_SIZE + KEY_SIZE);
                //get the object
                raf.read(objectMask);
                Exoplanet temp = (Exoplanet) deserialize(objectMask);
                //close the file
                raf.close();
                //return the object
                return temp;
            }
        }
        raf.close();

        return null;
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
}
