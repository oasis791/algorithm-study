import java.util.*;
import java.io.*;

public class Baek_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];
        int index = 0;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j = 0; j < 3; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(i == 0) {
                    maxDp[0][j] = input;
                    minDp[0][j] = input;
                } else {
                    int max = Integer.MIN_VALUE;
                    int min = Integer.MAX_VALUE;

                    for(int k = -1; k < 2; k++) {
                        if((j + k) >= 0 && (j + k) < 3) {
                            max = Math.max(max, maxDp[Math.abs(index - 1)][j + k] + input);
                            min = Math.min(min, minDp[Math.abs(index - 1)][j + k] + input);
                        }
                    }
                    maxDp[index][j] = max;
                    minDp[index][j] = min;
                }
            }
            index = Math.abs(index - 1);
        }

        int answerMax = Integer.MIN_VALUE;
        int answerMin = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            answerMax = Math.max(maxDp[Math.abs(index - 1)][i], answerMax);
            answerMin = Math.min(minDp[Math.abs(index - 1)][i], answerMin);
        }

        System.out.println(answerMax + " " + answerMin);
    }
}
