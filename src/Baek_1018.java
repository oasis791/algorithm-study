//1018번 체스판 다시 칠하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1018{
    private static int N;
    private static int M;
    private static int result=100;
    private static String[][] chess;
    private static int[] move_x={1,-1,0,0};
    private static int[] move_y={0,0,1,-1};
    private static void reDraw(){

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        chess = new String[N][M];
        for(int i=0;i<N;i++){
            String input=bf.readLine();
            chess[i]=input.split("");
        }
        reDraw();
        System.out.println(result);
    }
}
