import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> Z = generateZ();

    }

    int L = 7;


    /**
     * generate Z
     * @return
     */
    public static ArrayList<String> generateZ() {
        ArrayList<String> Z = new ArrayList<>();
        Z.add("0");
        Z.add("1");
        Z.add("2");
        Z.add("3");
        Z.add("4");
        Z.add("5");
        Z.add("6");
        Z.add("7");
        Z.add("8");
        Z.add("9");
        Z.add("a");
        Z.add("b");
        Z.add("c");
        Z.add("d");
        Z.add("e");
        Z.add("f");
        Z.add("g");
        Z.add("h");
        Z.add("i");
        Z.add("j");
        Z.add("k");
        Z.add("l");
        Z.add("m");
        Z.add("n");
        Z.add("o");
        Z.add("p");
        Z.add("q");
        Z.add("r");
        Z.add("s");
        Z.add("t");
        Z.add("u");
        Z.add("v");
        Z.add("w");
        Z.add("x");
        Z.add("y");
        Z.add("z");
        return Z;
    }


    /**
     * generate Hash
     * @param password
     * @return
     */
    public static String generateHash(String password) {
        String hash = DigestUtils.md5Hex(password);
        return hash;
    }


    //Generate password
    public static void generatePassword(){
        String start = "0000000";
    }

    //Reduktionsfunktion
    public void Reduktionsfunktion(ArrayList<String> Hashwert, int Stufe, ArrayList<String> Z){
        ArrayList<String> H = Hashwert + Stufe;

        for (int i = 0; i < L; i++){
            int roundI = H%Z.size();
            H = H / Z.size();
        }
        return H;
    }

    //Create first 2000 passwords
}

