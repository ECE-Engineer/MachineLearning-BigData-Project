/**
 * @author Kyle Zeller
 * This class provides a way to store all of the features of the kepler objects of interest.
 */

public class Exoplanets {
    private float A;        //Semi-major axis (AU)
    private float DEC;      //Declination (@J200)
    private float RSTAR;    //Stellar radius (Sol radii)
    private short TSTAR;    //Effective temperature of host star as reported in KIC (k)
    private float KMAG;     //Kepler magnitude (kmag)
    private short TPLANET;  //Equilibrium temperature of planet, per Borucki et al. (k)
    private float T0;       //Time of transit center (BJD-2454900)
    private float UT0;      //Uncertainty in time of transit center (+-jd)
    private float PER;      //Period (days)
    private float RA;       //Right ascension (@J200)
    private float RPLANET;  //Planetary radius (Sol radii)
    private float MSTAR;    //Derived stellar mass (msol)

    /**
     * Creates an Exoplanet object
     * @param au is Semi-major axis (AU)
     * @param d is Declination (@J200)
     * @param rs is Stellar radius (Sol radii)
     * @param ts is Effective temperature of host star as reported in KIC (k)
     * @param km is Kepler magnitude (kmag)
     * @param tp is Equilibrium temperature of planet, per Borucki et al. (k)
     * @param t is Time of transit center (BJD-2454900)
     * @param u is Uncertainty in time of transit center (+-jd)
     * @param p is Period (days)
     * @param r is Right ascension (@J200)
     * @param rp is Planetary radius (Sol radii)
     * @param ms is Derived stellar mass (msol)
     */
    Exoplanets(float au, float d, float rs, short ts, float km, short tp, float t, float u, float p, float r, float rp, float ms){
        A = au;
        DEC = d;
        RSTAR = rs;
        TSTAR = ts;
        KMAG = km;
        TPLANET = tp;
        T0 = t;
        UT0 = u;
        PER = p;
        RA = r;
        RPLANET = rp;
        MSTAR = ms;
    }

    /**
     * Creates a formatted string of all the properties of the Exoplanet object
     * @return returns all the data the exoplanet stores
     */
    public String toString(){return "Semi-major axis (AU):\t\t\t" + A + "\n" +
                                    "Declination (@J200):\t\t\t" + DEC + "\n" +
                                    "Stellar radius (Sol radii):\t\t\t" + RSTAR + "\n" +
                                    "Effective temperature of host star as reported in KIC (k):\t" + TSTAR + "\n" +
                                    "Kepler magnitude (kmag):\t\t\t" + KMAG + "\n" +
                                    "Equilibrium temperature of planet, per Borucki et al. (k):\t" + TPLANET + "\n" +
                                    "Time of transit center (BJD-2454900):\t\t" + T0 + "\n" +
                                    "Uncertainty in time of transit center (+-jd):\t\t" + UT0 + "\n" +
                                    "Period (days):\t\t\t\t" + PER + "\n" +
                                    "Right ascension (@J200):\t\t\t" + RA + "\n" +
                                    "Stellar radius (Sol radii):\t\t\t" + RPLANET + "\n" +
                                    "Derived stellar mass (msol):\t\t\t" + MSTAR + "\n";}

    /**
     * Returns the Semi-major axis (AU)
     * @return returns the Semi-major axis (AU)
     */
    public float getA(){return A;}

    /**
     * Returns the Planetary radius (Earth radii)
     * @return returns the Declination (@J200)
     */
    public float getDEC(){return DEC;}

    /**
     * Returns the Stellar radius (Sol radii)
     * @return returns the Stellar radius (Sol radii)
     */
    public float getRSTAR(){return RSTAR;}

    /**
     * Returns the Returns the Effective temperature of host star as reported in KIC (k)
     * @return returns the Effective temperature of host star as reported in KIC (k)
     */
    public short getTSTAR(){return TSTAR;}

    /**
     * Returns the Kepler magnitude (kmag)
     * @return returns the Kepler magnitude (kmag)
     */
    public float getKMAG(){return KMAG;}

    /**
     * Returns the Equilibrium temperature of planet, per Borucki et al. (k)
     * @return returns the Equilibrium temperature of planet, per Borucki et al. (k)
     */
    public short getTPLANET(){return TPLANET;}

    /**
     * Returns the Time of transit center (BJD-2454900)
     * @return returns the Time of transit center (BJD-2454900)
     */
    public float getT0(){return T0;}

    /**
     * Returns the Uncertainty in time of transit center (+-jd)
     * @return returns the Uncertainty in time of transit center (+-jd)
     */
    public float getUT0(){return UT0;}

    /**
     * Returns the Period (days)
     * @return returns the Period (days)
     */
    public float getPER(){return PER;}

    /**
     * Returns the Right ascension (@J200)
     * @return returns the Right ascension (@J200)
     */
    public float getRA(){return RA;}

    /**
     * Returns the Planetary radius (Sol radii)
     * @return returns the Planetary radius (Sol radii)
     */
    public float getRPLANET(){return RPLANET;}

    /**
     * Returns the Derived stellar mass (msol)
     * @return returns the Derived stellar mass (msol)
     */
    public float getMSTAR(){return MSTAR;}
}