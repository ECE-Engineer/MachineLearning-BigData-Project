/**
 * Created by Kyle Z on 2/1/2017.
 */
public class Exoplanets {
    private double A;   //Semi-major axis (AU)
    private double DEC; //Planetary radius (Earth radii)
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

    public double getA(){return A;}

    public double getDEC(){return DEC;}

    public double getRSTAR(){return RSTAR;}

    public int getTSTAR(){return TSTAR;}

    public double getKMAG(){return KMAG;}

    public int getTPLANET(){return TPLANET;}

    public double getT0(){return T0;}

    public double getUT0(){return UT0;}

    public double getPER(){return PER;}

    public double getRA(){return RA;}

    public double getRPLANET(){return RPLANET;}

    public double getMSTAR(){return MSTAR;}
}