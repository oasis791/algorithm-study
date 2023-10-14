import java.io.*;
import java.util.*;

public class Codetree_Maze_Runner {
    static int n, m, k;
    static int[][] map;
    static int moveSum = 0;
    static int[] exit = new int[] { 0, 0 };
    static Queue<int[]> people = new LinkedList<>();
    static int[] move_row = new int[] { -1, 1, 0, 0 };
    static int[] move_col = new int[] { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        // 빈 칸 => 참가자가 이동 가능하다.
        // 벽
        // 참가자가 이동할 수 없다.
        // 1이상 9이하의 내구도
        // 회전할 때 내구도 1씩 깎임
        // 0이 되면 빈칸으로 변경
        // 출구 => 참가자가 해당 칸에 도달하면 즉시 탈출
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            people.offer(new int[] { row, col });
        }

        st = new StringTokenizer(bf.readLine());
        exit[0] = Integer.parseInt(st.nextToken());
        exit[1] = Integer.parseInt(st.nextToken());

        // 1초마다 => k초 동안
        for (int t = 0; t < k; t++) {
            move();
            if (people.isEmpty())
                break;
            rotate();
        }

        System.out.println(moveSum);
        System.out.println(exit[0] + " " + exit[1]);

    }

    static void move() {
        // 1. 참가자 이동
        // 1.2. 모든 참가자는 동시에 움직임
        int size = people.size();
        loop: for (int i = 0; i < size; i++) {
            int[] cur = people.poll();

            // 1.3.상하좌우로 움직일 수 있으며, 빈칸으로만 이동가능
            // 1.5. 만약 2개이상이라면, 상하로 움직이는 것을 우선시
            for (int j = 0; j < 4; j++) {
                int nextRow = cur[0] + move_row[j];
                int nextCol = cur[1] + move_col[j];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                    // 1.4. 움직일 칸은 현재 칸보다 출구까지의 최단거리가 가까워야함
                    if (map[nextRow][nextCol] == 0
                            && (calculateDistance(nextRow, nextCol) < calculateDistance(cur[0], cur[1]))) {
                        moveSum++;
                        if (nextRow == exit[0] && nextCol == exit[1])
                            continue loop;
                        people.offer(new int[] { nextRow, nextCol });
                        continue loop;
                    }
                }
            }
            // 1.6. 못움직이면 가만히
            people.offer(cur);
        }
        // 1.7. 한칸에 2명이상 있을 수 있음
    }

    static int calculateDistance(int row, int col) {
        // 1.1. 두 위치 (x1, y1), (x2, y2)의 최단거리는 |x1 - x2| + |y1 - y2|
        return Math.abs(row - exit[0]) + Math.abs(col - exit[1]);
    }

    static void rotate() {
        // 2. 미로 회전
        int[] point = findSquare();

        int leftRow = point[0];
        int leftCol = point[1];
        int rightRow = point[2];
        int rightCol = point[3];

        int[][] newMap = new int[rightRow - leftRow + 1][rightCol - leftCol + 1];
        // 2.4. 정사각형은 시계방향 90도 회전하고, 내구도가 1씩 깎임

        int newMapRow = 0;
        int newMapCol = 0;

        Queue<int[]> newPeople = new LinkedList<>();
        int[] tempExit = new int[] { 0, 0 };
        for (int j = leftCol; j <= rightCol; j++) {
            newMapCol = 0;
            for (int i = rightRow; i >= leftRow; i--) {
                if (map[i][j] > 0)
                    map[i][j]--;
                newMap[newMapRow][newMapCol] = map[i][j];

                int size = people.size();

                for (int k = 0; k < size; k++) {
                    int[] cur = people.poll();

                    if (cur[0] == i && cur[1] == j) {
                        newPeople.offer(new int[] { leftRow + newMapRow, leftCol + newMapCol });
                    } else {
                        people.offer(cur);
                    }
                }

                if (exit[0] == i && exit[1] == j) {
                    tempExit[0] = leftRow + newMapRow;
                    tempExit[1] = leftCol + newMapCol;
                }

                newMapCol++;
            }
            newMapRow++;
        }

        while (!newPeople.isEmpty()) {
            people.offer(newPeople.poll());
        }

        exit[0] = tempExit[0];
        exit[1] = tempExit[1];

        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap.length; j++) {
                map[leftRow + i][leftCol + j] = newMap[i][j];
            }
        }
    }

    static int[] findSquare() {
        int[] val = new int[4];
        // 2.1. "한 명 이상의 참가자"와 "출구"를 포함한 가장 작은 정사각형 찾음

        // 2.2. 만약 두개 이상이면, 좌상단 r좌표가 작은 것이 우선
        // 2.3. 만약 두개 이상이라면 c좌표가 작은 것이 우선
        sideloop: for (int sideSize = 1; sideSize < n; sideSize++) {
            for (int row = 1; row <= n - sideSize; row++) {
                for (int col = 1; col <= n - sideSize; col++) {
                    if (check(row, col, sideSize)) {
                        val[0] = row;
                        val[1] = col;
                        val[2] = row + sideSize;
                        val[3] = col + sideSize;
                        break sideloop;
                    }
                }
            }
        }

        return val;
    }

    static boolean check(int curRow, int curCol, int amount) {
        boolean isPerson = false;
        boolean isExit = false;

        for (int i = curRow; i <= curRow + amount; i++) {
            for (int j = curCol; j <= curCol + amount; j++) {
                if (isPerson && isExit)
                    return true;
                if (i == exit[0] && j == exit[1]) {
                    isExit = true;
                }

                int size = people.size();
                for (int k = 0; k < size; k++) {
                    int[] loc = people.poll();
                    if (loc[0] == i && loc[1] == j)
                        isPerson = true;
                    people.offer(loc);
                }

            }
        }

        if (isPerson && isExit) {
            return true;
        } else {
            return false;
        }
    }
}