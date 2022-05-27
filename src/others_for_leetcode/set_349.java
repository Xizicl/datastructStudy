package others_for_leetcode;

import java.util.ArrayList;
import java.util.TreeSet;

public class set_349 {
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            TreeSet<Integer> numSet1 = new TreeSet<>();

            for (int i : nums1) {
                numSet1.add(i);
            }
            ArrayList<Integer> result = new ArrayList<>();
            for (int i : nums2) {
                if (numSet1.contains(i)) {
                    result.add(i);
                    numSet1.remove(i);
                }
            }
            int[] array = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                array[i] = result.get(i);
            }

            return array;
        }
    }
}
