import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1590 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int sum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int between = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                if (start >= t) {
                    sum = Math.min(sum, start - t);
                    break;
                }

                start += between;
            }
        }

        if (sum == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
        }
    }
}
