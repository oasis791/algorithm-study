import java.io.*;
import java.util.*;

public class Codetree_destroy_canon {
    static int n, m, k;
    static int[][] map;
    static int[][] recentAttack;
    static int[] attacker = new int[]{0, 0};
    static int[] defender = new int[]{0, 0};
    static boolean[][] isAttacked;
    static int[] move_row = new int[]{0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] move_col = new int[]{1, 0, -1, 0};
    static ArrayList<int[]> route;
    static int canonCount = 0;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        recentAttack = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0)
                    canonCount++;
            }
        }

        // 1.모든 격자엔 포탑 존재 (포탑 NM개)
        // 2.각 포탑은 공격력이 존재
        // 2.1. 공격력이 0 이하가 되면 포탑이 부서지고, 더이상 공격할 수 없음(초기에 있을 수 있음)

        // 3. 하나의 턴 -> k번 반복, 부서지지 않은 포탑이 1개가 되면 즉시 중지
        for (int turn = 1; turn <= k; turn++) {
            isAttacked = new boolean[n][m];
            if (canonCount == 1)
                break;
            selectAttacker(turn);
            attack();
            if (canonCount == 1)
                break;
            repairCanon();
        }

        System.out.println(findStrongestCanon());
    }

    static void selectAttacker(int nowTurn) {
        // 3.1. 공격자 선정
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 3.1.1. 0이 아닌 포탑 중, 가장 약한 포탑이 공격자로 선정
                if (map[i][j] == 0)
                    continue;

                if (map[i][j] < min) {
                    // 3.1.1.1. 공격력이 가장 낮은 포탑
                    min = map[i][j];
                    attacker[0] = i;
                    attacker[1] = j;
                } else if (map[i][j] == min) {
                    // 3.1.1.2. 만약 여러개라면, 가장 최근에 공격한 포탑
                    if (recentAttack[i][j] > recentAttack[attacker[0]][attacker[1]]) {
                        attacker[0] = i;
                        attacker[1] = j;
                    } else if (recentAttack[i][j] == recentAttack[attacker[0]][attacker[1]]) {
                        // 3.1.1.3. 만약 여러개라면, 포탑 위치의 행과 열의 합이 가장 큰 포탑
                        int sum1 = i + j;
                        int sum2 = attacker[0] + attacker[1];

                        if (sum1 > sum2) {
                            attacker[0] = i;
                            attacker[1] = j;
                        } else if (sum1 == sum2) {
                            // 3.1.1.4. 만약 여러개라면, 포탑 위치의 열 값이 가장 큰 포탑
                            if (j > attacker[1]) {
                                attacker[0] = i;
                                attacker[1] = j;
                            }
                        }
                    }
                }
            }
        }

        // 3.1.2. 핸디캡 적용 => N + M 만큼 공격력 증가
        map[attacker[0]][attacker[1]] += n + m;
        isAttacked[attacker[0]][attacker[1]] = true;
        recentAttack[attacker[0]][attacker[1]] = nowTurn;
    }

    static void attack() {
        // 3.2. 공격자의 공격
        // 1. 자신을 제외한 가장 강한 포탑 공격
        // 2. 가장 강한 포탑 선정
        selectDefender();

        // 3.2.1. 레이저 공격 - 우선순위 1
        int[][][] from = new int[n][m][2];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        visited[attacker[0]][attacker[1]] = true;
        queue.offer(new int[]{attacker[0], attacker[1]});

        // 3.2.1.1. 레이저의 이동 방법
        boolean razorOkay = false;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == defender[0] && cur[1] == defender[1]) {
                razorOkay = true;
                break;
            }

            // 3.2.1.1.1. 상하좌우 4개의 방향으로 움직일 수 있음
            // 3.2.1.2.2. 만약 여러개라면, 우/하/좌/상의 우선순위대로 움직인 경로가 선택
            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + move_row[i];
                int nextCol = cur[1] + move_col[i];

                // 3.2.1.1.3. 가장자리에서 막힌 방향으로 진행하고자 하면 반대편으로 나옴 (2,3 에서 두번 이동 -> 2,4 -> 2,1)
                if (nextRow >= n) {
                    nextRow -= n;
                } else if (nextRow < 0) {
                    nextRow += n;
                }
                if (nextCol >= m) {
                    nextCol -= m;
                } else if (nextCol < 0) {
                    nextCol += m;
                }

                // 3.2.1.1.2. 부서진 포탑의 위치는 지날 수 없음
                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] != 0) {
                    // 3.2.1.2. 레이저 공격은 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 이동
                    visited[nextRow][nextCol] = true;
                    from[nextRow][nextCol][0] = cur[0];
                    from[nextRow][nextCol][1] = cur[1];
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }

        }

        // 3.2.1.2.1. 만약 존재하지 않는다면 포탄 공격으로
        if (razorOkay) {
            // 3.2.1.3. 최단 경로가 정해졌으면 공격 대상은 공격자의 공격력 만큼 공격력 감소
            map[defender[0]][defender[1]] -= map[attacker[0]][attacker[1]];
            isAttacked[defender[0]][defender[1]] = true;

            if (map[defender[0]][defender[1]] <= 0) {
                map[defender[0]][defender[1]] = 0;
                canonCount--;
            }
            // 3.2.1.4. 공격 대상을 제외한 레이저 경로에 있는 포탑도 공격자의 공격력 / 2 만큼 받음

            int destroyRow = from[defender[0]][defender[1]][0];
            int destroyCol = from[defender[0]][defender[1]][1];

            while ((destroyRow != attacker[0] || destroyCol != attacker[1])) {
                map[destroyRow][destroyCol] -= (map[attacker[0]][attacker[1]] / 2);
                isAttacked[destroyRow][destroyCol] = true;

                if (map[destroyRow][destroyCol] <= 0) {
                    map[destroyRow][destroyCol] = 0;
                    canonCount--;
                }

                int tempRow = from[destroyRow][destroyCol][0];
                int tempCol = from[destroyRow][destroyCol][1];

                destroyRow = tempRow;
                destroyCol = tempCol;

            }

            return;
        }

        // 3.2.2. 포탄 공격 - 우선순위 2 (레이저 공격 안될 시)
        int[] canonMoveRow = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        int[] canonMoveCol = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
        // 3.2.2.1. 공격 대상은 공격자 공격력만큼 피해를 입음
        map[defender[0]][defender[1]] -= map[attacker[0]][attacker[1]];
        isAttacked[defender[0]][defender[1]] = true;

        if (map[defender[0]][defender[1]] <= 0) {
            map[defender[0]][defender[1]] = 0;
            canonCount--;
        }
        // 3.2.2.2. 추가적으로 주위 8개의 방향에 있는 포탑도 공격자 공격력 / 2 만큼 피해입음
        int curRow = defender[0];
        int curCol = defender[1];

        for (int i = 0; i < 8; i++) {
            int nextRow = curRow + canonMoveRow[i];
            int nextCol = curCol + canonMoveCol[i];

            // 3.2.2.2.2. 가장자리에 포탄이 떨어지면 반대편 격자에 미침
            if (nextRow >= n) {
                nextRow -= n;
            } else if (nextRow < 0) {
                nextRow += n;
            }
            if (nextCol >= m) {
                nextCol -= m;
            } else if (nextCol < 0) {
                nextCol += m;
            }

            // 3.2.2.2.1. 공격자는 영향을 받지 않음
            if (map[nextRow][nextCol] != 0 && (nextRow != attacker[0] || nextCol != attacker[1])) {
                map[nextRow][nextCol] -= (map[attacker[0]][attacker[1]] / 2);
                isAttacked[nextRow][nextCol] = true;

                if (map[nextRow][nextCol] <= 0) {
                    map[nextRow][nextCol] = 0;
                    canonCount--;
                }

            }
        }
    }

    static void selectDefender() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1. 공격력이 가장 높은 포탑
                if (map[i][j] == 0 || (i == attacker[0] && j == attacker[1]))
                    continue;

                if (max < map[i][j]) {
                    max = map[i][j];
                    defender[0] = i;
                    defender[1] = j;
                } else if (max == map[i][j]) {
                    // 2. 만약 여러개라면, 공격한지 가장 오래된 포탑
                    if (recentAttack[i][j] < recentAttack[defender[0]][defender[1]]) {
                        defender[0] = i;
                        defender[1] = j;
                    } else if (recentAttack[i][j] == recentAttack[defender[0]][defender[1]]) {
                        // 3. 만약 여러개라면, 포탑 위치의 행과 열의 합이 가장 작은 포탑
                        int sum1 = i + j;
                        int sum2 = defender[0] + defender[1];

                        if (sum1 < sum2) {
                            defender[0] = i;
                            defender[1] = j;
                        } else if (sum1 == sum2) {
                            // 4. 만약 여러개라면, 포탑 위치의 열 값이 가장 작은 포탑
                            if (j < defender[1]) {
                                defender[0] = i;
                                defender[1] = j;
                            }
                        }
                    }
                }
            }
        }

        isAttacked[defender[0]][defender[1]] = true;
    }

    static void repairCanon() {
        // 3.4. 포탑 정비
        // 3.4.1. 부서지지 않은 포탑 중, 공격을 받지 않은 포탑의 공격력 + 1 (공격자도 제외)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1 && !isAttacked[i][j]) {
                    map[i][j]++;
                }
            }
        }

    }

    static int findStrongestCanon() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }
        return max;
    }
}
