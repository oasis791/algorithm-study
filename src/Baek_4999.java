import java.io.*;

public class Baek_4999 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bf.readLine();
        String s2 = bf.readLine();
        
        if(s1.length() >= s2.length()) {
            System.out.println("go");
        } else {
            System.out.println("no");
        }
    }
}
