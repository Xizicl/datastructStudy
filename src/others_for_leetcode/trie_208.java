package others_for_leetcode;

import java.util.TreeMap;

public class trie_208 {
    class Trie {
        private class Node {
            public boolean isEnd;
            public TreeMap<Character, Node> next;

            public Node(boolean isEnd) {
                this.isEnd = isEnd;
                this.next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            if (!cur.isEnd) {
                cur.isEnd = true;
            }

        }

        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    return false;
                }
                cur = cur.next.get(c);
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.next.get(c) == null) {
                    return false;
                }
                cur = cur.next.get(c);
            }
            return true;
        }
    }
}
