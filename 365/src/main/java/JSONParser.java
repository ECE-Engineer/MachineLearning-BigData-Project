/**
 * @author Kyle Zeller
 * This class provides a way to retrieve and parse the JSON formatted information coming from the database.
 * It after it parses the data, it can create a hashtable or an arraylist of tuples out of the data.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.*;

public class JSONParser {
    private HashAlgorithm<Short, Exoplanet> exoplanets = new HashAlgorithm<>();
    private ArrayList<Pair<Short, Exoplanet>> tuples = new ArrayList<>();
    private final String USER_AGENT = "Mozilla/5.0";
    private final int MAX_API_CALLS = 3;
    private int apiCounter = 1;
    private String totalAPIResponse = "";

    /**
     * Retrieves the overall API response as one string.
     * @return returns the overall API response as one string.
     */
    public String getAPIResponse() {
        return totalAPIResponse;
    }

    /**
     * Retrieves an arrayList of the key value pairs.
     * @return returns an arrayList of tuples.
     */
    public ArrayList<Pair<Short, Exoplanet>> getTupleList() {return tuples;}

    /**
     * Retrieves the constructed hashtable.
     * @return returns the constructed hashtable.
     */
    public HashAlgorithm<Short, Exoplanet> getHashTable() {return exoplanets;}

    /**
     * Clears out the hashtable.
     */
    public void clearHashTable() {exoplanets.clear();}

    /**
     * Clears out the arrayList of tuples.
     */
    public void clearTupleList() {tuples.clear();}

    /**
     * Pulls in the Kepler Object data, and builds up the API response.
     * @param u is the url with what and how much information to retrieve from the database.
     * @throws Exception is used for the IO exceptions that might occur.
     */
    // HTTP GET request
    public void sendGet(String u) throws Exception {
        try {
            URL obj = new URL(u);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            //take in the data
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();

            //handle any io exceptions that come up
            try {
                //check for the data and store it here
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }

            //construct the api response
            if (apiCounter == 1)
                totalAPIResponse += "[" + response.toString().replace("[","").replaceAll("]","");
            else if (apiCounter == MAX_API_CALLS)
                totalAPIResponse += ", " + response.toString().replace("[","").replaceAll("]","") + "]";
            else
                totalAPIResponse += ", " + response.toString().replace("[","").replaceAll("]","");
            //increment counter
            apiCounter++;
        }catch (UnknownHostException e) {
            System.out.println("Currently cannot connect to online API.");
            System.out.println("Please Try Again.");
            Runtime.getRuntime().exit(1);
        }
    }

    /**
     * Creates and populates the tuple arrayList using the API response.
     * @param s is the API response.
     */
    public void createTuples(String s) {
        //parse the data out of the JSON object
        JsonParser parser = new JsonParser();
        JsonObject obj1 = parser.parse("{         \"dataArray\": " + s + "         }").getAsJsonObject();
        JsonArray array = obj1.getAsJsonArray("dataArray");

        if (array.size() != 0){
            for (int i = 0; i < array.size(); i++) {
                JsonElement temp = array.get(i);
                JsonObject obj2 = temp.getAsJsonObject();

                //set a factor to cast the floating point number to an integer that can be worked with
                final Short MULT_FACTOR = 10;

                //set the index where the dot placeholder is present
                int index = obj2.get("KOI").toString().indexOf('.');

                short KOI = (short) ((short) (Short.parseShort(obj2.get("KOI").toString().substring(0, index)) * MULT_FACTOR) + Short.parseShort(Character.toString(obj2.get("KOI").toString().charAt(obj2.get("KOI").toString().length()-1)))); //Object of Interest number

                //set default fields
                float A = 0;
                float DEC = 0;
                float RSTAR = 0;
                short TSTAR = 0;
                float KMAG = 0;
                short TPLANET = 0;
                float T0 = 0;
                float UT0 = 0;
                float PER = 0;
                float RA = 0;
                float RPLANET = 0;
                float MSTAR = 0;

                //set all the actual values
                if(!obj2.get("A").isJsonNull())
                    A = Float.parseFloat(obj2.get("A").toString());   //Semi-major axis (AU)

                if(!obj2.get("DEC").isJsonNull())
                    DEC = Float.parseFloat(obj2.get("DEC").toString());   //Planetary radius (Earth radii)

                if(!obj2.get("RSTAR").isJsonNull())
                    RSTAR = Float.parseFloat(obj2.get("RSTAR").toString());   //Stellar radius (Sol radii)

                if(!obj2.get("TSTAR").isJsonNull())
                    TSTAR = Short.parseShort(obj2.get("TSTAR").toString());   ///Effective temperature of host star as reported in KIC (k)

                if(!obj2.get("KMAG").isJsonNull())
                    KMAG = Float.parseFloat(obj2.get("KMAG").toString());   //	Kepler magnitude (kmag)

                if(!obj2.get("TPLANET").isJsonNull())
                    TPLANET = Short.parseShort(obj2.get("TPLANET").toString());   //	Equilibrium temperature of planet, per Borucki et al. (k)

                if(!obj2.get("T0").isJsonNull())
                    T0 = Float.parseFloat(obj2.get("T0").toString());   //Time of transit center (BJD-2454900)

                if(!obj2.get("UT0").isJsonNull())
                    UT0 = Float.parseFloat(obj2.get("UT0").toString());   //Uncertainty in time of transit center (+-jd)

                if(!obj2.get("PER").isJsonNull())
                    PER = Float.parseFloat(obj2.get("PER").toString());   //Uncertainty in time of transit center (+-jd)

                if(!obj2.get("RA").isJsonNull())
                    RA = Float.parseFloat(obj2.get("RA").toString());   //Period (days)

                if(!obj2.get("RPLANET").isJsonNull())
                    RPLANET = Float.parseFloat(obj2.get("RPLANET").toString());   //Declination (@J200)

                if(!obj2.get("MSTAR").isJsonNull())
                    MSTAR = Float.parseFloat(obj2.get("MSTAR").toString());   //Right ascension (@J200)

                //create an exoplanet and add it to the hashtable
                Exoplanet exoplanet = new Exoplanet(A, DEC, RSTAR, TSTAR, KMAG, TPLANET, T0, UT0, PER, RA, RPLANET, MSTAR);
                tuples.add(new Pair<Short, Exoplanet> (KOI, exoplanet));
            }
        }
    }

    /**
     * Creates and populates the hashtable using the API response.
     * @param s is the API response.
     */
    public void createHashTable(String s) {
        //parse the data out of the JSON object
        JsonParser parser = new JsonParser();
        JsonObject obj1 = parser.parse("{         \"dataArray\": " + s + "         }").getAsJsonObject();
        JsonArray array = obj1.getAsJsonArray("dataArray");

        if (array.size() != 0){
            for (int i = 0; i < array.size(); i++) {
                JsonElement temp = array.get(i);
                JsonObject obj2 = temp.getAsJsonObject();

                //set a factor to cast the floating point number to an integer that can be worked with
                final Short MULT_FACTOR = 10;

                //set the index where the dot placeholder is present
                int index = obj2.get("KOI").toString().indexOf('.');

                short KOI = (short) ((short) (Short.parseShort(obj2.get("KOI").toString().substring(0, index)) * MULT_FACTOR) + Short.parseShort(Character.toString(obj2.get("KOI").toString().charAt(obj2.get("KOI").toString().length()-1)))); //Object of Interest number

                //set default fields
                float A = 0;
                float DEC = 0;
                float RSTAR = 0;
                short TSTAR = 0;
                float KMAG = 0;
                short TPLANET = 0;
                float T0 = 0;
                float UT0 = 0;
                float PER = 0;
                float RA = 0;
                float RPLANET = 0;
                float MSTAR = 0;

                //set all the actual values
                if(!obj2.get("A").isJsonNull())
                    A = Float.parseFloat(obj2.get("A").toString());   //Semi-major axis (AU)

                if(!obj2.get("DEC").isJsonNull())
                    DEC = Float.parseFloat(obj2.get("DEC").toString());   //Planetary radius (Earth radii)

                if(!obj2.get("RSTAR").isJsonNull())
                    RSTAR = Float.parseFloat(obj2.get("RSTAR").toString());   //Stellar radius (Sol radii)

                if(!obj2.get("TSTAR").isJsonNull())
                    TSTAR = Short.parseShort(obj2.get("TSTAR").toString());   ///Effective temperature of host star as reported in KIC (k)

                if(!obj2.get("KMAG").isJsonNull())
                    KMAG = Float.parseFloat(obj2.get("KMAG").toString());   //	Kepler magnitude (kmag)

                if(!obj2.get("TPLANET").isJsonNull())
                    TPLANET = Short.parseShort(obj2.get("TPLANET").toString());   //	Equilibrium temperature of planet, per Borucki et al. (k)

                if(!obj2.get("T0").isJsonNull())
                    T0 = Float.parseFloat(obj2.get("T0").toString());   //Time of transit center (BJD-2454900)

                if(!obj2.get("UT0").isJsonNull())
                    UT0 = Float.parseFloat(obj2.get("UT0").toString());   //Uncertainty in time of transit center (+-jd)

                if(!obj2.get("PER").isJsonNull())
                    PER = Float.parseFloat(obj2.get("PER").toString());   //Uncertainty in time of transit center (+-jd)

                if(!obj2.get("RA").isJsonNull())
                    RA = Float.parseFloat(obj2.get("RA").toString());   //Period (days)

                if(!obj2.get("RPLANET").isJsonNull())
                    RPLANET = Float.parseFloat(obj2.get("RPLANET").toString());   //Declination (@J200)

                if(!obj2.get("MSTAR").isJsonNull())
                    MSTAR = Float.parseFloat(obj2.get("MSTAR").toString());   //Right ascension (@J200)

                //create an exoplanet and add it to the hashtable
                Exoplanet exoplanet = new Exoplanet(A, DEC, RSTAR, TSTAR, KMAG, TPLANET, T0, UT0, PER, RA, RPLANET, MSTAR);
                exoplanets.put(KOI, exoplanet);
            }
        }
    }
}