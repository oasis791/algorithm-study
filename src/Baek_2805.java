import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] tree = new int[n];
		int maxHeight = 0;

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if (maxHeight < tree[i]) {
				maxHeight = tree[i];
			}
		}

		int start = 0;
		int end = maxHeight;

		while (start + 1 < end) {
			int mid = (start + end) / 2;

			if (check(mid, tree, m)) {
				start = mid;
			} else {
				end = mid;
			}
		}

		System.out.println(start);
	}

	static boolean check(int cut, int[] tree, int m) {
		long sum = 0;
		for (int i = 0; i < tree.length; i++) {
			if(tree[i] - cut > 0) {
				sum += tree[i] - cut;
			}
		}

		if(sum >= m) {
			return true;
		} else {
			return false;
		}
	}
}
