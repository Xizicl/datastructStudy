package others_for_leetcode;

public class SegmentTree_307 {
    class NumArray {
        private int[] tree;
        private int[] data;

        public NumArray(int[] nums) {
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            tree = new int[4 * nums.length];
            buildSegmentTree(0, 0, data.length - 1);
        }

        public int sumRange(int left, int right) {
            return query(0, 0, data.length - 1, left, right);
        }

        private int query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2; // int mid = (l + r) / 2;

            if (queryL >= mid + 1) {
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(leftTreeIndex, l, mid, queryL, queryR);
            }
            int leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            int rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return leftResult + rightResult;
        }

        private int leftChild(int index) {
            return 2 * index + 1;
        }

        private int rightChild(int index) {
            return 2 * index + 2;
        }

        private void buildSegmentTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2; // int mid = (l + r) / 2;

            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

            tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];

        }

        public void update(int index, int val) {
            data[index] = val;
            update(0, 0, data.length - 1, index, val);
        }

        // 在以treeIndex为根的线段树中更新index的值为e
        private void update(int treeIndex, int l, int r, int index, int e) {
            if (l == r) {
                tree[treeIndex] = e;
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2; // int mid = (l + r) / 2;
            if (index >= mid + 1) {
                update(rightTreeIndex, mid + 1, r, index, e);
            } else { // index <= mid
                update(leftTreeIndex, l, mid, index, e);
            }
            tree[treeIndex] = tree[leftTreeIndex] + tree[rightTreeIndex];
        }
    }
}
