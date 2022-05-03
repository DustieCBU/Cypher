/* First rendition of encoded cypher message
 Caleb Aragones 4/27/22  */

// TODO: IDEAS
//  ++ random float as a space in between ++
//  have a JPanel window display the final message
//  input/type into JPanel instead of console

// TODO: FIXING
//  wrapping output text
//  fix decypher()
//  reading multi-line input from user



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Cypher {
    private static  int SEED;
    private static final int DECIMAL_PLACES = 2;
    private static final String LINEBREAK = "\n";
    private static final int MAX_SIZE = 10000;
    private static final int MAX_RANDOM_INTS = 10;
    private static boolean RUN = true;

    public static void main(String[] args) {
        while (RUN) {
            String input = prompt();
            String[] arr = toArray(input.toLowerCase());
            boolean outgoing = check(arr);
            if (!outgoing) displayMessage(encrypt(arr));
            else displayMessage(decrypt(arr));
            RUN = quit();
        }
    }

    // intro prompt to get message
    private static String prompt() {
        Scanner scan = new Scanner(System.in);

        SEED = getSeed(scan);
        return getMessage(scan);
    }

    private static boolean quit() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCONTINUE - ENTER\nQUIT     - ANY CHARACTER");
        return scan.nextLine().isEmpty();
    }
    private static int getSeed(Scanner scan) {
        String seedInput = "";

        while (!isNumeric(seedInput)) {
            System.out.print("ENTER SEED >>> "); // get SEED from user
            seedInput = scan.nextLine();
            if (!isNumeric(seedInput)) System.out.println("INVALID SEED");
        }
        return Integer.parseInt(seedInput);
    }

    private static String getMessage(Scanner scan) {
        System.out.println("ENTER MESSAGE (NO SPECIAL CHARACTERS) ");
        return scan.nextLine();
    }

    // displays message with wrapped lines
    private static void displayMessage (String in) {
        // TODO: Disabled until I can figure out how to read multiple lines of input
//        System.out.println("\nMESSAGE >>> " + wrap(in, 100));
        System.out.println("\nMESSAGE >>> \n" + in);

    }

    // method that separates message into an array of tokens
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

    // method to encrypt message by adding random integers and doubles at set intervals
    public static String encrypt(String[] arr) {
        StringBuilder str = new StringBuilder();
        Random r = new Random();
        String dub; // idk what to name it :sob:
        double randomDouble;

        for (int i = 0; i < arr.length; i++) {
            randomDouble = 0.1 + r.nextDouble() * (10 - 0.1); // random double
            dub = Double.toString(randomDouble).substring(0, 2 + DECIMAL_PLACES); // rounding to decimal places

            for (char c : arr[i].toCharArray()) {
                str.append(toNum(c));
                str.append(" ");
                str.append(generateRandomInts(r, r.nextInt((MAX_RANDOM_INTS - 1) + 1) + 1)); // generate a random number of integers
            }
            str.append(dub); // every double is a space
            str.append(" ");
        }

        return str.toString();
    }

    public static String generateRandomInts(Random r, int count) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i <= count; i++) {
            str.append(r.nextInt((MAX_SIZE - 100) + 1) + 100);
            str.append(" ");
        }
        return str.toString();
    }

    // method excludes floats and adds a space instead
    public static String decrypt(String[] arr) {
        StringBuilder str = new StringBuilder();

        for (String s : arr) {
            if (!s.contains(".") && s.length() <= 2) str.append(toChar(Integer.parseInt(s)));
            if (s.contains(".")) str.append(" ");
        }

       return str.toString();
    }

    public static String toChar(int num) {

        if (num < 0) return "";

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem + SEED); // TODO: <<<scuffed algorithm>>>

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
    }

    public static int toNum(char in) {
        int num = in - '0';

        return num - 49;
    }

    // both methods from
    // https://stackoverflow.com/questions/4055430/java-code-for-wrapping-text-lines-to-a-max-line-width
    public static String wrap(String string, int lineLength) {
        StringBuilder b = new StringBuilder();
        for (String line : string.split(Pattern.quote(LINEBREAK))) {
            b.append(wrapLine(line, lineLength));
        }
        return b.toString();
    }

    private static String wrapLine(String line, int lineLength) {
        if (line.length() == 0) return LINEBREAK;
        if (line.length() <= lineLength) return line + LINEBREAK;
        String[] words = line.split(" ");
        StringBuilder allLines = new StringBuilder();
        StringBuilder trimmedLine = new StringBuilder();
        for (String word : words) {
            if (trimmedLine.length() + 1 + word.length() <= lineLength) {
                trimmedLine.append(word).append(" ");
            } else {
                allLines.append(trimmedLine).append(LINEBREAK);
                trimmedLine = new StringBuilder();
                trimmedLine.append(word).append(" ");
            }
        }
        if (trimmedLine.length() > 0) {
            allLines.append(trimmedLine);
        }
        allLines.append(LINEBREAK);
        return allLines.toString();
    }
}
