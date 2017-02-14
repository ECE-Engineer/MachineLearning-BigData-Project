/**
 * Created by Kyle Z on 2/1/2017.
 */
public class Exoplanets {
    private double A;   //Semi-major axis (AU)
    private double DEC; //Declination (@J200)
    private double RSTAR;   //Stellar radius (Sol radii)
    private int TSTAR;  //Effective temperature of host star as reported in KIC (k)
    private double KMAG;    //	Kepler magnitude (kmag)
    private int TPLANET;    //	Equilibrium temperature of planet, per Borucki et al. (k)
    private double T0;  //Time of transit center (BJD-2454900)
    private double UT0; //Uncertainty in time of transit center (+-jd)
    private double PER; //Period (days)
    private double RA;  //Right ascension (@J200)
    private double RPLANET; //Stellar radius (Sol radii)
    private double MSTAR;   //Derived stellar mass (msol)

    /**
     * Creates an Exoplanet object
     */
    Exoplanets(double au, double d, double rs, int ts, double km, int tp, double t, double u, double p, double r, double rp, double ms){
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
     */
    public double getA(){return A;}

    /**
     * Returns the Planetary radius (Earth radii)
     */
    public double getDEC(){return DEC;}

    /**
     * Returns the Stellar radius (Sol radii)
     */
    public double getRSTAR(){return RSTAR;}

    /**
     * Returns the Returns the Effective temperature of host star as reported in KIC (k)
     */
    public int getTSTAR(){return TSTAR;}

    /**
     * Returns the Kepler magnitude (kmag)
     */
    public double getKMAG(){return KMAG;}

    /**
     * Returns the Equilibrium temperature of planet, per Borucki et al. (k)
     */
    public int getTPLANET(){return TPLANET;}

    /**
     * Returns the Time of transit center (BJD-2454900)
     */
    public double getT0(){return T0;}

    /**
     * Returns the Uncertainty in time of transit center (+-jd)
     */
    public double getUT0(){return UT0;}

    /**
     * Returns the Period (days)
     */
    public double getPER(){return PER;}

    /**
     * Returns the Right ascension (@J200)
     */
    public double getRA(){return RA;}

    /**
     * Returns the Stellar radius (Sol radii)
     */
    public double getRPLANET(){return RPLANET;}

    /**
     * Returns the Derived stellar mass (msol)
     */
    public double getMSTAR(){return MSTAR;}
}