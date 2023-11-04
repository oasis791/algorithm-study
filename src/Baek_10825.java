import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Baek_10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[][] info = new String[n][4];

        for (int i = 0; i < n; i++) {
            info[i] = Arrays.copyOf(bf.readLine().split(" "), 4);
        }

        Arrays.sort(info, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[1].equals(o2[1])) {
                    if (o1[2].equals(o2[2])) {
                        if (o1[3].equals(o2[3])) {
                            return o1[0].compareTo(o2[0]);
                        } else {
                            return -1 * (Integer.parseInt(o1[3]) - Integer.parseInt(o2[3]));
                        }
                    } else {
                        return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                    }
                } else {
                    return -1 * (Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]));
                }
            }
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(info[i][0]).append("\n");
        }

        System.out.print(sb);
    }
}
