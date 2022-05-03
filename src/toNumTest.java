public class toNumTest {
    private static final int SEED = 0;
    public static void main(String[] args) {
        System.out.println("To Num Test");
        char letter = 'b';
        int num;

        toNum('a');
//        toNum('b');

    }

    public static String toChar(int num) {
        if(num < 0) return null;

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem + SEED); // TODO: <<<scuffed algorithm>>>

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
    }

    public static void toNum(char in) {
        int intValue = in; // returns ascii
        char char1 = 'b';
        int num = char1 - 0;
        System.out.println("Inputted Character: " + in + "\nInteger value: " + intValue);

        System.out.println(num - '0' - 49 - SEED);
    }
}
