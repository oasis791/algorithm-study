import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Codetree_Royal_Knight_Duel {
	static int l, n, q;
	static int[][] map;
	static int[][] knights;
	static ArrayList<int[]>[] knightsShields;
	static int[][] knightsMap;
	static int[] move_row = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] move_col = new int[] {0, 1, 0, -1};
	static Queue<Integer> moveKnightsIndex;
	static Queue<Integer> damageKnights;
	static int firstKnightIndex;
	static int[] answer;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		map = new int[l + 1][l + 1];
		knightsMap = new int[l + 1][l + 1];
		knights = new int[n + 1][5];
		knightsShields = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			knightsShields[i] = new ArrayList<>();
		}
		answer = new int[n + 1];

		// 1. 왕실의 기사들은 l x l 크기의 체스판 위에서 대결을 준비하고 있다. (O)
		// 2. 왼쪽 상단은 (1, 1)로 시작, 각 칸은 빈칸(0), 함정 (1), 벽(2), 체스판 밖도 벽 (O)
		for (int i = 1; i <= l; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= l; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 3. 왕실의 기사들은 자신의 마력으로 상대방을 밀쳐낼 수 있다.
		// 4. 각 기사의 초기 위치는 (r,c)
		// 6. 각 기사의 체력은 k로 주어짐
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			knights[i][0] = r;
			knights[i][1] = c;
			knights[i][2] = h;
			knights[i][3] = w;
			knights[i][4] = k;

			calculateKnightsShield(i);
			// 5. 기사는 방패를 들고 있기 때문에 (r,c)를 좌측 상단으로 하며, h(높이) x w(너비) 크기의 직사각형 형태를 띄고있다.
			for (int[] point : knightsShields[i]) {
				knightsMap[point[0]][point[1]] = i;
			}
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(bf.readLine());

			int orderKnightIndex = Integer.parseInt(st.nextToken());
			int dirIndex = Integer.parseInt(st.nextToken());

			// 7. 기사 이동 (O)
			firstKnightIndex = orderKnightIndex;
			moveKnightsIndex = new LinkedList<>();
			damageKnights = new LinkedList<>();
			visited = new boolean[n + 1];

			if (knightMove(orderKnightIndex, dirIndex)) {
				changeMap(dirIndex);
				// 18. 기사들은 모두 밀린 이후에 대미지를 입게 됨
				// 13. 대결 대미지 (O)
				if (!damageKnights.isEmpty()) {
					fightDamage(firstKnightIndex);
				}
			}

		}

		// 20. Q번에 걸쳐 왕의 명령이 주어졌을 때, Q번의 대결이 모두 끝난후 생존한 기사들이 총 받은 대미지의 합 출력(O)
		int count = 0;
		for (int i = 1; i <= n; i++) {
			count += answer[i];
		}
		System.out.println(count);
	}

	static boolean knightMove(int knightIndex, int dirIndex) {

		// 12. 체스판에서 사라진 기사에게 명령을 내리면 아무런 반응이 없게됨 (O)
		if (knights[knightIndex][4] <= 0) {
			return false;
		}

		visited[knightIndex] = true;
		// 8. 왕에게 명령을 받은 기사는 상하좌우 중 한칸 이동할 수 있음. (O)
		for (int[] point : knightsShields[knightIndex]) {
			int nextRow = point[0] + move_row[dirIndex];
			int nextCol = point[1] + move_col[dirIndex];

			// 11. 만약 기사가 이동하려는 방향의 끝에 벽이 있다면, 모든 기사는 이동할 수 없게 됨 (O)
			if (nextRow <= 0 || nextRow > l || nextCol <= 0 || nextCol > l) {
				return false;
			} else {
				// 11. 만약 기사가 이동하려는 방향의 끝에 벽이 있다면, 모든 기사는 이동할 수 없게 됨 (O)
				if (map[nextRow][nextCol] == 2) {
					return false;
				}
				// 9. 이 때, 이동하려는 위치에 다른 기사가 있다면, 그 기사도 연쇄적으로 한칸 밀려남. (O)
				// 10. 그 옆에 또 기사가 있다면 연쇄적으로 한 칸 밀림. (O)
				if (knightsMap[nextRow][nextCol] > 0 && knightsMap[nextRow][nextCol] != knightIndex
					&& !visited[knightsMap[nextRow][nextCol]]) {
					if (!knightMove(knightsMap[nextRow][nextCol], dirIndex)) {
						return false;
					}
				}
			}
		}

		moveKnightsIndex.offer(knightIndex);
		damageKnights.offer(knightIndex);
		return true;
	}

	// 연쇄적으로 한칸씩 미는 메소드
	static void changeMap(int dirIndex) {

		while (!moveKnightsIndex.isEmpty()) {
			int knightIndex = moveKnightsIndex.poll();

			// 원래꺼 일단 다 0으로 초기화
			for (int[] point : knightsShields[knightIndex]) {
				if (knightsMap[point[0]][point[1]] == knightIndex)
					knightsMap[point[0]][point[1]] = 0;
			}

			// 이제 한 칸 이동한 칸에 인덱스 부여
			ArrayList<int[]> temp = new ArrayList<>();
			for (int[] point : knightsShields[knightIndex]) {
				int nextRow = point[0] + move_row[dirIndex];
				int nextCol = point[1] + move_col[dirIndex];

				knightsMap[nextRow][nextCol] = knightIndex;
				temp.add(new int[] {nextRow, nextCol});
			}

			// knight 정보 최신화
			knights[knightIndex][0] += move_row[dirIndex];
			knights[knightIndex][1] += move_col[dirIndex];

			// knightShield 정보 최신화
			knightsShields[knightIndex] = new ArrayList<>();
			for (int[] point : temp) {
				knightsShields[knightIndex].add(point);
			}
		}
	}

	static void fightDamage(int firstKnightIndex) {

		// 14. 명령을 받은 기사가 다른 기사를 밀치게 되면, 밀려난 기사들은 피해를 입게 됨 (O)
		while (!damageKnights.isEmpty()) {
			int curKnightIndex = damageKnights.poll();

			// 17. 명령을 받은 기사는 피해를 입지 않음 (O)
			if (curKnightIndex == firstKnightIndex)
				continue;

			// 15. 이때, 각 기사들은 해당 기사가 이동한 곳에서 w x h 직사각형 내에 놓여있는 함정의 수만큼 피해를 입게됨(O)
			int count = 0;
			for (int[] point : knightsShields[curKnightIndex]) {
				if (map[point[0]][point[1]] == 1)
					count++;
			}
			knights[curKnightIndex][4] -= count;
			// 16. 각 기사마다 피해를 받은 만큼 체력이 깎이게 되며, 현재 체력 이상의 데미지를 받을경우 체스판에서 사라짐
			if (knights[curKnightIndex][4] <= 0) {
				answer[curKnightIndex] = 0;
				for (int[] point : knightsShields[curKnightIndex]) {
					knightsMap[point[0]][point[1]] = 0;
				}
			} else {
				answer[curKnightIndex] += count;
			}
			// 19. 밀렸더라도 밀쳐진 위치에 함정이 없다면 그 기사는 피해를 입지 않게 됨
		}
	}

	static void calculateKnightsShield(int knightIndex) {
		int[] knight = knights[knightIndex];

		for (int i = knight[0]; i < knight[0] + knight[2]; i++) {
			for (int j = knight[1]; j < knight[1] + knight[3]; j++) {
				if (i >= 1 && i <= l && j >= 1 && j <= l) {
					knightsShields[knightIndex].add(new int[] {i, j});
				}
			}
		}
	}
}
