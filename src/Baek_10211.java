import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_10211 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(bf.readLine());
            int[] arr = new int[n + 1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    if(arr[i] - arr[j] > max) {
                        max = arr[i] - arr[j];
                    }
                }
            }
            sb.append(max).append("\n");
        }

        System.out.print(sb);
    }
}