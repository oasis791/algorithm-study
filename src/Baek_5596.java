import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_5596 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int minkuk = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            minkuk += Integer.parseInt(st.nextToken());
        }

        int manse = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            manse += Integer.parseInt(st.nextToken());
        }

        System.out.println(Math.max(minkuk, manse));
    }
}
