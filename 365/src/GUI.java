import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    public GUI() {
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



    JSONParser http = new JSONParser();

    /**
     * Handles the requests of the user: including the type & amount of data to retrieve and toggling the similarity metric
     */
    private void okButton(java.awt.event.ActionEvent evt) throws Exception {
        http.exoplanets.clear();
        textArea1.setText("");
        String text = textField1.getText();
        String keplerObject = typeSelect.getSelectedItem().toString();
        String text2 = textField2.getText();

        if (!text.equalsIgnoreCase("")) {
            int amount = Integer.parseInt(text);    //it will look at this many interstellar objects

            if (keplerObject.equals("None")) {
                if (checkBox1.isSelected()) {
                    if (!text2.equalsIgnoreCase("")) {
                        String obj1 = "PER";
                        String obj2 = "TPLANET";
                        String obj3 = "RSTAR";
                        String obj4 = "TSTAR";
                        String obj5 = "MSTAR";

                        if (amount > 2000) {//This will compare period, planet temperature, stellar mass, star temperature, & star size
                            //give them 3 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":2000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        } else if (amount > 1000) {
                            //give then 2 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        } else {
                            //give the user 1 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\""+ obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        }
                        //RETURN THE 2 COMMONMOST OBJECTS by the period
                        ArrayList<String> temp = http.exoplanets.keySet();

                        if (temp.size() != 0){
                            String[] sim = pearsonCorrelationCoefficient(temp, text2);

                            for(int i = 0; i < sim.length; i++){
                                textArea1.append("KEY IS: \t" + sim[i] + " DATA IS: \n" + http.exoplanets.getValue(sim[i]).toString() + "\n");
                            }
                        }
                    }
                    else {
                        String obj1 = "PER";
                        String obj2 = "TPLANET";
                        String obj3 = "RSTAR";
                        String obj4 = "TSTAR";
                        String obj5 = "MSTAR";

                        if (amount > 2000) {//This will compare period, planet temperature, stellar mass, star temperature, & star size
                            //give them 3 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":2000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        } else if (amount > 1000) {
                            //give then 2 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                            url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\"" + obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        } else {
                            //give the user 1 parts of 1000 each
                            String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + obj1 + "\":{\"$exists\":true},\""+ obj2 + "\":{\"$exists\":true},\"" + obj3 + "\":{\"$exists\":true},\"" + obj4 + "\":{\"$exists\":true},\"" + obj5 + "\":{\"$exists\":true}}&limit=" + amount;
                            http.sendGet(url);
                        }
                        //RETURN THE 2 COMMONMOST OBJECTS by the period
                        ArrayList<String> temp = http.exoplanets.keySet();

                        if (temp.size() != 0){
                            String[] sim = pearsonCorrelationCoefficient(temp);

                            for(int i = 0; i < sim.length; i++){
                                textArea1.append("KEY IS: \t" + sim[i] + " DATA IS: \n" + http.exoplanets.getValue(sim[i]).toString() + "\n");
                            }
                        }
                    }
                }
                else {
                    if (amount > 2000) {
                        //give them 3 parts of 1000 each
                        String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":2000}}&limit=" + amount;
                        http.sendGet(url);
                        url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000}}&limit=" + amount;
                        http.sendGet(url);
                        url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true}}&limit=" + amount;
                        http.sendGet(url);
                    } else if (amount > 1000) {
                        //give then 2 parts of 1000 each
                        String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000}}&limit=" + amount;
                        http.sendGet(url);
                        url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true}}&limit=" + amount;
                        http.sendGet(url);
                    } else {
                        //give the user 1 parts of 1000 each
                        String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true}}&limit=" + amount;
                        http.sendGet(url);
                    }
                    ArrayList<String> temp = http.exoplanets.keySet();

                    if (temp.size() != 0){
                        for(String elements : temp){
                            textArea1.append("KEY IS: \t" + elements + " DATA IS: \n" + http.exoplanets.getValue(elements).toString() + "\n");
                        }
                    }
                }
            }
            else {
                if (amount > 2000) {
                    //give them 3 parts of 1000 each
                    String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":2000},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                    url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                    url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                } else if (amount > 1000) {
                    //give then 2 parts of 1000 each
                    String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$gt\":1000},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                    url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                } else {
                    //give the user 1 parts of 1000 each
                    String url = "http://asterank.com/api/kepler?query={\"KOI\":{\"$exists\":true},\"" + keplerObject + "\":{\"$exists\":true}}&limit=" + amount;
                    http.sendGet(url);
                }
                ArrayList<String> temp = http.exoplanets.keySet();

                if (temp.size() != 0){
                    for(String elements : temp){
                        textArea1.append("KEY IS: \t" + elements + " DATA IS: \n" + http.exoplanets.getValue(elements).toString() + "\n");
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
    private double ED(String key1, String key2) {   //returns the score value for the 2 objects based on their features
        return Math.sqrt(Math.pow(http.exoplanets.getValue(key2).getPER()-http.exoplanets.getValue(key1).getPER(), 2) + Math.pow(http.exoplanets.getValue(key2).getTPLANET()-http.exoplanets.getValue(key1).getTPLANET(), 2) + Math.pow(http.exoplanets.getValue(key2).getRSTAR()-http.exoplanets.getValue(key1).getRSTAR(), 2) + Math.pow(http.exoplanets.getValue(key2).getTSTAR()-http.exoplanets.getValue(key1).getTSTAR(), 2) + Math.pow(http.exoplanets.getValue(key2).getMSTAR()-http.exoplanets.getValue(key1).getMSTAR(), 2));
    }

    /**
     * Retrieves the comparison score value between 2 Exoplanet objects using Pearson Correlation Coefficient
     * @param key1 is a String that is used to retrieve the features of it's respective exoplanet object
     * @param key2 is a String that is used to retrieve the features of it's respective exoplanet object
     * @return returns the score value for the 2 objects based on their features
     */
    public double PCC(String key1, String key2) {   //returns the score value for the 2 objects based on their features
        //The number of features
        final int FEATURE_AMOUNT = 5;

        //sum the product of the features between the objects
        double SOP = (http.exoplanets.getValue(key2).getPER() * http.exoplanets.getValue(key1).getPER())+(http.exoplanets.getValue(key2).getTPLANET() * http.exoplanets.getValue(key1).getTPLANET())+(http.exoplanets.getValue(key2).getRSTAR() * http.exoplanets.getValue(key1).getRSTAR())+(http.exoplanets.getValue(key2).getTSTAR() * http.exoplanets.getValue(key1).getTSTAR())+(http.exoplanets.getValue(key2).getMSTAR() * http.exoplanets.getValue(key1).getMSTAR());
        //sum the features of the first object
        double sum1 = (http.exoplanets.getValue(key1).getPER())+(http.exoplanets.getValue(key1).getTPLANET())+(http.exoplanets.getValue(key1).getRSTAR())+(http.exoplanets.getValue(key1).getTSTAR())+(http.exoplanets.getValue(key1).getMSTAR());
        //sum the features of the second object
        double sum2 = (http.exoplanets.getValue(key2).getPER())+(http.exoplanets.getValue(key2).getTPLANET())+(http.exoplanets.getValue(key2).getRSTAR())+(http.exoplanets.getValue(key2).getTSTAR())+(http.exoplanets.getValue(key2).getMSTAR());
        //square of the sum the features of the first object
        double squareSum1 = (Math.pow(http.exoplanets.getValue(key1).getPER(), 2)+Math.pow((http.exoplanets.getValue(key1).getTPLANET()), 2)+Math.pow((http.exoplanets.getValue(key1).getRSTAR()), 2)+Math.pow((http.exoplanets.getValue(key1).getTSTAR()), 2)+Math.pow((http.exoplanets.getValue(key1).getMSTAR()), 2));
        //square of the sum the features of the second object
        double squareSum2 = (Math.pow(http.exoplanets.getValue(key2).getPER(), 2)+Math.pow((http.exoplanets.getValue(key2).getTPLANET()), 2)+Math.pow((http.exoplanets.getValue(key2).getRSTAR()), 2)+Math.pow((http.exoplanets.getValue(key2).getTSTAR()), 2)+Math.pow((http.exoplanets.getValue(key2).getMSTAR()), 2));

        return ((SOP)-((sum1)*(sum2)/FEATURE_AMOUNT))/(Math.sqrt(((squareSum1)-(Math.pow(sum1, 2)/FEATURE_AMOUNT))*((squareSum2)-(Math.pow(sum2, 2)/FEATURE_AMOUNT))));
    }

    /**
     * Retrieves the most similar Exoplanet object to the one selected
     * @param keys is the list of all the keys to all of their respective exoplanet objects
     * @param testKey is a key to a specified exoplanet
     * @return returns a list of the exoplanet from the key specified and an exoplanet most similar to the one given
     */
    private String[] pearsonCorrelationCoefficient(ArrayList<String> keys, String testKey){  //Pearson Correlation Coefficient
        ArrayList<String[]> largeList = new ArrayList<String[]>();
        double pastVal = 0;
        double currentVal;
        int counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            if(i == 0 && !keys.get(i).equalsIgnoreCase(testKey)){
                pastVal = PCC(testKey, keys.get(i));
                largeList.add(new String[]{testKey, keys.get(i)});
                counter++;
            }
            else {
                currentVal = PCC(testKey, keys.get(i));

                if (currentVal > pastVal && !keys.get(i).equalsIgnoreCase(testKey)) {
                    largeList.remove((counter-1));
                    largeList.add(new String[]{testKey, keys.get(i)});
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
    private String[] pearsonCorrelationCoefficient(ArrayList<String> keys){  //Pearson Correlation Coefficient
        ArrayList<String[]> largeList = new ArrayList<String[]>();
        double pastVal = 0;
        double currentVal;
        int counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < keys.size(); j++) {
                if(j == 0){
                    pastVal = PCC(keys.get(j+1), keys.get(i));
                    largeList.add(new String[]{keys.get(i), keys.get(j)});
                    counter++;
                }
                else {
                    currentVal = PCC(keys.get(j), keys.get(i));

                    if (currentVal > pastVal) {
                        largeList.remove((counter-1));
                        largeList.add(new String[]{keys.get(i), keys.get(j)});
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
    private String[] euclideanDistance(ArrayList<String> keys, String testKey){    //EUCLIDEAN DISTANCE
        ArrayList<String[]> largeList = new ArrayList<String[]>();
        double pastVal = 0;
        double currentVal;
        int counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            if(i == 0  && !keys.get(i).equalsIgnoreCase(testKey)){
                pastVal = ED(testKey, keys.get(i));
                largeList.add(new String[]{testKey, keys.get(i)});
                counter++;
            }
            else {
                currentVal = ED(testKey, keys.get(i));

                if (currentVal < pastVal && !keys.get(i).equalsIgnoreCase(testKey)) {
                    largeList.remove((counter-1));
                    largeList.add(new String[]{testKey, keys.get(i)});
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
    private String[] euclideanDistance(ArrayList<String> keys){    //EUCLIDEAN DISTANCE
        ArrayList<String[]> largeList = new ArrayList<String[]>();
        double pastVal = 0;
        double currentVal;
        int counter = 0;

        for(int i = 0; i < keys.size(); i++) {
            for (int j = 0; j < keys.size(); j++) {
                if(j == 0){
                    pastVal = ED(keys.get(j+1), keys.get(i));
                    largeList.add(new String[]{keys.get(i), keys.get(j)});
                    counter++;
                }
                else {
                    currentVal = ED(keys.get(j), keys.get(i));

                    if (currentVal < pastVal) {
                        largeList.remove((counter-1));
                        largeList.add(new String[]{keys.get(i), keys.get(j)});
                        pastVal = currentVal;
                    }
                }
            }
        }

        return largeList.get(0);
    }

    /**
     * This invokes the graphical user interface, which displays it to the screen and listens for the button actions to be pressed
     */
    public static void main(String[] args) {
        //make the gui visible
        //make the assignments in the list visible
        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}