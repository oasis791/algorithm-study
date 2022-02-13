import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_1713 {
    private static int N;
    private static int num;
    private static int[] students;
    private static Map<Integer, int[]> pic;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        num = Integer.parseInt(bf.readLine());
        students = new int[num];
        pic = new HashMap<>(N);

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < num; i++)
            students[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < students.length; i++) {
            if (pic.size() == N) {
                int min = Integer.MAX_VALUE;
                int targetIndex = 0;
                for (Map.Entry<Integer, int[]> entry : pic.entrySet()) {
                    if (entry.getValue()[0] < min) {
                        min = entry.getValue()[0];
                        targetIndex = entry.getKey();
                    } else if (entry.getValue()[0] == min) {
                        if (pic.get(targetIndex)[1] > entry.getValue()[1]) {
                            targetIndex = entry.getKey();
                        }
                    }
                }

                if (pic.containsKey(students[i])) {
                    pic.put(students[i], new int[]{pic.get(students[i])[0] + 1, pic.get(students[i])[1]});
                } else {
                    pic.remove(targetIndex);
                    pic.put(students[i], new int[]{1, i});
                }

            } else {
                if (pic.get(students[i]) != null) {
                    pic.put(students[i], new int[]{pic.get(students[i])[0] + 1, pic.get(students[i])[1]});
                } else {
                    pic.put(students[i], new int[]{1, i});
                }
            }
        }

        int[] keys = new int[N];
        int index = 0;
        for (int key : pic.keySet()) {
            keys[index++] = key;
        }
        Arrays.sort(keys);

        for (int i = 0; i < N; i++) {
            if (keys[i] != 0) {
                System.out.print(keys[i] + " ");
            }
        }
    }
}
