package com.alyer.rbtree;

import java.util.Objects;

public class RBTree {

    int[] treeValue;
    RBNode root;
    boolean BLACK = false;
    boolean RED = true;

    public RBTree() {
        root = null;
    }

    public class RBNode {
        int key;
        boolean color;
        RBNode parent, left, right;

        public RBNode(int key, boolean color, RBNode left, RBNode right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (Objects.nonNull(left)) {
                left.parent = this;
            }
            if (Objects.nonNull(right)) {
                right.parent = this;
            }
            parent = null;
        }

        public RBNode() {
            if (Objects.nonNull(left)) {
                left = null;
            }
            if (Objects.nonNull(this.right)) {
                right = null;
            }
        }
    }

    // 祖父节点
    public RBNode grandParent(RBNode node) {
        if (Objects.isNull(node) || Objects.isNull(node.parent)) {
            return null;
        }
        return node.parent.parent;
    }

    // 父节点
    public RBNode parant(RBNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return node.parent;
    }

    // 兄弟节点
    public RBNode sibling(RBNode node) {
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
    public RBNode uncle(RBNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return sibling(node.parent);
    }

    // 节点颜色
    public boolean colorOf(RBNode node) {
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
    public void Replace(RBNode oldNode, RBNode newNode) {
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
    public void rotateLeft(RBNode node) {
        if (Objects.isNull(node)) {
            throw new NullPointerException("节点不能为空");
        }
        if (Objects.isNull(node.parent)) {
            throw new NullPointerException("节点的父节点不能为空");
        }
        // 自己的原左节点
        RBNode left = node.left;
        // 把父节点作为自己的现左节点
        node.left = node.parent;
        // 把自己的原左节点作为现左节点的右节点
        node.left.right = left;
        // 删除自己的父节点
        node.parent = null;
    }

    // 右旋  以自己为中心
    public void rotateRight(RBNode node) {
        if (Objects.isNull(node)) {
            throw new NullPointerException("节点不能为空");
        }
        if (Objects.isNull(node.parent)) {
            throw new NullPointerException("节点的父节点不能为空");
        }
        // 自己的原右节点
        RBNode right = node.right;
        // 把父节点作为自己的现右节点
        node.right = node.parent;
        // 把自己的原右节点作为现右节点的左节点
        node.right.left = right;
        // 删除自己的父节点
        node.parent = null;
    }

    // 如果是插入根节点，则节点为黑色
    public void insert1(RBNode node) {
        if (Objects.isNull(node.parent)) {
            node.color = BLACK;
            root = node;
        } else {
            insert2(node);
        }
    }

    // 如果要插入的节点是红色，其父节点是黑色，则不用调整
    public void insert2(RBNode node) {
        if (node.parent.color != BLACK) {

        }
    }
}
