package i_AVLTree;


import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else { // >0
            return getNode(node.right, key);
        }
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;

    }

    // 向以node为根的二分搜索树中插入元素 key value，并且返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { //等于
            node.value = value;
        }

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("不平衡：" + balanceFactor);
//        }

        //维护平衡
        //RR
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //LL
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }


    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node returnNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            returnNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            returnNode = node;
        } else { // key.compareTo(node.key) == 0

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                returnNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                returnNode = leftNode;
            } else {
                //待删除节点左右子树均不为空
                // predecessor
                Node successor = minimum(node.right);

                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;

                returnNode = successor;
            }
        }
        if (returnNode == null) {
            return null;
        }
        // 更新height
        returnNode.height = 1 + Math.max(getHeight(returnNode.left), getHeight(returnNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(returnNode);

        //维护平衡
        //RR
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) >= 0) {
            return rightRotate(returnNode);
        }
        //LL
        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) <= 0) {
            return leftRotate(returnNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(returnNode.left) < 0) {
            node.left = leftRotate(returnNode.left);
            return rightRotate(returnNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(returnNode.right) > 0) {
            node.right = rightRotate(returnNode.right);
            return leftRotate(returnNode);
        }

        return returnNode;

    }


    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key.toString() + "不存在");
        }
        node.value = newValue;
    }


    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


}
