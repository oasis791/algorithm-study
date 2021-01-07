//2178번 미로탐색
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Pair{
    public Integer x;
    public Integer y;
    public Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
}
public class Baek_2178{
    private static int N;
    private static int M;
    private static Queue<Pair> Q=new LinkedList<>();
    private static String[] input;
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int[] move_x={-1,1,0,0};
    private static int[] move_y={0,0,1,-1};
    private static void bfs(int x,int y){
        visited[x][y]=true;
        Q.offer(new Pair(x,y));
        while(!Q.isEmpty()){
            Pair pair=Q.poll();
            x=pair.x;
            y=pair.y;
            if(x==N-1&&y==M-1)
                break;
            for(int i=0;i<4;i++){
                int nextX=x+move_x[i];
                int nextY=y+move_y[i];
                if(nextX<0||nextY<0||nextX>=N||nextY>=M)
                    continue;
                else{
                    if(matrix[nextX][nextY]!=0&&visited[nextX][nextY]==false){
                        Q.offer(new Pair(nextX,nextY));
                        matrix[nextX][nextY]=matrix[x][y]+1;
                        visited[nextX][nextY]=true;
                    }
                }
            }
        }
        System.out.println(matrix[N-1][M-1]);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();
        matrix=new int[N][M];
        visited=new boolean[N][M];
        input=new String[N];
        for(int i=0;i<N;i++)
            input[i]=sc.next();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++)
                matrix[i][j]=input[i].charAt(j)-'0';
        }
        bfs(0,0);
    }
}