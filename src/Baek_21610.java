import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_21610 {
	static int n, m;
	static int[][] map;
	static int[] move_row = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] move_col = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static ArrayList<int[]> clouds = new ArrayList<>();
	static boolean[][] isRained;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];

		// 1. 1번 row 위 n번 row, 1번 col 왼쪽 n번 col
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2. 초기 구름 위치
		clouds.add(new int[] {n, 1});
		clouds.add(new int[] {n, 2});
		clouds.add(new int[] {n - 1, 1});
		clouds.add(new int[] {n - 1, 2});

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// 1. 모든 구름이 d 방향으로 s칸 이동
			moveClouds(d - 1, s);

			// 2. 각 구름에서 비가 내려, 구름이 있는 칸의 바구니에 저장된 물이 1씩 증가
			rain();

			// 4. 2에서 물이 증가한 칸(r,c)에 물복사 마법 시작
			magic();

			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이 때, 구름이 생기는
			//칸은 3에서 구름이 사리진 칸이 아니어야 한다.
			makeClouds();
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				answer += map[i][j];
			}
		}

		System.out.println(answer);
	}

	static void moveClouds(int dir, int dist) {
		int size = clouds.size();
		for (int i = 0; i < size; i++) {
			int[] cloud = clouds.get(0);
			int nextRow = cloud[0] + (move_row[dir] * dist);
			int nextCol = cloud[1] + (move_col[dir] * dist);

			while (nextCol <= 0) {
				nextCol += n;
			}
			while (nextRow <= 0) {
				nextRow += n;
			}
			while (nextCol > n) {
				nextCol -= n;
			}
			while (nextRow > n) {
				nextRow -= n;
			}

			clouds.add(new int[] {nextRow, nextCol});
			clouds.remove(0);
		}
	}

	static void rain() {
		isRained = new boolean[n + 1][n + 1];

		for (int i = 0; i < clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			map[cloud[0]][cloud[1]]++;
			isRained[cloud[0]][cloud[1]] = true;
		}
	}

	static void magic() {

		int size = clouds.size();

		for (int i = 0; i < size; i++) {

			int[] cloud = clouds.get(0);
			// 1. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양이 증가
			// index: 1, 3, 5, 7
			int count = 0;
			for (int j = 1; j <= 7; j += 2) {
				int nextRow = cloud[0] + move_row[j];
				int nextCol = cloud[1] + move_col[j];

				// 2. 이 때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인칸이 아님
				if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
					if (map[nextRow][nextCol] > 0)
						count++;
				}
			}

			map[cloud[0]][cloud[1]] += count;
			// 3. 예를들어 (N,2)에서 인접한 대각선칸은 (N-1, 1), (N-1,3)이고, (N,N)에서 인접한 대각선칸은 (N-1, N-1) 뿐이다.
			// 3. 구름이 모두 사라짐
			clouds.remove(0);
		}
	}

	static void makeClouds() {
		// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이 때, 구름이 생기는
		//칸은 3에서 구름이 사리진 칸이 아니어야 한다.

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (map[i][j] >= 2 && !isRained[i][j]) {
					clouds.add(new int[] {i, j});
					map[i][j] -= 2;
				}
			}
		}
	}
}