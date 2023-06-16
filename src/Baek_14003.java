import java.io.*;
import java.util.*;

public class Baek_14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());
        int[] input = new int[n];
        int[] LIS = new int[n];
        int[] indices = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = input[0];
        int index = 1;

        for(int i = 1; i < n; i++) {
            int nextNum = input[i];

            if(LIS[index - 1] < nextNum) {
                LIS[index] = nextNum;
                indices[i] = index++;
            } else {
                int replaceIndex = lower_bound(LIS, nextNum, index);
                LIS[replaceIndex] = nextNum;
                indices[i] = replaceIndex;
            }
        }

        sb.append(index).append("\n");
        int[] answer = new int[index];

        index -= 1;
        for(int i = n - 1; i >= 0; i--) {
            if(indices[i] == index) {
                answer[index--] = input[i];
            }
        }

        for(int i = 0; i < answer.length; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }

    static int lower_bound(int[] LIS, int nextNum, int index) {
        int left = 0;
        int right = index;

        while(left < right) {
            int mid = (left + right) / 2;
            if(LIS[mid] < nextNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
