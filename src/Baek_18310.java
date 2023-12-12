import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_18310 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        if (n == 1) {
            System.out.println(bf.readLine());
            return;
        }

        int[] houses = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(houses);

        int target = n / 2;
        int target2 = n / 2 - 1;
        int targetSum = 0;
        int target2Sum = 0;

        for (int i = 0; i < n; i++) {
            targetSum += Math.abs(houses[target] - houses[i]);
            target2Sum += Math.abs(houses[target2] - houses[i]);
        }

        if (target2Sum <= targetSum) {
            System.out.println(houses[target2]);
        } else {
            System.out.println(houses[target]);
        }
    }
}
