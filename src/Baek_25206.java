import java.io.*;
import java.util.*;

public class Baek_25206 {
    static Map<String, Double> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        init();
        
        double sumScore = 0;
        double sum = 0;
        for(int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String subject = st.nextToken();
            Double score = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();

            if(grade.equals("P"))
                continue;
            
            sumScore += score;
            sum += score * map.get(grade);
        }

        System.out.println(sum / sumScore);
    }
    
    static void init() {
        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);
    }
}
