package others_for_leetcode;

import java.util.TreeSet;

public class set_804 {
    class Solution {
        public int uniqueMorseRepresentations(String[] words) {

            String[] passwd = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

            TreeSet<String> set = new TreeSet<>();

            for (int i = 0; i < words.length; i++) {
                StringBuilder tempPassStr = new StringBuilder();
                String p = words[i];

                for (char c : p.toCharArray()) {
                    tempPassStr.append(passwd[c - 97]);
                }
                set.add(tempPassStr.toString());
            }

            return set.size();
        }
    }
}
