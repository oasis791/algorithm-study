//7562번 나이트의 이동
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_7562 {
    private static int l;
    private static int goal_x;
    private static int goal_y;
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int[] move_x = {-1,-2,-2,-1,1,2,2,1};
    private static int[] move_y = {-2,-1,1,2,-2,-1,1,2};
    private static Queue<int[]> Q = new LinkedList<>();
    private static void bfs(int x,int y) {
        boolean check=false;
        while (!Q.isEmpty()) {
            int[] temp = Q.poll();
            if (!check) {
                for (int i = 0; i < 8; i++) {
                    int nextX = temp[0] + move_x[i];
                    int nextY = temp[1] + move_y[i];
                    if (nextX < 0 || nextX >= l || nextY < 0 || nextY >= l)
                        continue;
                    if (nextX == goal_x && nextY == goal_y) {
                        matrix[nextX][nextY] = matrix[temp[0]][temp[1]] + 1;
                        check = true;
                        break;
                    }
                    if (!visited[nextX][nextY] && matrix[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                        matrix[nextX][nextY] = matrix[temp[0]][temp[1]] + 1;
                        Q.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int test;
        test = Integer.parseInt(bf.readLine());
        for(int t=0;t<test;t++){
            l = Integer.parseInt(bf.readLine());
            matrix = new int[l][l];
            visited = new boolean[l][l];
            StringTokenizer st = new StringTokenizer(bf.readLine()," ");
            int start_x = Integer.parseInt(st.nextToken());
            int start_y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine()," ");
            goal_x = Integer.parseInt(st.nextToken());
            goal_y = Integer.parseInt(st.nextToken());
            if(start_x==goal_x&&start_y==goal_y){
                System.out.println(0);
                continue;
            }
            Q.offer(new int[] {start_x,start_y});
            visited[start_x][start_y] = true;
            bfs(start_x,start_y);
            System.out.println(matrix[goal_x][goal_y]);
        }
    }
}
