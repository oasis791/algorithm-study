//4354번 문자열 제곱(KMP)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_4354 {
    private static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int index = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (index > 0 && pattern.charAt(i) != pattern.charAt(index)) {
                index = pi[index - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(index)) {
                pi[i] = ++index;
            }
        }
        return pi;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = bf.readLine();
            if (input.equals(".")) {
                System.exit(0);
            }
            int[] pi = getPi(input);
            int index = pi.length - 1;
            int result;
            if (input.length() == 1) {
                System.out.println(1);
                continue;
            }
            while (index > 0) {
                if (input.length() % (input.length() - pi[index]) != 0) {
                    index = pi[index - 1];
                }
                if (input.length() % (input.length() - pi[index]) == 0) {
                    result = input.length() / (input.length() - pi[index]);
                    System.out.println(result);
                    break;
                }
            }
        }
    }
}
