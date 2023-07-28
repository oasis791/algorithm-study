import java.io.*;
import java.util.*;

public class Baek_10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = bf.readLine().split("");
        Arrays.sort(arr);

        if(!arr[0].equals("0")) {
            System.out.println(-1);
            return;
        }

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length - 1; i >= 0; i--) {
            int temp = Integer.parseInt(arr[i]);
            sum += temp;
            sb.append(temp);
        }

        if(sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(sb);
    }
}
