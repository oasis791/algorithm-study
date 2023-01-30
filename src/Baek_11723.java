import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(bf.readLine());
        int x = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            if (command.equals("all")) {
                x = (1 << 21) - 1;
                continue;
            }
            if (command.equals("empty")) {
                x = 0;
                continue;
            }
            int num = Integer.parseInt(st.nextToken());

            switch (command) {
                case "add":
                    x |= 1 << num;
                    break;
                case "remove" :
                    x &= ~(1 << num);
                    break;
                case "check":
                    int temp = x & (1 << num);
                    if (temp == (1 << num)) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "toggle":
                    x ^= 1 << num;
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);
    }
}
