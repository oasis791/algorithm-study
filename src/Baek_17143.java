import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_17143 {
    static int r, c, m;
    static int fisherManCol = 0;
    static int answer = 0;
    static ArrayList<int[]>[][] sharks;
    static HashMap<Integer, int[]> sharkLocation = new HashMap<>();
    static int[] move_row = new int[]{0, -1, 1, 0, 0}; // 위 아래 오른 왼
    static int[] move_col = new int[]{0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sharks = new ArrayList[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                sharks[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            sharks[row][col].add(new int[]{speed, dir, size});
            sharkLocation.put(size, new int[]{row, col});
        }

        // 1. 초기 낚시왕 열 = 0
        // 2. 낚시왕의 열이 c + 1 가 되면 이동 멈춤
        while (fisherManCol < c) {
            // 1. 낚시왕이 오른쪽 한 칸 이동
            fisherManCol++;
            catchShark();
            moveShark();
            deleteShark();
        }

        System.out.println(answer);
    }

    static void catchShark() {
        // 2. 낚시왕이 있는 열의 상어 중, 땅과 제일 가까운 상어를 잡음 (잡으면 상어 사라짐)
        for (int i = 1; i <= r; i++) {
            if (!sharks[i][fisherManCol].isEmpty()) {
                answer += sharks[i][fisherManCol].get(0)[2];
                sharkLocation.remove(sharks[i][fisherManCol].get(0)[2]);
                sharks[i][fisherManCol].remove(0);
                return;
            }
        }
    }

    static void moveShark() {
        // 3. 상어 이동
        for (Integer shark : sharkLocation.keySet()) {
            int[] cur = sharkLocation.get(shark);
            int[] sharkInfo = new int[3];
            int sharkIndex = 0;

            for (int i = 0; i < sharks[cur[0]][cur[1]].size(); i++) {
                if (shark == sharks[cur[0]][cur[1]].get(i)[2]) {
                    sharkInfo[0] = sharks[cur[0]][cur[1]].get(i)[0];
                    sharkInfo[1] = sharks[cur[0]][cur[1]].get(i)[1];
                    sharkInfo[2] = sharks[cur[0]][cur[1]].get(i)[2];
                    sharkIndex = i;
                    break;
                }
            }

            int remain = sharkInfo[0];
            int dir = sharkInfo[1];
            int size = sharkInfo[2];
            int curRow = cur[0];
            int curCol = cur[1];

            if(dir == 1 || dir == 2) {
                remain %= (r - 1) * 2;
            } else {
                remain %= (c - 1) * 2;
            }

            while (remain > 0) {
                int nextRow = curRow + move_row[dir];
                int nextCol = curCol + move_col[dir];

                if (nextRow < 1 || nextRow > r || nextCol < 1 || nextCol > c) {
                    dir = changeDir(dir);
                } else {
                    curRow = nextRow;
                    curCol = nextCol;
                    remain--;

                }
            }

            sharks[cur[0]][cur[1]].remove(sharkIndex);

            sharkLocation.put(shark, new int[]{curRow, curCol});
            sharks[curRow][curCol].add(new int[]{sharkInfo[0], dir, size});
        }

        // 3.1. 상어는 입력으로 주어진 속도로 이동한다.
        // 3.2. 속도의 단위는 칸/초
        // 3.3. 이동하려는 칸이 경계를 넘는경우, 방향 반대로, 속력 유지
        // 3.4. 한칸에 여러마리 있으면, 크기가 큰놈만 남고 나머진 사라짐
    }

    static int changeDir(int curDir) {
        // 1: 위, 2: 아래, 3: 오른, 4: 왼
        switch (curDir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    static void deleteShark() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (sharks[i][j].size() > 1) {
                    int maxSize = sharks[i][j].get(0)[2];
                    int maxSharkDir = sharks[i][j].get(0)[1];
                    int maxSharkSpeed = sharks[i][j].get(0)[0];

                    for (int k = 1; k < sharks[i][j].size(); k++) {
                        int[] cur = sharks[i][j].get(k);
                        if (cur[2] > maxSize) {
                            sharkLocation.remove(maxSize);
                            maxSharkSpeed = cur[0];
                            maxSharkDir = cur[1];
                            maxSize = cur[2];
                        } else {
                            sharkLocation.remove(cur[2]);
                        }
                    }

                    sharks[i][j] = new ArrayList<>();
                    sharks[i][j].add(new int[]{maxSharkSpeed, maxSharkDir, maxSize});
                }
            }
        }
    }
}