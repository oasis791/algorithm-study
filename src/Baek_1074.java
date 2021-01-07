//1074번 Z
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1074 {
    private static int goal_x;
    private static int goal_y;
    private static int count=0;
    private static boolean check=false;
    private static void Z(int x,int y,int N){
        if(N==1)
            check=true;
        if(check==false) {
            if (goal_x < x + N / 2 && goal_y < y + N / 2) {
                Z(x, y, N / 2);
            }
            if (goal_x < x + N / 2 && goal_y >= y + N / 2) {
                count = count + (N / 2 * N / 2);
                Z(x, y + N / 2, N / 2);
            }
            if (goal_x >= x + N / 2 && goal_y < y + N / 2) {
                count = count + 2 * (N / 2 * N / 2);
                Z(x + N / 2, y, N / 2);
            }
            if (goal_x >= x + N / 2 && goal_y >= y + N / 2) {
                count = count + 3 * (N / 2 * N / 2);
                Z(x + N / 2, y + N / 2, N / 2);
            }
        }
    }
    public static void main(String[] aras) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine()," ");
        int N;
        int size=1;
        N=Integer.parseInt(st.nextToken());
        goal_x=Integer.parseInt(st.nextToken());
        goal_y=Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++)
            size*=2;
        Z(0,0,size);
        System.out.println(count);
    }
}
