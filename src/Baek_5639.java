import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_5639 {
    static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        root = new Node(Integer.parseInt(input), null, null);

        while (true) {
            input = bf.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            Node newNode = new Node(Integer.parseInt(input), null, null);
            Node parentNode = order(root, Integer.parseInt(input));
            if (newNode.getData() > parentNode.getData()) {
                parentNode.setRight(newNode);
            } else {
                parentNode.setLeft(newNode);
            }
        }
        postOrder(root);
    }

    static Node order(Node tree, int target) {
        if (tree.getData() > target) {
            if (tree.getLeft() == null) {
                return tree;
            } else {
                return order(tree.getLeft(), target);
            }
        } else {
            if (tree.getRight() == null) {
                return tree;
            } else {
                return order(tree.getRight(), target);
            }
        }
    }

    static void postOrder(Node node) {
        if (node.getLeft() != null) {
            postOrder(node.getLeft());
        }

        if (node.getRight() != null) {
            postOrder(node.getRight());
        }
        System.out.println(node.getData());
    }

    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData() {
            return data;
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
    }
}
