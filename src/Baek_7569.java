//7569번 토마토
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_7569 {
    public static void bfs(){
        Queue<Integer> Q=new LinkedList<>();

        Q.offer(1);
        Q.poll();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int M,N,H;
        M=sc.nextInt();
        N=sc.nextInt();
        H=sc.nextInt();

        int[][][] tomato=new int[H][N][M];
        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<M;k++){
                    tomato[i][j][k]=sc.nextInt();
                }
            }
        }
        bfs();

    }
}
