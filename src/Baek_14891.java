//14891번 톱니바퀴
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14891 {
    private static int K;
    private static boolean[] check = new boolean[4];
    private static int[][] gear = new int[4][8];
    private static int[] cycleInfo = new int[4];
    private static void cycleCheck(int index) {
        check[index] = true;
        switch (index) {
            case 0:
                if (gear[0][2] != gear[1][6] && !check[1]) {
                    cycleInfo[1] = cycleInfo[0] * -1;
                    cycleCheck(1);
                }
                break;
            case 1:
                if (gear[1][2] != gear[2][6] && !check[2]) {
                    cycleInfo[2] = cycleInfo[1] * -1;
                    cycleCheck(2);
                }
                if (gear[1][6] != gear[0][2] && !check[0]) {
                    cycleInfo[0] = cycleInfo[1] * -1;
                    cycleCheck(0);
                }
                break;
            case 2:
                if (gear[2][2] != gear[3][6] && !check[3]) {
                    cycleInfo[3] = cycleInfo[2] * -1;
                    cycleCheck(3);
                }
                if (gear[2][6] != gear[1][2] && !check[1]) {
                    cycleInfo[1] = cycleInfo[2] * -1;
                    cycleCheck(1);
                }
                break;
            case 3:
                if (gear[3][6] != gear[2][2] && !check[2]) {
                    cycleInfo[2] = cycleInfo[3] * -1;
                    cycleCheck(2);
                }
                break;
            default:
                break;
        }
    }
    private static void shift(){
        for (int i = 0; i < 4; i++) {
            if (cycleInfo[i] == -1) {
                int temp = gear[i][7];
                for (int j = 6; j >= 0; j--) {
                    if (j == 0) {
                        gear[i][7] = gear[i][j];
                        gear[i][j] = temp;
                    } else {
                        int temp1 = gear[i][j];
                        gear[i][j] = temp;
                        temp = temp1;
                    }
                }
            }
            if (cycleInfo[i] == 1) {
                int temp = gear[i][0];
                for (int j = 1; j < 8; j++) {
                    if (j == 7) {
                        gear[i][0] = gear[i][j];
                        gear[i][j] = temp;
                    } else {
                        int temp1 = gear[i][j];
                        gear[i][j] = temp;
                        temp = temp1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String input = bf.readLine();
            String[] temp = input.split("");
            for (int j = 0; j < temp.length; j++)
                gear[i][j] = Integer.parseInt(temp[j]);
        }
        K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                cycleInfo[j] = 0;
                check[j] = false;
            }
            int index = Integer.parseInt(st.nextToken()) - 1;
            cycleInfo[index] = Integer.parseInt(st.nextToken());
            cycleCheck(index);
            shift();
        }

        int result = 0;
        if (gear[0][0] == 1)
            result += 1;
        if (gear[1][0] == 1)
            result += 2;
        if (gear[2][0] == 1)
            result += 4;
        if (gear[3][0] == 1)
            result += 8;
        System.out.println(result);
    }
}
