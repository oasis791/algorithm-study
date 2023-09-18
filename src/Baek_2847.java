import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int answer = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1]) {
                int temp = arr[i] - arr[i + 1] + 1;
                answer += temp;
                arr[i] -= temp;
            }
        }
        System.out.println(answer);
    }
}
