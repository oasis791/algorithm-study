import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baek_5052 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] input = new String[n];

            for (int i = 0; i < n; i++) {
                input[i] = br.readLine();
            }
            Arrays.sort(input);

            if (isConsistent(n, input)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    public static boolean isConsistent(int n, String[] input) {
        for (int i = 0; i < n - 1; i++) {
            if (input[i + 1].startsWith(input[i])) {
                return false;
            }
        }
        return true;
    }
}