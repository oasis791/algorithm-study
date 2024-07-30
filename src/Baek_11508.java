import java.io.*;
import java.util.*;

public class Baek_11508 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bf.readLine());
		Integer[] costs = new Integer[n];

		for (int i = 0; i < n; i++) {
			costs[i] = Integer.parseInt(bf.readLine());
		}

		Arrays.sort(costs, Collections.reverseOrder());

		int sum = 0;
		for (int i = 0; i < n; i++) {
			if(i % 3 == 2) continue;

			sum += costs[i];
		}

		System.out.println(sum);
    }
}
