//1920번 수 찾기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1920 {
    private static int N;
    private static int M;
    private static boolean check;
    private static int[] arr;
    private static int[] arr2;
    private static void binSearch(int x, int start, int end) {
        if (start == end - 1) {
            if(arr[start] == x)
                check = true;
            if (arr[end - 1] == x)
                check = true;
        } else {
            int center = (start + end) / 2;
            if (x == arr[center]) {
                check = true;
            } else if (x < arr[center]) {
                binSearch(x, start, center);
            } else if (x > arr[center]) {
                binSearch(x, center, end);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        M = Integer.parseInt(bf.readLine());
        arr2 = new int[M];
        st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < M; i++)
            arr2[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            check = false;
            binSearch(arr2[i], 0, N);
            if(check)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
