import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Map<String, Node> tree = new HashMap<>();

        /**
         * todo
         * 1. 트리 생성
         * 2. preorder
         * 3. inorder
         * 4. postoreder
         */
        //루트노드 먼저 생성
        Node rootNode = new Node("A", null, null, null);
        tree.put("A", rootNode);

        // 1. 트리 생성
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String currentNodeData = st.nextToken();
            String leftNodeData = st.nextToken();
            String rightNodeData = st.nextToken();

            //현재 노드
            Node currentNode = tree.get(currentNodeData);

            //왼쪽 노드 세팅
            if (!leftNodeData.equals(".")) {
                Node leftNode = new Node(leftNodeData, null, null, tree.get(currentNodeData));
                currentNode.setLeft(leftNode);
                tree.put(leftNodeData, leftNode);
            }
            //오른쪽 노드 세팅
            if (!rightNodeData.equals(".")) {
                Node rightNode = new Node(rightNodeData, null, null, tree.get(currentNodeData));
                currentNode.setRight(rightNode);
                tree.put(rightNodeData, rightNode);
            }
        }

        System.out.println(preorder(tree));
        System.out.println(inorder(tree));
        System.out.println(postorder(tree));
    }

    static class Node {
        private String data;
        private Node left;
        private Node right;
        private Node parentNode;

        public Node(String data, Node left, Node right, Node parentNode) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parentNode = parentNode;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }

    static String preorder(Map<String, Node> tree) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    static String inorder(Map<String, Node> tree) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    static String postorder(Map<String, Node> tree) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}
