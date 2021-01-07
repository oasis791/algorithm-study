//7576 토마토
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_7576{
    private static int N;
    private static int M;
    private static int result=0;
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int[] move_x={-1,1,0,0};
    private static int[] move_y={0,0,1,-1};
    private static Queue<int[]> Q=new LinkedList<>();
    private static Queue<int[]> Q2=new LinkedList<>();
    private static void bfs(int x,int y){
        int[] temp;
        visited[x][y]=true;
        Q2.offer(new int[] {x,y});
        while(!Q.isEmpty())
            Q2.offer(Q.poll());
        while(!Q2.isEmpty()) {
            temp= Q2.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;
                else {
                    if (matrix[nextX][nextY] == 0 && visited[nextX][nextY] == false) {
                        matrix[nextX][nextY] = matrix[temp[0]][temp[1]] + 1;
                        visited[nextX][nextY]=true;
                        Q2.offer(new int[] {nextX,nextY});
                        result=matrix[nextX][nextY]-1;
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        M=sc.nextInt();
        N=sc.nextInt();
        matrix=new int[N][M];
        visited=new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++) {
                matrix[i][j] = sc.nextInt();
                if(matrix[i][j]==1)
                    Q.offer(new int[] {i,j});
            }
        }
        int[] temp = Q.poll();
        bfs(temp[0], temp[1]);

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++) {
               if(matrix[i][j]==0){
                   result=-1;
                   break;
               }
            }
        }
        System.out.println(result);
    }
}