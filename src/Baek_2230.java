import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;

        while (end < n) {
            int sub = arr[end] - arr[start];
            if (sub == m) {
                answer = m;
                break;
            } else if (sub > m) {
                answer = Math.min(answer, sub);
                start++;
            } else {
                end++;
            }
        }

        System.out.println(answer);
    }
}