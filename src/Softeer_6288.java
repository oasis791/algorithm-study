import java.io.*;
import java.util.*;

public class Softeer_6288 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int w = Integer.parseInt(st.nextToken()); // 배낭의 무게 10_000
        int n = Integer.parseInt(st.nextToken()); // 귀금속 종류 max: 1_000_000

        int[][] jewelries = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            jewelries[i][0] = Integer.parseInt(st.nextToken()); // 금속의 무게
            jewelries[i][1] = Integer.parseInt(st.nextToken()); // 무게당 가격
        }

        Arrays.sort(jewelries, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int answer = 0; // 배낭을 채울 수 있는 가장 값 비싼 가격
        int remain = w;

        for(int i = 0; i < n; i++) {
            if(remain == 0) {
                break;
            }

            int weight = jewelries[i][0];
            int price = jewelries[i][1];

            if(remain >= weight) {
                remain -= weight;
                answer += weight * price;
            } else {
                answer += remain * price;
                remain = 0;
            }
        }

        System.out.println(answer);
    }
}

