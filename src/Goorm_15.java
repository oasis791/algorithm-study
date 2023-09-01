import java.io.*;
import java.util.*;

public class Goorm_15 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] fruits = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            fruits[i][0] = p;
            fruits[i][1] = c;
        }

        Arrays.sort(fruits, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int o1Div = o1[1] / o1[0];
                int o2Div = o2[1] / o2[0];

                if (o1Div == o2Div) {
                    return o1[0] - o2[0];
                } else {
                    return (o1Div - o2Div) * -1;
                }
            }
        });

        int count = 0;
        long answer = 0;
        for (int i = 0; i < n; i++) {
            if(count == k)
                break;
            if (count + fruits[i][0] > k) {
                int div = fruits[i][1] / fruits[i][0];
                answer += (long) div * (k - count);
                count = k;
            } else {
                count += fruits[i][0];
                answer += fruits[i][1];
            }
        }

        System.out.println(answer);
    }
}