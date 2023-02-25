//1978번 소수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1978 {
    static int result = 0;
    static boolean[] prime;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] input = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, input[i]);
        }
        prime = new boolean[1000001];
        isPrime();
        for (int i = 0; i < N; i++) {
            if (!prime[input[i]]) {
                result++;
            }
        }

        System.out.println(result);
    }
    static void isPrime() {
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i < 1000000; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    prime[j] = true;
                }
            }
        }
    }
}


