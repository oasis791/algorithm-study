import java.io.*;
import java.util.*;

public class Baek_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        int count = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;
            while(true) {
                if(start == i) {
                    start++;
                } else if (end == i) {
                    end --;
                }

                if(start >= end) {
                    break;
                }

                if(arr[start] + arr[end] > arr[i]) {
                    end--;
                } else if(arr[start] + arr[end] < arr[i]) {
                    start++;
                } else {
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}
