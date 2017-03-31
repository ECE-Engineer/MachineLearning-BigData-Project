import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kyle Zeller
 * This project is about providing a GUI that interfaces with a custom implementation of a HashTable Algorithm,
 * different similarity metrics used in big data analytics and machine learning, and very large data sets from
 * the Kepler Object API. The GUI will allow the user to display all the Kepler Objects of Interest, display only
 * Kepler Objects of Interest with selected features, or finding the most similar Kepler Object of Interest to the
 * one selected. An additional feature was also added to find the 2 most similar Kepler Objects of Interest in the
 * entire data set if needed.
 */

public class GUI extends JFrame {
    /**
     * The constructor calls initComponents() to initialize all of the components of the graphical user interface
     */
    public GUI() throws IOException, ClassNotFoundException {
        initComponents();
    }

    /**
     * Initializes all the components within the GUI
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Kyle Zeller
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        typeSelect = new JComboBox<>();
        button1 = new JButton();
        label3 = new JLabel();
        textField1 = new JTextField();
        label4 = new JLabel();
        checkBox1 = new JCheckBox();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        textField2 = new JTextField();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(51, 204, 255));

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "365 HW1", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


            //---- label1 ----
            label1.setText("Welcome to StarLabs");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Segoe UI", Font.BOLD, 30));

            //---- label2 ----
            label2.setText("Please Select A Classifier Type:");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));

            //---- typeSelect ----
            typeSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                    "None",
                    "A",
                    "DEC",
                    "RSTAR",
                    "TSTAR",
                    "KMAG",
                    "TPLANET",
                    "T0",
                    "UT0",
                    "PER",
                    "RA",
                    "MSTAR"
            }));

            //---- button1 ----
            button1.setText("OK");
            button1.setBackground(new Color(51, 204, 255));
            button1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        okButton(evt);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //---- label3 ----
            label3.setText("Please Select An Object Limit (1 - 2500):");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD));

            //---- label4 ----
            label4.setText("Simularity Metric:");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD));

            //---- checkBox1 ----
            checkBox1.setText("Compares Interstellar Objects");
            checkBox1.setBackground(new Color(51, 204, 255));

            //---- label5 ----
            label5.setText("Please Set Classifier Type to None");

            //---- label6 ----
            label6.setText("The comparisons are made by: {period, planet size, star size, star temperature, and stellar mass}");

            //---- label7 ----
            label7.setText("Please Provide An Object Key:");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getStyle() | Font.BOLD));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(152, 152, 152)
                                                    .addComponent(label1))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(131, 131, 131)
                                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(151, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addGap(0, 45, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(checkBox1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGap(60, 60, 60)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addComponent(button1)
                                                                    .addGap(267, 267, 267))
                                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(18, 18, 18)
                                                                                    .addComponent(typeSelect, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
                                                                                    .addGap(18, 18, 18)
                                                                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(119, 119, 119))))))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label1)
                                    .addGap(81, 81, 81)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(typeSelect)
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkBox1)
                                            .addComponent(label5))
                                    .addGap(18, 18, 18)
                                    .addComponent(label6)
                                    .addGap(18, 18, 18)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button1)
                                    .addContainerGap())
            );
        }

        //======== panel2 ========
        {

            //======== scrollPane1 ========
            {

                //---- textArea1 ----
                textArea1.setEditable(false);
                scrollPane1.setViewportView(textArea1);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 408, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setSize(1190, 660);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Kyle Zeller
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JComboBox<String> typeSelect;
    private JButton button1;
    private JLabel label3;
    private JTextField textField1;
    private JLabel label4;
    private JCheckBox checkBox1;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField2;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    //load in all kepler objects from the API
    private final int MAX_AMOUNT = 2500;

    //create a hashcache object to use the data as needed
    private HashCache APIcache = new HashCache();

    /**
     * Loads the BTree either from the Kepler API or from disk.
     * @throws Exception is used for the exceptions that might occur
     */
    public void generalLoad() throws Exception {
        //check to see if the cache file exists
        if (new File(".\\Cache\\hashcache").exists()) {
            //get the current timestamp
            LocalDateTime currentTime = LocalDateTime.now();
            //get the past timestamp
            LocalDateTime pastTime = APIcache.readTimeStamp();
            //check to see if the timestamp is out of date
            if ((currentTime.getYear() > pastTime.getYear()) || (currentTime.getYear() == pastTime.getYear() && currentTime.getMonthValue() > pastTime.getMonthValue())) {////////////////////////////////////////
                //run the API loader b/c the data is out of date
                System.out.println("API");/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                loadFromAPI();
            } else {
                //run the file loader b/c the data is up to date
                System.out.println("FILE");/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                loadFromFile();
            }
        } else {
            //write a timestamp to file
            APIcache.writeTimeStamp(LocalDateTime.now());
            //create and write the API response and API call to file and populate the BTree
            System.out.println("API");/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            loadFromAPI();
        }
    }

