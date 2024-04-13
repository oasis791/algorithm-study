import java.util.*;
import java.io.*;

public class Codetree_hide_and_seek {
	static int n, m, h, k, answer = 0, turn = 1;
	static Map<Integer, int[]> runnerInfo = new HashMap<>();
	static int[] catcherInfo = new int[6];
	static ArrayList<Integer>[][] runnerMap;
	static boolean[][] treeMap;
	static int[] move_row = new int[] {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] move_col = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 1. n x n 격자에서 진행 됨 (n: 홀수)
		runnerMap = new ArrayList[n + 1][n + 1]; // 0: 도망자 정보, 1: 나무 정보
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				runnerMap[i][j] = new ArrayList<>();
			}
		}
		treeMap = new boolean[n + 1][n + 1];
		// 2. 술래는 정중앙
		catcherInfo[0] = (n + 1) / 2;
		catcherInfo[1] = (n + 1) / 2;
		catcherInfo[2] = 0; // 현재 방향
		catcherInfo[3] = 1; // 현재 방향 바꿀 때 까지 남은 거리
		catcherInfo[4] = 1; // 이번 회차에 총 움직여야 하는 거리
		catcherInfo[5] = 0; // 시계 방향: 0, 반시계 방향 1

		// 3. m명의 도망자 존재
		// 3.1. 도망자는 처음 지정된 곳에 서있음
		// 3.2. 도망자는 중앙에서 시작하지 않음
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(bf.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 1은 좌우, 2는 상하
			// 3.3. 도망자는 좌우로만 움직이는 유형, 상하로만 움직이는 유형이 있음

			runnerMap[x][y].add(i);

			if (d == 1) {
				// 3.4. 이 때, 좌우로 움직이는 사람은 항상 오른쪽을 보고 시작
				runnerInfo.put(i, new int[] {x, y, 1}); // row, col, dir
			} else {
				// 3.5. 상하로 움직이는 사람은 항상 아래쪽을 보고 시작
				runnerInfo.put(i, new int[] {x, y, 2});
			}
		}

		// 4. 술래잡기에는 h개의 나무 존재
		// 4.1. 나무가 도망자와 초기에 겹쳐져 주어지는 것 가능
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(bf.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			treeMap[x][y] = true;
		}

		// 5. 술래잡기 게임은 m명의 도망자가 먼저 동시에 움직이고 술래 움직임
		// 5.1. 도망자, 술래 움직임 => k번 반복
		for (; turn <= k; turn++) {
			runnerMove();
			catcherMove();
			catchRunner();
		}

		System.out.println(answer);
	}

	static void runnerMove() {
		// 5. 술래잡기 게임은 m명의 도망자가 먼저 동시에 움직이고 술래 움직임
		// 5.1. 도망자, 술래 움직임 => k번 반복

		// 6. 도망자 움직임
		for (int runnerIndex : runnerInfo.keySet()) {
			int[] info = runnerInfo.get(runnerIndex);
			// 6.1. 현재 술래와의 거리가 3 이하인 도망자만 움직임
			if (calculateDistance(info) <= 3) {
				// 6.2. 술래와의 거리가 3 이하인 도망자들은 1턴동안 다음 규칙에 따라 움직임
				int nextRow = info[0] + move_row[info[2]];
				int nextCol = info[1] + move_col[info[2]];
				// 6.2.1. 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때, 격자를 벗어나지 않는 경우
				if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
					// 6.2.1.1. 움직이려는 칸에 술래가 있는 경우라면 움직이지 않음
					if (nextRow != catcherInfo[0] || nextCol != catcherInfo[1]) {
						// 6.2.1.2. 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동 (나무도 괜찮)
						runnerMap[nextRow][nextCol].add(runnerIndex);
						for (int i = 0; i < runnerMap[info[0]][info[1]].size(); i++) {
							if (runnerMap[info[0]][info[1]].get(i) == runnerIndex) {
								runnerMap[info[0]][info[1]].remove(i);
								break;
							}
						}
						runnerInfo.put(runnerIndex, new int[] {nextRow, nextCol, info[2]});
					}
				} else {
					// 6.2.2. 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
					// 6.2.2.1. 먼저 방향을 반대로 틀어줌.
					info[2] = (info[2] + 2) % 4;
					nextRow = info[0] + move_row[info[2]];
					nextCol = info[1] + move_col[info[2]];
					// 6.2.2.2. 이후 바라보고 있는 방향으로 1칸 움직인다 했을 때 해당 위치에 술래가 없다면 1칸이동
					if (nextRow != catcherInfo[0] || nextCol != catcherInfo[1]) {
						// 6.2.1.2. 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동 (나무도 괜찮)
						runnerMap[nextRow][nextCol].add(runnerIndex);
						for (int i = 0; i < runnerMap[info[0]][info[1]].size(); i++) {
							if (runnerMap[info[0]][info[1]].get(i) == runnerIndex) {
								runnerMap[info[0]][info[1]].remove(i);
								break;
							}
						}
						runnerInfo.put(runnerIndex, new int[] {nextRow, nextCol, info[2]});
					}
				}
			}
		}

	}

	static void catcherMove() {
		// 7. 술래 움직임
		//		catcherInfo[0] = (n + 1) / 2;
		//		catcherInfo[1] = (n + 1) / 2;
		//		catcherInfo[2] = 0; // 현재 방향
		//		catcherInfo[3] = 1; // 현재 방향 바꿀 때 까지 남은 거리
		//		catcherInfo[4] = 1; // 이번 회차에 총 움직여야 하는 거리
		//		catcherInfo[5] = 0; // 시계 방향: 0, 반시계 방향 1

		// 7.1. 술래는 처음 위 방향으로 시작하여, 달팽이 모양으로 움직임.
		// 7.4. 술래는 1번의 턴 동안 한 칸 해당하는 방향으로 이동
		catcherInfo[0] += move_row[catcherInfo[2]];
		catcherInfo[1] += move_col[catcherInfo[2]];
		catcherInfo[3]--;

		// 7.2. 만약 끝에 도달하게 된다면, 다시 거꾸로 중심으로 달팽이(반시계) 이동
		// 7.3. 다시 중심에 오게되면 처음처럼 시계 달팽이 이동 하는 것을 k턴에 걸쳐 반복
		if ((catcherInfo[0] == 1 && catcherInfo[1] == 1)
			|| (catcherInfo[0] == (n + 1) / 2 && catcherInfo[1] == (n + 1) / 2)) {
			catcherInfo[5] = Math.abs(catcherInfo[5] - 1);
			// 7.6. 만약 이동을 통해 양 끝에 해동하는 위치(1행 1열 혹은 정중앙)에 도달하면, 이 역시 바로 틀어줘야함
			catcherInfo[2] = (catcherInfo[2] + 2) % 4;
			catcherInfo[3] = catcherInfo[4];
			return;
		}

		// 7.5. 이동 후의 위치가 만약 이동방향이 틀어지는 지점이라면, 방향을 바로 틀어줌
		if (catcherInfo[3] == 0) {

			// 정방향 일 때
			if (catcherInfo[5] == 0) {
				// 정방향 위 -> 오, 오 -> 아, 아 -> 왼, 왼 -> 위
				switch (catcherInfo[2]) {
					case 1:
						catcherInfo[4] += 1;
						break;
					case 3:
						if (catcherInfo[1] != 1) {
							catcherInfo[4] += 1;
						}
						break;
					default:
						break;
				}
				catcherInfo[3] = catcherInfo[4];
				catcherInfo[2] = (catcherInfo[2] + 1) % 4;
			} else {
				// 역방향 일 때
				// 역방향 아 -> 오, 오 -> 위, 위 -> 왼, 왼 -> 아
				switch (catcherInfo[2]) {
					case 2:
						if (catcherInfo[1] != 1) {
							catcherInfo[4] -= 1;
						}
						break;
					case 0:
						catcherInfo[4] -= 1;
						break;
					default:
						break;
				}
				catcherInfo[3] = catcherInfo[4];
				catcherInfo[2]--;
				if (catcherInfo[2] < 0)
					catcherInfo[2] += 4;
			}
		}
	}

	static void catchRunner() {
		//		catcherInfo[0] = (n + 1) / 2;
		//		catcherInfo[1] = (n + 1) / 2;
		//		catcherInfo[2] = 0; // 현재 방향
		//		catcherInfo[3] = 1; // 현재 방향 바꿀 때 까지 남은 거리
		//		catcherInfo[4] = 1; // 이번 회차에 총 움직여야 하는 거리
		//		catcherInfo[5] = 0; // 시계 방향: 0, 반시계 방향 1

		// 8. 도망자 잡기
		// 8.1.술래는 턴을 넘기기 전에 시야내에 있는 도망자를 잡게 됨

		// 8.2. 술래의 시야는 현재 바라보고 있는 방향을 기준으로, 현재 칸 포함 3칸
		for (int i = 0; i < 3; i++) {
			int nextRow = catcherInfo[0] + (move_row[catcherInfo[2]] * i);
			int nextCol = catcherInfo[1] + (move_col[catcherInfo[2]] * i);

			if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
				// 8.2.1. 만약, 나무가 놓여있는 칸이라면, 해당 칸에 있는 도망자는 나무에 가려져 보이지 않음
				// 8.2.2. 나무 칸만 해당, 나무 뒤에는 보임
				if (!treeMap[nextRow][nextCol]) {
					if (runnerMap[nextRow][nextCol].size() > 0) {
						int size = runnerMap[nextRow][nextCol].size();
						for (int j = 0; j < size; j++) {
							// 8.2.3. 잡힌 도망자는 사라지게 된다.
							int runnerIndex = runnerMap[nextRow][nextCol].get(0);
							runnerMap[nextRow][nextCol].remove(0);
							runnerInfo.remove(runnerIndex);
							// 8.2.4. 현재 턴을 t번쨰 턴이라고 했을 때, 술래는 t x 현재 턴에서 잡힌 도망자 수 만큼 점수 획득
							answer += turn;
						}
					}
				}
			}
		}

		// 9. k번에 걸쳐 술래잡기를 진행하는 동안 술래가 총 얻게 된 점수를 출력
	}

	static int calculateDistance(int[] runner) {
		// 6.1.1. 도망자의 위치가 (x1, y1), 술래의 위치가 (x2,y2)라 했을 때,
		// 두 사람간의 거리는 |x1 - x2| + |y1 - y2|

		return Math.abs(runner[0] - catcherInfo[0]) + Math.abs(runner[1] - catcherInfo[1]);

	}
}
