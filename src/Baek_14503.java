import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] room = new int[N][M];

        int[] leftCheckRow = new int[]{0, -1, 0, 1};
        int[] leftCheckCol = new int[]{-1, 0, 1, 0};
        int[] moveBackRow = new int[]{1, 0, -1, 0};
        int[] moveBackCol = new int[]{0, -1, 0, 1};
        int answer = 0;

        st = new StringTokenizer(bf.readLine());
        int curRow = Integer.parseInt(st.nextToken());
        int curCol = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        int checkedDirections = 1;
        // dir : 0 북, 1 동, 2 남, 3 서
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1. 청소한다
            if (room[curRow][curCol] == 0) {
                answer++;
                room[curRow][curCol] = 2;
            }
            // 2.1 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
            if (curRow + leftCheckRow[dir] >= 0 && curCol + leftCheckCol[dir] >= 0
                    && curRow + leftCheckRow[dir] < N && curCol + leftCheckCol[dir] < M
                    && room[curRow + leftCheckRow[dir]][curCol + leftCheckCol[dir]] == 0) {
                curRow = curRow + leftCheckRow[dir];
                curCol = curCol + leftCheckCol[dir];
                dir -= 1;
                if (dir == -1)
                    dir = 3;
                checkedDirections = 1;
            } else if (checkedDirections <= 4) {
                // 2.2 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
                dir -= 1;
                if (dir == -1)
                    dir = 3;
                checkedDirections++;
            } else if (curRow + moveBackRow[dir] >= 0 && curCol + moveBackCol[dir] >= 0
                    && curRow + moveBackRow[dir] < N && curCol + moveBackCol[dir] < M
                    && room[curRow + moveBackRow[dir]][curCol + moveBackCol[dir]] != 1) {
                // 2.3 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
                curRow = curRow + moveBackRow[dir];
                curCol = curCol + moveBackCol[dir];
                checkedDirections = 1;
            } else {
                // 2.4 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                break;
            }
        }
        System.out.println(answer);
    }
}
