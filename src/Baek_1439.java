import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int zeroBlockSize = 0;
        int oneBlockSize = 0;

        char prev = 'k';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (prev != c) {
                if(c == '0') {
                    zeroBlockSize++;
                    prev = '0';
                } else {
                    oneBlockSize++;
                    prev = '1';
                }
            }
        }

        System.out.println(Math.min(zeroBlockSize, oneBlockSize));
    }
}
