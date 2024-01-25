import java.io.*;
import java.util.*;

public class Softeer_6253 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if(a > b) {
            System.out.println("A");
        } else if (b > a) {
            System.out.println("B");
        } else {
            System.out.println("same");
        }
    }
}
