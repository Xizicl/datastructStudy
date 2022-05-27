package z_algorithm;


import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static int[] getRandomArray() {
        /*
         * 获得用于测试的随机数组
         * */
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static int[] newBubbleSort(int[] array) {
        /*
         * 优化的冒泡排序
         * */
        int temp;
        // 外层循环：n个元素排序，则至多需要n-1趟循环
        for (int i = 0; i < array.length - 1; i++) {
            // 定义一个布尔类型的变量，标记数组是否已达到有序状态
            boolean flag = false;
            /*内层循环：每一趟循环都从数列的前两个元素开始进行比较，比较到无序数组的最后*/
            for (int j = 0; j < array.length - i - 1; j++) {
                // 如果前一个元素大于后一个元素，则交换两元素的值；
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //本趟发生了交换，表明该数组在本趟处于无序状态，需要继续比较；
                    flag = true;
                }
            }
            //根据标记量的值判断数组是否有序，如果有序，则退出；无序，则继续循环。
            if (!flag) break;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = getRandomArray();
        System.out.println(Arrays.toString(array));
        newBubbleSort(array);
        System.out.println(Arrays.toString(array));

    }
}
