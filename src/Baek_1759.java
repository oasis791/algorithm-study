//1759번 암호 만들기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1759 {
    private static int L;
    private static int C;
    private static boolean[] visited;
    private static char[] alphabet;
    private static char[] result;

    private static void dfs(int index, int depth) {
        result[depth - 1] = alphabet[index];
        visited[index] = true;
        boolean[] temp = Arrays.copyOf(visited, visited.length);
        if (depth == L) {
            int consonantCount = 0;
            boolean check = false;
            for (int i = 0; i < L; i++) {
                if (result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u') {
                    check = true;
                } else {
                    consonantCount++;
                }
            }
            if (check && consonantCount>=2) {
                for (int i = 0; i < L; i++) {
                    System.out.print(result[i]);
                }
                System.out.println();
            }
        } else {
            for (int i = index + 1; i < C; i++) {
                visited = Arrays.copyOf(temp, temp.length);
                if (!visited[i]) {
                    dfs(i, depth + 1);
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabet = new char[C];
        result = new char[L];
        st = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphabet);
        for (int i = 0; i < C; i++) {
            visited = new boolean[C];
            dfs(i, 1);
        }
    }
}
