import java.io.*;
import java.util.*;

public class Baek_16562 {
	static int[] fee, parents;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		fee = new int[n + 1];
		parents = new int[n + 1];

		st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= n; i++) {
			fee[i] = Integer.parseInt(st.nextToken());
			parents[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());

			union(f1, f2);
		}

		for (int i = 1; i <= n; i++) {
			find(i);
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			set.add(parents[i]);
		}

		int temp = 0;
		for (int i : set) {
			temp += fee[i];
		}

		if(temp <= k) {
			System.out.println(temp);
		} else {
			System.out.println("Oh no");
		}
	}

	static int find(int x) {
		if(x == parents[x]) 
			return x;
		return parents[x] = find(parents[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if(fee[x] > fee[y]) {
			parents[x] = y;
		} else {
			parents[y] = x;
		}
	
	}
}
