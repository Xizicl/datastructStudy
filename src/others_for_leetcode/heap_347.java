package others_for_leetcode;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class heap_347 {
    class Solution {
        private class Num_and_Frequency_Group implements Comparable<Num_and_Frequency_Group> {
            public Num_and_Frequency_Group(int num, int frequency) {
                this.frequency = frequency;
                this.num = num;
            }

            int frequency, num;

            @Override
            public int compareTo(Num_and_Frequency_Group in) {
                if (this.frequency > in.frequency) {
                    return 1;
                } else if (this.frequency < in.frequency) {
                    return -1;
                } else { //(this.frequency == in.frequency)
                    return 0;
                }
            }
        }

        public int[] topKFrequent(int[] nums, int k) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i : nums) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
            }
            PriorityQueue<Num_and_Frequency_Group> queue = new PriorityQueue<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (queue.size() < k) {
                    queue.add(new Num_and_Frequency_Group(entry.getKey(), entry.getValue()));
                } else if (entry.getValue() > queue.peek().frequency) {
                    queue.poll();
                    queue.add(new Num_and_Frequency_Group(entry.getKey(), entry.getValue()));
                }

            }

            int[] result = new int[k];
            while (!queue.isEmpty()) {
                result[result.length - (k--)] = queue.poll().num;
            }
            return result;

        }
    }
}
