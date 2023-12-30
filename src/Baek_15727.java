import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_15727 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(bf.readLine());
        if(l % 5 == 0) {
            System.out.println(l / 5);
        } else {
            System.out.println((l / 5) + 1);
        }
    }
}
