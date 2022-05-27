package h_UnionFind;

public class QuickFind_UnionFind_1 implements UnionFind {

    private int[] id;

    public QuickFind_UnionFind_1(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    //O(1)
    private int find(int p) {
        if (p < 0 && p >= id.length) {
            throw new IllegalArgumentException("p越界");
        }
        return id[p];
    }


    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //O(N)
    @Override
    public void unionElement(int p, int q) {
        int pID = find(p);

        int qID = find(q);

        if (pID == qID) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qID) {
                id[i] = pID;
            }
        }
    }
}
