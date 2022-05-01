public class toNumTest {
    private static final int SEED = 0;
    public static void main(String[] args) {
        System.out.println("To Num Test");
        char letter = 'b';
        int num;

        num = (int)(letter - 97 + SEED);

        System.out.println(num);
        System.out.println(toChar(num));
    }

    public static String toChar(int num) {

        if(num < 0) return null;

        int quot = num / 26;
        int rem = num % 26;
        char letter = (char)((int)'a' + rem + SEED); // TODO: <<<scuffed algorithm>>>

        if (quot == 0) return "" + letter;
        else return toChar(quot - 1) + letter;
    }
}
