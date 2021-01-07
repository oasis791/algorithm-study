//2667번 단지번호 붙이기
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baek_2667 {
    private static String[] input;
    private static int N;
    private static int cnt=0;
    private static int[][] matrix;
    private static boolean[][] visited;
    private static ArrayList<Integer> apartment=new ArrayList<>();
    private static int[] move_x={0,0,1,-1};
    private static int[] move_y={-1,1,0,0};
    private static void dfs(int x,int y){
        visited[x][y]=true;
        matrix[x][y]=0;
        for(int i=0;i<4;i++){
            int newX=x+move_x[i];
            int newY=y+move_y[i];
            if(newX>=N||newY>=N||newX<0||newY<0)
                continue;
            else {
                if (matrix[newX][newY] == 1 && visited[newX][newY] == false) {
                    cnt++;
                    dfs(newX, newY);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        input=new String[N];
        matrix=new int[N][N];
        visited=new boolean[N][N];
        for(int i=0;i<N;i++)
            input[i] = sc.next();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                matrix[i][j]=input[i].charAt(j)-'0';
            }
        }
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                if(matrix[x][y]==1) {
                    cnt=1;
                    dfs(x, y);
                    apartment.add(cnt);
                }
            }
        }
        Collections.sort(apartment);
        System.out.println(apartment.size());
        for(int i=0;i<apartment.size();i++) {
            System.out.println(apartment.get(i));
        }
    }
}
