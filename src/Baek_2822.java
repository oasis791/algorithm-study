import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Baek_2822 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[8][2];

        int index = 1;
        for (int i = 0; i < 8; i++) {
            arr[i][0] = Integer.parseInt(bf.readLine());
            arr[i][1] = index++;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return -1 * (o1[0] - o2[0]);
            }
        });

        StringBuilder sb = new StringBuilder();
        int count = 0;
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            count += arr[i][0];
            answer[i] = arr[i][1];
        }
        Arrays.sort(answer);

        sb.append(count).append("\n");
        for (int i = 0; i < 5; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }
}
