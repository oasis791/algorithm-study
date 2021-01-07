//1085번 직사각형에서 탈출
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1085 {
    private static int x;
    private static int y;
    private static int w;
    private static int h;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        x=Integer.parseInt(st.nextToken());
        y=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        h=Integer.parseInt(st.nextToken());
        int height,weight;
        weight=w-x;
        height=h-y;
        System.out.println(Math.min(Math.min(weight,x),Math.min(height,y)));
    }
}
