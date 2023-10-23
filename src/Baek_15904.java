import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_15904 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int targetIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if(targetIndex >= 4)
                break;
            char c = s.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                switch (targetIndex) {
                    case 0:
                        if(c == 'U')
                            targetIndex++;
                        break;
                    case 1:
                        if(c == 'C')
                            targetIndex++;
                        break;
                    case 2:
                        if(c == 'P')
                            targetIndex++;
                        break;
                    case 3:
                        if(c=='C')
                            targetIndex++;
                        break;
                    default:
                        break;
                }
            }
        }

        if(targetIndex >= 4) {
            System.out.println("I love UCPC");
        } else {
            System.out.println("I hate UCPC");
        }
    }
}
