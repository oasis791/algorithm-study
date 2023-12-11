import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String board = bf.readLine();

        String s = board.replaceAll("XXXX", "AAAA");
        String s2 = s.replaceAll("XX", "BB");

        if (s2.contains("X")) {
            System.out.println(-1);
        } else {
            System.out.println(s2);
        }
    }
}
