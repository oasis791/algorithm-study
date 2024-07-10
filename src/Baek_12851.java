import java.io.*;
import java.util.*;

public class Baek_12851 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		if (n == k) {
			System.out.println("0\n1");
			return;
		}

		int count = 0;
		int[] loc = new int[200001];
		Arrays.fill(loc, Integer.MAX_VALUE);
		loc[n] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(n);

		while(!queue.isEmpty()) {
			int cur = queue.poll();

			if (cur == k) continue;

			if(loc[k] >= loc[cur] + 1) {
				if(cur - 1 >= 0) {
					if(loc[cur - 1] > loc[cur] + 1) {
						if(cur - 1 == k) {
							count = 1;
						}
						loc[cur - 1] = loc[cur] + 1;
						queue.offer(cur - 1);
					} else if (loc[cur - 1] == loc[cur] + 1) {
						if(cur - 1 == k) {
							count++;
						}
						queue.offer(cur - 1);
					}
				} 

				if(cur + 1 <= 200000) {
					if(loc[cur + 1] > loc[cur] + 1) {
						if(cur + 1 == k) {
							count = 1;
						}
						loc[cur + 1] = loc[cur] + 1;
						queue.offer(cur + 1);
					} else if (loc[cur + 1] == loc[cur] + 1) {
						if(cur + 1 == k) {
							count++;
						}
						queue.offer(cur + 1);
					}

				}

				if(cur * 2 <= 200000) {
					if(loc[cur * 2] > loc[cur] + 1) {
						if(cur * 2 == k) {
							count = 1;
						}
						loc[cur * 2] = loc[cur] + 1;
						queue.offer(cur * 2);
					} else if (loc[cur * 2] == loc[cur] + 1) {
						if(cur * 2 == k) {
							count++;
						}
						queue.offer(cur * 2);
					}
				}

			}
		}

		System.out.println(loc[k]);
		System.out.println(count);
	}
}
