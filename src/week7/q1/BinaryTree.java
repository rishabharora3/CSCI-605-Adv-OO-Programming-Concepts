package week7.q1;
/* BinaryTree.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Binary Tree generic code which the child class uses
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public abstract class BinaryTree {

    /**
     * adding node
     *
     * @param root root node
     * @param key  key node
     * @return boolean true id node is added else false
     */
    public boolean addNode(Node root, Comparable key) {
        if (root.isNull()) {
            root.add(key);
            return true;
        }
        Node tempNode = root;
        while (true) {
            if (tempNode.getKey().compareTo(key) > 0) {
                Node parentNode = tempNode;
                if (parentNode.left == null) {
                    parentNode.left = parentNode.newNode(key);
                    return true;
                } else {
                    tempNode = parentNode.left;
                }
            } else if (tempNode.getKey().compareTo(key) < 0) {
                Node parentNode = tempNode;
                if (parentNode.right == null) {
                    parentNode.right = parentNode.newNode(key);
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
     * delete node
     *
     * @param rootNode root node is passed.
     * @param k key
     * @return Node type object
     */
    public Node deleteNode(Node rootNode, Comparable k) {
        if (rootNode == null) {
            return null;
        }
        if (rootNode.getKey().compareTo(k) > 0) {
            rootNode.left = deleteNode(rootNode.left, k);
            return rootNode;
        } else if (rootNode.getKey().compareTo(k) < 0) {
            rootNode.right = deleteNode(rootNode.right, k);
            return rootNode;
        } else {
            if (rootNode.left == null) {
                return rootNode.right;
            } else if (rootNode.right == null) {
                return rootNode.left;
            } else {
                rootNode.setKey(minLeftNode(rootNode.right).getKey());
                rootNode.right = deleteNode(rootNode.right, rootNode.getKey());
            }
        }
        return rootNode;
    }

    /**
     * finding min left node
     *
     * @param rootNode  root node passed
     * @return Node type object
     */
    public Node minLeftNode(Node rootNode) {
        Node tempNode = rootNode;
        if (rootNode.isNull()) {
            return null;
        } else if (rootNode.left == null || rootNode.left.isNull()) {
            return rootNode;
        } else {
            while (!rootNode.left.isNull()) {
                tempNode = rootNode.left;
                rootNode = rootNode.left;
            }
            return tempNode;
        }
    }

    /**
     * search node
     *
     * @param rootNode root node passed
     * @param key  key
     * @return Node type object is returned
     */
    public Node searchNode(Node rootNode, Comparable key) {
        Node newNode;
        if (rootNode == null || (rootNode.getKey().compareTo(key) == 0)) {
            newNode = rootNode;
        } else if (rootNode.getKey().compareTo(key) > 0) {
            newNode = searchNode(rootNode.left, key);
        } else {
            newNode = searchNode(rootNode.right, key);
        }
        return newNode;
    }

}
