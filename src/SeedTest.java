public class SeedTest {

    private static final int SEED = 14;
    private static final int ENTRIES = 5;
    private static String[] arr = new String[ENTRIES];

    public static void main(String[] args) {
        randomize(arr);
        System.out.println(toAlphabeticInt(0));
        System.out.println((char)((int) 'a' + 1));
    }

    private static void randomize(String[] strings) {
        double randNum;
        String cut;

        for (int i = 0; i < ENTRIES; i++) {
            randNum = Math.random() * (2.0);

            cut = Float.toString((float) randNum); //TODO: add to map or array

        }
    }

    public static String toAlphabeticInt(int in) {
        if(in < 0) {
            return "-" + toAlphabeticInt(-in - 1);
        }

        int quot = in / 26;
        int rem = in % 26;
        char letter = (char)((int)'a' + rem); // TODO: add seed

        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabeticInt(quot - 1) + letter;
        }
    }

    public static String randomzieFill() {
        return null;
    }
}
