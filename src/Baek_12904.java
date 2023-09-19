import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String t = bf.readLine();
        StringBuilder sb = new StringBuilder(t);

        while (sb.length() >= s.length()) {
            if (sb.toString().equals(s)) {
                System.out.println(1);
                return;
            }

            if (sb.charAt(sb.length() - 1) == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.deleteCharAt(sb.length() - 1);
                sb.reverse();
            }
        }

        System.out.println(0);
    }
}