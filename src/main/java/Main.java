import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

public class Main {

    public static void main(String[] args) {
        generatePassword(generateZ(), lengthOfPassword);

    }

    static int lengthOfPassword = 7;


    /**
     * generate Z
     * @return
     */
    public static ArrayList<String> generateZ() {
        ArrayList<String> Z = new ArrayList();
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
    public static void generatePassword(ArrayList<String> Z, int lengthOfPassword) {
       ArrayList<String> firstPassword = new ArrayList<String>();
       firstPassword.add("0");
       firstPassword.add("0");
       firstPassword.add("0");
       firstPassword.add("0");
       firstPassword.add("0");
       firstPassword.add("0");
       firstPassword.add("0");

       ArrayList<String> password = firstPassword;

       int indexInPassword = lengthOfPassword-1;

       password = makePassword(password, indexInPassword,Z);

       System.out.println(password);

//       for (int i=0;i<2;i++) {
//           for (int indexInZ = 0; indexInZ < Z.size(); indexInZ++) {
//               password.set(indexInPassword, Z.get(indexInZ));
//               // password.add(indexInPassword, Z.get(indexInZ));
//               System.out.println(password);
//           }
//        indexInPassword--;
//       }
    }

    public static ArrayList<String> makePassword(ArrayList<String> password, int indexInPassword, ArrayList<String> Z){
        for (int indexInZ = 0; indexInZ < Z.size(); indexInZ++) {
            password.set(indexInPassword, Z.get(indexInZ));
            // password.add(indexInPassword, Z.get(indexInZ));
            //System.out.println(password);

            if (indexInPassword<=5) {
                for (int i = indexInPassword + 1; i <7; i++) {
                    for (int j = 0; j < Z.size(); j++) {
                        password.set(i, Z.get(j));
                        System.out.println(password);
                    }
                }
            }
        }

        if (indexInPassword-1>-1){
            makePassword(password, indexInPassword-1,Z);
        }
        return password;
    }

    ////Reduktionsfunktion
    //public void Reduktionsfunktion(ArrayList<String> Hashwert, int Stufe, ArrayList<String> Z){
    //    ArrayList<String> H = Hashwert + Stufe;
    //    for (int i = 0; i < L; i++){
    //        int roundI = H%Z.size();
    //        H = H / Z.size();
    //    }
    //    return H;
    //}

    //Create first 2000 passwords
}

