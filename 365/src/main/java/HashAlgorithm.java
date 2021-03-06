/**
 * @author Kyle Zeller
 * This class provides a way to store and access all of the exoplanet objects in an efficient manner.
 */

import java.util.ArrayList;

public class HashAlgorithm<K,V> {

    /**
     * @author Kyle Zeller
     * The constructor calls HT() to initialize the size of the HashTable.
     */
    HashAlgorithm() {
        this.HT();
    }

    static class Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;

        /**
         *Creates the key / value pairs
         * @param k is the unique identification value that the object has
         * @param v is the Exoplanet object
         * @param n is the Entry tuple
         * @param h is the unique hashing value to the unique key that was given
         */
        Entry(K k, V v, Entry<K,V> n, int h) {
            key = k;
            value = v;
            next = n;
            hash = h;
        }
    }

    /*tab.length should always be a power of 2*/
    Entry<K,V>[] tab;

    private final int INIT_SIZE = 16;

    /**
     * Initializes the size of the HashTable.
     */
    private void HT() {
        tab = new Entry[INIT_SIZE];    // selected to be a good starting size
    }

    /**
     *
     * Looks for a key already present in the hash table
     */
    public boolean containsKey(K key){
        int h = hashCode(key);
        Entry<K,V>[] t = tab;
        int i = h & (tab.length-1);
        for (Entry<K,V> e = t[i]; e != null; e=e.next){
            //see if there is a match
            if (e.hash == h && key.equals(e.key))
                return true;
        }
        return false;
    }

    /**
     * Retrieves an Exoplanet from the given key in the hash table
     * @param s is a key that will be used to access it's respective exoplanet object
     * @return returns an exoplanet object corresponding to the key given
     */
    public V getValue(K s) {
        //update --- handle collisions
        int h = hashCode(s);
        Entry<K,V>[] t = tab;
        int i = h & (tab.length-1);

        for (Entry<K,V> e = t[i]; e != null; e = e.next){
            //get the value from the end of the list
            if (e.hash == h && s.equals(e.key)){
                return e.value;
            }
        }

        return null;
    }

    /**
     * Retrieves all the keys in the hash table
     * @return returns the keys in the hash table
     */
    public ArrayList<K> keySet(){
        ArrayList<K> temp = new ArrayList<K>();

        //look for the values on the tuple array
        for (int k = 0; k < tab.length; ++k){
            //check to see if there is ALREADY a value at this key
            if (tab[k] != null){
                //add all the values on the linked list (including the first one)
                for (Entry<K,V> e = tab[k]; e != null; e = e.next){
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

    /**
     * Determines how many exoplanet objects are currently being stored in the hashtable
     * @return returns the amount of exoplanet objects currently stored in the hashtable
     */
    public int size() {
        int count = 0;

        //look for the values on the tuple array
        for (int k = 0; k < tab.length; ++k){
            //check to see if there is ALREADY a value at this key
            if (tab[k] != null){
                //count all the values on the linked list (including the first one)
                for (Entry<K,V> e = tab[k]; e != null; e = e.next){
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Checks to see if there is nothing in the hashtable
     * @return returns true if the hashtable is empty
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    int count = 0;

    /**
     * Adds an Exoplanet to the hash table
     * @param key is the unique identification value that the object has
     * @param value is the exoplanet that is going to be stored
     */
    public void put(K key, V value){
        //update
        int h = hashCode(key);
        Entry<K,V>[] t = tab;
        int i = h & (tab.length-1);

        for (Entry<K,V> e = t[i]; e != null; e = e.next){
            //check to see if there is ALREADY a value at this key
            if (e.hash == h && key.equals(e.key)){
                //lengthen the list
                e.value = value;
                return;
            }
        }


        //handle collisions
        //add --- make a new entry for a key, value tuple
        Entry<K,V> p = new Entry(key, value, t[i], h);
        t[i] = p;

        //resize --- the array
        int n = t.length;
        int c = ++count;
        //check if the size is smaller than the threshold
        if ((c/(float)t.length) < 0.75)
            return;

        //double the size of the array
        int newN = n << 1;
        Entry<K,V>[] newTab = new Entry[newN];

        for (int k = 0; k < n; ++k){
            Entry<K,V> e;
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
     * @param key is the unique identification value that the object has
     */
    public void remove(K key){
        //update --- handle collisions
        int h = hashCode(key);
        Entry<K,V>[] t = tab;
        int i = h & (tab.length-1);
        Entry<K,V> pred = null;
        Entry<K,V> p = t[i];

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
        Entry<K,V>[] newTab = new Entry[newN];

        for (int k = 0; k < n; ++k){
            Entry<K,V> e;
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
     * @param s is the unique identification value that the object has
     * @return returns the unique hashing value to the unique key that was given
     */
    private int hashCode(K s){
        char[] info = (s.toString()).toCharArray();

        int temp, i;
        for (temp = i = 0; i < (s.toString()).length(); ++i) {
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