/* First rendition of encoded cypher message
 Caleb Aragones 4/27/22  */

// TODO: IDEAS
//  ++ random float as a space in between ++
//  have a JPanel window display the final message
//  input/type into JPanel instead of console

// TODO: FIXING
//  wrapping output text OR multi-line input from user from one paste
//  reading multi-line input from user
//  optimize duplicate code on line 99

import java.util.*;
import java.util.regex.Pattern;

public class Cypher {
    private static  int SEED;
    private static final int DECIMAL_PLACES = 2;
    private static final String LINEBREAK = "\n";
    private static final int MAX_SIZE = 1000;
    private static final int MAX_RANDOM_INTS = 10;
    private static boolean RUN = true;
    private static TreeMap<Integer, Character> map = new TreeMap<>();

    public static void main(String[] args) {
        while (RUN) {
            String input = prompt();
            initialize();
            String[] arr = toArray(input.toLowerCase());
            boolean outgoing = check(arr);
            if (!outgoing) displayMessage(encrypt(arr));
            else displayMessage(decrypt(arr));
            RUN = quit();
        }
    }

    // initialize maps with appropriate values based on seed given
    private static void initialize() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        char[] letterArr = letters.toCharArray();
        int[] intArr = new int[26];
        int count = SEED;

        // clear the map
        map.clear();

        // initialize  map
        for (char c : letterArr) {
            map.put(count, c);
            count++;
        }
    }

    // finding number in map
    private static int getNum(Character value) {
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    // finding character in map
    private static String getChar(int in) {
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            if (entry.getKey().equals(in)) {
                return "" + entry.getValue(); // casting character to string
            }
        }
        return "";
    }

    // intro prompt to get message
    private static String prompt() {
        Scanner scan = new Scanner(System.in);

        SEED = getSeed(scan); // prompt for seed
        return getMessage(scan); // prompt for message and return String
    }

    // prompt user to continue
    private static boolean quit() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCONTINUE - ENTER\nQUIT     - ANY CHARACTER");
        return scan.nextLine().isEmpty();
    }

    private static int getSeed(Scanner scan) {
        String seedInput = "";

        while (!isNumeric(seedInput) || Integer.parseInt(seedInput) > 74) { // how to optimize this duplicate code?
            System.out.print("ENTER SEED >>> "); // get SEED from user
            seedInput = scan.nextLine();
            if (!isNumeric(seedInput) || Integer.parseInt(seedInput) > 74) System.out.println("INVALID SEED\n");
        }
        return Integer.parseInt(seedInput);
    }

    private static String getMessage(Scanner scan) {
        System.out.println("ENTER MESSAGE ");
        return scan.nextLine();
    }

    // displays message with wrapped lines
    private static void displayMessage (String in) {
        // TODO: Disabled until I can figure out how to read multiple lines of input
        //System.out.println("\n\nMESSAGE >>> " + wrap(in, 100));
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
        String dub; // idk what to name it :sob: the double as a string
        double randomDouble;

        // each word
        for (int i = 0; i < arr.length; i++) {
            randomDouble = 0.1 + r.nextDouble() * (10 - 0.1); // random double
            dub = Double.toString(randomDouble).substring(0, 2 + DECIMAL_PLACES); // rounding to decimal places

            // each word as character array
            for (char c : arr[i].toCharArray()) {
                str.append(getNum(c)).append(" ");
                str.append(generateRandomInts(r, r.nextInt((MAX_RANDOM_INTS - 1) + 1) + 1)); // generate a random number of integers
            }
            str.append(dub).append(" "); // every double is a space
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
            if (!s.contains(".") && s.length() <= 2) str.append(getChar(Integer.parseInt(s)));
            if (s.contains(".")) str.append(" ");
        }

       return str.toString();
    }

    public static String toChar(int num) {
        if (num < 0) return "";

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem);

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
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
