package week5.q2;/*
 * SortedStorage.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


/**
 * This program generates a binary search tree of strings
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class SortedStorage {
    private static int emptyStringCount = 0;
    Node root;


    public boolean add(String x) {
        return add(x, true);
    }

    /**
     * The function add() adds the nodes to the binary search
     * tree in the sorted order.
     *
     * @param x string which has tobe added to the tree.
     * @return returns a boolean value if node is added, its true.
     */
    public boolean add(String x, boolean flag) {
        if (x.equals("")) {
            if (emptyStringCount >= 1) {
                return false;
            }
            emptyStringCount++;
            return true;
        } else {
            Node newNode = new Node(x);
            if (root == null) {
                root = newNode;
                return true;
            }
            Node traverseNode = root;
            Node parent = traverseNode;
            while (true) {
                if (traverseNode.value.compareTo(x) > 0) {
                    parent = traverseNode;
                    traverseNode = traverseNode.left;
                    if (traverseNode == null) {
                        if (parent.value.compareTo(x) != 0) {
                            parent.left = newNode;
                            return true;
                        } else {
                            return false;
                        }
                    } else if ("".equals(traverseNode.left.value)) {
                        newNode.left = parent.left;
                        parent.left = newNode;
                        return true;
                    }
                } else if (traverseNode.value.compareTo(x) < 0) {
                    parent = traverseNode;
                    traverseNode = traverseNode.right;
                    if (traverseNode == null) {
                        if (parent.value.compareTo(x) != 0) {
                            parent.right = newNode;
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else if (flag) {
                    if (traverseNode.right == null) {
                        traverseNode.right = new Node(x);
                    } else {
                        Node tempRightNode = traverseNode.right;
                        traverseNode.right = new Node(x);
                        traverseNode.right.right = tempRightNode;
                    }
                    return true;
                } else
                    return false;
            }
        }
    }

    /**
     * The function find() finds the nodes to the binary search
     * tree.
     *
     * @param root
     * @param x    string which has to be searched.
     * @return returns a boolean value if node is found, its true.
     */
    public Node findNode(Node root, String x) {
        Node traverseNode;
        if (root == null || (root.value.compareTo(x) == 0)) {
            traverseNode = root;
        } else if (root.value.compareTo(x) > 0) {
            traverseNode = findNode(root.left, x);
        } else {
            traverseNode = findNode(root.right, x);
        }
        return traverseNode;
    }

    public boolean find(String key) {
        if (key == null) {
            return false;
        } else {
            return findNode(root, key) != null;
        }
    }

    /**
     * The function delete() finds the nodes to the binary search
     * tree.
     *
     * @param k string which has to be deleted.
     * @return returns a boolean value if a node is deleted
     * successfully.
     */
    public Node deleteNode(Node rootNode, String k) {
        return deleteNode(rootNode, k, true);
    }

    public Node deleteNode(Node root, String k, boolean flag) {
        if (root == null) {
            return null;
        }
        if (root.value.compareTo(k) > 0) {
            root.left = deleteNode(root.left, k, flag);
            return root;
        } else if (root.value.compareTo(k) < 0) {
            root.right = deleteNode(root.right, k, flag);
            return root;

        } else {
            if (root.left == null) {//if only left node is not null
                return root.right;
            } else if (root.right == null) {// if only right node is not null
                return root.left;
            } else if (root.left.value.compareTo(k) == 0 && flag) {
                root.right = root.right.right;
                return root;
            } else {
                root.value = findMinimumLeftNode(root.right).value;
                root.right = deleteNode(root.right, root.value, flag);
            }
        }
        return root;
    }

    /**
     * The function findMinimumLeftNode finds the leaf node
     * of the left most child
     *
     * @param rootNode Root Node of the tree
     * @return returns the minimum left node leaf
     */
    public Node findMinimumLeftNode(Node rootNode) {
        Node tempNode = rootNode;
        if (rootNode == null || rootNode.value == null) {
            return null;
        } else if (rootNode.left == null || rootNode.left.value == null) {
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
     * The function delete() calls the method
     * deleteNode() which in turn deletes the
     * node of the key passed.
     *
     * @param key string which has to be deleted.
     * @return returns a boolean value if a node is deleted
     * successfully.
     */
    public boolean delete(String key) {
        if (find(key)) {
            root = deleteNode(root, key);
            return true;
        } else
            return false;
    }

    /**
     * The function prints the tree in inorder traversal
     * manner.
     *
     * @param traverseNode root from traversing will start.
     * @return returns a string
     */
    public String printTree(Node traverseNode) {
        if (traverseNode == null)
            return null;
        return "\t( l: " + (traverseNode.left == null ? "null" : printTree(traverseNode.left))
                + " " + traverseNode.value + "/ r: " +
                (traverseNode.right == null ? "null" : printTree(traverseNode.right)) + " )";
    }

    /**
     * The function toString overrides the function of
     * object class.
     *
     * @return returns a string
     */
    @Override
    public String toString() {
        String str = "\n\t\tValues stored:\t";
        System.out.print(str + printTree(root));
        return "";
    }

}//SortedStorage

