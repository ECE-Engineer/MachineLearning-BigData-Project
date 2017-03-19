import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Kyle Z on 2/10/2017.
 */
public class HashAlgorithmTest {
    @Test
    public void HT() throws Exception {

    }

    @Test
    public void containsKey() throws Exception {

    }

    @Test
    public void getValue() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

    @Test
    public void put() throws Exception {
        HashAlgorithm ht = new HashAlgorithm();
        int size = 1;
        String key = "1306.01";
        Exoplanet e = new Exoplanet(15, 5, 3, (short) 2, 76, (short) 35, 43, 23, 23, 235, 234, 7365);
        ht.put(key, e);

        assertEquals(ht.size(), size);
        assertEquals(ht.getValue(key), e);
    }

    @Test
    public void putMultiple() throws Exception {
        ArrayList<Exoplanet> exoplanets = new ArrayList<>();
        HashAlgorithm ht = new HashAlgorithm();

        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String key = i + ".01";
            temp.add(key);
            exoplanets.add(new Exoplanet(i, i, i, (short) i, i, (short) i, i, i, i, i, i, i));
            ht.put(key, exoplanets.get(i));
        }


        assertEquals(ht.size(), temp.size());

        for(int i = 0; i < temp.size(); i++){
            assertEquals(ht.getValue(temp.get(i)), exoplanets.get(i));
        }
    }

}