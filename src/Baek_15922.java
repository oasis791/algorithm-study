import java.io.*;
import java.util.*;

public class Baek_15922 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int answer = 0;

        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            int curStart = Integer.parseInt(st.nextToken());
            int curEnd = Integer.parseInt(st.nextToken());

            if(curStart <= end) {
                if(curEnd > end)
                    end = curEnd;
            } else {
                answer += end - start;
                start = curStart;
                end = curEnd;
            }
        }

        answer += end - start;
        System.out.println(answer);
    }
}
