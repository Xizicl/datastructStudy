package h_UnionFind;

// 并查集
public interface UnionFind {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElement(int p, int q);
}
