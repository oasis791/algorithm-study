import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_5555 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int n = Integer.parseInt(bf.readLine());
        int answer = 0;

        for (int i = 0; i < n; i++) {
            String temp = bf.readLine();
            StringBuilder sb = new StringBuilder();
            sb.append(temp).append(temp);
            if(sb.toString().contains(s))
                answer++;
        }

        System.out.println(answer);
    }
}
