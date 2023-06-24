import java.io.*;
import java.util.*;

public class Baek_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] num = new int[26];

        for(int i = 0; i < n; i++) {
            String input = bf.readLine();

            int ten = 1;

            for(int j = input.length() - 1; j >= 0; j--) {
                char temp = input.charAt(j);
                num[(int)temp - 65] += ten;
                ten *= 10;
            }
        }

        Arrays.sort(num);

        int sum = 0;
        int target = 9;

        for(int i = num.length - 1; i >= 0; i--) {
            if(num[i] == 0) {
                break;
            }
            sum += num[i] * target--;
        }

        System.out.println(sum);
    }
}
