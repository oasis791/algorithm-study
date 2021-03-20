//1786번 찾기 (KMP)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1786 {
    private static int count = 0;
    private static ArrayList<Integer> result = new ArrayList<>();
    private static int[] getPi(String pattern, int size) {
        int[] pi = new int[size];
        int index = 0;
        pi[0] = 0;
        for (int i = 1; i < size; i++) {
            if (pattern.charAt(index) != pattern.charAt(i)) {
                if (index == 0) {
                    continue;
                }
                index = pi[index - 1];
            }
            if (pattern.charAt(index) == pattern.charAt(i)) {
                pi[i] = ++index;
            }
        }
        return pi;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String mainString = bf.readLine();
        String subString = bf.readLine();
        int n = mainString.length(), m = subString.length(), j = 0;
        int[] pi = getPi(subString, m);

        for (int i = 0; i < n; i++) {
            if (mainString.charAt(i) != subString.charAt(j)) {
                if (j == 0) {
                    continue;
                }
                j = pi[j - 1];
            }

            if (mainString.charAt(i) == subString.charAt(j)) {
                if (j == m - 1) {
                    count++;
                    result.add(i - m + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
