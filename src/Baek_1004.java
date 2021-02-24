//1004번 어린 왕자
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1004 {
    private static int T;
    private static int x1; // x1,x2 출발점
    private static int y1;
    private static int x2; // x2,y2 도착점
    private static int y2;
    private static int[][] planet;
    private static int count = 0;
    private static int n;
    private static void check() {
        for (int i = 0; i < n; i++) {
            boolean check1 = false;
            boolean check2 = false;
            double temp = ((x1 - planet[i][0]) * (x1 - planet[i][0])) + ((y1 - planet[i][1]) * (y1 - planet[i][1]));
            double temp2 = ((x2 - planet[i][0]) * (x2 - planet[i][0])) + ((y2 - planet[i][1]) * (y2 - planet[i][1]));
            if (planet[i][2] >= Math.sqrt(temp)) {
                count++;
                check1 = true;
            }
            if (planet[i][2] >= Math.sqrt(temp2)) {
                check2 = true;
                count++;
            }
            if(check1 && check2)
                count -= 2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {
            count = 0;
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(bf.readLine()); // 원의 개수
            planet = new int[n][3];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                planet[i][0] = Integer.parseInt(st.nextToken()); // planet[원번호][0] x좌표
                planet[i][1] = Integer.parseInt(st.nextToken()); // [1] y좌표
                planet[i][2] = Integer.parseInt(st.nextToken()); // [2] 반지름 r
            }
            check();
            System.out.println(count);
        }
    }
}
