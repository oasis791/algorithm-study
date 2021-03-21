import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1305 {
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
        int L = Integer.parseInt(bf.readLine());
        String input = bf.readLine();
        int[] pi = getPi(input);
        int result = L - pi[L - 1];
        System.out.println(result);
    }
}
