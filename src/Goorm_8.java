import java.io.*;

public class Goorm_8 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[]{14, 7, 1};
        int count = 0;

        for (int i = 0; i < 3; i++) {
            if (n / arr[i] > 0) {
                count += n / arr[i];
                n = n % arr[i];
            }
        }

        System.out.println(count);
    }
}