import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_21609 {
    static int n, m;
    static int score = 0;
    static int[][] map;
    static int[] move_row = new int[]{-1, 0, 1, 0};
    static int[] move_col = new int[]{0, 1, 0, -1};
    static ArrayList<int[]> groupBlocks;
    static int[] standardblock;
    static int groupBlockSize;
    static int rainbowBlockCount;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 검은 : -1, 무지개 : 0, 일반(M가지 색상 : 1 ~ M)
        // 인접: 상하좌우

        // 오토 플레이 => 다음 과정이 블록 그룹이 존재하는 동안 계속 반복
        while (findBlock()) {
            deleteBlocks();
            gravity();
            rotate();
            gravity();
        }
        System.out.println(score);
    }

    static boolean findBlock() {
        //블록 그룹 : 연결된 블록의 집합
        groupBlocks = new ArrayList<>();
        standardblock = new int[]{0, 0};
        groupBlockSize = 0;
        rainbowBlockCount = 0;
        boolean[][] visited = new boolean[n][n];
        boolean status = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != -1 && map[i][j] != 0 && map[i][j] != -2) {
                    Queue<int[]> queue = new LinkedList<>();
                    ArrayList<int[]> rainbowList = new ArrayList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int blockColor = map[i][j];
                    int blockCount = 1;
                    int candiRainbowCount = 0;
                    int[] candiStandardBlock = new int[]{i, j};
                    ArrayList<int[]> candiGroup = new ArrayList<>();
                    candiGroup.add(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextRow = cur[0] + move_row[k];
                            int nextCol = cur[1] + move_col[k];

                            if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                                if (!visited[nextRow][nextCol] &&
                                        (map[nextRow][nextCol] == blockColor || map[nextRow][nextCol] == 0)) {
                                    if (map[nextRow][nextCol] == 0) {
                                        rainbowList.add(new int[]{nextRow, nextCol});
                                        candiRainbowCount++;
                                    }
                                    visited[nextRow][nextCol] = true;
                                    blockCount++;
                                    candiGroup.add(new int[]{nextRow, nextCol});
                                    queue.offer(new int[]{nextRow, nextCol});
                                }
                            }
                        }
                    }

                    if (blockCount >= 2) {
                        if (blockCount > groupBlockSize) {
                            changeGroup(blockCount, candiRainbowCount, candiGroup, candiStandardBlock);
                            status = true;
                        } else if (blockCount == groupBlockSize) {
                            if (candiRainbowCount > rainbowBlockCount) {
                                changeGroup(blockCount, candiRainbowCount, candiGroup, candiStandardBlock);
                                status = true;
                            } else if (candiRainbowCount == rainbowBlockCount) {
                                if (candiStandardBlock[0] > standardblock[0]) {
                                    changeGroup(blockCount, candiRainbowCount, candiGroup, candiStandardBlock);
                                    status = true;
                                } else if (candiStandardBlock[0] == standardblock[0]) {
                                    if (candiStandardBlock[1] > standardblock[1]) {
                                        changeGroup(blockCount, candiRainbowCount, candiGroup, candiStandardBlock);
                                        status = true;
                                    }
                                }
                            }
                        }
                    }

                    for(int[] rainbow : rainbowList) {
                        visited[rainbow[0]][rainbow[1]] = false;
                    }
                }
            }
        }
        //  -> 일반블록 적어도 하나 있어야함
        //  -> 일반블록의 색은 모두 같아야함
        //  -> 검은 블록은 포함X, 무지개는 상관없음
        //  -> 그룹에 속한 블록의 개수는 2보다 크거나 같아야함
        //  -> 임의의 한 블록은 그룹내 모든칸으로 이동할 수 있어야함
        //  -> 블록 그룹의 기준블록
        //      -> 무지개 블록이 아닌 블록 중 행의 번호가 가장 작은 블록
        //      -> 여러개면 열의 번호가 가장 작은 블록


        // 1. 크기가 가장 큰 블록 그룹을 찾는다
        // 1.1. 여러개라면 포함된 무지개 블록 수가 가장 많은 블록 그룹
        // 1.2. 그것도 여러개면 기준 블록 행이 가장 큰것
        // 1.3. 그것도 여러개면 열이 가장 큰것
        return status;
    }

    static void changeGroup(int blockCount, int candiRainbowCount, ArrayList<int[]> candiGroup, int[] candiStandardBlock) {
        groupBlockSize = blockCount;
        rainbowBlockCount = candiRainbowCount;
        standardblock = candiStandardBlock;
        groupBlocks = candiGroup;
    }

    static void deleteBlocks() {
        // 2. 1에서 찾은 블록 그룹의 모든 블록 제거
        for (int i = 0; i < groupBlockSize; i++) {
            int[] cur = groupBlocks.get(i);
            map[cur[0]][cur[1]] = -2;
        }
        // 2.1. 블록 그룹에 포함된 블록의 수를 B라 했을 때, B^2 점을 획득
        score += groupBlockSize * groupBlockSize;
    }

    static void gravity() {
        // 3. 격자에 중력 작용
        for (int c = 0; c < n; c++) {
            int fallIndex = n - 1;
            for (int r = n - 1; r >= 0; r--) {
                if (map[r][c] == -1) {
                    fallIndex = r - 1;
                } else {
                    if (map[r][c] >= 0) {
                        if (r == fallIndex) {
                            fallIndex -= 1;
                        } else {
                            map[fallIndex][c] = map[r][c];
                            map[r][c] = -2;
                            fallIndex -= 1;
                        }
                    }
                }
            }
        }
        // => 중력: 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동
        //      => 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속
    }

    static void rotate() {
        // 4. 격자가 90도 반시계 방향 회전
        int[][] newMap = new int[n][n];
        int changeRow = 0;
        int changeCol = n - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[i][j] = map[changeRow++][changeCol];
            }
            changeRow = 0;
            changeCol--;
        }

        map = newMap;
    }
}
