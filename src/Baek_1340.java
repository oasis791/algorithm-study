import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_1340 {
    static HashMap<Integer, Integer> monthToDay = new HashMap<>();
    static HashMap<String, Integer> monthInfo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String month = st.nextToken();
        int day = Integer.parseInt(st.nextToken().split(",")[0]);
        int year = Integer.parseInt(st.nextToken());
        String[] split = st.nextToken().split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        init();

        int minSum = 0;
        int totalDay = 365;
        for (int i = 1; i < monthInfo.get(month); i++) {
            if (i == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    minSum += 29 * 60 * 24;
                    continue;
                }
            }
            minSum += monthToDay.get(i) * 60 * 24;
        }

        minSum += (day - 1) * 60 * 24;
        minSum += hour * 60 + min;

        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            totalDay = 366;
        }

        System.out.println((double) minSum / (totalDay * 60 * 24) * 100);
    }

    static void init() {
        monthToDay.put(1, 31);
        monthToDay.put(2, 28);
        monthToDay.put(3, 31);
        monthToDay.put(4, 30);
        monthToDay.put(5, 31);
        monthToDay.put(6, 30);
        monthToDay.put(7, 31);
        monthToDay.put(8, 31);
        monthToDay.put(9, 30);
        monthToDay.put(10, 31);
        monthToDay.put(11, 30);
        monthToDay.put(12, 31);

        monthInfo.put("January", 1);
        monthInfo.put("February", 2);
        monthInfo.put("March", 3);
        monthInfo.put("April", 4);
        monthInfo.put("May", 5);
        monthInfo.put("June", 6);
        monthInfo.put("July", 7);
        monthInfo.put("August", 8);
        monthInfo.put("September", 9);
        monthInfo.put("October", 10);
        monthInfo.put("November", 11);
        monthInfo.put("December", 12);
    }
}
