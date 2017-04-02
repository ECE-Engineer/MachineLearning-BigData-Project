import java.awt.*;
import java.io.*;

/**
 * @author Kyle Zeller
 * The first part of this project is about providing a GUI that interfaces with a custom implementation of a HashTable Algorithm,
 * different similarity metrics used in big data analytics and machine learning, and very large data sets from
 * the Kepler Object API. The GUI will allow the user to display all the Kepler Objects of Interest, display only
 * Kepler Objects of Interest with selected features, or finding the most similar Kepler Object of Interest to the
 * one selected. An additional feature was also added to find the 2 most similar Kepler Objects of Interest in the
 * entire data set if needed.
 * The second part of this project is about implementing a custom persistent BTree structure, interfacing it with
 * the current state of the program and utilize k-means-clustering and z-score normalization to display the clustering
 * of the data to the user. The user may then select the number of clusters they want to make and which ones they want
 * to see.
 */
public class Main {
    /**
     * This invokes the graphical user interface, which displays it to the screen and listens for the button actions to be pressed
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //make the gui visible
        EventQueue.invokeLater(() -> {
            GUI gui = null;
            try {
                gui = new GUI();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                gui.generalLoad();

            } catch (Exception e) {
                e.printStackTrace();
            }
            gui.setVisible(true);
        });
    }
}