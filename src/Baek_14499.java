//14499번 주사위 굴리기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14499 {
    private static int N;
    private static int M;
    private static int x;
    private static int y;
    private static int K;
    private static int[][] map;
    private static int[] command;
    private static boolean check;
    private static void dice(){
        int choice;
        int top=0,bottom=0,left=0,right=0,front=0,back=0;
        int temp,temp2;
        int currentX=x,currentY=y;
        for(int i=0;i<K;i++){
            check=true;
            choice=command[i];
            switch (choice){
                case 1: //동쪽
                    currentY=currentY+1;
                    if(currentY<0||currentY>=M){
                        currentY=currentY-1;
                        check=false;
                        break;
                    }
                    temp=top;
                    temp2=right;
                    top=left;
                    left=bottom;
                    right=temp;
                    if(map[currentX][currentY]==0){
                        bottom=temp2;
                        map[currentX][currentY] = bottom;
                    }
                    else {
                        bottom = map[currentX][currentY];
                        map[currentX][currentY] = 0;
                    }
                    break;
                case 2: // 서쪽
                    currentY=currentY-1;
                    if(currentY<0||currentY>=M) {
                        currentY=currentY+1;
                        check=false;
                        break;
                    }
                    temp=top;
                    temp2=left;
                    top=right;
                    left=temp;
                    right=bottom;
                    if(map[currentX][currentY]==0){
                        bottom=temp2;
                        map[currentX][currentY] = bottom;
                    }
                    else {
                        bottom = map[currentX][currentY];
                        map[currentX][currentY] = 0;
                    }
                    break;
                case 3: //북쪽
                    currentX=currentX-1;
                    if(currentX<0||currentX>=N){
                        currentX=currentX+1;
                        check=false;
                        break;
                    }
                    temp=top;
                    temp2=back;
                    top=front;
                    back=temp;
                    front=bottom;
                    if(map[currentX][currentY]==0){
                        bottom=temp2;
                        map[currentX][currentY] = bottom;
                    }
                    else {
                        bottom = map[currentX][currentY];
                        map[currentX][currentY] = 0;
                    }
                    break;
                case 4: //남쪽
                    currentX=currentX+1;
                    if(currentX<0||currentX>=N){
                        currentX=currentX-1;
                        check=false;
                        break;
                    }
                    temp=top;
                    temp2=front;
                    top=back;
                    back=bottom;
                    front=temp;
                    if(map[currentX][currentY]==0){
                        bottom=temp2;
                        map[currentX][currentY] = bottom;
                    }
                    else {
                        bottom = map[currentX][currentY];
                        map[currentX][currentY] = 0;
                    }
                    break;
                default:
                    break;
            }
            if(check)
                System.out.println(top);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        x=Integer.parseInt(st.nextToken());
        y=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        map=new int[N][M];
        command=new int[K];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            for(int j=0;j<M;j++)
                map[i][j]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(bf.readLine());
        for(int i=0;i<K;i++){
            command[i]=Integer.parseInt(st.nextToken());
        }
        dice();
    }
}
