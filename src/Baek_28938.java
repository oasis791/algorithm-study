import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_28938 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int sum = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
        }

        if (sum > 0) {
            System.out.println("Right");
        } else if (sum < 0) {
            System.out.println("Left");
        } else {
            System.out.println("Stay");
        }
    }
}
