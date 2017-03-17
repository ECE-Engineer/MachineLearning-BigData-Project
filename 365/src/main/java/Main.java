import java.awt.*;

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
    public static void main(String[] args) {
        //make the gui visible
        //make the assignments in the list visible
        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            try {
                gui.loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            gui.setVisible(true);
        });
    }
}
