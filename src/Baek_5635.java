import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_5635 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        ArrayList<int[]> list = new ArrayList<>();
        String[] name = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            name[i] = st.nextToken();
            int d = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new int[]{y, m, d, i});
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    if (o1[1] == o2[1]) {
                        return o1[2] - o2[2];
                    } else {
                        return o1[1] - o2[1];
                    }
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        System.out.println(name[list.get(list.size() - 1)[3]]);
        System.out.println(name[list.get(0)[3]]);
    }
}
