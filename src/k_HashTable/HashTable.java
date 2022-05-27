package k_HashTable;

import d_Map.Map;

import java.util.TreeMap;

public class HashTable<K, V> implements Map<K, V> {
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static final float loadingFactor = 0.7f;
    private static int capacityIndex = 0;
    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        this.hashtable = new TreeMap[M];
        this.size = 0;

        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }


    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }

    @Override
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
            // 装填因子：size/M 一般>0.7 时候需要 resize
            if (size >= loadingFactor * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    @Override
    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            if (size < loadingFactor * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    @Override
    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "不存在");
        }
        map.put(key, value);
    }

    @Override
    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    @Override
    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
