package h_UnionFind;

public class QuickUnion_UnionFind_6_OptimizationByPathCompressionOptimization implements UnionFind {
    private int[] parent;
    private int[] rank; //rank[i]表示以i为根的集合所表述树的深度的相对排名

    public QuickUnion_UnionFind_6_OptimizationByPathCompressionOptimization(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
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
        if (p != parent[p]) {
            parent[p] = find(parent[p]);//路径压缩优化
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
        // optimization：根据两个元素所在树的rank判断合并方向，将rank低的集合合并到rank高的集合上。
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[qRoot] == rank[pRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

    }
}
