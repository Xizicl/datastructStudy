package others_for_leetcode;


import java.util.TreeMap;

public class trie_677 {
    class MapSum {
        private class Node {
            public int val;
            public TreeMap<Character, Node> next;

            public Node(int val) {
                this.val = val;
                this.next = new TreeMap<>();
            }

            public Node() {
                this(0);
            }
        }

        private Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.val = val;

        }

        private int sum(Node node) {
            if (node.next.size() == 0) {
                return node.val;
            }
            int res = node.val;
            for (char c : node.next.keySet()) {
                res += sum(node.next.get(c));
            }
            return res;
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null) {
                    return 0;
                }
                cur = cur.next.get(c);
            }
            return sum(cur);
        }
    }
}
