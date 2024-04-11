import java.util.*;
import java.io.*;

public class SWEA_2105 {
	static int n, answer = 0;
	static int[] move_row = new int[] {1, 1, -1, -1}; // 우하,좌하, 좌상, 우상
	static int[] move_col = new int[] {1, -1, -1, 1};
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(bf.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			answer = 0;

			// 1. N * N의 정사각형 모양을 가진 지역에 디저트 카페가 모여있음
			n = Integer.parseInt(bf.readLine());
			map = new int[n][n];
			// 2. 각 칸에는 디저트의 종류
			// 3. 카페들 사이에는 대각선 방향으로 움직일 수 있는 길들이 있음
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			tour();

			sb.append(answer == 0 ? -1 : answer).append("\n");
		}

		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	static void tour() {
		// 4. 디저트 카페 투어
		// 4.1. 디저트 카페 투어는 어느 한 카페에서 출발하여
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boolean[][] visited = new boolean[n][n];
				Set<Integer> set = new HashSet<>();
				visited[i][j] = true;
				set.add(map[i][j]);

				// 시작은 항상 우하 부터
				int nextRow = i + move_row[0];
				int nextCol = j + move_col[0];

				if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n)
					move(new int[] {i, j}, new int[] {i + move_row[0], j + move_col[0]}, 1, 0, visited, set);
			}
		}

	}

	static void move(int[] startPoint, int[] curPoint, int count, int dir, boolean[][] visited, Set<Integer> set) {
		// 5. 디저트를 되도록 많이 먹으려 한다.
		if (startPoint[0] == curPoint[0] && startPoint[1] == curPoint[1]) {
			answer = Math.max(answer, count);
			return;
		}

		// 4.4. 카페 투어중 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안된다.
		// 4.6. 왔던길을 다시 돌아가는 것도 안된다.
		if (set.contains(map[curPoint[0]][curPoint[1]])) {
			return;
		}

		visited[curPoint[0]][curPoint[1]] = true;
		set.add(map[curPoint[0]][curPoint[1]]);

		int nextRow = curPoint[0] + move_row[dir];
		int nextCol = curPoint[1] + move_col[dir];
		// 4.3. 디저트 카페 투어를 하는 도중에 해당 지역을 벗어나면 안된다.
		if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
			if ((nextRow == startPoint[0] && nextCol == startPoint[1]) || !visited[nextRow][nextCol])
				move(startPoint, new int[] {nextRow, nextCol}, count + 1, dir, visited, set);
		}

		// dir이 3일땐 직진 밖에 안되도록
		if (dir < 3) {
			nextRow = curPoint[0] + move_row[dir + 1];
			nextCol = curPoint[1] + move_col[dir + 1];
			if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
				if ((nextRow == startPoint[0] && nextCol == startPoint[1]) || !visited[nextRow][nextCol])
					move(startPoint, new int[] {nextRow, nextCol}, count + 1, dir + 1, visited, set);
			}
		}

		visited[curPoint[0]][curPoint[1]] = false;
		set.remove(map[curPoint[0]][curPoint[1]]);

		// 4.2. 대각선 방향으로 움직이고, 사각형 모양을 그리며 출발한 카페로 돌아와야 한다. (오는 방향으로 가든지, 우측으로 한바꾸 돌리든지)
		// 4.5. 하나의 카페에서 디저트를 먹는것도 안된다.
	}
}
