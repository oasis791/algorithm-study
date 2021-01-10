//1541번 잃어버린 괄호
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1541 {
    private static String input;
    private static boolean check = false;
    private static int result = 0;
    private static ArrayList<Integer> al = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input = bf.readLine();
        StringTokenizer st1 = new StringTokenizer(input,"-");
        while(st1.hasMoreTokens()) {
            String temp = st1.nextToken();
            if(!check) {
                int sum = 0;
                StringTokenizer st2 = new StringTokenizer(temp,"+");
                while(st2.hasMoreTokens()){
                    sum += Integer.parseInt(st2.nextToken());
                }
                al.add(sum);
                check = true;
            }
            else{
                int sum = 0;
                StringTokenizer st2 = new StringTokenizer(temp,"+");
                while(st2.hasMoreTokens()){
                    sum += Integer.parseInt(st2.nextToken());
                }
                al.add(-sum);
            }
        }
        for(int i=0;i<al.size();i++)
            result += al.get(i);
        System.out.println(result);
    }
}
