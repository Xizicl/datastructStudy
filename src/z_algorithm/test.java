package z_algorithm;

import f_SegmentTree.Merger;
import f_SegmentTree.SegmentTree;

public class test {
    class NumArray {
        private int[] sum_data;

        public NumArray(int[] nums) {
            sum_data = new int[nums.length + 1];
            sum_data[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                sum_data[i + 1] = nums[i] + sum_data[i];
            }
        }

        public int sumRange(int left, int right) {
            return sum_data[right+1] - sum_data[left];
        }
    }


}
