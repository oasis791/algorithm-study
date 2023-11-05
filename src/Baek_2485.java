import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_2485 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] trees = new int[n];
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(trees);

        // 1. find GCD
        int a = trees[1] - trees[0];
        for(int i = 2; i < n; i++) {
            int b = trees[i] - trees[i - 1];
            a = gcd(a, b);
        }

        int answer = 0;

        for (int i = 0; i < n - 1; i++) {
            int t1 = trees[i];
            int t2 = trees[i + 1];
            int dist = t2 - t1;

            if(dist == a)
                continue;

            answer += dist / a - 1;
        }

        System.out.println(answer);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
