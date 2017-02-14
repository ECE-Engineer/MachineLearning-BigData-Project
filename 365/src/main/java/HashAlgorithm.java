/**
 * Created by Kyle Z on 2/1/2017.
 */


import java.util.ArrayList;

public class HashAlgorithm {

    /**
     * The constructor calls HT() to initialize the size of the HashTable.
     */
    HashAlgorithm() {
        this.HT();
    }

    /**
     * Creates the key / value pairs
     */
    static class Entry {
        final String key;
        Exoplanets value;
        Entry next;
        int hash;

        Entry(String k, Exoplanets v, Entry n, int h) {
            key = k;
            value = v;
            next = n;
            hash = h;
        }
    }

    /*tab.length should always be a power of 2*/
    Entry[] tab;

    /**
     * Initializes the size of the HashTable.
     */
    public void HT() {
        tab = new Entry[16];    // selected to be a good starting size
    }

    /**
     *
     * Looks for a key already present in the hash table
     */
    public boolean containsKey(String key){
        int h = hashCode(key);
        Entry[] t = tab;
        int i = h & (tab.length-1);
        for (Entry e = t[i]; e != null; e=e.next){
            //see if there is a match
            if (e.hash == h && key.equals(e.key))
                return true;
        }
        return false;
    }

    /**
     * Retrieves an Exoplanet from the given key in the hash table
     */
    public Exoplanets getValue(String s) {
        //update --- handle collisions
        int h = hashCode(s);
        Entry[] t = tab;
        int i = h & (tab.length-1);

        for (Entry e = t[i]; e != null; e = e.next){
            //get the value from the end of the list
            if (e.hash == h && s.equals(e.key)){
                return e.value;
            }
        }

        return null;
    }

    /**
     * Retrieves all the keys in the hash table
     */
    public ArrayList<String> keySet(){
        ArrayList<String> temp = new ArrayList<String>();

        //look for the values on the tuple array
        for (int k = 0; k < tab.length; ++k){
            //check to see if there is ALREADY a value at this key
            if (tab[k] != null){
                //add all the values on the linked list (including the first one)
                for (Entry e = tab[k]; e != null; e = e.next){
                    temp.add(e.key);
                }
            }
        }

        return temp;
    }

    /**
     * Clears everything in the hash table
     */
    public void clear() {   // remove all tuples
        //look for the values on the tuple array
        for (int k = 0; k < tab.length; ++k){
            //check to see if there is ALREADY a value at this key
            if (tab[k] != null){
                //remove all the values on the linked list (including the first one)
                tab[k] = null;
            }
        }
    }

    public int size() {
        int count = 0;

        //look for the values on the tuple array
        for (int k = 0; k < tab.length; ++k){
            //check to see if there is ALREADY a value at this key
            if (tab[k] != null){
                //count all the values on the linked list (including the first one)
                for (Entry e = tab[k]; e != null; e = e.next){
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Checks to see if there is nothing in the hash table
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    int count = 0;

    /**
     * Adds an Exoplanet to the hash table
     */
    public void put(String key, Exoplanets value){
        //update
        int h = hashCode(key);
        Entry[] t = tab;
        int i = h & (tab.length-1);

        for (Entry e = t[i]; e != null; e = e.next){
            //check to see if there is ALREADY a value at this key
            if (e.hash == h && key.equals(e.key)){
                //lengthen the list
                e.value = value;
                return;
            }
        }


        //handle collisions
        //add --- make a new entry for a key, value tuple
        Entry p = new Entry(key, value, t[i], h);
        t[i] = p;

        //resize --- the array
        int n = t.length;
        int c = ++count;
        //check if the size is smaller than the threshold
        if ((c/(float)t.length) < 0.75)
            return;

        //double the size of the array
        int newN = n << 1;
        Entry[] newTab = new Entry[newN];

        for (int k = 0; k < n; ++k){
            Entry e;
            while ((e = t[k]) != null){ // add to the linked list
                t[k] = e.next;
                int j = e.hash & (newN - 1);
                e.next = newTab[j];
                newTab[j] = e;
            }
            // add to the tuple array
            tab = newTab;
        }
    }

    /**
     * Removes an Exoplanet at the specified key
     */
    public void remove(String key){
        //update --- handle collisions
        int h = hashCode(key);
        Entry[] t = tab;
        int i = h & (tab.length-1);
        Entry pred = null;
        Entry p = t[i];

        while (p != null) {
            if (p.hash == h && key.equals(p.key)) {
                if (pred == null)
                    t[i] = p.next;
                else
                    pred.next = p.next;
                --count;
                return;
            }
            pred = p;
            p = p.next;
        }

        //remove --- remove an entry for a key, value tuple
        t[i] = null;

        //resize --- the array
        int n = t.length;
        int c = ++count;
        //check if the size is greater than the threshold
        if ((c/(float)t.length) > 0.25)
            return;

        //halve the size of the array
        int newN = n >>> 1;
        Entry[] newTab = new Entry[newN];

        for (int k = 0; k < n; ++k){
            Entry e;
            while ((e = t[k]) != null){ // add to the linked list
                t[k] = e.next;
                int j = e.hash & (newN - 1);
                e.next = newTab[j];
                newTab[j] = e;
            }
            // add to the tuple array
            tab = newTab;
        }
    }

    /**
     * Creates a unique hash value for the given
     */
    public int hashCode(String s){
        char[] info = s.toCharArray();

        int temp, i;
        for (temp = i = 0; i < s.length(); ++i) {
            temp += info[i];
            temp += (temp << 10);
            temp ^= (temp >>> 6);
        }
        temp += (temp << 3);
        temp ^= (temp >>> 11);
        temp += (temp << 15);
        return temp;
    }
}