import java.io.*;
import java.util.*;

public class Baek_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int index = 1;
        dp[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < n; i++) {
            int nextNum = Integer.parseInt(st.nextToken());

            if(nextNum > dp[index - 1]) {
                dp[index++] = nextNum;
            } else {
                int replaceIndex = lowerBound(dp, nextNum, index);
                dp[replaceIndex] = nextNum;
            }
        }

        System.out.println(index);
    }

    static int lowerBound(int[] dp, int nextNum, int index) {
        int left = 0;
        int right = index;

        while(left < right) {
            int mid = (left + right) / 2;

            if(dp[mid] < nextNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
