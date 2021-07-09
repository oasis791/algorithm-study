//1244번 스위치 켜고 끄기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1244 {
    private static int N;
    private static int M;
    private static int[] swit;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        swit = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            swit[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            switch (sex) {
                case 1:
                    for (int j = num; j <= swit.length; j = j + num) {
                        change(j - 1);
                    }
                    break;
                case 2:
                    int small = num - 1;
                    int big = N - num;
                    int check = Math.min(small, big);
                    change(num - 1);

                    for (int j = 1; j <= check; j++) {
                        if (swit[num - 1 - j] == swit[num - 1 + j]) {
                            change(num - 1 - j);
                            change(num - 1 + j);
                        } else
                            break;
                    }
                    break;
                default:
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i : swit) {
            sb.append(i).append(" ");
            count++;
            if (count == 20) {
                System.out.println(sb);
                sb.delete(0, sb.length());
                count = 0;
            }
        }
        System.out.println(sb);
    }

    public static void change(int index) {
        if (swit[index] == 0) {
            swit[index] = 1;
        } else {
            swit[index] = 0;
        }
    }
}
