package others_for_leetcode;

public class arr_307 {
    class NumArray {
        private int[] sum_data;
        private int[] backup_data;

        public NumArray(int[] nums) {
            backup_data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                backup_data[i] = nums[i];
            }
            sum_data = new int[nums.length + 1];
            sum_data[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                sum_data[i + 1] = nums[i] + sum_data[i];
            }
        }


        public void update(int index, int val) {
            backup_data[index] = val;
            for (int i = index; i < backup_data.length; i++) {
                sum_data[i + 1] = backup_data[i] + sum_data[i];
            }
        }


        public int sumRange(int left, int right) {
            return sum_data[right + 1] - sum_data[left];
        }

    }
}
