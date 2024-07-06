import java.io.*;
import java.util.*;

public class Baek_16928 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Map<Integer, Integer> stairs = new HashMap<>();
		Map<Integer, Integer> snakes = new HashMap<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			stairs.put(from, to);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			snakes.put(from, to);
		}


		int[] answer = new int[101];
		Arrays.fill(answer, Integer.MAX_VALUE);
		Queue<Integer> queue = new LinkedList<>();

		answer[1] = 0;
		queue.offer(1);

		while(!queue.isEmpty()) {
			int curBlock = queue.poll();

			if (stairs.containsKey(curBlock)) {
				int nextBlock = stairs.get(curBlock);
				answer[nextBlock] = answer[curBlock];
				queue.offer(nextBlock);
				continue;
			}

			if (snakes.containsKey(curBlock)) {
				int nextBlock = snakes.get(curBlock);
				answer[nextBlock] = answer[curBlock];
				queue.offer(nextBlock);
				continue;
			}

			for (int i = 1; i <= 6; i++) {
				if(curBlock + i <= 100 && answer[curBlock + i] > answer[curBlock] + 1) {
					answer[curBlock + i] = answer[curBlock] + 1;
					queue.offer(curBlock + i);

				}
			}
		}

		System.out.println(answer[100]);
	}
}
