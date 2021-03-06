/**
 * @author Kyle Zeller
 * This class provides a way to USE all of the features of the kepler objects of interest with more precision.
 */

public class PrecisionExoplanet {
    private double A;        //Semi-major axis (AU)
    private double DEC;      //Declination (@J200)
    private double RSTAR;    //Stellar radius (Sol radii)
    private double TSTAR;    //Effective temperature of host star as reported in KIC (k)
    private double KMAG;     //Kepler magnitude (kmag)
    private double TPLANET;  //Equilibrium temperature of planet, per Borucki et al. (k)
    private double T0;       //Time of transit center (BJD-2454900)
    private double UT0;      //Uncertainty in time of transit center (+-jd)
    private double PER;      //Period (days)
    private double RA;       //Right ascension (@J200)
    private double RPLANET;  //Planetary radius (Sol radii)
    private double MSTAR;    //Derived stellar mass (msol)

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
    PrecisionExoplanet(double au, double d, double rs, double ts, double km, double tp, double t, double u, double p, double r, double rp, double ms){
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
    public double getA(){return A;}

    /**
     * Returns the Planetary radius (Earth radii)
     * @return returns the Declination (@J200)
     */
    public double getDEC(){return DEC;}

    /**
     * Returns the Stellar radius (Sol radii)
     * @return returns the Stellar radius (Sol radii)
     */
    public double getRSTAR(){return RSTAR;}

    /**
     * Returns the Effective temperature of host star as reported in KIC (k)
     * @return returns the Effective temperature of host star as reported in KIC (k)
     */
    public double getTSTAR(){return TSTAR;}

    /**
     * Returns the Kepler magnitude (kmag)
     * @return returns the Kepler magnitude (kmag)
     */
    public double getKMAG(){return KMAG;}

    /**
     * Returns the Equilibrium temperature of planet, per Borucki et al. (k)
     * @return returns the Equilibrium temperature of planet, per Borucki et al. (k)
     */
    public double getTPLANET(){return TPLANET;}

    /**
     * Returns the Time of transit center (BJD-2454900)
     * @return returns the Time of transit center (BJD-2454900)
     */
    public double getT0(){return T0;}

    /**
     * Returns the Uncertainty in time of transit center (+-jd)
     * @return returns the Uncertainty in time of transit center (+-jd)
     */
    public double getUT0(){return UT0;}

    /**
     * Returns the Period (days)
     * @return returns the Period (days)
     */
    public double getPER(){return PER;}

    /**
     * Returns the Right ascension (@J200)
     * @return returns the Right ascension (@J200)
     */
    public double getRA(){return RA;}

    /**
     * Returns the Planetary radius (Sol radii)
     * @return returns the Planetary radius (Sol radii)
     */
    public double getRPLANET(){return RPLANET;}

    /**
     * Returns the Derived stellar mass (msol)
     * @return returns the Derived stellar mass (msol)
     */
    public double getMSTAR(){return MSTAR;}

    /**
     * Sets the Semi-major axis (AU)
     */
    public void setA(double v){A = v;}

    /**
     * Sets the Planetary radius (Earth radii)
     */
    public void setDEC(double v){DEC = v;}

    /**
     * Sets the Stellar radius (Sol radii)
     */
    public void setRSTAR(double v){RSTAR = v;}

    /**
     * Sets the Effective temperature of host star as reported in KIC (k)
     */
    public void setTSTAR(double v){TSTAR = v;}

    /**
     * Sets the Kepler magnitude (kmag)
     */
    public void setKMAG(double v){KMAG = v;}

    /**
     * Sets the Equilibrium temperature of planet, per Borucki et al. (k)
     */
    public void setTPLANET(double v){TPLANET = v;}

    /**
     * Sets the Time of transit center (BJD-2454900)
     */
    public void setT0(double v){T0 = v;}

    /**
     * Sets the Uncertainty in time of transit center (+-jd)
     */
    public void setUT0(double v){UT0 = v;}

    /**
     * Sets the Period (days)
     */
    public void setPER(double v){PER = v;}

    /**
     * Sets the Right ascension (@J200)
     */
    public void setRA(double v){RA = v;}

    /**
     * Sets the Planetary radius (Sol radii)
     */
    public void setRPLANET(double v){RPLANET = v;}

    /**
     * Sets the Derived stellar mass (msol)
     */
    public void setMSTAR(double v){MSTAR = v;}
}