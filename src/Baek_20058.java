import java.util.*;
import java.io.*;

public class Baek_20058 {
	static int n, q, l, size;
	static int[][] map;
	static int[] move_row = new int[] { -1, 0, 1, 0 };
	static int[] move_col = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, n);
		// 1. 크기가 2^n x 2^n 격자
		map = new int[size][size];
		// 2. A[r][c]는 얼음의 양
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(bf.readLine());
		// 4. 파이어스톰을 총 Q번 시전한다.
		for (int turn = 0; turn < q; turn++) {
			// 3. 파이어스톰을 시전하려면 시전할 때 마다 단계 L을 결정해야 한다.
			l = Integer.parseInt(st.nextToken());

			splitMap();
		}

		// 5. 모든 파이어스톰을 시전한 후, 다음 2가지를 구한다.
		// 5.1. 남은 얼음 A[r][c]의 합
		int iceCount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				iceCount += map[i][j];
			}
		}
		System.out.println(iceCount);
		// 5.2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
		System.out.println(biggestIce());

	}

	static void splitMap() {

		int splitSize = (int) Math.pow(2, l);
		int[] leftUp = new int[2];
		// 3.1. 먼저 격자 2^L, 2^L 크기의 부분 격자로 나눈다.
		while (leftUp[0] <= size - splitSize) {
			int[] rightDown = new int[2];
			rightDown[0] = leftUp[0] + splitSize - 1;
			rightDown[1] = leftUp[1] + splitSize - 1;
			// 3.2. 이후, 모든 부분 격자를 시계방향으로 90도 회전시킨다.
			rotateMap(leftUp, rightDown, splitSize);
			leftUp[1] += splitSize;

			if (leftUp[1] >= size) {
				leftUp[1] = 0;
				leftUp[0] += splitSize;
			}

		}

		reduceIce();

	}

	static void reduceIce() {
		// 3.3. 이후, 얼음이 있는 칸 3개보다 큰 격자와 인접해있지 않은 칸은 얼음의 양이 1줄어든다.

		ArrayList<int[]> sub = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (map[i][j] > 0) {
					int count = 0;

					for (int k = 0; k < 4; k++) {
						int nextRow = i + move_row[k];
						int nextCol = j + move_col[k];

						if (nextRow >= 0 && nextRow < size && nextCol >= 0 && nextCol < size) {
							if (map[nextRow][nextCol] > 0)
								count++;
						}
					}

					if (count < 3) {
						sub.add(new int[] { i, j });
					}
				}
			}
		}

		for (int[] point : sub) {
			map[point[0]][point[1]]--;
		}
		// 3.3.1. 인접은 상하좌우
	}

	static void rotateMap(int[] leftUp, int[] rightDown, int splitSize) {
		int[][] newMap = new int[splitSize][splitSize];

		int col = leftUp[1];
		for (int i = 0; i < splitSize; i++) {
			int row = rightDown[0];
			for (int j = 0; j < splitSize; j++) {
				newMap[i][j] = map[row--][col];
			}
			col++;
		}

		int row = leftUp[0];
		for (int i = 0; i < splitSize; i++) {
			col = leftUp[1];
			for (int j = 0; j < splitSize; j++) {
				map[row][col++] = newMap[i][j];
			}
			row++;
		}
	}

	static int biggestIce() {
		int max = 0;
		boolean[][] visited = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					Queue<int[]> queue = new LinkedList<>();
					visited[i][j] = true;
					int tempCount = 1;
					queue.offer(new int[] { i, j });

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();

						for (int k = 0; k < 4; k++) {
							int nextRow = cur[0] + move_row[k];
							int nextCol = cur[1] + move_col[k];

							if (nextRow >= 0 && nextRow < size && nextCol >= 0 && nextCol < size) {
								if (map[nextRow][nextCol] > 0 && !visited[nextRow][nextCol]) {
									visited[nextRow][nextCol] = true;
									queue.offer(new int[] { nextRow, nextCol });
									tempCount++;
								}
							}
						}
					}

					max = Math.max(max, tempCount);
				}
			}
		}
		// 5.2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
		// 5.2.1. 얼음이 있는 칸이 얼음이 있는 칸과 인접해있으면, 두 칸은 연결 되어있다고 한다.
		// 5.2.2. 덩어리는 연결된 칸의 집합이다.

		return max;
	}
}
