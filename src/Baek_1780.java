//1780 종이의 개수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1780 {
    private static int[][] paper;
    private static int[] count=new int[3];
    private static boolean check;
    private static void cut(int x,int y,int N){
        check=true;
        for(int i=x;i<x+N;i++){
            for(int j=y;j<y+N;j++){
                if(paper[i][j]!=paper[x][y]){
                    cut(x,y,N/3);
                    cut(x,y+(N/3),N/3);
                    cut(x,y+2*(N/3),N/3);
                    cut(x+(N/3),y,N/3);
                    cut(x+(N/3),y+(N/3),N/3);
                    cut(x+(N/3),y+2*(N/3),N/3);
                    cut(x+2*(N/3),y,N/3);
                    cut(x+2*(N/3),y+(N/3),N/3);
                    cut(x+2*(N/3),y+2*(N/3),N/3);
                    check=false;
                    break;
                }
            }
            if(check==false)
                break;
        }
        if(check){
            if(paper[x][y]==-1)
                count[0]++;
            if(paper[x][y]==0)
                count[1]++;
            if(paper[x][y]==1)
                count[2]++;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine()," ");
        int N;
        N=Integer.parseInt(st.nextToken());
        paper=new int[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            for(int j=0;j<N;j++){
                paper[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        cut(0,0,N);
        for(int i=0;i<3;i++)
            System.out.println(count[i]);
    }
}
