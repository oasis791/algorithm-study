//2602번 바이러스
import java.util.Scanner;

public class Baek_2606 {
    private static int computer,connectedComputer;
    private static int cnt=0;
    private static int[][] adjMat;
    private static int[] visited;
    private static int dfs(int x){
        visited[x]=1;
        for(int i=1;i<=computer;i++){
            if(adjMat[x][i]==1&&visited[i]==0) {
                dfs(i);
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        computer=sc.nextInt();
        connectedComputer=sc.nextInt();
        adjMat=new int[computer+1][computer+1];
        visited=new int[computer+1];

        int m,n;
        for(int i=0;i<connectedComputer;i++){
            m=sc.nextInt();
            n=sc.nextInt();
            adjMat[m][n]=1;
            adjMat[n][m]=1;
        }
        System.out.println(dfs(1));
    }
}
