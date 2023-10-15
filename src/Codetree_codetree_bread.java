import java.io.*;
import java.util.*;

public class Codetree_codetree_bread {
    static int n, m;
    static int[][] map;
    static ArrayList<int[]> baseCamps = new ArrayList<>();
    static HashMap<Integer, int[]> people = new HashMap<>();
    static HashMap<Integer, int[]> conv = new HashMap<>();
    static int peopleCount = 0;
    static int[] move_row = new int[] { -1, 0, 0, 1 }; // 위 왼 오 아
    static int[] move_col = new int[] { 0, -1, 1, 0 };
    static ArrayList<int[]> candi = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        peopleCount = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    baseCamps.add(new int[] { i, j });
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            conv.put(i, new int[] { row, col });
        }

        // 각 m번 사람은 정확히 m분에 출발.
        // 출발 시간이 되기 전까지 격자 밖에 있음
        // 사람들이 목표로하는 편의점은 모두 다름

        int time = 0;
        while (true) {
            // 0. 각 1분 동안
            time++;
            if (time > m && peopleCount == 0)
                break;
            move();
            acceptCandi();
            // 3. 현재 시간이 t분이고, t<=m을 만족한다면
            if (time <= m) {
                intoBaseCamp(time);
            }
            if (time > m && peopleCount == 0)
                break;
            acceptCandi();
        }

        System.out.println(time);
    }

    static void move() {
        // 1. 격자에 있는 모든 사람들이 가고 싶은 편의점을 향해 1칸 움직임.
        for (int p : people.keySet()) {
            int[] movePerson = people.get(p);
            int[] store = conv.get(p);
            if (movePerson[0] == 0)
                continue;

            int[] nextPoint = findNextPoint(movePerson, store);
            movePerson[0] = nextPoint[0];
            movePerson[1] = nextPoint[1];

            if (movePerson[0] == store[0] && movePerson[1] == store[1]) {
                people.put(p, new int[] { 0, 0 });
                peopleCount--;
                candi.add(new int[] { movePerson[0], movePerson[1] });
            }
        }

        // 2. 만약 편의점에 도착한다면 해당 편의점에서 멈추게 된다.
        // 2.1. 이 때부터 다른 사람들은 해당 편의점이 있는 칸을 지나갈 수 없다.
        // 2.1.1. 격자에 있는 모든 사람들이 모두 이동한 뒤에 칸을 지나갈 수 없어짐
    }

    static int[] findNextPoint(int[] person, int[] store) {
        // 1.1. 최단거리로 움직여야함
        // 1.1.1.최단거리란, 상하좌우 인접한 칸 중 이동 가능한 칸으로만 이동하여, 도달하기 까지 거쳐야하는 칸의 수가 최소가 되는 거리
        // 1.1.2. 최단 거리가 여러개라면 위, 왼, 오, 아래의 우선순위

        int[][][] route = new int[n + 1][n + 1][2];
        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { person[0], person[1] });
        visited[person[0]][person[1]] = true;

        loop: while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                    if (!visited[nextRow][nextCol] && (map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == 1)) {
                        visited[nextRow][nextCol] = true;
                        route[nextRow][nextCol][0] = cur[0];
                        route[nextRow][nextCol][1] = cur[1];

                        if (nextRow == store[0] && nextCol == store[1]) {
                            break loop;
                        }

                        queue.offer(new int[] { nextRow, nextCol });
                    }
                }
            }
        }

        int beforeRow = 0;
        int beforeCol = 0;
        int row = store[0];
        int col = store[1];

        while (row != person[0] || col != person[1]) {
            beforeRow = row;
            beforeCol = col;
            row = route[beforeRow][beforeCol][0];
            col = route[beforeRow][beforeCol][1];
        }

        return new int[] { beforeRow, beforeCol };
    }

    static void intoBaseCamp(int time) {
        // 3.1. t번 사람은 자신이 가고 싶은 편의점에서 가장 가까운 베이스 캠프에 들어감
        int[] wantConv = conv.get(time);
        int[] baseCamp = findNearBaseCamp(wantConv);
        people.put(time, new int[] { baseCamp[0], baseCamp[1] });
        // 3.2. 이 때부터 다른 사람들은 해당 베이스 캠프가 있는 칸을 지나갈 수 없음
        candi.add(new int[] { baseCamp[0], baseCamp[1] });
        // 3.2.1. t번 사람이 편의점을 향해 움직이기 시작했더라도 해당 베이스 캠프는 앞으로 절대 지나갈 수 없음
        // 3.2.2. 격자에 있는 사람들이 모두 이동한 뒤에 지나갈 수 없게 되는 것임
    }

    static int[] findNearBaseCamp(int[] conv) {
        int[] target = new int[] { 0, 0 };
        int shortestDist = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];
        queue.offer(new int[] { conv[0], conv[1] });
        visited[conv[0]][conv[1]] = true;

        loop: while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 3.1.1. 가장 가깝다는 의미는 최단거리와 같음
            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                    if (!visited[nextRow][nextCol]) {
                        if (map[nextRow][nextCol] == 0) {
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            visited[nextRow][nextCol] = true;
                            queue.offer(new int[] { nextRow, nextCol });
                        } else if (map[nextRow][nextCol] == 1) {
                            dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                            visited[nextRow][nextCol] = true;

                            if (dist[nextRow][nextCol] < shortestDist) {
                                shortestDist = dist[nextRow][nextCol];
                                target[0] = nextRow;
                                target[1] = nextCol;
                            } else if (dist[nextRow][nextCol] == shortestDist) {
                                // 3.1.2. 여러개일 경우 행이 작은 베이스캠프
                                if (nextRow < target[0]) {
                                    target[0] = nextRow;
                                    target[1] = nextCol;
                                } else if (nextRow == target[0]) {
                                    // 3.1.3. 여러개일 경우 열이 작은 베이스 캠프
                                    if (nextCol < target[1]) {
                                        target[0] = nextRow;
                                        target[1] = nextCol;
                                    }
                                }
                            }
                            queue.offer(new int[] { nextRow, nextCol });
                        }
                    }
                }
            }
        }

        // 3.1.4. t번 사람이 베이스 캠프로 이동하는데 시간이 소요되지 않음
        peopleCount++;
        return target;
    }

    static void acceptCandi() {
        for (int[] a : candi) {
            map[a[0]][a[1]] = -1;
        }
        candi = new ArrayList<>();
    }
}
