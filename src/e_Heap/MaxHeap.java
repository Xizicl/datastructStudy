package e_Heap;

import a_SequentialList.a_List.ArrayList;

public class MaxHeap<E extends Comparable<E>> {
    ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    //heapify
    public MaxHeap(E[] arr) {
        data = new ArrayList<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组(从0开始作为树的第一节点也就是根)表示中，一个索引所表示的元素的父节点的索引
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index 0没有父节点");
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    //上浮元素 把index为k的那个元素上浮到(交换到)最大堆中正确的上层节点
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("堆为空时不能查看最大元素");
        }
        return data.get(0);
    }


    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    //下沉元素
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // j+1是他的右兄弟的index
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);// j++ 也行
            }
            // j就是左右孩子中的最大值的节点的下标
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    // 取出来最大的元素，然后放入一个新元素
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);

        return ret;
    }

    // 传入一个数组 整理成堆的形状
    public MaxHeap<E> heapify(E[] arr) {
        return new MaxHeap<E>(arr);
    }

}
