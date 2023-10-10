import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_19237 {
    static int n, m, k;
    static int[] move_row = new int[]{0, -1, 1, 0, 0}; // 상 하 좌 우
    static int[] move_col = new int[]{0, 0, 0, -1, 1};
    static ArrayList<Integer>[][] sharks;
    static HashMap<Integer, int[]> sharkInfo = new HashMap<>();
    static Smell[][] smells = new Smell[n + 1][n + 1];
    static int[][][] priority;
    static ArrayList<int[]> deleteSmells;

    static class Smell {
        public int remain;
        public int sharkNum;

        public Smell() {
            this.remain = 0;
            this.sharkNum = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 상어의 번호 1번이 가장 강함
        k = Integer.parseInt(st.nextToken());

        smells = new Smell[n + 1][n + 1];
        sharks = new ArrayList[n + 1][n + 1];
        priority = new int[m + 1][5][4]; // priortiy[어떤상어][어떤방향일때][우선순위순서]

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                smells[i][j] = new Smell();
                sharks[i][j] = new ArrayList<>();
                int temp = Integer.parseInt(st.nextToken());
                if (temp > 0) {
                    sharkInfo.put(temp, new int[]{i, j, 0}); // row, col, dir
                    sharks[i][j].add(temp);
                }
            }
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= m; i++) {
            int curDir = Integer.parseInt(st.nextToken());
            sharkInfo.get(i)[2] = curDir;
        }

        for (int i = 1; i <= m; i++) {
            // 우선순위 설정
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 0;

        // 1. 상어가 자신의 위치에 자신의 냄새를 뿌린다. (k로 두면될거같음)
        spread();

        while (sharkInfo.size() > 1) {
            if (time == 1000) {
                System.out.println(-1);
                return;
            }

            time++;
            copyCurSmells();
            moveShark();
            deleteSmell();
            // 3. 자신의 냄새를 그 칸에 뿌린다.
            spread();
            // 4. 냄새는 상어가 k번 이동하면 사라진다.
            deleteShark();

        }

        System.out.println(time);

    }

    static void spread() {
        for (Integer i : sharkInfo.keySet()) {
            int[] loc = sharkInfo.get(i);
            if (smells[loc[0]][loc[1]].remain == k) {
                if (smells[loc[0]][loc[1]].sharkNum > i) {
                    smells[loc[0]][loc[1]].sharkNum = i;
                }
            } else {
                smells[loc[0]][loc[1]].remain = k;
                smells[loc[0]][loc[1]].sharkNum = i;
            }
        }
    }

    static void moveShark() {
        // 2. 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동한다.
        for (Integer shark : sharkInfo.keySet()) {
            int[] cur = sharkInfo.get(shark);
            int curRow = cur[0];
            int curCol = cur[1];
            int curDir = cur[2];

            // 상하좌우 빈 칸 개수 세기
            // 상하좌우 내 냄새 칸 개수 세기
            int empty = 0;
            int mySmell = 0;
            for (int i = 1; i <= 4; i++) {
                int nextRow = curRow + move_row[i];
                int nextCol = curCol + move_col[i];

                if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                    // 빈칸
                    if (smells[nextRow][nextCol].remain == 0) {
                        empty++;
                    } else {
                        if (smells[nextRow][nextCol].sharkNum == shark) {
                            mySmell++;
                        }
                    }
                }
            }

            // 2.1. 인접한 칸 중 아무 냄새가 없는 칸으로 이동 -> 우선 순위 1등
            if (empty >= 1) {
                for (int i = 0; i < 4; i++) {
                    int nextDir = priority[shark][curDir][i];
                    int nextRow = curRow + move_row[nextDir];
                    int nextCol = curCol + move_col[nextDir];

                    if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                        if (smells[nextRow][nextCol].remain == 0) {
                            sharks[curRow][curCol].remove(0);
                            sharkInfo.put(shark, new int[]{nextRow, nextCol, nextDir});
                            sharks[nextRow][nextCol].add(shark);
                            break;
                        }
                    }
                }
            } else {
                // 2.2. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. -> 우선 순위 2등
                if (mySmell >= 1) {
                    for (int i = 0; i < 4; i++) {
                        int nextDir = priority[shark][curDir][i];
                        int nextRow = curRow + move_row[nextDir];
                        int nextCol = curCol + move_col[nextDir];

                        if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
                            if (smells[nextRow][nextCol].sharkNum == shark && smells[nextRow][nextCol].remain > 0) {
                                sharks[curRow][curCol].remove(0);
                                sharkInfo.put(shark, new int[]{nextRow, nextCol, nextDir});
                                sharks[nextRow][nextCol].add(shark);
                                break;
                            }
                        }

                    }
                }
                // 2.4. 우선 순위는 상어마다 다를 수 있고, 같은 상어라도 보고있는 방향에 따라 다를 수 있다. (처음은 주어지고, 그 이후로는 방금 이동한 방향을 보고있음)
            }
        }
    }

    static void deleteSmell() {
        for (int[] cur : deleteSmells) {
            smells[cur[0]][cur[1]].remain--;
        }
    }

    static void copyCurSmells() {
        deleteSmells = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (smells[i][j].remain > 0) {
                    deleteSmells.add(new int[]{i, j});
                }
            }
        }
    }

    static void deleteShark() {
        // 5. 모든 상어가 이동한 후 한칸에 여러마리 상어가 남아있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 없어짐
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (sharks[i][j].size() >= 2) {
                    int king = Integer.MAX_VALUE;
                    for (int k = 0; k < sharks[i][j].size(); k++) {
                        Integer shark = sharks[i][j].get(k);
                        if (king > shark) {
                            king = shark;
                        } else {
                            sharks[i][j].remove(k);
                            sharkInfo.remove(shark);
                            k--;
                        }
                    }
                }
            }
        }
    }
}
