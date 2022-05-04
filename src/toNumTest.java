import java.util.TreeMap;

public class toNumTest {
    private static final int SEED = 0;

    public static void main(String[] args) {
        System.out.println("To Num Test");
        System.out.println("SEED >>> " + SEED);
        
        testNums();
        testChars();      
        
    }

    public static void testNums() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        char[] letterArr = letters.toCharArray();
        TreeMap<Character, Integer> nums = new TreeMap<>();

        for (int i = 0; i < letterArr.length; i++) {
            int count = i + SEED;
            nums.put(letterArr[i], count);
        }
        System.out.println("Testing toNums()...");
        System.out.println(nums);
    }

    public static void testChars() {
        int[] intArr = new int[26];
        TreeMap<String, Integer> chars = new TreeMap<>();

        for (int i = 0; i < 26 ; i++) {
            intArr[i] = i;
            chars.put(toChar(intArr[i]), SEED + i);
        }

        System.out.println("Testing toChar()...");
        System.out.println(chars);


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
