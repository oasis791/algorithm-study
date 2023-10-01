import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int answer = 0;
        int[] arr = new int[1000002];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (arr[num + 1] > 0) {
                --arr[num + 1];
            }
            ++arr[num];
        }

        for (int i = 0; i < arr.length; ++i) {
            answer = arr[i] > 0 ? answer + arr[i] : answer;
        }

        System.out.println(answer);
    }
}