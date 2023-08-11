import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baek_5670 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = bf.readLine()) != null) {
            int n = Integer.parseInt(input);
            Node root = new Node();
            List<String> targets = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Node curNode = root;
                String dic = bf.readLine();
                targets.add(dic);

                for (int j = 0; j < dic.length(); j++) {
                    char c = dic.charAt(j);
                    if (!curNode.children.containsKey(c)) {
                        curNode.children.put(c, new Node());
                    }

                    curNode = curNode.children.get(c);
                    if (j == dic.length() - 1) {
                        curNode.endOfWord = true;
                    }
                }
            }

            int count = 0;

            for (String target : targets) {
                Node curNode = root.children.get(target.charAt(0));
                count++;

                for (int i = 1; i < target.length(); i++) {
                    char c = target.charAt(i);

                    if (curNode.endOfWord || curNode.children.size() >= 2) {
                        count++;
                    }

                    curNode = curNode.children.get(c);
                }
            }

            double answer = Math.round((double) count / n * 100) / 100.0;
            sb.append(String.format("%.2f", answer)).append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean endOfWord = false;
    }
}