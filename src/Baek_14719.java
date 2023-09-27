import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());

        boolean[][] block = new boolean[h][w];

        for (int i = 0; i < w; i++) {
            int height = Integer.parseInt(st.nextToken());

            for (int j = 0; j < height; j++) {
                block[h - 1 - j][i] = true;
            }
        }

        int answer = 0;

        for (int i = 0; i < h; i++) {
            boolean left = false;
            int count = 0;
            for (int j = 0; j < w; j++) {
                if (left) {
                    if (block[i][j]) {
                        answer += count;
                        count = 0;
                    } else {
                        count++;
                    }
                } else {
                    if(block[i][j])
                        left = true;
                }
            }
        }

        System.out.println(answer);
    }
}
