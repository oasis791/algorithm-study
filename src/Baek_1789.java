import java.io.*;
import java.util.*;

public class Baek_1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        long s = Long.parseLong(bf.readLine());

        int i = 1;
        long sum = 0;

        for (; sum + i <= s; i++) {
            sum += i;
        }

        System.out.println(i - 1);
    }
}
