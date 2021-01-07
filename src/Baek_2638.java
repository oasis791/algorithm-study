//2638번 치즈
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2638 {
    private static int N;
    private static int M;
    private static int[][] matrix;
    private static int[] move_x={1,-1,0,0};
    private static int[] move_y={0,0,1,-1};
    private static boolean[][] visited;
    private static int hour=0;
    private static int count=0;
    private static Queue<int[]> Q=new LinkedList<>();
    private static Queue<int[]> Q2=new LinkedList<>();
    private static void searching(int x,int y){
        Queue<int []>queue=new LinkedList<>();
        matrix[x][y]=-1;
        visited[x][y]=true;
        queue.offer(new int[] {x,y});
        while(!queue.isEmpty()) {
            int temp[];
            temp=queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX=temp[0]+move_x[i];
                int nextY=temp[1]+move_y[i];
                if(nextX<0||nextY<0||nextX>=N||nextY>=M)
                    continue;
                else{
                    if(matrix[nextX][nextY]!=1&&visited[nextX][nextY]==false){
                        matrix[nextX][nextY]=-1;
                        visited[nextX][nextY]=true;
                        queue.offer(new int[] {nextX,nextY});
                    }
                    if(matrix[nextX][nextY]==1&&visited[nextX][nextY]==false){
                        Q.offer(new int[] {nextX,nextY});
                        visited[nextX][nextY]=true;
                    }
                }
            }
        }
    }
    private static void cheese(){
        while(!Q.isEmpty()){
            int temp[];
            int check=0;
            temp=Q.poll();
            for(int i=0;i<4;i++){
                int nextX=temp[0]+move_x[i];
                int nextY=temp[1]+move_y[i];
                if(matrix[nextX][nextY]==-1)
                    check++;
                if(check==2){
                    Q2.offer(new int[] {temp[0],temp[1]});
                    break;
                }
            }
        }
        if(!Q2.isEmpty())
            hour();
    }
    private static void hour(){
        while(!Q2.isEmpty()){
            int temp[];
            temp=Q2.poll();
            matrix[temp[0]][temp[1]]=-1;
            count--;
        }
        hour++;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        matrix=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            for(int j=0;j<M;j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j]==1)
                    count++;
            }
        }
        while(count>0){
            visited=new boolean[N][M];
            searching(0,0);
            cheese();
        }
        System.out.println(hour);
    }
}
