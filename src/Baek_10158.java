import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_10158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] info = br.readLine().split(" ");
        int W = Integer.parseInt(info[0]);
        int H = Integer.parseInt(info[1]);

        String[] pos = br.readLine().split(" ");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        int t = Integer.parseInt(br.readLine());
        int xCnt = (x + t) / W;
        int yCnt = (y + t) / H;
        int p, q;

        if (xCnt % 2 == 0) {
            p = (x + t) % W;
        } else {
            p = W - ((x + t) % W);
        }
        if (yCnt % 2 == 0) {
            q = (y + t) % H;
        } else {
            q = H - ((y + t) % H);
        }
        sb.append(p).append(" ").append(q);
        System.out.println(sb);
    }

}