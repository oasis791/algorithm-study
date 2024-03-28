import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Codetree_rudolph_rebellion {
	static int n, m, p, c, d, remainSanta;
	static int turn = 0;
	static int[] rudolph = new int[] {0, 0};
	static int[][] santa;
	static ArrayList<Integer>[][] map;
	static int[] answer;
	static int[] rudolph_move_row = new int[] {-1, -1, 0, 1, 1, 1, 0, -1}; // 상 우상 우 우하 하 좌하 좌 좌상
	static int[] rudolph_move_col = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] santa_move_row = new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] santa_move_col = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());

		rudolph[0] = Integer.parseInt(st.nextToken());
		rudolph[1] = Integer.parseInt(st.nextToken());

		// 1. 산타: 1~P번까지 P명 (O)
		santa = new int[p + 1][3];
		remainSanta = p;
		answer = new int[p + 1];

		// 2. 게임판: N x N, 각 위치 (r,c), 좌상단 (1,1) (O)
		map = new ArrayList[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(bf.readLine());
			int santaNum = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			santa[santaNum][0] = sr;
			santa[santaNum][1] = sc;
			santa[santaNum][2] = 0; // 기절이 풀려나는 인덱스

			map[sr][sc].add(santaNum);
		}

		// 3. 게임은 M개의 턴에 걸쳐 진행 (O)
		for (; turn < m; turn++) {

			// 3.1. 매 턴마다 루돌프와 산타가 한 번씩 움직임
			rudolphMove();
			// 3.2. 루돌프가 한번 움직인 뒤, 1번 산타부터 P번 산타까지 순서대로 움직임
			santaMove();
			// 4. M번의 턴에 걸쳐 루돌프, 산타가 순서대로 움직인 이후 게임 종료 (O)
			// 5. 만약 P명의 산타가 모두 게임에서 탈락하게 된다면 즉시 게임 종료 (O)
			if (remainSanta == 0)
				break;
			// 6. 매 턴 이후 탈락하지 않은 산타들에게는 1점씩 추가 부여 (O)
			givePoints();
		}

		// 출력 (O)
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= p; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	static void rudolphMove() {

		// 1. 가장 가까운 산타를 향해 1칸 돌진
		int shortestDist = Integer.MAX_VALUE;
		int shortestSantaIndex = 0;

		for (int i = 1; i <= p; i++) {
			// 1.1. 게임에서 탈락하지 않은 산타여야함 (O)
			if (santa[i][0] == -1)
				continue;

			int dist = calculateDist(rudolph, santa[i]);
			if (dist < shortestDist) {
				shortestDist = dist;
				shortestSantaIndex = i;
			} else if (dist == shortestDist) {
				// 1.2. 가장 가까운 산타가 2명 이상이라면, r좌표가 큰 산타로, r이 동일하면 c좌표가 큰 산타로 (O)
				if (santa[i][0] > santa[shortestSantaIndex][0]) {
					shortestSantaIndex = i;
				} else if (santa[i][0] == santa[shortestSantaIndex][0]) {
					if (santa[i][1] > santa[shortestSantaIndex][1]) {
						shortestSantaIndex = i;
					}
				}
			}
		}

		// 1.3. 루돌프는 8방향중 하나로 돌진(O)
		// 1.4. 우선순위가 가장 높은 산타를 향해 8방향 중 가장 가까워지는 방향으로 한칸 돌진(O)
		int dirIndex = findDirection(shortestSantaIndex);
		rudolph[0] += rudolph_move_row[dirIndex];
		rudolph[1] += rudolph_move_col[dirIndex];

		// 1.5. 산타와 루돌프가 같은 칸에 있게되면 충돌 발생 (O)
		if (map[rudolph[0]][rudolph[1]].size() > 0) {
			rudolphMoveCrash(dirIndex);
		}
	}

	static int findDirection(int santaIndex) {
		int shortestDist = Integer.MAX_VALUE;
		int returnDir = 0;

		for (int i = 0; i < 8; i++) {
			int nextRow = rudolph[0] + rudolph_move_row[i];
			int nextCol = rudolph[1] + rudolph_move_col[i];

			if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
				int dist = calculateDist(new int[] {nextRow, nextCol}, santa[santaIndex]);
				if (dist < shortestDist) {
					shortestDist = dist;
					returnDir = i;
				}
			}
		}

		return returnDir;
	}

	static void santaMove() {

		// 1. 산타는 1번부터 P번까지 순서대로 움직임
		for (int i = 1; i <= p; i++) {
			// 2. 이 때, 기절해있거나 밖으로 빠져나가 탈락한 산타는 움직일 수 없음.
			if (santa[i][0] == -1 || santa[i][2] > turn)
				continue;

			int moveIndex = -1;
			int shortestDist = Integer.MAX_VALUE;

			for (int j = 0; j < 4; j++) {
				int nextRow = santa[i][0] + santa_move_row[j];
				int nextCol = santa[i][1] + santa_move_col[j];

				// 4. 산타는 다른 산타가 있는 칸이나, 게임판 밖으로는 움직일 수 없음. (O)
				if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= n) {
					if (map[nextRow][nextCol].size() > 0)
						continue;

					int dist = calculateDist(rudolph, new int[] {nextRow, nextCol});
					// 3. 산타는 루돌프에게 가장 가까워지는 방향으로 1칸 이동(O)
					// 8. 이때, 가까워질 수 있는 방향이 여러개라면, 상-> 우 -> 하-> 좌 우선순위에 맞춰 움직임(O)
					if (dist < shortestDist) {
						moveIndex = j;
						shortestDist = dist;
					}
				}
			}

			// 5. 움직일 수 있는 칸이 없다면 산타는 움직이지 않음 (O)
			// 6. 움직일 수 있는 칸이 있더라도, 루돌프와 가까워질 수 있는 방법이 없다면 움직이지 않음 (O)
			if (moveIndex != -1 && (shortestDist < calculateDist(rudolph, santa[i]))) {
				// 7. 산타는 상하좌우 인접한 4방향 중 한 곳으로만 움직일 수 있음. (O)
				map[santa[i][0]][santa[i][1]].remove(0);
				santa[i][0] += santa_move_row[moveIndex];
				santa[i][1] += santa_move_col[moveIndex];
				map[santa[i][0]][santa[i][1]].add(i);

				// 9. 산타와 루돌프가 같은 칸에 있게되면 충돌 발생(O)
				if (santa[i][0] == rudolph[0] && santa[i][1] == rudolph[1]) {
					santaMoveCrash(i, moveIndex);
				}
			}

		}
	}

	static void rudolphMoveCrash(int dirIndex) {

		int originSantaIndex = map[rudolph[0]][rudolph[1]].get(0);
		// 1. 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 C만큼 점수를 얻게 됨 (O)
		answer[originSantaIndex] += c;

		// 2. 이와 동시에, 루돌프가 이동해온 방향으로 C만큼 밀려남 (O)
		// 3. 밀려나는 것은 포물선 모양을 그리며 밀려나는 것이기 때문에, 도중에 충돌이 일어나지는 않고 정확히 원하는 위치에 도달 (O)
		map[rudolph[0]][rudolph[1]].remove(0);
		santa[originSantaIndex][0] += rudolph_move_row[dirIndex] * c;
		santa[originSantaIndex][1] += rudolph_move_col[dirIndex] * c;

		// 4. 만약 산타가 밀려난 위치가 게임판 밖이라면 산타는 탈락 (O)
		if (santa[originSantaIndex][0] <= 0 || santa[originSantaIndex][0] > n ||
			santa[originSantaIndex][1] <= 0 || santa[originSantaIndex][1] > n) {
			santa[originSantaIndex][0] = -1;
			santa[originSantaIndex][1] = -1;
			remainSanta--;
		} else {
			// 5. 밀려난 칸에 다른 산타가 있는 경우 상호작용 발생 (O)
			map[santa[originSantaIndex][0]][santa[originSantaIndex][1]].add(originSantaIndex);
			if (map[santa[originSantaIndex][0]][santa[originSantaIndex][1]].size() >= 2) {
				interaction(new int[] {santa[originSantaIndex][0], santa[originSantaIndex][1]}, originSantaIndex,
					dirIndex, true);
			}

			// 6. 산타는 루돌프와의 충돌 후 기절하게 됨 (O)
			stun(originSantaIndex);
		}
	}

	static void santaMoveCrash(int originSantaIndex, int dirIndex) {

		// 1. 산타가 움직여서 충돌이 일어난 경우, 해당 산타는 D만큼 점수를 얻게 됨 (O)
		answer[originSantaIndex] += d;

		// 2. 이와 동시에, 산타는 자신이 이동해온 반대 방향으로 D만큼 밀려나게 됨 (O)
		// 0: 상 1: 우 2: 하 3: 좌
		// 3. 밀려나는 것은 포물선 모양을 그리며 밀려나는 것이기 때문에, 도중에 충돌이 일어나지는 않고 정확히 원하는 위치에 도달 (O)
		dirIndex = (dirIndex + 2) % 4;
		map[rudolph[0]][rudolph[1]].remove(0);
		santa[originSantaIndex][0] += santa_move_row[dirIndex] * d;
		santa[originSantaIndex][1] += santa_move_col[dirIndex] * d;

		// 4. 만약 산타가 밀려난 위치가 게임판 밖이라면 산타는 탈락 (O)
		if (santa[originSantaIndex][0] <= 0 || santa[originSantaIndex][0] > n ||
			santa[originSantaIndex][1] <= 0 || santa[originSantaIndex][1] > n) {
			santa[originSantaIndex][0] = -1;
			santa[originSantaIndex][1] = -1;
			remainSanta--;
		} else {
			map[santa[originSantaIndex][0]][santa[originSantaIndex][1]].add(originSantaIndex);
			// 5. 밀려난 칸에 다른 산타가 있는 경우 상호작용 발생
			if (map[santa[originSantaIndex][0]][santa[originSantaIndex][1]].size() >= 2) {
				interaction(new int[] {santa[originSantaIndex][0], santa[originSantaIndex][1]}, originSantaIndex,
					dirIndex, false);
			}
			// 6. 산타는 루돌프와의 충돌 후 기절하게 됨
			stun(originSantaIndex);
		}
	}

	static void interaction(int[] point, int originSantaIndex, int dirIndex, boolean isRudolph) {

		// 1. 루돌프와의 충돌 후, 산타는 포물선의 궤적으로 이동하며 착지하게 되는 칸에서만 상호작용이 발생할 수 있음
		// 2. 산타는 충돌 후 착지하게 되는 칸에 다른 산타가 있다면, 그 산타는 1칸 해당 방향으로 밀려나게 됨 (O)

		for (int i = 0; i < map[point[0]][point[1]].size(); i++) {
			int crashedSantaIndex = map[point[0]][point[1]].get(i);
			if (crashedSantaIndex == originSantaIndex)
				continue;

			if (isRudolph) {
				santa[crashedSantaIndex][0] += rudolph_move_row[dirIndex];
				santa[crashedSantaIndex][1] += rudolph_move_col[dirIndex];
			} else {
				santa[crashedSantaIndex][0] += santa_move_row[dirIndex];
				santa[crashedSantaIndex][1] += santa_move_col[dirIndex];
			}
			map[point[0]][point[1]].remove(i--);

			// 4. 게임판 밖으로 밀려나오게 된 산타의 경우는 탈락 (O)
			if (santa[crashedSantaIndex][0] <= 0 || santa[crashedSantaIndex][0] > n ||
				santa[crashedSantaIndex][1] <= 0 || santa[crashedSantaIndex][1] > n) {
				santa[crashedSantaIndex][0] = -1;
				santa[crashedSantaIndex][1] = -1;
				remainSanta--;
			} else {
				map[santa[crashedSantaIndex][0]][santa[crashedSantaIndex][1]].add(crashedSantaIndex);
				// 3. 그 옆에 산타가 있다면 연쇄적으로 1칸씩 밀려나는 것 반복 (O)
				if (map[santa[crashedSantaIndex][0]][santa[crashedSantaIndex][1]].size() >= 2) {
					interaction(new int[] {santa[crashedSantaIndex][0], santa[crashedSantaIndex][1]},
						crashedSantaIndex,
						dirIndex, isRudolph);
				}
			}
		}
	}

	static void stun(int santaIndex) {
		// 1. 산타는 루돌프와의 충돌 후 기절하게 된다.
		// 2. 현재가 k번째 턴이었다면, (k + 1)번째 턴까지 기절하게 되어, (k + 2)번째 턴부터는 다시 정상상태가 됨
		santa[santaIndex][2] = turn + 2;
		// 3. 기절한 산타는 움직일 수 없음
		// 4. 루돌프는 기절한 산타를 돌진 대상으로 선택할 수 있음
	}

	static void givePoints() {
		for (int i = 1; i <= p; i++) {
			if (santa[i][0] != -1) {
				answer[i]++;
			}
		}
	}

	// 3.3. 두 칸 (r1,c1), (r2,c2) 사이의 거리는 (r1 - r2)^2 + (c1 - c2)^2 으로 계산됨
	static int calculateDist(int[] p1, int[] p2) {
		return (int)Math.pow((p1[0] - p2[0]), 2) + (int)Math.pow(p1[1] - p2[1], 2);
	}
}
