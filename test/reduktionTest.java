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
        String result2 = Main.Reduktionsfunktion("12e2feb5a0feccf82a8d4172a3bd51c3", 1, Z);
        assertEquals("frrkiis", result2);
        String result3 = Main.Reduktionsfunktion("437988e45a53c01e54d21e5dc4ae658a", 2, Z);
        assertEquals("dues6fg", result3);
    }


}
