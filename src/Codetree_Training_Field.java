import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Codetree_Training_Field {
	static int n, m, k;
	// 총은 ArrayList
	static ArrayList<Integer>[][] gunMap;

	// 플레이어 배열 맨 마지막에 갖고있는 총 공격력
	static int[][] playerInfo;
	static int[][] playerMap;
	static int[] answer;
	static int[] move_row = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] move_col = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 1. n * n 크기의 격자에서 진행된다. (O)
		playerInfo = new int[m + 1][5]; // 0: row, 1: col, 2: 플레이어 방향, 3: 초기 능력치, 4: 갖고있는 총
		playerMap = new int[n + 1][n + 1];
		answer = new int[m + 1];
		gunMap = new ArrayList[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				gunMap[i][j] = new ArrayList<>();
			}
		}

		// 2. 각각의 격자에는 무기들이 있을 수 있다. (O)

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= n; j++) {
				int gunInfo = Integer.parseInt(st.nextToken());
				if (gunInfo > 0) {
					gunMap[i][j].add(gunInfo);
				}
			}
		}

		// 3. 초기에는 무기들이 없는 빈 격자에 플레이어들이 위치하며, 각 플레이어는 초기 능력치를 가진다. (O)
		// 3.1. 각 플레이어의 초기 능력치는 모두 다르다. (O)
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(bf.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			playerInfo[i][0] = row;
			playerInfo[i][1] = col;
			playerInfo[i][2] = dir;
			playerInfo[i][3] = power;
			playerInfo[i][4] = 0;

			playerMap[row][col] = i;
		}

		for (int round = 0; round < k; round++) {
			// 4. 하나의 라운드는 다음의 과정에 걸쳐 진행된다. (O)
			movePlayers();
		}

		// 7. k 라운드 동안 게임을 진행하면서 각 플레이어들이 획득한 포인트를 공백을 사이에 두고 출력 (O)
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= m; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}

	static void movePlayers() {

		for (int i = 1; i <= m; i++) {

			// 4.1. 첫번째 플레이어부터 순차적으로 본인이 향하고 있는 방향대로 한 칸만큼 이동한다. (O)
			int nextRow = playerInfo[i][0] + move_row[playerInfo[i][2]];
			int nextCol = playerInfo[i][1] + move_col[playerInfo[i][2]];

			// 4.1.1. 만약 해당 방향으로 나갈 때 격자를 벗어나는 경우, 정 반대 방향으로 방향을 바꾸어 1만큼 이동 (O)
			if (nextRow <= 0 || nextRow > n || nextCol <= 0 || nextCol > n) {
				playerInfo[i][2] = (playerInfo[i][2] + 2) % 4;
				nextRow = playerInfo[i][0] + move_row[playerInfo[i][2]];
				nextCol = playerInfo[i][1] + move_col[playerInfo[i][2]];
			}

			playerMap[playerInfo[i][0]][playerInfo[i][1]] = 0;
			playerInfo[i][0] = nextRow;
			playerInfo[i][1] = nextCol;

			// 5. 만약 이동한 방향에 플레이어가 없다면, (O)
			if (playerMap[playerInfo[i][0]][playerInfo[i][1]] == 0) {
				playerMap[playerInfo[i][0]][playerInfo[i][1]] = i;
				// 5.1. 해당 칸에 총이 있는지 확인. (O)
				if (gunMap[playerInfo[i][0]][playerInfo[i][1]].size() > 0) {
					selectGun(playerInfo[i]);
				}
			} else {
				// 6. 만약 이동한 방향에 플레이어가 있다면 두 플레이어가 싸우게 됨 (O)
				playerFight(i, playerMap[playerInfo[i][0]][playerInfo[i][1]]);
			}

		}
	}

	static void selectGun(int[] playerInfo) {
		int curGunPower = playerInfo[4];

		// 5.1.1. 총이 있는 경우, 해당 플레이어는 총을 획득 (O)
		if (curGunPower == 0 && gunMap[playerInfo[0]][playerInfo[1]].size() == 1) {
			playerInfo[4] = gunMap[playerInfo[0]][playerInfo[1]].get(0);
			gunMap[playerInfo[0]][playerInfo[1]].remove(0);
			return;
		}

		// 5.1.2. 플레이어가 이미 총을 가지고 있는 경우에는, 놓여있는 총들과 플레이어가 가지고 있는 총 가운데 공격력이 더 쎈 총을 획득하고, 나머지는 해당 격자에 둠(O)
		for (int i = 0; i < gunMap[playerInfo[0]][playerInfo[1]].size(); i++) {
			if (gunMap[playerInfo[0]][playerInfo[1]].get(i) > curGunPower) {
				int temp = gunMap[playerInfo[0]][playerInfo[1]].get(i);
				gunMap[playerInfo[0]][playerInfo[1]].set(i, curGunPower);
				curGunPower = temp;
			}
		}
		playerInfo[4] = curGunPower;
	}

	static void playerFight(int p1Index, int p2Index) {

		int winIndex = 0;
		int loseIndex = 0;

		// 6.1. 해당 플레이어의 초기 능력치 + 갖고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이김 (O)
		int p1Power = playerInfo[p1Index][3] + playerInfo[p1Index][4];
		int p2Power = playerInfo[p2Index][3] + playerInfo[p2Index][4];

		if (p1Power > p2Power) {
			winIndex = p1Index;
			loseIndex = p2Index;
		} else if (p2Power > p1Power) {
			winIndex = p2Index;
			loseIndex = p1Index;
		} else {
			// 6.2. 만약 이 수치가 같은 경우에는, 플레이어의 초기 능력치가 높은 플레이어가 승리 (O)
			if (playerInfo[p1Index][3] > playerInfo[p2Index][3]) {
				winIndex = p1Index;
				loseIndex = p2Index;
			} else {
				winIndex = p2Index;
				loseIndex = p1Index;
			}
		}

		// 6.3. 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이만큼을 포인트로 획득 (O)
		answer[winIndex] += Math.abs(p1Power - p2Power);
		// 이긴 사람을 playerMap에 넣어주고, 원래 있는 애는 이동시켜줘야함 (O)
		playerMap[playerInfo[winIndex][0]][playerInfo[winIndex][1]] = winIndex;

		// 6.4. 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓는다. (O)
		if (playerInfo[loseIndex][4] != 0) {
			gunMap[playerInfo[loseIndex][0]][playerInfo[loseIndex][1]].add(playerInfo[loseIndex][4]);
			playerInfo[loseIndex][4] = 0;
		}

		// 6.4.1. 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동한다. (O)
		losePlayerMove(loseIndex);

		// 6.5. 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과, 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득하고, 나머지는 내려놓는다.(O)
		selectGun(playerInfo[winIndex]);
	}

	static void losePlayerMove(int playerIndex) {

		// 6.4.1. 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동한다. (O)
		int dir = playerInfo[playerIndex][2];
		int nextRow = playerInfo[playerIndex][0] + move_row[dir];
		int nextCol = playerInfo[playerIndex][1] + move_col[dir];

		// 6.4.1.1. 만약 이동하려는 칸에 다른 플레이어가 있거나, 격자 범위 밖인 경우에는, 오른쪽으로 90도 회전하여 빈칸이 보이는 순간 이동 (O)
		while (nextRow <= 0 || nextRow > n || nextCol <= 0 || nextCol > n || (playerMap[nextRow][nextCol] > 0)) {
			dir = (dir + 1) % 4;
			nextRow = playerInfo[playerIndex][0] + move_row[dir];
			nextCol = playerInfo[playerIndex][1] + move_col[dir];
		}

		playerInfo[playerIndex][0] = nextRow;
		playerInfo[playerIndex][1] = nextCol;
		playerInfo[playerIndex][2] = dir;
		playerMap[nextRow][nextCol] = playerIndex;

		// 6.4.2. 만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을 획득하고, 나머지 총들은 해당 격자에 내려 놓는다.
		if (gunMap[nextRow][nextCol].size() > 0) {
			selectGun(playerInfo[playerIndex]);
		}
	}
}
