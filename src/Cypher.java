/* First rendition of encoded cypher message
 Caleb Aragones 4/27/22  */

// TODO: IDEAS
//  random float as a space in between
//  have a JPanel window display the final message
//  input/type into JPanel instead of console

// TODO: FIXING
//  rearrange when it checks for incoming or outgoing because toArray() scans for tokens and not characters.
//  or just reuse to array with a different scanner?
//  >
//  fix decrypt to make floats a space


// This first rendition isn't using random at all
// I have it set to convert a character to an integer using basic parsing
// and just adding the seed to it

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cypher {
    private static final int SEED = 0;

    public static void main(String[] args) {
        String input = prompt();
        String[] arr = toArray(input);
        boolean outgoing = check(arr);
        if (!outgoing) displayMessage(encrypt(arr)); // TODO: Uncomment
        else displayMessage(decrypt(arr));
    }

    // intro prompt
    private static String prompt() {
        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter seed >>> ");
//        SEED = scan.nextInt();
        System.out.print("Enter message >>> "); // prompt user
        return scan.nextLine();
    }

    private static void displayMessage (String in) {
        System.out.println("\n" + in);
    }

    // method that separates outgoing message into an array
    // TODO: Need an array or separation for each character
    private static String[] toArray(String input) {
        int size;
        // StringTokenizer counts tokens to get the size of the array
        StringTokenizer tokens = new StringTokenizer(input);
        if (input == null || input.isEmpty()) size = 0;
        else size = tokens.countTokens(); // setting size of the array

        Scanner token = new Scanner(input); // making scanner for inputted message
        String[] arr = new String[size];

        for (int i = 0; i < size; i++) {
            arr[i] = token.next(); // adding each word to a respective index
        }

        return arr;
    }

    // method that will depict if string is incoming or outgoing message
    private static boolean check(String[] in) {
        return isNumeric(in[0]);
    }

    // method that checks if string is numeric
    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String encrypt(String[] arr) {
        StringBuilder str = new StringBuilder();
        Random r = new Random();
        double random;

        for (int i = 0; i < arr.length; i++) {
            random = 0.1 + r.nextDouble() * (10 - 0.1);
            for (char c : arr[i].toCharArray()) {
                str.append(toNum(c));
            }
            str.append(" ");
            str.append(random);
            str.append(" ");
        }

        return str.toString();
    }
    public static String decrypt(String[] arr) { // works with single int but not an actual array yet (see fixing ^)
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].contains(".")) str.append(toChar(Integer.parseInt(arr[i])));
        }

       return str.toString();
    }

    // method to make each integer a character
    public static String toChar(int num) {

        if(num < 0) return null;

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem + SEED); // TODO: <<<scuffed algorithm>>>

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
    }

    public static int toNum(char in) {
        return (char)(in - 97 + SEED);
    }
}
