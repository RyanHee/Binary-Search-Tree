package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;
    private int diameter;
    public BinarySearchTree() {
        root = null;
    }

    public void add(T val) {
        java.util.Objects.requireNonNull(val, "value");
        BinaryNode<T> b = new BinaryNode<>(val);
        if (root == null) {
            root = b;
        }
        else {
            add(b, root);
        }
    }

    private void add(BinaryNode<T> addNode, BinaryNode<T> b) {
        if (addNode.value().compareTo(b.value())<0) {
            if (b.left() == null) {
                b.setLeft(addNode);
            }
            else {
                add(addNode, b.left());
            }
        }
        else if (addNode.value().compareTo(b.value())>0) {
            if (b.right() == null) {
                b.setRight(addNode);
            }
            else {
                add(addNode, b.right());
            }
        }
        else {
            return;
        }
    }

    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(BinaryNode<T> node) {
        if (node == null) {
            return true;
        }
        if (node.right() != null && node.left() != null) {
            return isFull(node.right()) && isFull(node.left());
        }
        else if (node.right() == null && node.left() == null) {
            return true;
        }
        return false;
    }
    public boolean contains(T value) {
        java.util.Objects.requireNonNull(value, "value");
        BinaryNode<T> node = root;
        while (node != null) {
            int comparison = value.compareTo(node.value());
            if (comparison == 0) {
                return true;
            }
            node = comparison < 0 ? node.left() : node.right();
        }
        return false;
    }

    public int getWidthAtLevel(int level) {
        if (level < 0) {
            return 0;
        }
        if (root == null) {
            return 0;
        }

        ArrayList<BinaryNode<T>> list = new ArrayList<>();
        list.add(root);
        for (int i = 0; i<level && !list.isEmpty(); i++) {
            ArrayList<BinaryNode<T>> nList = new ArrayList<>();
            for (BinaryNode<T> node:list) {
                if (node.left() != null) {
                    nList.add(node.left());
                }
                if (node.right() != null) {
                    nList.add(node.right());
                }
            }
            list = nList;
        }

        int width = list.size();
        return width;
    }

    public int getNumLevels() {
        return getHeight()+1;
    }
    /** Height in edges; an empty tree has height -1. */
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryNode<T> node) {
        return node == null ? -1 : 1 + Math.max(getHeight(node.left()), getHeight(node.right()));
    }

    public int getDiameter() {
        diameter = 0;
        diameterHeight(root);
        return diameter;
    }

    private int diameterHeight(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        int left = diameterHeight(node.left());
        int right = diameterHeight(node.right());
        diameter = Math.max(diameter, left + right + 1);
        return 1 + Math.max(left, right);
    }

    public int getNumNodes() {
        if (root == null) {
            return 0;
        }
        return getNumNodes(root);
    }

    private int getNumNodes(BinaryNode<T> node) {
        return node == null ? 0 : 1 + getNumNodes(node.left()) + getNumNodes(node.right());
    }

    public int getNumLeaves() {
        if (root == null) {
            return 0;
        }
        return getNumLeaves(root);
    }

    private int getNumLeaves(BinaryNode<T> node) {
        if (node.right() == null && node.left() == null) {
            return 1;
        }
        int ans = 0;
        if (node.left() != null) {
            ans += getNumLeaves(node.left());
        }
        if (node.right() != null) {
            ans += getNumLeaves(node.right());
        }
        return ans;
    }

    public int getWidth() {
        if (root == null) {
            return 0;
        }
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            maxWidth = Math.max(length, maxWidth);
            for (int i = 0; i<length; i++) {
                BinaryNode<T> node = queue.remove();
                if (node.right() != null) {
                    queue.add(node.right());
                }
                if (node.left() != null) {
                    queue.add(node.left());
                }
            }
        }
        return maxWidth;
    }

    public T getSmallest() {
        if (root == null) {
            return null;
        }
        else {
            BinaryNode<T> node = root;
            while (node.left() != null) {
                node = node.left();
            }
            return node.value();
        }
    }

    public T getLargest() {
        if (root == null) {
            return null;
        }
        if (root.right() == null) {
            return root.value();
        }
        else {
            BinaryNode<T> node = root;
            while (node.right() != null) {
                node = node.right();
            }
            return node.value();
        }

    }

    public ArrayList<T> preOrder() {
        return preOrder(root, new ArrayList<>());
    }

    private ArrayList<T> preOrder(BinaryNode<T> node, ArrayList<T> list) {
        if (node == null) {
            return list;
        }
        list.add(node.value());
        if (node.left() != null) {
            preOrder(node.left(), list);
        }
        if (node.right() != null) {
            preOrder(node.right(), list);
        }
        return list;
    }

    public ArrayList<T> postOrder() {
        ArrayList<T> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(BinaryNode<T> node, ArrayList<T> result) {
        if (node == null) {
            return;
        }
        postOrder(node.left(), result);
        postOrder(node.right(), result);
        result.add(node.value());
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(BinaryNode<T> node, ArrayList<T> result) {
        if (node == null) {
            return;
        }
        inOrder(node.left(), result);
        result.add(node.value());
        inOrder(node.right(), result);
    }

    public ArrayList<T> reverseOrder() {
        ArrayList<T> result = new ArrayList<>();
        reverseOrder(root, result);
        return result;
    }

    private void reverseOrder(BinaryNode<T> node, ArrayList<T> result) {
        if (node == null) {
            return;
        }
        reverseOrder(node.right(), result);
        result.add(node.value());
        reverseOrder(node.left(), result);
    }

    public ArrayList<T> levelOrder() {
        ArrayList<T> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<BinaryNode<T>> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            ArrayList<BinaryNode<T>> nList = new ArrayList<>();
            for (BinaryNode<T> node : list) {
                ans.add(node.value());
                if (node.left() != null) {
                    nList.add(node.left());
                }
                if (node.right() != null) {
                    nList.add(node.right());
                }
            }
            list = nList;
        }

        return ans;
    }

    /** Removes a comparison-equivalent value and returns its detached node, or null. */
    public BinaryNode<T> remove(T value) {
        java.util.Objects.requireNonNull(value, "value");
        BinaryNode<T> parent = null;
        BinaryNode<T> node = root;
        while (node != null) {
            int comparison = value.compareTo(node.value());
            if (comparison == 0) {
                break;
            }
            parent = node;
            node = comparison < 0 ? node.left() : node.right();
        }
        if (node == null) {
            return null;
        }

        if (node.left() != null && node.right() != null) {
            BinaryNode<T> successorParent = node;
            BinaryNode<T> successor = node.right();
            while (successor.left() != null) {
                successorParent = successor;
                successor = successor.left();
            }
            T removedValue = node.value();
            node.setValue(successor.value());
            successor.setValue(removedValue);
            parent = successorParent;
            node = successor;
        }
        BinaryNode<T> child = node.left() != null ? node.left() : node.right();
        if (parent == null) {
            root = child;
        } else if (parent.left() == node) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        node.setLeft(null);
        node.setRight(null);
        return node;
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

}
