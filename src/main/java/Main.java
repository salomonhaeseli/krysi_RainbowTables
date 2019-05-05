import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> password = generateFirstPassword();
        ArrayList<String> passwords = getPasswords(amountOfPasswords, password,lengthOfPassword); //Arraylist to save the first 2000 passwords
        generateHash("0000000");
        ArrayList<String> output = getReducedPasswords(amountOfPasswords, passwords); //Arraylist to save the 2000 reduced hashes
        System.out.println(output);

    }

    static int lengthOfPassword = 7;
    static int amountOfPasswords = 2000;

    /**
     * generate first password
     *
     * @return
     */
    public static ArrayList<String> generateFirstPassword() {
        ArrayList<String> firstPassword = new ArrayList<String>();
        firstPassword.add("0");
        firstPassword.add("0");
        firstPassword.add("0");
        firstPassword.add("0");
        firstPassword.add("0");
        firstPassword.add("0");
        firstPassword.add("0");

        return firstPassword;
    }

    /**
     * generate Z
     *
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
     *
     * @param password
     * @return
     */
    public static String generateHash(String password) {
        String hash = DigestUtils.md5Hex(String.valueOf(password));
        return hash;
    }

    /**
     * generatePassword, Idee von Till Dreier übernommen
     * @param password
     * @param lengthOfPassword
     * @param Z
     * @return
     */
    public static ArrayList<String> generatePassword(ArrayList<String> password, int lengthOfPassword, ArrayList<String> Z) {
        boolean increment = true;
        int index = lengthOfPassword - 1;

        while (increment) {
            if (password.get(index).equals("z")) {
                password.set(index, "0");
                index--;
            } else {
                for (int indexInZ = 0; indexInZ < Z.size() - 1; indexInZ++) {
                    if(password.get(index)==Z.get(indexInZ)){
                        password.set(index, Z.get(indexInZ+1));
                        break;
                    }
                }
                increment = false;
            }
        }
        return password;
    }


    /**
     * Reduktionsfunktion gemäss Folie 3.27.
     * @param Hashwert: Hashwert, welcher reduziert werden soll.
     * @param Stufe: Die Stufe, welche mit dem, in einen BigInteger verwandelte, Hashwert addiert wird.
     * @param Z: Liste der möglichen Zeichen im Passwort. Wird gebraucht um neues mögliches Passwort zu erhalten.
     * @return : Mögliches neues Passwort.
     */
    public static String Reduktionsfunktion(String Hashwert, int Stufe, ArrayList<String> Z){
        int L = lengthOfPassword;
        BigInteger H = new BigInteger(Hashwert,16);
        int zSize = Z.size();
        try{
            H = H.add(BigInteger.valueOf(Stufe));
        }catch(NumberFormatException e){
            System.out.println("Das geht nicht");
        }

        int[] roundI = new int[L];

        for (int i = 1; i < L+1; i++){
            roundI[L-i] = H.mod(BigInteger.valueOf(zSize)).intValue();
            H = H.divide(BigInteger.valueOf(zSize));
        }
        String hHelper = "";
        for(int I : roundI){
           hHelper = hHelper.concat(Z.get(I));
        }
        return hHelper;

    }

    public static ArrayList<String> getPasswords(int amountOfPasswords, ArrayList<String> password, int lengthOfPassword){
        ArrayList<String> passwords = new ArrayList<>();
        String passwordAsString = "";
        for (int i = 0; i < amountOfPasswords; i++){
            password = generatePassword(password, lengthOfPassword, generateZ());
            passwordAsString = password.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining("","", ""));
            passwords.add(i,passwordAsString);
        }
        return passwords;
    }

    public static ArrayList<String> getReducedPasswords(int amountOfPasswords, ArrayList<String> passwords){
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < amountOfPasswords; i++){
            String hash = generateHash(passwords.get(i));
            String reduced = Reduktionsfunktion(hash, i, generateZ());
            output.add(i, reduced);
        }
        return output;
    }
}
