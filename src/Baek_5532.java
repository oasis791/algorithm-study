import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_5532 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(bf.readLine());
        int a = Integer.parseInt(bf.readLine());
        int b = Integer.parseInt(bf.readLine());
        int c = Integer.parseInt(bf.readLine());
        int d = Integer.parseInt(bf.readLine());

        double answer = 0;
        answer = Math.max((double) a / (double) c, (double) b / (double) d);
        answer = Math.ceil(answer);
        l -= (int) answer;
        System.out.println(l);
    }
}