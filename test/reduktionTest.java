import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class reduktionTest {

    @Test
    public void testReduktion(){
        ArrayList<String> Z = Main.generateZ();
        String result = Main.Reduktionsfunktion("29c3eea3f305d6b823f562ac4be35217", 0, Z);
        assertEquals("87inwgn", result);


    }

   @Test
   public void hexTesting(){
        BigInteger bi = new BigInteger("29c3eea3f305d6b823f562ac4be35217", 16);
        System.out.println("BigInteger " + bi);
        Long l = new BigInteger("29c3eea3f305d6b823f562ac4be35217", 16).longValue();
        System.out.println("long "+l);
        String in = Integer.toHexString(10);
        System.out.println("HexString " + in);


   }

}
