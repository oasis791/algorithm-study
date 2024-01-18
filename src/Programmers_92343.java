import java.util.HashMap;

public class Programmers_92343 {
    static class Solution {
        static int answer = 0;
        public int solution(int[] info, int[][] edges) {
            HashMap<Integer, Node> tree = new HashMap<>();
            tree.put(0, new Node(0));

            for (int i = 0; i < edges.length; i++) {
                int parentNum = edges[i][0];
                int childNum = edges[i][1];

                Node parentNode;
                if(tree.containsKey(parentNum)) {
                    parentNode =  tree.get(parentNum);
                } else {
                    parentNode = new Node(parentNum);
                    tree.put(parentNum, parentNode);
                }

                Node childNode;
                if(tree.containsKey(childNum)) {
                    childNode = tree.get(childNum);
                } else {
                    childNode = new Node(childNum);
                    tree.put(childNum, childNode);
                }

                if(parentNode.left == null) {
                    parentNode.left = childNode;
                } else {
                    parentNode.right = childNode;
                }
                childNode.parent = parentNode;
            }

            boolean[] visited = new boolean[info.length];
            visited[0] = true;
            findSheep(0, 1, 0, info, tree, visited);

            return answer;
        }

        static void findSheep (int curNodeNum, int sheep, int wolf, int[] info, HashMap<Integer, Node> tree, boolean[] visited) {
            if(wolf >= sheep) {
                return;
            }

            // parent 노드가 false면 조회불가
            answer = Math.max(sheep, answer);

            Node curNode = tree.get(curNodeNum);
            for(int i = 1; i < info.length; i++) {
                if(!visited[i] && visited[tree.get(i).parent.nodeNum]) {
                    visited[i] = true;
                    if(info[i] == 0) {
                        findSheep(i, sheep + 1, wolf, info, tree, visited);
                    } else {
                        findSheep(i, sheep, wolf + 1, info, tree, visited);
                    }
                    visited[i] = false;
                }
            }
        }

        static class Node {
            int nodeNum;
            Node parent;
            Node left;
            Node right;

            public Node (int nodeNum) {
                this.nodeNum = nodeNum;
            }
        }
    }
}
