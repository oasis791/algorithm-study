import java.util.*;
import java.io.*;
public class Baek_23290 {
    static int m, s;
    static int[][] map;
    static Queue<int[]> fishes = new LinkedList<>(); // 0: row, 1: col
    static Queue<int[]> toCopyFishes = new LinkedList<>();
    static int[] shark = new int[2];
    // 1부터 좌, 좌상, 상, 우상, 우, 우하,하, 좌하
    static int[] move_row = new int[] {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] move_col = new int[] {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][][] trace = new int[5][5][2]; //trace[x][y][0]이 1면 흔적이 있는거, trace[x][y][1]이 남은 횟
    static int max;
    static ArrayList<int[]> sharkRoad;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new int[5][5];

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fishes.offer(new int[] {x, y, d});
            map[x][y]++;
        }
        st = new StringTokenizer(br.readLine(), " ");
        shark[0] = Integer.parseInt(st.nextToken());
        shark[1] = Integer.parseInt(st.nextToken());

        for(int t = 0; t < s; t++) {
            // 1. 물고기 복제 큐
            int fishesQueueSize = fishes.size();
            for(int i = 0; i < fishesQueueSize; i++) {
                int[] fish = fishes.poll();
                toCopyFishes.offer(fish);
                fishes.offer(fish);
            }
            // 2. 물고기 한 칸 이동 (상어 위치, 물고기 냄새,범위 벗어나는거 제외), 현재 방향 반시계 45도
            for(int f = 0; f < fishesQueueSize; f++) {
                int[] fish = fishes.poll();
                boolean check = false;
                for(int i = 0; i < 8; i++) {
                    int nextDir = fish[2] - i;
                    if(nextDir < 1)
                        nextDir += 8;
                    int nextRow = fish[0] + move_row[nextDir];
                    int nextCol = fish[1] + move_col[nextDir];


                    if(canMove(nextRow, nextCol)) {
                        map[fish[0]][fish[1]]--;
                        map[nextRow][nextCol]++;
                        fishes.offer(new int[]{nextRow, nextCol, nextDir});
                        check = true;
                        break;
                    }
                }
                if(!check)
                    fishes.offer(fish);
            }


            // 3. 상어 3칸이동 (상어는 상하좌우, 연속 이동하는데 격자 벗어나면 불가능, 물고기 만나면 그 칸 물고기 모두 제거 및 냄새 남김, 물고기 제거 가장 많이되는 방향으로 이동 및 사전순
            // 상 -> 좌 -> 하 -> 우 순으로 dfs 고고 3 -> 1 -> 7 -> 5
            boolean[][] visited = new boolean[5][5];
            sharkRoad = new ArrayList<>();
            max = Integer.MIN_VALUE;
            dfs(shark[0], shark[1], 0, 0, visited, new ArrayList<int[]>());

            for(int i = 0; i<sharkRoad.size(); i++) {
                int[] now = sharkRoad.get(i);
                if(map[now[0]][now[1]] > 0) {
                    map[now[0]][now[1]] = 0;
                    trace[now[0]][now[1]][0] = 1;
                    trace[now[0]][now[1]][1] = 3;

                    fishesQueueSize = fishes.size();
                    for(int j = 0; j < fishesQueueSize; j++) {
                        int[] poll = fishes.poll();
                        if(poll[0] == now[0] && poll[1] == now[1]) {
                            continue;
                        } else {
                            fishes.offer(poll);
                        }
                    }
                }
            }
            shark[0] = sharkRoad.get(sharkRoad.size() - 1)[0];
            shark[1] = sharkRoad.get(sharkRoad.size() - 1)[1];

            // 4. 두 번전 시행의 물고기 냄새 제거
            for(int i = 1; i < 5; i++) {
                for(int j = 1; j < 5; j++) {
                    if(trace[i][j][0] == 1) {
                        if(trace[i][j][1] > 1) {
                            trace[i][j][1]--;
                        } else {
                            trace[i][j][0] = 0;
                            trace[i][j][1] = 0;
                        }
                    }
                }
            }
            // 5. 1에서의 물고기 복제 완료
            while(!toCopyFishes.isEmpty()) {
                int[] copyFish = toCopyFishes.poll();
                map[copyFish[0]][copyFish[1]]++;
                fishes.offer(copyFish);
            }
        }

        int answer = 0;
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                if(map[i][j] > 0)
                    answer += map[i][j];
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean canMove(int x, int y) {
        if(x < 1 || x > 4 || y < 1 || y > 4 || (x == shark[0] && y == shark[1])) {
            return false;
        } else if (trace[x][y][0] == 1) {
            return false;
        }
        return true;
    }

    static void dfs(int x, int y, int depth, int ate, boolean[][] visited, ArrayList<int[]> road) {
        if(depth == 3) {
            if(ate > max) {
                max = ate;
                sharkRoad.clear();
                for(int[] i : road) {
                    sharkRoad.add(i);
                }
            }
            return;
        }

        // 상, 좌, 하, 우 3 -> 1 -> 7 -> 5
        for(int i = 0; i < 8; i += 2) {
            int moveDir = 3 - i;
            if(moveDir <= 0)
                moveDir += 8;
            int nextRow = x + move_row[moveDir];
            int nextCol = y + move_col[moveDir];

            if(nextRow >= 1 && nextRow < 5 && nextCol >= 1 && nextCol < 5) {
                if(!visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    road.add(new int[] {nextRow, nextCol});
                    dfs(nextRow, nextCol, depth + 1, ate + map[nextRow][nextCol], visited, road);
                    visited[nextRow][nextCol] = false;
                    road.remove(road.size() - 1);
                } else {
                    road.add(new int[] {nextRow, nextCol});
                    dfs(nextRow, nextCol, depth + 1, ate, visited, road);
                    road.remove(road.size() - 1);
                }
            }

        }
    }
}
