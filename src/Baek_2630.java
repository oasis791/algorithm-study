//2630 색종이 만들기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2630{
    private static int[][] colorPaper;
    private static boolean check;
    private static int[] count=new int[2]; // count[0] 하양 || count[1] 블루
    private static void cut(int x,int y,int N){
        check=true;
        for(int i=x;i<x+N;i++){
            for(int j=y;j<y+N;j++){
                if(colorPaper[i][j]!=colorPaper[x][y]) {
                    cut(x,y,N / 2);
                    cut(x,y+(N/2),N/2);
                    cut(x+(N/2),y,N/2);
                    cut(x+(N/2),y+(N/2),N/2);
                    check=false;
                    break;
                }
            }
            if(check==false)
                break;
        }
        if(check){
            if(colorPaper[x][y]==0)
                count[0]++;
            if(colorPaper[x][y]==1)
                count[1]++;
        }
    }
    public static void main(String[] args) throws IOException {
        int N;
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        colorPaper=new int[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            for(int j=0;j<N;j++)
                colorPaper[i][j]=Integer.parseInt(st.nextToken());
        }
        cut(0,0,N);
        System.out.println(count[0]+"\n"+count[1]);
    }
}