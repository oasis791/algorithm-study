import java.io.*;
import java.util.*;

public class Softeer_6283 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        boolean isUp = false;
        int prev = 0;
        for(int i = 0; i < 8; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(prev == 0) {
                if(num == 1) {
                    prev = 1;
                    isUp = true;
                } else if (num == 8) {
                    prev = 8;
                } else {
                    System.out.println("mixed");
                    return;
                }
                continue;
            }

            if(isUp) {
                if(num == prev + 1) {
                    prev = num;
                } else {
                    System.out.println("mixed");
                    return;
                }
            } else {
                if(num == prev - 1) {
                    prev = num;
                } else {
                    System.out.println("mixed");
                    return;
                }
            }
        }

        if(isUp) {
            System.out.println("ascending");
        } else {
            System.out.println("descending");
        }
    }
}
