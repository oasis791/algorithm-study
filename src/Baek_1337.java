import java.io.*;
import java.util.*;

public class Baek_1337 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        int answer = 4;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            int numCount = 1;
            int target = arr[i] + 1;
            int need = 0;
            for (int j = i + 1; j < n; j++) {
                if (numCount >= 5)
                    break;
                if (target != arr[j]) {
                    need++;
                    j--;
                }
                target += 1;
                numCount++;
            }

            if (numCount < 5) {
                need = need + 5 - numCount;
            }
            answer = Math.min(need, answer);
        }
        System.out.println(answer);
    }
}