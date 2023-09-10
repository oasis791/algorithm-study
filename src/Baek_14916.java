import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_14916 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int five = n / 5;

        while (five >= 0) {
            int remain = n - five * 5;
            if (remain % 2 == 0) {
                System.out.println(five + remain / 2);
                return;
            }

            five--;
        }

        System.out.println(-1);
    }
}
