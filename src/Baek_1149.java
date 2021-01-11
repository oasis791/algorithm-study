//1149번 RGB거리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1149 {
    private static int N;
    private static int[][] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        cost = new int[N][3];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine()," ");
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());
            cost[i][0] = red;
            cost[i][1] = green;
            cost[i][2] = blue;
        }

        for(int i=N-2;i>=0;i--){
            for(int j=0;j<N;j++){
                switch (j){
                    case 0:
                        cost[i][j] += Math.min(cost[i+1][1],cost[i+1][2]);
                        break;
                    case 1:
                        cost[i][j] += Math.min(cost[i+1][0],cost[i+1][2]);
                        break;
                    case 2:
                        cost[i][j] += Math.min(cost[i+1][0],cost[i+1][1]);
                        break;
                    default:
                        break;
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<3;i++)
            min = Math.min(min,cost[0][i]);
        System.out.println(min);
    }
}
