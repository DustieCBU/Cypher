import java.util.TreeMap;

public class toNumTest {
    private static final int SEED = 2;

    public static void main(String[] args) {
        System.out.println("To Num Test");
        System.out.println("SEED >>> " + SEED);
        
        testNums();
        testChars();      
        
    }

    public static void testNums() {
        String letters = "abcdefghijklmnopqurstuvwxyz";
        char[] letterArr = letters.toCharArray();
        int count = SEED;
        TreeMap<Character, Integer> nums = new TreeMap<>();

        for (char c : letterArr) {
            nums.put(c, count);
            count++;
        }
        System.out.println("Testing toNums()...");
        System.out.println(nums.toString());
    }

    public static void testChars() {
        int[] intArr = new int[26];
        int count = SEED;
        TreeMap<String, Integer> chars = new TreeMap<>();

        for (int i = 0; i < 26 ; i++) {
            intArr[i] = i;
            chars.put(toChar(intArr[i]), SEED + i);
        }

        System.out.println("Testing toChar()...");
        System.out.println(chars.toString());


    }

    public static String toChar(int num) {
        if(num < 0) return null;

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem); // TODO: <<<scuffed algorithm>>>

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
    }

    public static int toNum(char in) {
        return in  - 'a';
    }


}
