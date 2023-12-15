import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1267 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int minsik = 0;
        int yeongsik = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            minsik += (temp / 60 + 1) * 15;
            yeongsik += (temp / 30 + 1) * 10;
        }

        if (yeongsik < minsik) {
            System.out.println("Y " + yeongsik);
        } else if (minsik < yeongsik) {
            System.out.println("M " + minsik);
        } else {
            System.out.println("Y " + "M " + minsik);
        }
    }
}
