package z_algorithm;

import java.util.Arrays;

public class binarySearch {
    public static int binarySearch(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;

            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                end = mid - 1;
            }
            if (array[mid] < target) {
                start = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = BubbleSort.getRandomArray();
        System.out.println(Arrays.toString(array));
        BubbleSort.newBubbleSort(array);
        System.out.println(Arrays.toString(array));
//        array = new int[]{0, 6, 9, 12, 40, 51, 76, 78, 92, 98};
//        System.out.println(Arrays.toString(array));
        System.out.println(binarySearch(array, 50));

    }

}
