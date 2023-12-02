import java.io.*;
import java.util.StringTokenizer;

public class Baek_1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int answer = 0;
        while (st.hasMoreTokens()) {
            st.nextToken();
            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
