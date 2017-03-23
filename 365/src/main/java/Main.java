import java.awt.*;
import java.io.*;

/**
 * @author Kyle Zeller
 * This project is about providing a GUI that interfaces with a custom implementation of a HashTable Algorithm,
 * different similarity metrics used in big data analytics and machine learning, and very large data sets from
 * the Kepler Object API. The GUI will allow the user to display all the Kepler Objects of Interest, display only
 * Kepler Objects of Interest with selected features, or finding the most similar Kepler Object of Interest to the
 * one selected. An additional feature was also added to find the 2 most similar Kepler Objects of Interest in the
 * entire data set if needed.
 */
public class Main {
    /**
     * This invokes the graphical user interface, which displays it to the screen and listens for the button actions to be pressed
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ////LOADER::::::

        //pull data from the API--------------------DONE

        //store the API responses in the hashcache--------------------DONE

        //make a TEMPORARY!!!!!! hashtable from the hashcache--------------------DONE

        //store data objects in the btree --------------------DONE------------------------------BUT NOT VERIFIED

        //(optionally) cluster the data

        //-------------------------

        //APPLICATION::::::

        //load the BTree with the data from the files

        //load or compute the clusters

        //display clusters and data samples

        //ALSO enable the user to look at specific data






        /**LOOK FOR AN ITEM*/
        /**does it exist in the hashcache file*/
        /**if it does NOT, then create a hashcache file, THEN find it in the Btree, get it, THEN add it to the hashcache file, THEN DO STUFF WITH THE ITEM*/
        /**if it DOES, then see if it contains the item*/
        /**if it does NOT, then find it in the Btree, get it, THEN add it to the hashcache file, THEN DO STUFF WITH THE ITEM*/
        /**if it DOES, then get the item, THEN DO STUFF WITH THE ITEM*/

        /**KEEP GOING??  which also means clicking the exit button*/
        /**if NO, then save & close the hashcache file, AND then write to the Btree cache file & save & close it*/
        /**if YES, then the GUI should simply continue*/


//        //make the gui visible
//        //make the assignments in the list visible
        EventQueue.invokeLater(() -> {
            GUI gui = null;
            try {
                gui = new GUI();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                /**do the Btree cache files exist*/
                /**if they do NOT, then create the Btree cache & populate it with the data, THEN load the data into the BTREE structure*/
                /**if they do, then check and see if it IS empty*/
                /**if it is NOT, start the GUI*/
                /**if it IS, populate it with the data, THEN load the data into the BTREE structure, THEN start the GUI*/
                gui.loadDataAPI();/////////////////////////////////////////this loads data from the API AND THEN this stores the API response in the hashcache
                gui.initFromFile();//make a TEMPORARY!!!!!! hashtable from the hashcache

            } catch (Exception e) {
                e.printStackTrace();
            }
            gui.setVisible(true);
        });
    }
}