import java.io.*;
import java.util.*;

public class Baek_15686 {
    static int answer = Integer.MAX_VALUE;
    static int n, m;
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> home = new ArrayList<>();
    static int[] next_row = new int[]{-1, 0, 1, 0};
    static int[] next_col = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for(int j = 1; j <= n; j++) {
                int info = Integer.parseInt(st.nextToken());
                if(info == 2) {
                    chicken.add(new int[]{i ,j});
                } else if(info == 1) {
                    home.add(new int[]{i, j});
                }
            }
        }

        for(int i = 1; i <= m; i++) {
            ArrayList<int[]> selected = new ArrayList<>();
            dfsToSelectChicken(selected, i, 0, new boolean[chicken.size()], 0);
        }

        System.out.println(answer);
    }

    static void dfsToSelectChicken(ArrayList<int[]> selected, int max, int depth, boolean[] visited, int index) {
        if(depth == max) {
            calculateChickenDistance(bfs(selected, new int[n + 1][n + 1]));
            return;
        }

        for(int i = index; i < chicken.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected.add(chicken.get(i));
                dfsToSelectChicken(selected, max, depth + 1, visited, i + 1);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    static int[][] bfs(ArrayList<int[]> selected, int[][] map) {
        for(int i = 0; i < selected.size(); i++) {
            Queue<int[]> queue = new LinkedList<>();
            int[] startPoint = selected.get(i);
            map[startPoint[0]][startPoint[1]] = 0;
            queue.offer(startPoint);

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];

                for(int j = 0; j < 4; j++) {
                    int nextRow = row + next_row[j];
                    int nextCol = col + next_col[j];
                    if(nextRow > 0 && nextRow <= n && nextCol > 0 && nextCol <= n) {
                        if((nextRow != startPoint[0] || nextCol != startPoint[1])) {
                            if(map[nextRow][nextCol] == 0 || map[nextRow][nextCol] > map[row][col] + 1) {
                                map[nextRow][nextCol] = map[row][col] + 1;
                                queue.offer(new int[]{nextRow, nextCol});
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    static void calculateChickenDistance(int[][] map) {
        int count = 0;
        for(int i = 0; i < home.size(); i++) {
            int[] homePoint = home.get(i);
            count += map[homePoint[0]][homePoint[1]];
        }

        answer = Math.min(answer, count);
    }
}
