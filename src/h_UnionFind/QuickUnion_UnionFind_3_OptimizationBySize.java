package h_UnionFind;

public class QuickUnion_UnionFind_3_OptimizationBySize implements UnionFind {
    private int[] parent;
    private int[] size; //size[i]表示以i为根的集合中元素的个数

    public QuickUnion_UnionFind_3_OptimizationBySize(int size) {
        this.parent = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.size[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //查找元素p所对应的的集合编号，O(h)复杂度
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p越界");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //O(h)复杂度
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        // optimization：根据两个元素所在树的元素个数不同判断合并方向，将元素个数少的集合合并到元素个数多的集合上。
        if (this.size[pRoot] < this.size[qRoot]) {
            parent[pRoot] = qRoot;
            this.size[qRoot] += this.size[pRoot];
        } else { // this.size[qRoot]<= this.size[pRoot]
            parent[qRoot] = pRoot;
            this.size[pRoot] += this.size[qRoot];
        }

    }
}
