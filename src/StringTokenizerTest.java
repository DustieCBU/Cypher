import java.util.StringTokenizer;

public class StringTokenizerTest {
    public static void main(String[] args) {
        String input = "Hello";
        int size;
        StringTokenizer tokens = new StringTokenizer(input);
        if (input == null || input.isEmpty()) size = 0;
        else size = tokens.countTokens();

        System.out.println(size);
    }
}
