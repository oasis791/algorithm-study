import java.util.*;
import java.io.*;

public class Baek_17406 {
    static int n,m,k;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<int[]> op = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(bf.readLine()," ");
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            op.add(new int[]{r, c, s});
        }

        for(int i = 0; i < k; i++) {
            boolean[] visited = new boolean[k];
            visited[i] = true;
            permutation(visited, 1, i, map);
        }

        System.out.println(answer);
    }

    static void permutation(boolean[] visited, int depth, int index, int[][] rotateMap) {
        int[][] copiedMap = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++)
                copiedMap[i][j] = rotateMap[i][j];
        }

        rotate(op.get(index), copiedMap);

        if(depth == k) {
            answer = Math.min(answer,calculateAnswer(copiedMap));
            return;
        }

        for(int i = 0; i < k; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation(visited, depth + 1, i, copiedMap);
                visited[i] = false;
            }
        }
    }

    static void rotate (int[] op, int[][] rotateMap) {
        int r = op[0];
        int c = op[1];
        int S = op[2];
        
        for (int s = 1; s <= S; s++) {
            //위
            int upTemp = rotateMap[r - s][c + s];
            for(int i = c + s; i > c - s; i--) {
                rotateMap[r - s][i] = rotateMap[r - s][i - 1];
            }
            //오른
            int rightTemp = rotateMap[r + s][c + s];
            for (int i = r + s; i > r - s; i--) {
                rotateMap[i][c + s] = rotateMap[i - 1][c + s];
            }
            rotateMap[r - s + 1][c + s] = upTemp;
            //아래
            int downTemp = rotateMap[r + s][c - s];
            for(int i = c - s; i < c + s; i++) {
                rotateMap[r + s][i] = rotateMap[r + s][i + 1];
            }
            rotateMap[r + s][c + s - 1] = rightTemp;
            //왼쪽
            for(int i = r -s; i < r + s; i++) {
                rotateMap[i][c - s] = rotateMap[i + 1][c - s];
            }
            rotateMap[r + s - 1][c - s] = downTemp;
        }
    }

    static int calculateAnswer (int[][] rotateMap) {
        int value = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= m; j++) {
                sum += rotateMap[i][j];
            }
            value = Math.min(value, sum);
        }

        return value;
    }
}
