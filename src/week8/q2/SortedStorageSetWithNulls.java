package week8.q2;/*
 * SortedStorageSetWithNulls.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Generic class for creating different type of binary trees
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class SortedStorageSetWithNulls<T extends Comparable<T>> implements StorageInterface<T> {

    private Node<T> root;
    private int totalNulls = 0;

    /**
     * Adds comparable extended keys in binary tree
     *
     * @param key: generic key
     * @return true, if key added successfully
     */
    public boolean add(T key) {
        if (key == null) {
            totalNulls += 1;
            return true;
        }
        Node<T> newNode = new Node<>(key);
        if (root == null) {
            root = newNode;
            root.key = key;
            return true;
        }
        Node<T> tempNode = root;
        while (true) {
            if (tempNode.key.compareTo(key) > 0) {
                Node<T> parentNode = tempNode;
                if (parentNode.left == null) {
                    newNode.key = key;
                    parentNode.left = newNode;
                    return true;
                } else {
                    tempNode = parentNode.left;
                }
            } else if (tempNode.key.compareTo(key) < 0) {
                Node<T> parentNode = tempNode;
                if (parentNode.right == null) {
                    newNode.key = key;
                    parentNode.right = newNode;
                    return true;
                } else {
                    tempNode = parentNode.right;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * finds keys in binary tree
     *
     * @param key key to search
     * @return true if found
     */
    public boolean find(T key) {
        if (key == null && totalNulls > 0) {
            return true;
        }
        return find(root, key) != null;
    }

    /**
     * finds keys in binary tree
     *
     * @param key:      key to search
     * @param rootNode: root node
     * @return node found
     */
    public Node<T> find(Node<T> rootNode, T key) {
        Node<T> newNode;
        if (rootNode == null || (rootNode.key.compareTo(key) == 0)) {
            newNode = rootNode;
        } else if (rootNode.key.compareTo(key) > 0) {
            newNode = find(rootNode.left, key);
        } else {
            newNode = find(rootNode.right, key);
        }
        return newNode;
    }

    /**
     * check if null is present
     *
     * @return true if present
     */
    @Override
    public boolean includesNull() {
        return totalNulls > 0;
    }

    /**
     * delete a node in binary tree
     *
     * @param key generic key
     * @return true, if added successfully
     */
    public boolean delete(T key) {
        if (find(key)) {
            if (key == null && totalNulls > 0) {
                totalNulls -= 1;
            } else {
                root = delete(root, key);
            }
            return true;
        }
        return false;
    }

    /**
     * deletes the node from tree
     *
     * @param key:      generic key
     * @param rootNode: root node
     * @return rootNode, if key is deleted successfully
     */
    public Node<T> delete(Node<T> rootNode, T key) {
        if (rootNode == null) {
            return null;
        }
        if (rootNode.key.compareTo(key) > 0) {
            rootNode.left = delete(rootNode.left, key);
            return rootNode;
        } else if (rootNode.key.compareTo(key) < 0) {
            rootNode.right = delete(rootNode.right, key);
            return rootNode;

        } else {
            if (rootNode.left == null) {
                return rootNode.right;
            } else if (rootNode.right == null) {
                return rootNode.left;
            } else {
                rootNode.key = minLeftNode(rootNode.right).key;
                rootNode.right = delete(rootNode.right, rootNode.key);
            }
        }
        return rootNode;
    }

    /**
     * finds the leftmost leaf of the binary search tree
     *
     * @param rootNode: root node
     * @return Node:      the leftmost leaf Node
     */
    public Node<T> minLeftNode(Node<T> rootNode) {
        Node<T> tempNode = rootNode;
        if (rootNode == null) {
            return null;
        } else if (rootNode.left == null) {
            return rootNode;
        } else {
            while (rootNode.left != null) {
                tempNode = rootNode.left;
                rootNode = rootNode.left;
            }
            return tempNode;
        }
    }

    /**
     * printing purpose string for object
     *
     * @return String to print
     */
    @Override
    public String toString() {
        return "Values: " + inorder(root) + "\t\n" +
                "number of Nulls: " + totalNulls;
    }

    /**
     * recursive method returns values of BST
     *
     * @param pointer: root node
     * @return String:  The traversed tree
     */
    public String inorder(Node<T> pointer) {
        if (pointer == null)
            return null;
        return "\t( l: " + (pointer.left == null ? "null" : inorder(pointer.left))
                + " " + pointer.key + "/ r: " +
                (pointer.right == null ? "null" : inorder(pointer.right)) + " )";
    }
}
