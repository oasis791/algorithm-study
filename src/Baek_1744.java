import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baek_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(bf.readLine());
            if (temp > 0) {
                positive.add(temp);
            } else {
                negative.add(temp);
            }
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int sum = 0;

        for (int i = 0; i < positive.size(); i++) {
            if (i + 1 < positive.size() && positive.get(i) != 1 && positive.get(i + 1) != 1) {
                sum += positive.get(i) * positive.get(i + 1);
                i++;
            } else {
                sum += positive.get(i);
            }
        }

        for (int i = 0; i < negative.size(); i++) {
            if (i + 1 < negative.size()) {
                sum += negative.get(i) * negative.get(i + 1);
                i++;
            } else {
                sum += negative.get(i);
            }
        }

        System.out.println(sum);
    }
}

// 3 2 1 -1
// 6 1 -1 => 6

// 5 4 3 2 1 0
// 20 + 6 + 1 => 27

// -1

// 1 0 -1
// 1 + 0 => 1

// 4 3 -2 -2 -3
// 16

// -1 -2 -3 -4 -5
// 25

//5
//-537
//81
//-435
//257
//157
// -> 274025

//5
//-5
//-2
//-3
//0
//0
// -> 15


