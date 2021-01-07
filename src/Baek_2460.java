//2460번 지능형 기차2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2460 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int in,out,people=0,max=0;
        for(int i=0;i<10;i++){
            StringTokenizer st=new StringTokenizer(bf.readLine()," ");
            out=Integer.parseInt(st.nextToken());
            in=Integer.parseInt(st.nextToken());
            people=people+in-out;
            max=Math.max(people,max);
        }
        System.out.println(max);
    }
}
