import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_1246 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int max = Integer.MIN_VALUE;
        int price = 0;
        Integer[] info = new Integer[m];

        for (int i = 0; i < m; i++) {
            info[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(info, Collections.reverseOrder());

        for (int i = 0; i < m; i++) {
            if (i + 1 <= n) {
                if (max < info[i] * (i + 1)) {
                    max = info[i] * (i + 1);
                    price = info[i];
                }
            } else {
                if (max < info[i] * n) {
                    max = info[i] * n;
                    price = info[i];
                }
            }
        }

        System.out.println(price + " " + max);
    }
}
