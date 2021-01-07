//1012번 유기농 배추

import java.util.Scanner;

public class Baek_1012{
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int[] move_x={0,0,1,-1};
    private static int[] move_y={-1,1,0,0};
    private static int hori;
    private static int vert;
    private static int cabbage;
    private static int cnt;
    private static void dfs(int x,int y){
        visited[x][y]=true;
        matrix[x][y]=0;
        for(int i=0;i<4;i++){
            int newX=x+move_x[i];
            int newY=y+move_y[i];
            if(newX>=hori||newY>=vert||newX<0||newY<0)
                continue;
            else{
                if(matrix[newX][newY]==1&&visited[newX][newY]==false)
                    dfs(newX,newY);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int testCase;
        testCase=sc.nextInt();

        for(int t=0;t<testCase;t++){
            cnt=0;
            hori=sc.nextInt();
            vert=sc.nextInt();
            matrix=new int[hori][vert];
            visited=new boolean[hori][vert];
            cabbage=sc.nextInt();
            for(int c=0;c<cabbage;c++){
                int x=sc.nextInt();
                int y=sc.nextInt();
                matrix[x][y]=1;
            }

            for(int y=0;y<vert;y++){
                for(int x=0;x<hori;x++){
                    if(matrix[x][y]==1) {
                        dfs(x, y);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}