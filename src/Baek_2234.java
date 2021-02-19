//2234번 성곽
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_2234 {
    private static int N;
    private static int M;
    private static Node[][] castle;
    private static int[][] roomNumArray;
    private static Queue<int[]> q = new LinkedList<>();
    private static boolean[][] visited;
    private static int[] move_x = {-1, 0, 1, 0};
    private static int[] move_y = {0, 1, 0, -1};
    private static int[] result = new int[3];
    private static int roomNum = 0;
    private static int[] roomSize;
    private static ArrayList<Integer> roomSizeResult = new ArrayList<>();
    public static class Node {
        public int west; //1
        public int north; //2
        public int east; //4
        public int south; //8
        public Node(int w, int n, int e, int s) {
            west = w;
            north = n;
            east = e;
            south = s;
        }
    }
    private static void bfs() {
        int width = 0;
        while (!q.isEmpty()) {
            width++;
            int[] temp = q.poll();
            roomNumArray[temp[0]][temp[1]] = roomNum;
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                } else {
                    switch (i) {
                        case 0: // 북쪽
                            if (castle[temp[0]][temp[1]].north != 1 && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                            }
                            break;
                        case 1: // 동쪽
                            if (castle[temp[0]][temp[1]].east != 1 && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                            }
                            break;
                        case 2: // 남쪽
                            if (castle[temp[0]][temp[1]].south != 1 && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                            }
                            break;
                        case 3: // 서쪽
                            if (castle[temp[0]][temp[1]].west != 1 && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                q.offer(new int[]{nextX, nextY});
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        result[0]++;
        result[1] = Math.max(result[1], width);
    }
    private static void breakWall() {
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + move_x[i];
                int nextY = temp[1] + move_y[i];
                if ((nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) && !visited[nextX][nextY]) {
                    if (roomNumArray[temp[0]][temp[1]] != roomNumArray[nextX][nextY]) {
                        roomSizeResult.add(roomSize[roomNumArray[temp[0]][temp[1]]] + roomSize[roomNumArray[nextX][nextY]]);
                    }
                    q.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
        for (int i = 0; i < roomSizeResult.size(); i++) {
            result[2] = Math.max(result[2], roomSizeResult.get(i));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        castle = new Node[N][M];
        visited = new boolean[N][M];
        roomNumArray = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                castle[i][j] = new Node(0, 0, 0, 0);
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++) {
                    switch (k) {
                        case 0:
                            castle[i][j].west = temp % 2;
                            temp /= 2;
                            break;
                        case 1:
                            castle[i][j].north = temp % 2;
                            temp /= 2;
                            break;
                        case 2:
                            castle[i][j].east = temp % 2;
                            castle[i][j].south = temp / 2;
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    roomNum++;
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    bfs();
                }
            }
        }
        roomSize = new int[result[0] + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k <= roomNum; k++) {
                    if (roomNumArray[i][j] == k) {
                        roomSize[k]++;
                    }
                }
            }
        }
        visited = new boolean[N][M];
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        breakWall();

        for (int i = 0; i < 3; i++) {
            System.out.println(result[i]);
        }
    }
}
