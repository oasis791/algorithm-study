import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(arr);

        int num = (int) Math.round(n * 0.15);

        double sum = 0;
        for (int i = num; i < n - num; i++) {
            sum += arr[i];
        }

        System.out.println((int) Math.round(sum / (n - 2 * num)));
    }
}
