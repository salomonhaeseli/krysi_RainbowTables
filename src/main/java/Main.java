import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> password = generateFirstPassword();
        ArrayList<String> passwords = getPasswords(amountOfPasswords, password,lengthOfPassword); //Arraylist to save the first 2000 passwords
        generateHash("0000000");
        ArrayList<String> output = getReducedPasswords(amountOfPasswords, passwords, 2000); //Arraylist to save the 2000 reduced hashes
        String found = findPassword(findPasswordFor,passwords,output,2000,generateZ());
        System.out.println(found);

    }

    static int lengthOfPassword = 7;
    static int amountOfPasswords = 2000;
    static String findPasswordFor = "1d56a37fb6b08aa709fe90e12ca59e12";

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

    /**
     *
     * @param amountOfPasswords: amount of passwords needed to be generated
     * @param password: first password
     * @param lengthOfPassword: length of password
     * @return: Arraylist of n passwords
     */
    public static ArrayList<String> getPasswords(int amountOfPasswords, ArrayList<String> password, int lengthOfPassword){
        ArrayList<String> passwords = new ArrayList<>();
        passwords.add(0,"0000000");
        String passwordAsString = "";
        for (int i = 1; i < amountOfPasswords; i++){
            password = generatePassword(password, lengthOfPassword, generateZ());
            passwordAsString = password.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining("","", ""));
            passwords.add(i,passwordAsString);
        }
        return passwords;
    }

    /**
     *
     * @param amountOfPasswords: amount of passwords needed to be generated
     * @param passwords: Arraylist of password which to be hashed/reduced
     * @param chainLength: lenght of chain
     * @return: Arraylist of hashed/reduced passwords n times
     */
    public static ArrayList<String> getReducedPasswords(int amountOfPasswords, ArrayList<String> passwords, int chainLength){
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < amountOfPasswords; i++){
            String current = passwords.get(i);
            for (int j=0; j < chainLength; j++){
                String hash = generateHash(current);
                current = Reduktionsfunktion(hash, j, generateZ());
            }
            output.add(i, current);
        }
        return output;
    }

    /**
     *
     * @param toBeFound: Hash value which needs to be found
     * @param startValues: Arraylist with initial password
     * @param endValues: Arraylist which passwords from end of chain
     * @param chainLength: length of chain
     * @param Z: list of possible chars at password
     * @return: password matching to hash value
     */
    public static String findPassword(String toBeFound, ArrayList<String> startValues, ArrayList<String> endValues, int chainLength, ArrayList<String> Z){
        String check = "";
        String foundEndValue = "";
        int times = 0;

        for(int k=0;k<chainLength;k++){

            int step = chainLength-times;
            check=toBeFound;

            for (int i=step; i<chainLength-1;i++){
                String reduced = Reduktionsfunktion(check,i,Z);
                String hash = generateHash(reduced);
                check = hash;
            }

            check=Reduktionsfunktion(check,chainLength-1,Z);

            for(int j =0;j<endValues.size();j++){
                if (check.equals(endValues.get(j))){
                    ArrayList<String> start = new ArrayList<>();
                    start.add(startValues.get(j));
                    return findHash(start,2000,toBeFound);
                }
            }
            times++;
        }
        return null;

    }

    /**
     *
     * @param passwords: start password of chain
     * @param chainLength: length of chain
     * @param toFind: hach value which needs to be found
     * @return: value before hash value in chain
     */
    public static String findHash(ArrayList<String> passwords, int chainLength, String toFind){
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < 1; i++){
            String current = passwords.get(i);
            for (int j=0; j < chainLength; j++){
                String hash = generateHash(current);
                String reduce = Reduktionsfunktion(hash, j, generateZ());
                if (hash.equals(toFind)){
                    return current+" "+(j-1);
                }
                current=reduce;
            }
        }
        return "";
    }
}
