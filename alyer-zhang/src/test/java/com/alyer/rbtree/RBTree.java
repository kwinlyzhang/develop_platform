package com.alyer.rbtree;

import java.util.Objects;

public class RBTree {
    int iCount;
    int[] treeValue;
    RBNode root;
    boolean BLACK = false;
    boolean RED = true;

    public RBTree() {
        root = null;
    }

    // 祖父节点
    private RBNode grandParent(RBNode node) {
        if (Objects.isNull(node) || Objects.isNull(node.parent)) {
            return null;
        }
        return node.parent.parent;
    }

    // 父节点
    private RBNode parant(RBNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return node.parent;
    }

    // 兄弟节点
    private RBNode sibling(RBNode node) {
        if (Objects.isNull(node) || Objects.isNull(node.parent)) {
            return null;
        }
        if (Objects.isNull(node.parent.left)) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }
    }

    // 叔叔节点
    private RBNode uncle(RBNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return sibling(node.parent);
    }

    // 节点颜色
    private boolean colorOf(RBNode node) {
        return Objects.isNull(node) ? BLACK : node.color;
    }

    // 查找
    public RBNode find(int key) {
        RBNode node = root;
        while (Objects.nonNull(node)) {
            if (key == node.key) {
                return node;
            }
            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    // 替换节点
    private void Replace(RBNode oldNode, RBNode newNode) {
        if (oldNode.parent == null) {
            root = newNode;
        } else {
            if (oldNode == oldNode.parent.left) {
                oldNode.parent.left = newNode;
            } else {
                oldNode.parent.right = newNode;
            }
        }
        if (newNode != null) {
            newNode.parent = oldNode.parent;
        }
    }

    // 左旋  以自己为中心
    private void rotateLeft(RBNode node) {
        if (Objects.isNull(node)) {
            throw new NullPointerException("节点不能为空");
        }
        if (Objects.isNull(node.parent)) {
            throw new NullPointerException("节点的父节点不能为空");
        }
        // 自己的原左节点
        RBNode left = node.left;
        // 自己的祖父节点
        RBNode grandParent = node.parent.parent;
        // 把父节点作为自己的现左节点
        node.left = node.parent;
        node.left.parent = node;
        // 把自己的原左节点作为现左节点的右节点
        node.left.right = left;
        // 自己的祖父节点作为自己的父节点
        node.parent = grandParent;
        if (Objects.nonNull(grandParent)) {
            grandParent.left = node;
        }
    }

    // 右旋  以自己为中心
    private void rotateRight(RBNode node) {
        if (Objects.isNull(node)) {
            throw new NullPointerException("节点不能为空");
        }
        if (Objects.isNull(node.parent)) {
            throw new NullPointerException("节点的父节点不能为空");
        }
        // 自己的原右节点
        RBNode right = node.right;
        // 自己的祖父节点
        RBNode grandParent = node.parent.parent;
        // 把父节点作为自己的现右节点
        node.right = node.parent;
        node.right.parent = node;
        // 把自己的原右节点作为现右节点的左节点
        node.right.left = right;
        // 自己的祖父节点作为自己的父节点
        node.parent = grandParent;
        if (Objects.nonNull(grandParent)) {
            grandParent.right = node;
        }
    }

    // 如果是插入根节点，则节点为黑色
    private void insert1(RBNode node) {
        if (Objects.isNull(node.parent)) {
            node.color = BLACK;
        } else {
            insert2(node);
        }
    }

    // 如果父节点是黑色，则不用调整
    private void insert2(RBNode node) {
        if (node.parent.color != BLACK) {
            insert3(node);
        }
    }

    //
    private void insert3(RBNode node) {
        RBNode uncle = uncle(node);
        RBNode grandParent = grandParent(node);
        if (colorOf(uncle) == BLACK) {// 叔叔节点未黑色
            insert4(node);
        } else {// 叔叔节点为红色
            node.parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED;
            insert1(grandParent);
        }
    }

    //
    private void insert4(RBNode node) {
        RBNode grandParent = grandParent(node);
        if (node == node.parent.right && node.parent == grandParent.left) {
            rotateLeft(node);
            rotateRight(node);
        } else if (node == node.parent.left && node.parent == grandParent.left) {
            rotateRight(node);
        } else if (node == node.parent.right && node.parent == grandParent.right) {
            rotateLeft(node);
        } else if (node == node.parent.left && node.parent == grandParent.right) {
            rotateRight(node);
            rotateLeft(node);
        }
    }

    // 插入一个红色节点
    public boolean insert(int newKey) {
        RBNode insNode = new RBNode(newKey, RED, null, null);
        RBNode node;
        if (root == null) {
            root = insNode;
        } else {
            node = root;
            while (true) {
                if (newKey == node.key) {
                    return false;
                }
                if (newKey < node.key) {
                    if (node.left == null) {
                        node.left = insNode;
                        break;
                    } else
                        node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = insNode;
                        break;
                    } else
                        node = node.right;
                }
            }
            insNode.parent = node;
        }
        insert1(insNode);
        return true;
    }

    private void preOrder(RBNode node, int width, int height) {
        if (Objects.nonNull(node)) {
            treeValue[iCount] = node.key;
            iCount++;
            preOrder(node.left, width, height);
            preOrder(node.right, width, height);
        }
    }

    public int[] preOrder(int width, int height) {
        treeValue = new int[width * height];
        iCount = 0;
        preOrder(root, width, height);
        return treeValue;
    }
}
