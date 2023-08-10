import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek_14725 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node();
        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int k = Integer.parseInt(st.nextToken());

            Node curNode = root;

            for (int j = 0; j < k; j++) {
                String target = st.nextToken();

                if (!curNode.children.containsKey(target)) {
                    curNode.children.put(target, new Node());
                }

                curNode = curNode.children.get(target);
            }
        }

        print(root, 0);
        System.out.print(sb);
    }

    static void print(Node curNode, int depth) {
        Object[] keys = curNode.children.keySet().toArray();
        Arrays.sort(keys);

        for (Object key : keys) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            print(curNode.children.get(key), depth + 1);
        }
    }
    static class Node {
        public String name;
        public HashMap<String, Node> children = new HashMap<>();
    }
}
