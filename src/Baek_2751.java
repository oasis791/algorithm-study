//2751번 수 정렬하기2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baek_2751 {
    private static int N;
    private static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(bf.readLine());
        for(int i=0;i<N;i++)
            arr.add(Integer.parseInt(bf.readLine()));
        Collections.sort(arr);
        for (int i = 0; i < N; i++) {
            sb.append(arr.get(i));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
