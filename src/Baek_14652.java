import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14652 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int target = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                target++;
                if (target == k) {
                    System.out.println(i + " " + j);
                }
            }
        }
    }
}
