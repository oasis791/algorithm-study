import java.io.*;
import java.util.*;

public class Baek_15686 {
    static int answer = Integer.MAX_VALUE;
    static int n, m;
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> home = new ArrayList<>();
    static boolean[] open;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 2) {
                    chicken.add(new int[]{i, j});
                } else if (info == 1) {
                    home.add(new int[]{i, j});
                }
            }
        }
        open = new boolean[chicken.size()];
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int index, int depth) {
        if (depth == m) {
            int count = 0;

            for (int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int dist = Math.abs(home.get(i)[0] - chicken.get(j)[0]) + Math.abs(home.get(i)[1] - chicken.get(j)[1]);
                        temp = Math.min(temp, dist);
                    }
                }
                count += temp;
            }
            answer = Math.min(answer, count);
            return;
        }

        for (int i = index; i < chicken.size(); i++) {
            open[i] = true;
            dfs(i + 1, depth + 1);
            open[i] = false;
        }
    }
}
