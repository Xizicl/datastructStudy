package others_for_leetcode;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class map_350 {
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i : nums1) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
            }
            ArrayList<Integer> result = new ArrayList<>();

            for (int i : nums2) {
                if (map.containsKey(i)){
                    if (map.get(i).compareTo(0)>0){
                        map.put(i,map.get(i)-1);
                        result.add(i);
                    }else {
                        map.remove(i);
                    }
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
