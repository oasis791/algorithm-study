//1620번 나는야 포켓몬 마스터 이다솜
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_1620 {
    private static int N;
    private static int M;
    private static HashMap<Integer, String> hashMap1 = new HashMap<>();
    private static HashMap<String, Integer> hashMap2 = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            String temp = bf.readLine();
            hashMap1.put(i, temp);
            hashMap2.put(temp, i);
        }

        for (int i = 0; i < M; i++) {
            String temp = bf.readLine();
            char ch = temp.charAt(0);
            if ((int) ch >= 48 && (int) ch <= 57) {
                System.out.println(hashMap1.get(Integer.parseInt(temp)));
            } else {
                System.out.println(hashMap2.get(temp));
            }
        }
    }
}