    //create a BTree to quickly find information
    BTree btree;



    /**
     * Loads the BTree from file.
     * @throws IOException is used for the IO exceptions that might occur
     * @throws ClassNotFoundException is used for the class not found exceptions that might occur
     */
    public void loadFromFile() throws IOException, ClassNotFoundException {///////////////////////IT WON'T LET ME RUN THE btree variable because it is null
        //load the BTree
        btree = new BTree(btree);///////

        //recursively build up the BTree
//        btree.loadTree();




//        btree.printProperties();/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Gets all the data from the API, writes the API response to file, loads the API response into main memory, and then loads the BTree.
     * @throws Exception is used for the exceptions that might occur
     */
    public void loadFromAPI() throws Exception {
        //create a json parser object to collect the data from the API
        JSONParser http = new JSONParser();

        //clear everything out of the tuple list
        http.clearTupleList();

        //load all the API data
        String url1 = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":2000}}&limit=" + MAX_AMOUNT;
        http.sendGet(url1);
        String url2 = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000}}&limit=" + MAX_AMOUNT;
        http.sendGet(url2);
        String url3 = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true}}&limit=" + MAX_AMOUNT;
        http.sendGet(url3);

        //write the API response to file
        APIcache.overwrite(new Triple<String>(url1, url2, url3), http.getAPIResponse());

        //load the API response into main memory
        //create an arraylist of key, value tuples
        http.createTuples(APIcache.getResponse());


        //System.out.println(http.getTupleList().size());/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        //get all the keys and values of the kepler objects
        ArrayList<Pair<Short, Exoplanet>> tuples = http.getTupleList();

        //populate the BTree
        btree = new BTree();
        for (Pair<Short, Exoplanet> pair : tuples) {
            //insert a key into the BTree and the value into the file
            btree.BTreeInsert(pair.pairShort, pair.pairExoplanet);
        }

        //write the root node & degree of btree to the btreecache file
        btree.overwriteDegreeToFile();
        btree.overwriteRootToFile();
    }

    /**
     * Handles the requests of the user: including the type & amount of data to retrieve and toggling the similarity metric
     * @throws Exception is used for the exceptions that might occur
     * @param evt is the event handler for this button
     */
    private void okButton(java.awt.event.ActionEvent evt) throws Exception {





        Random rand = new Random();

        //set all the fields
        textArea1.setText("");
        String text_amount = textField1.getText();
        String keplerObject = typeSelect.getSelectedItem().toString();
        String text_key = textField2.getText();

        //get all the keys of the kepler objects
        ArrayList<Short> temp = btree.getKeys();

        System.out.println("KEYS ARRAYLIST SIZE : " + temp.size());///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////








        //set a limit for the user to give as an amount
        int USER_MAX_AMOUNT = temp.size();

        if (!text_amount.equalsIgnoreCase("") && Integer.parseInt(text_amount) < USER_MAX_AMOUNT) {

            //set all the fields
            int amount = Integer.parseInt(text_amount);

            if (keplerObject.equals("None")) {
                if (checkBox1.isSelected()) {

                    //set all the fields
                    String obj1 = "PER";
                    String obj2 = "TPLANET";
                    String obj3 = "RSTAR";
                    String obj4 = "TSTAR";
                    String obj5 = "MSTAR";

                    //remove all the data without information on the period, planet temperature, star temperature, stellar radius, or stellar mass
                    if (temp.size() != 0) {
                        for (int i = 0; i < temp.size(); i++) {
                            if (btree.BTreeSearch(temp.get(i)).getPER() == 0 || btree.BTreeSearch(temp.get(i)).getTPLANET() == 0 || btree.BTreeSearch(temp.get(i)).getRSTAR() == 0 || btree.BTreeSearch(temp.get(i)).getTSTAR() == 0 || btree.BTreeSearch(temp.get(i)).getMSTAR() == 0)
                                temp.remove(i);
                        }

                        //update the new limit size
                        USER_MAX_AMOUNT = temp.size();

                        //remove extra objects if the total size of the list still exceeds the number of requested items to compare
                        while (USER_MAX_AMOUNT > amount) {
                            //remove an item randomly
                            temp.remove(rand.nextInt(USER_MAX_AMOUNT));

                            //update the new limit size
                            USER_MAX_AMOUNT = temp.size();
                        }
                    }

                    if (!text_key.equalsIgnoreCase("")) {
                        //display the two most common kepler objects using a specified number of kepler objects and a specified kepler object key
                        //return the most common kepler object to the specified kepler object
                        if (temp.size() != 0){
                            Short[] sim = pearsonCorrelationCoefficient(temp, Short.parseShort(text_key));

                            for(int i = 0; i < sim.length; i++){
                                textArea1.append("KEY IS: " + sim[i] + "\n\t\tDATA IS: \n" + btree.BTreeSearch(sim[i]).toString() + "\n");
                            }
                        }
                    }
                    else {
                        //display the two most common kepler objects using a specified number of kepler objects
                        //return the two most common kepler objects
                        if (temp.size() != 0){
                            Short[] sim = pearsonCorrelationCoefficient(temp);

                            for(int i = 0; i < sim.length; i++){
                                textArea1.append("KEY IS: " + sim[i] + "\n\t\tDATA IS: \n" + btree.BTreeSearch(sim[i]).toString() + "\n");
                            }
                        }
                    }
                }
                else {
                    //display the specific amount of kepler objects
                    if (temp.size() != 0){
                        for(int i = 0; i < amount; i++){
                            textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    }
                }
            }
            else {
                //display the specific amount and only the matching type of kepler objects
                if (temp.size() != 0){
                    if (keplerObject.equalsIgnoreCase("A")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getA() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("DEC")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getDEC() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("RSTAR")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getRSTAR() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("TSTAR")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getTSTAR() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("KMAG")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getKMAG() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("TPLANET")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getTPLANET() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("T0")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getT0() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("UT0")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getUT0() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("PER")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getPER() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("RA")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getRA() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("RPLANET")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getRPLANET() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    } else if (keplerObject.equalsIgnoreCase("MSTAR")) {
                        for(int i = 0; i < amount; i++){
                            if (btree.BTreeSearch(temp.get(i)).getMSTAR() != 0)
                                textArea1.append("KEY IS: " + temp.get(i) + "\n\t\tDATA IS: \n" + btree.BTreeSearch(temp.get(i)).toString() + "\n");
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves the comparison score value between 2 Exoplanet objects using Euclidean Distance
     * @param key1 is a String that is used to retrieve the features of it's respective exoplanet object
     * @param key2 is a String that is used to retrieve the features of it's respective exoplanet object
     * @return returns the score value for the 2 objects based on their features
     */
    private double ED(Short key1, Short key2) throws IOException, ClassNotFoundException {   //returns the score value for the 2 objects based on their features
        return Math.sqrt(Math.pow(btree.BTreeSearch(key2).getPER()-btree.BTreeSearch(key1).getPER(), 2) + Math.pow(btree.BTreeSearch(key2).getTPLANET()-btree.BTreeSearch(key1).getTPLANET(), 2) + Math.pow(btree.BTreeSearch(key2).getRSTAR()-btree.BTreeSearch(key1).getRSTAR(), 2) + Math.pow(btree.BTreeSearch(key2).getTSTAR()-btree.BTreeSearch(key1).getTSTAR(), 2) + Math.pow(btree.BTreeSearch(key2).getMSTAR()-btree.BTreeSearch(key1).getMSTAR(), 2));
    }

    /**
     * Retrieves the comparison score value between 2 Exoplanet objects using Pearson Correlation Coefficient
     * @param key1 is a String that is used to retrieve the features of it's respective exoplanet object
     * @param key2 is a String that is used to retrieve the features of it's respective exoplanet object
     * @return returns the score value for the 2 objects based on their features
     */
    private double PCC(Short key1, Short key2) throws IOException, ClassNotFoundException {   //returns the score value for the 2 objects based on their features
        //The number of features
        final short FEATURE_AMOUNT = 5;

        //sum the product of the features between the objects
        float SOP = (btree.BTreeSearch(key2).getPER() * btree.BTreeSearch(key1).getPER())+(btree.BTreeSearch(key2).getTPLANET() * btree.BTreeSearch(key1).getTPLANET())+(btree.BTreeSearch(key2).getRSTAR() * btree.BTreeSearch(key1).getRSTAR())+(btree.BTreeSearch(key2).getTSTAR() * btree.BTreeSearch(key1).getTSTAR())+(btree.BTreeSearch(key2).getMSTAR() * btree.BTreeSearch(key1).getMSTAR());
        //sum the features of the first object
        float sum1 = (btree.BTreeSearch(key1).getPER())+(btree.BTreeSearch(key1).getTPLANET())+(btree.BTreeSearch(key1).getRSTAR())+(btree.BTreeSearch(key1).getTSTAR())+(btree.BTreeSearch(key1).getMSTAR());
        //sum the features of the second object
        float sum2 = (btree.BTreeSearch(key2).getPER())+(btree.BTreeSearch(key2).getTPLANET())+(btree.BTreeSearch(key2).getRSTAR())+(btree.BTreeSearch(key2).getTSTAR())+(btree.BTreeSearch(key2).getMSTAR());
        //square of the sum the features of the first object
        double squareSum1 = (Math.pow(btree.BTreeSearch(key1).getPER(), 2)+Math.pow((btree.BTreeSearch(key1).getTPLANET()), 2)+Math.pow((btree.BTreeSearch(key1).getRSTAR()), 2)+Math.pow((btree.BTreeSearch(key1).getTSTAR()), 2)+Math.pow((btree.BTreeSearch(key1).getMSTAR()), 2));
        //square of the sum the features of the second object
        double squareSum2 = (Math.pow(btree.BTreeSearch(key2).getPER(), 2)+Math.pow((btree.BTreeSearch(key2).getTPLANET()), 2)+Math.pow((btree.BTreeSearch(key2).getRSTAR()), 2)+Math.pow((btree.BTreeSearch(key2).getTSTAR()), 2)+Math.pow((btree.BTreeSearch(key2).getMSTAR()), 2));

        return ((SOP)-((sum1)*(sum2)/FEATURE_AMOUNT))/(Math.sqrt(((squareSum1)-(Math.pow(sum1, 2)/FEATURE_AMOUNT))*((squareSum2)-(Math.pow(sum2, 2)/FEATURE_AMOUNT))));
    }

    /**
     * Retrieves the most similar Exoplanet object to the one selected
     * @param keys is the list of all the keys to all of their respective exoplanet objects
     * @param testKey is a key to a specified exoplanet
     * @return returns a list of the exoplanet from the key specified and an exoplanet most similar to the one given
     */
    private Short[] pearsonCorrelationCoefficient(ArrayList<Short> keys, short testKey) throws IOException, ClassNotFoundException {  //Pearson Correlation Coefficient
        ArrayList<Short[]> largeList = new ArrayList<>();
        double pastVal = 0;
        double currentVal;
        short counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            if(i == 0 && keys.get(i) != testKey){
                pastVal = PCC(testKey, keys.get(i));
                largeList.add(new Short[]{testKey, keys.get(i)});
                counter++;
            }
            else {
                currentVal = PCC(testKey, keys.get(i));

                if (currentVal > pastVal && keys.get(i) != testKey) {
                    largeList.remove((counter-1));
                    largeList.add(new Short[]{testKey, keys.get(i)});
                    pastVal = currentVal;
                }
            }
        }
        return largeList.get(0);
    }

    /**
     * Retrieves the 2 most similar Exoplanet objects in the data set specified
     * @param keys is the list of all the keys to all of their respective exoplanet objects
     * @return returns a list of the 2 most similar exoplanets in the list
     */
    private Short[] pearsonCorrelationCoefficient(ArrayList<Short> keys) throws IOException, ClassNotFoundException {  //Pearson Correlation Coefficient
        ArrayList<Short[]> largeList = new ArrayList<>();
        double pastVal = 0;
        double currentVal;
        short counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < keys.size(); j++) {
                if(j == 0){
                    pastVal = PCC(keys.get(j+1), keys.get(i));
                    largeList.add(new Short[]{keys.get(i), keys.get(j)});
                    counter++;
                }
                else {
                    currentVal = PCC(keys.get(j), keys.get(i));

                    if (currentVal > pastVal) {
                        largeList.remove((counter-1));
                        largeList.add(new Short[]{keys.get(i), keys.get(j)});
                        pastVal = currentVal;
                    }
                }
            }
        }
        return largeList.get(0);
    }

    /**
     * Retrieves the most similar Exoplanet object to the one selected
     * @param keys is the list of all the keys to all of their respective exoplanet objects
     * @param testKey is a key to a specified exoplanet
     * @return returns a list of the exoplanet from the key specified and an exoplanet most similar to the one given
     */
    private Short[] euclideanDistance(ArrayList<Short> keys, short testKey) throws IOException, ClassNotFoundException {    //EUCLIDEAN DISTANCE
        ArrayList<Short[]> largeList = new ArrayList<>();
        double pastVal = 0;
        double currentVal;
        short counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            if(i == 0  && keys.get(i) != testKey){
                pastVal = ED(testKey, keys.get(i));
                largeList.add(new Short[]{testKey, keys.get(i)});
                counter++;
            }
            else {
                currentVal = ED(testKey, keys.get(i));

                if (currentVal < pastVal && keys.get(i) != testKey) {
                    largeList.remove((counter-1));
                    largeList.add(new Short[]{testKey, keys.get(i)});
                    pastVal = currentVal;
                }
            }
        }
        return largeList.get(0);
    }

    /**
     * Retrieves the 2 most similar Exoplanet objects in the data set specified
     * @param keys is the list of all the keys to all of their respective exoplanet objects
     * @return returns a list of the 2 most similar exoplanets in the list
     */
    private Short[] euclideanDistance(ArrayList<Short> keys) throws IOException, ClassNotFoundException {    //EUCLIDEAN DISTANCE
        ArrayList<Short[]> largeList = new ArrayList<>();
        double pastVal = 0;
        double currentVal;
        short counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < keys.size(); j++) {
                if(j == 0){
                    pastVal = ED(keys.get(j+1), keys.get(i));
                    largeList.add(new Short[]{keys.get(i), keys.get(j)});
                    counter++;
                }
                else {
                    currentVal = ED(keys.get(j), keys.get(i));

                    if (currentVal < pastVal) {
                        largeList.remove((counter-1));
                        largeList.add(new Short[]{keys.get(i), keys.get(j)});
                        pastVal = currentVal;
                    }
                }
            }
        }
        return largeList.get(0);
    }


//    public ArrayList<Exoplanet> kMeansClustering(ArrayList<Short> keys, int clusterAmount) throws IOException, ClassNotFoundException {
//        final int ITERATIONS = 30;
//
//
//        //ArrayList<Exoplanet> largeList = new ArrayList<>();
//
//        ArrayList<ArrayList<Short>> clusters = new ArrayList<>(clusterAmount);
//        Random rand = new Random();
//        //select N centroids at random//////////////////////////////////////MAKE THE CORRESPONDING CHANGES TO MAKE THESE THE EXOPLANET OBJECTS
//        ArrayList<Short> temp = keys;
//        for (int i = 0; i < clusterAmount; i++) {
//            short key = keys.get(rand.nextInt(temp.size()));
//            ArrayList<Short> temp2 = new ArrayList<>();
//            temp2.add(key);
//            clusters.add(i, temp2);
//            temp.remove(key);
//        }
//
//        for (int z = 0; z < ITERATIONS; z++) {
//            double[] centroidValues = new double[clusterAmount];
//            if (z == 0) {
//                //calculate the distance of every data point to all the centroids to determine which cluster to place the point into
//                for (short k : keys) {
//                    //calculate one point to every centroid and place it into a cluster
//                    //the clusters are labeled 0 through n-1 -->> so make a list of values the sie of the amount of clusters and the index of the one that is most similar USE THE INDEX to put the corresponding value into the specific cluster
//                    double[] similarityValues = new double[clusterAmount];
//                    for (int i = 0; i < similarityValues.length; i++) {
//                        similarityValues[i] = ED(clusters.get(i).get(0), k);////it is assumed here that the centroid will always be located at the the FIRST location in every cluster!
//                    }
//                    //find the best similarity value
//                    int position = 0;
//                    for (int i = 0; i < similarityValues.length-1; i++) {
//                        if (similarityValues[i+1] < similarityValues[i])
//                            position = i;
//                    }
//                    //add to the cluster
//                    clusters.get(position).add(k);
//                }
//
//                //recalculate the new centroid for every cluster
//                for (int i = 0; i < clusterAmount; i++) {
//                    //get a new centroid as an average of all the points in that cluster
//                    double totalSum = 0;
//                    for (int j = 0; j < clusters.get(i).size(); j++) {
//                        totalSum += SUM(clusters.get(i).get(j));
//                    }
//                    totalSum /= clusters.get(i).size();
//                    centroidValues[i] = totalSum;/////////////////////////////////////////when repeating, just look at these for the centroids INSTEAD OF THE ARRAYLIST OF ARRAYLIST FOR THE CENTROIDS!
//                }
//            }
//            else {
//                //calculate the distance of every data point to all the centroids to determine which cluster to place the point into
//                for (short k : keys) {
//                    //calculate one point to every centroid and place it into a cluster
//                    //the clusters are labeled 0 through n-1 -->> so make a list of values the sie of the amount of clusters and the index of the one that is most similar USE THE INDEX to put the corresponding value into the specific cluster
//                    double[] similarityValues = new double[clusterAmount];
//                    for (int i = 0; i < similarityValues.length; i++) {
//                        similarityValues[i] = ED(centroidValues[i], k);////it is assumed here that the centroid will always be located at the the FIRST location in every cluster!
//                    }
//                    //find the best similarity value
//                    int position = 0;
//                    for (int i = 0; i < similarityValues.length-1; i++) {
//                        if (similarityValues[i+1] < similarityValues[i])
//                            position = i;
//                    }
//                    //add to the cluster
//                    clusters.get(position).add(k);
//                }
//
//                //recalculate the new centroid for every cluster
//                for (int i = 0; i < clusterAmount; i++) {
//                    //get a new centroid as an average of all the points in that cluster
//                    double totalSum = 0;
//                    for (int j = 0; j < clusters.get(i).size(); j++) {
//                        totalSum += SUM(clusters.get(i).get(j));
//                    }
//                    totalSum /= clusters.get(i).size();
//                    centroidValues[i] = totalSum;/////////////////////////////////////////when repeating, just look at these for the centroids INSTEAD OF THE ARRAYLIST OF ARRAYLIST FOR THE CENTROIDS!
//                }
//            }
//
//
//        }
//
//        return largeList;
//    }




    /**
     * Retrieves the comparison score value between 2 Exoplanet objects using Euclidean Distance
     * @param key is a String that is used to retrieve the features of it's respective exoplanet object
     * @return returns the score value for the 2 objects based on their features
     */
    private double SUM(Short key) throws IOException, ClassNotFoundException {   //returns the score value for the 2 objects based on their features
        return btree.BTreeSearch(key).getPER() + btree.BTreeSearch(key).getTPLANET() + btree.BTreeSearch(key).getRSTAR() + btree.BTreeSearch(key).getTSTAR() + btree.BTreeSearch(key).getMSTAR();
    }

































}