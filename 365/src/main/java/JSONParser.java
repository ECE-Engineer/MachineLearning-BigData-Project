/**
 * @author Kyle Zeller
 * This class provides a way to retrieve and parse the JSON formatted information coming from the database.
 * It after it parses the data, it will create Exoplanet objects, and store them in the hash table.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;

public class JSONParser {
    public HashAlgorithm<String,Exoplanets> exoplanets = new HashAlgorithm<>();
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * Pulls in the Kepler Object data, creates Exoplanet objects, and stores them in the hash table
     * @param u is the url with what and how much information to retrieve from the database
     * @throws Exception
     */
    // HTTP GET request
    public void sendGet(String u) throws Exception {
        URL obj = new URL(u);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        try {
            //take in the data
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();

            //check for the data and store it here
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //parse the data out of the JSON object
            JsonParser parser = new JsonParser();
            JsonObject obj1 = parser.parse("{         \"dataArray\": " + response.toString() + "         }").getAsJsonObject();
            JsonArray array = obj1.getAsJsonArray("dataArray");

            if (array.size() != 0){
                for (int i = 0; i < array.size(); i++) {
                    JsonElement temp = array.get(i);
                    JsonObject obj2 = temp.getAsJsonObject();

                    String KOI = obj2.get("KOI").toString(); //Object of Interest number
                    double A;
                    if(!obj2.get("A").isJsonNull())
                        A = Double.parseDouble(obj2.get("A").toString());   //Semi-major axis (AU)
                    else
                        A = 0;

                    double DEC;
                    if(!obj2.get("DEC").isJsonNull())
                        DEC = Double.parseDouble(obj2.get("DEC").toString());   //Planetary radius (Earth radii)
                    else
                        DEC = 0;

                    double RSTAR;
                    if(!obj2.get("RSTAR").isJsonNull())
                        RSTAR = Double.parseDouble(obj2.get("RSTAR").toString());   //Stellar radius (Sol radii)
                    else
                        RSTAR = 0;

                    int TSTAR;
                    if(!obj2.get("TSTAR").isJsonNull())
                        TSTAR = Integer.parseInt(obj2.get("TSTAR").toString());   ///Effective temperature of host star as reported in KIC (k)
                    else
                        TSTAR = 0;

                    double KMAG;
                    if(!obj2.get("KMAG").isJsonNull())
                        KMAG = Double.parseDouble(obj2.get("KMAG").toString());   //	Kepler magnitude (kmag)
                    else
                        KMAG = 0;

                    int TPLANET;
                    if(!obj2.get("TPLANET").isJsonNull())
                        TPLANET = Integer.parseInt(obj2.get("TPLANET").toString());   //	Equilibrium temperature of planet, per Borucki et al. (k)
                    else
                        TPLANET = 0;

                    double T0;
                    if(!obj2.get("T0").isJsonNull())
                        T0 = Double.parseDouble(obj2.get("T0").toString());   //Time of transit center (BJD-2454900)
                    else
                        T0 = 0;

                    double UT0;
                    if(!obj2.get("UT0").isJsonNull())
                        UT0 = Double.parseDouble(obj2.get("UT0").toString());   //Uncertainty in time of transit center (+-jd)
                    else
                        UT0 = 0;

                    double PER;
                    if(!obj2.get("PER").isJsonNull())
                        PER = Double.parseDouble(obj2.get("PER").toString());   //Uncertainty in time of transit center (+-jd)
                    else
                        PER = 0;

                    double RA;
                    if(!obj2.get("RA").isJsonNull())
                        RA = Double.parseDouble(obj2.get("RA").toString());   //Period (days)
                    else
                        RA = 0;

                    double RPLANET;
                    if(!obj2.get("RPLANET").isJsonNull())
                        RPLANET = Double.parseDouble(obj2.get("RPLANET").toString());   //Declination (@J200)
                    else
                        RPLANET = 0;

                    double MSTAR;
                    if(!obj2.get("MSTAR").isJsonNull())
                        MSTAR = Double.parseDouble(obj2.get("MSTAR").toString());   //Right ascension (@J200)
                    else
                        MSTAR = 0;

                    //create an exoplanet and add it to the hashtable
                    Exoplanets exoplanet = new Exoplanets(A, DEC, RSTAR, TSTAR, KMAG, TPLANET, T0, UT0, PER, RA, RPLANET, MSTAR);
                    exoplanets.put(KOI, exoplanet);
                }
            }
        }catch (java.io.IOException e) {
            System.out.println(e.getMessage());

            JsonParser parser = new JsonParser();
            JsonObject obj1 = parser.parse("{         \"dataArray\": " + "[]" + "         }").getAsJsonObject();
            JsonArray array = obj1.getAsJsonArray("dataArray");

            if (array.size() != 0){
                for (int i = 0; i < array.size(); i++) {
                    JsonElement temp = array.get(i);
                    JsonObject obj2 = temp.getAsJsonObject();

                    String KOI = obj2.get("KOI").toString(); //Object of Interest number
                    double A;
                    if(!obj2.get("A").isJsonNull())
                        A = Double.parseDouble(obj2.get("A").toString());   //Semi-major axis (AU)
                    else
                        A = 0;

                    double DEC;
                    if(!obj2.get("DEC").isJsonNull())
                        DEC = Double.parseDouble(obj2.get("DEC").toString());   //Planetary radius (Earth radii)
                    else
                        DEC = 0;

                    double RSTAR;
                    if(!obj2.get("RSTAR").isJsonNull())
                        RSTAR = Double.parseDouble(obj2.get("RSTAR").toString());   //Stellar radius (Sol radii)
                    else
                        RSTAR = 0;

                    int TSTAR;
                    if(!obj2.get("TSTAR").isJsonNull())
                        TSTAR = Integer.parseInt(obj2.get("TSTAR").toString());   ///Effective temperature of host star as reported in KIC (k)
                    else
                        TSTAR = 0;

                    double KMAG;
                    if(!obj2.get("KMAG").isJsonNull())
                        KMAG = Double.parseDouble(obj2.get("KMAG").toString());   //	Kepler magnitude (kmag)
                    else
                        KMAG = 0;

                    int TPLANET;
                    if(!obj2.get("TPLANET").isJsonNull())
                        TPLANET = Integer.parseInt(obj2.get("TPLANET").toString());   //	Equilibrium temperature of planet, per Borucki et al. (k)
                    else
                        TPLANET = 0;

                    double T0;
                    if(!obj2.get("T0").isJsonNull())
                        T0 = Double.parseDouble(obj2.get("T0").toString());   //Time of transit center (BJD-2454900)
                    else
                        T0 = 0;

                    double UT0;
                    if(!obj2.get("UT0").isJsonNull())
                        UT0 = Double.parseDouble(obj2.get("UT0").toString());   //Uncertainty in time of transit center (+-jd)
                    else
                        UT0 = 0;

                    double PER;
                    if(!obj2.get("PER").isJsonNull())
                        PER = Double.parseDouble(obj2.get("PER").toString());   //Uncertainty in time of transit center (+-jd)
                    else
                        PER = 0;

                    double RA;
                    if(!obj2.get("RA").isJsonNull())
                        RA = Double.parseDouble(obj2.get("RA").toString());   //Period (days)
                    else
                        RA = 0;

                    double RPLANET;
                    if(!obj2.get("RPLANET").isJsonNull())
                        RPLANET = Double.parseDouble(obj2.get("RPLANET").toString());   //Declination (@J200)
                    else
                        RPLANET = 0;

                    double MSTAR;
                    if(!obj2.get("MSTAR").isJsonNull())
                        MSTAR = Double.parseDouble(obj2.get("MSTAR").toString());   //Right ascension (@J200)
                    else
                        MSTAR = 0;

                    //create an exoplanet and add it to the hashtable
                    Exoplanets exoplanet = new Exoplanets(A, DEC, RSTAR, TSTAR, KMAG, TPLANET, T0, UT0, PER, RA, RPLANET, MSTAR);
                    exoplanets.put(KOI, exoplanet);
                }
            }
        }
    }
}