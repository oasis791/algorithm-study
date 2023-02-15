import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_16938 {
    static int N, L, R, X;
    static Set<String> set = new LinkedHashSet<>();
    static int[] questions;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제의 개수
        L = Integer.parseInt(st.nextToken()); // 문제 난이도 최소 합
        R = Integer.parseInt(st.nextToken()); // 문제 난이도 최대 합
        X = Integer.parseInt(st.nextToken()); // (가장 어려운 - 가장 쉬운) >= X

        questions = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            questions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(questions);

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, Integer.MAX_VALUE, Integer.MIN_VALUE, visited, 0, new StringBuilder(), 1);
        }

        System.out.println(set.size());
    }

    static void dfs(int index, int min, int max, boolean[] visited, int sum, StringBuilder s, int length) {
        min = Math.min(min, questions[index]);
        max = Math.max(max, questions[index]);
        s.append(questions[index]).append(index);
        sum += questions[index];
        if (sum >= L && sum <= R && (max - min) >= X && length > 1) {
            set.add(s.toString());
        }

        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, min, max, visited, sum, new StringBuilder(s), length + 1);
                visited[i] = false;
            }
        }
    }
}
