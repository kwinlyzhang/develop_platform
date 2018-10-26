package com.alyer.rbtree;

import java.util.Objects;

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
