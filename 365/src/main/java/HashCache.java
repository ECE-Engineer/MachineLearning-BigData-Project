import java.io.*;
import java.time.LocalDateTime;

/**
 * @author Kyle Zeller
 * This class provides a way to store the API response on disk.
 */

public class HashCache implements Serializable {
    private RandomAccessFile raf;
    private final short TRIPLE_SIZE = 299;
    private final short TIMESTAMP_SIZE = 51;

    /**
     * Writes the API string tuple to disk.
     * @throws IOException is used for the IO exceptions that might occur
     * @param t is the API call which is a giant string
     * @param s is the API response which is a giant string
     */
    public void overwrite(Triple<String> t, String s) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");

        //seek to the start of the file
        raf.seek(0);
        //write the url key to the file
        raf.write(this.serialize(t));
        //write the api response to the file
        raf.write(serialize(s));
        //close the file
        raf.close();
    }

    /**
     * Retrieves the API response from disk.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns the API response from disk.
     */
    public String getResponse() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        final int RESPONSE_SIZE = (int) (raf.length() - TRIPLE_SIZE);
        byte[] objectMask = new byte[RESPONSE_SIZE];
        //seek to the position specified
        raf.seek(TRIPLE_SIZE);
        //get the key stored there
        raf.read(objectMask);
        String temp = (String) deserialize(objectMask);
        //close the file
        raf.close();
        //return the string
        return temp;
    }

    /**
     * Writes the API string tuple to disk at the end of the file.
     * @throws IOException is used for the IO exceptions that might occur
     * @param s is the API response which is a giant string
     */
    public void append(String s) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");

        //seek to the end of the file
        raf.seek(raf.length());
        //write the api response to the file
        raf.write(serialize(s));
        //close the file
        raf.close();
    }

    /**
     * Writes the localdatetime object to disk.
     * @throws IOException is used for the IO exceptions that might occur
     * @param l is the localdatetime object.
     */
    public void writeTimeStamp(LocalDateTime l) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\timecache", "rw");

        //seek to the start of the file
        raf.seek(0);
        //write the api response to the file
        raf.write(serialize(l));
        //close the file
        raf.close();
    }

    /**
     * Retrieves the localdatetime object from disk.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     * @return returns the localdatetime object.
     */
    public LocalDateTime readTimeStamp() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\timecache", "r");
        byte[] objectMask = new byte[TIMESTAMP_SIZE];
        //seek to the position specified
        raf.seek(0);
        //get the key stored there
        raf.read(objectMask);
        LocalDateTime temp = (LocalDateTime) deserialize(objectMask);
        //close the file
        raf.close();

        return temp;
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
