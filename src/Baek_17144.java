//17144번 미세먼지 안녕!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17144 {
    private static int R;
    private static int C;
    private static int T;
    private static int[][] A;
    private static int[] move_r = {-1,0,1,0};
    private static int[] move_c = {0,1,0,-1};
    private static Queue<int[]> Q = new LinkedList<>();
    private static void spread(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                int count = 0;
                if(A[i][j] != 0 && A[i][j] != -1){
                    for(int k=0;k<4;k++){
                        int nextR = i + move_r[k];
                        int nextC = j + move_c[k];
                        if(nextR<1||nextR>R||nextC<1||nextC>C||A[nextR][nextC]==-1)
                            continue;
                        else{
                            Q.offer(new int[] {nextR,nextC,A[i][j]/5});
                            count++;
                        }
                    }
                    A[i][j] -= count * (A[i][j]/5);
                }
            }
        }
        while(!Q.isEmpty()){
            int[] temp = Q.poll();
            A[temp[0]][temp[1]] += temp[2];
        }
    }
    private static void cycle(){
        int count = 0;
            for(int i=1;i<=R;i++){
               for(int j=1;j<=C;j++) {
                   if (count == 2)
                       break;
                   else {
                       if (A[i][j] == -1) {
                           switch (count) {
                               case 0: // 윗부분
                                   int temp = A[i][j + 1];
                                   A[i][j + 1] = 0;
                                   int nextC = j + 2;
                                   while (nextC <= C) {
                                       int temp2 = A[i][nextC];
                                       A[i][nextC] = temp;
                                       temp = temp2;
                                       nextC++;
                                   }
                                   nextC--;
                                   int nextR = i - 1;
                                   while (nextR >= 1) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextR--;
                                   }
                                   nextR++;
                                   nextC -= 1;
                                   while (nextC >= 1) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextC--;
                                   }
                                   nextC++;
                                   nextR += 1;
                                   while (nextR != i) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextR++;
                                   }
                                   count++;
                                   break;
                               case 1: // 아랫부분
                                   temp = A[i][j + 1];
                                   A[i][j + 1] = 0;
                                   nextC = j + 2;
                                   while (nextC <= C) {
                                       int temp2 = A[i][nextC];
                                       A[i][nextC] = temp;
                                       temp = temp2;
                                       nextC++;
                                   }
                                   nextC--;
                                   nextR = i + 1;
                                   while (nextR <= R) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextR++;
                                   }
                                   nextR--;
                                   nextC -= 1;
                                   while (nextC >= 1) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextC--;
                                   }
                                   nextC++;
                                   nextR -= 1;
                                   while (nextR != i) {
                                       int temp2 = A[nextR][nextC];
                                       A[nextR][nextC] = temp;
                                       temp = temp2;
                                       nextR--;
                                   }
                                   count++;
                                   break;
                               default:
                                   break;
                           }
                       }
                   }
               }
            if(count == 2)
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        A = new int[R+1][C+1];
        for(int i=1;i<=R;i++){
            st = new StringTokenizer(bf.readLine()," ");
            for(int j=1;j<=C;j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int time=0;time<T;time++){
            spread();
            cycle();
        }
        int result = 0;
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(A[i][j]!=0&&A[i][j]!=-1)
                    result += A[i][j];
            }
        }
        System.out.println(result);
    }
}
