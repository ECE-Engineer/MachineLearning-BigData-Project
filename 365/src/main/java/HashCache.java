import java.io.*;

/**
 * @author Kyle Zeller
 * This class provides a way to store the API responses as a string.
 */

public class HashCache implements Serializable {
    private RandomAccessFile raf;

    public void append(String s) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");

        //seek to the end of the file
        raf.seek(raf.length());
        //write the api response to the file
        raf.write(serialize(s));
        //close the file
        raf.close();
    }

    public void overwrite(String s) throws IOException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "rw");

        //seek to the start of the file
        raf.seek(0);
        //write the api response to the file
        raf.write(serialize(s));
        //close the file
        raf.close();
    }

    public String getResponse() throws IOException, ClassNotFoundException {
        raf = new RandomAccessFile(".\\Cache\\hashcache", "r");
        final int RESPONSE_SIZE = (int) raf.length();
        byte[] objectMask = new byte[RESPONSE_SIZE];
        //seek to the position specified
        raf.seek(0);
        //get the key stored there
        raf.read(objectMask);
        String temp = (String) deserialize(objectMask);
        //close the file
        raf.close();
        //return the string
        return temp;
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
