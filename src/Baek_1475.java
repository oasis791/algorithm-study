import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String n = bf.readLine();

        int[] arr = new int[10];
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (c == '6') {
                arr[9]++;
            } else {
                arr[c - '0']++;
            }
        }

        int max = 0;
        arr[9] = arr[9] % 2 == 1 ? arr[9] / 2 + 1 : arr[9] / 2;
        for (int i = 0; i < 10; i++) {
            max = Math.max(max, arr[i]);
        }
        System.out.println(max);
    }
}
