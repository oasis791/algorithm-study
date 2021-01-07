//2869 달팽이는 오르고 싶다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2869{
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        int A=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());
        int V=Integer.parseInt(st.nextToken());
        int cnt=(V-B)/(A-B);
        if((V-B)%(A-B)!=0)
            cnt++;
        System.out.println(cnt);
    }
}
