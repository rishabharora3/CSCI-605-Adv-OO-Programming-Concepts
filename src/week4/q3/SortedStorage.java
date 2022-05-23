package week4.q3;/*
 * SortedStorage.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


/**
 * This program generates a binary search tree of strings and integers
 * It also stores the null as a separate node.
 * Also we can add, delete, find the nodes
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class SortedStorage {
    static int nullNodeCount = 0;
    static int emptyStringCount = 0;
    Node root;

    /**
     * The function add() adds the nodes to the binary search
     * tree in the sorted order.
     *
     * @param x         string which has tobe added to the tree.
     *
     * @return          returns a boolean value if node is added, its true.
     */
    public boolean add(String x) {
        if(x == null){
            Node newNode = new Node("null");
            if(root == null){
                root = newNode;
                return true;
            }else {
                Node traverseNode = root;
                Node parent = traverseNode;
                while (traverseNode!=null){
                    parent = traverseNode;
                    traverseNode = traverseNode.left;
                }
                parent.left = newNode;
                nullNodeCount++;
                return true;
            }

        }
        else if(x.equals("")){
            if(emptyStringCount>=1){
                return false;
            }
            emptyStringCount++;
            return  true;
        }
        else{
            int i = Integer.parseInt(x);
            Node newNode = new Node(x);
            if (root == null){
                root = newNode;
                return true;
            }
            Node traverseNode = root;
            Node parent = traverseNode;
            while (true){
                if(i < Integer.parseInt(traverseNode.value)){
                    parent= traverseNode;
                    traverseNode = traverseNode.left;
                    if(traverseNode == null){
                        if(Integer.parseInt(parent.value)!=i){
                            parent.left = newNode;
                            return true;
                        } else {
                                return false;
                            }
                        }else if(traverseNode.left.value == "null" || traverseNode.left.value == ""){
                        newNode.left = parent.left;
                        parent.left = newNode;
                        return true;
                    }
                    } else {
                    parent = traverseNode;
                    traverseNode = traverseNode.right;
                    if(traverseNode == null){
                        if(Integer.parseInt(parent.value)!=i){
                            parent.right = newNode;
                            return true;
                        } else {
                                return false;
                            }
                        }
                    }

                }
        }
    }

    /**
     * The function find() finds the nodes to the binary search
     * tree.
     *
     * @param x         string which has to be searched.
     *
     * @return          returns a boolean value if node is found, its true.
     */
    public boolean find(String x){
        int tempNullCount = nullNodeCount;
        Node traverseNode = root;

        if (x.equals("null")){
            if (tempNullCount >0){
                --tempNullCount;
                return true;
            }
            else {
                return false;
            }
        }else if(x.equals("")){
            if(emptyStringCount== 1){
                return true;
            }
            return false;
        }
        else{
            int i = Integer.parseInt(x);
            while (Integer.parseInt(traverseNode.value) != i) {
                    if (i < Integer.parseInt(traverseNode.value)) {
                        traverseNode = traverseNode.left;
                    } else {
                        traverseNode = traverseNode.right;
                    }
                }
                return true;


        }

    }

    /**
     * The function delete() finds the nodes to the binary search
     * tree.
     *
     * @param x         string which has to be deleted.
     *
     * @return          returns a boolean value if a node is deleted
     *                  successfully.
     */
    public boolean delete(String x){
        if(x == null){
            if(nullNodeCount > 0){
                String str = "null";
                Node traverseNode = root;
                Node parent = traverseNode;
                while(traverseNode.value != str){
                    parent = traverseNode;
                    traverseNode = traverseNode.left;
                }
                parent.left = traverseNode.left;
                nullNodeCount--;
                return  true;
            }else {
                return false;
            }

        }
        else if(x.equals("")){
            if(emptyStringCount==1){
                emptyStringCount--;
                return true;
            }
            else
                return false;
        }
        else {
            int i = Integer.parseInt(x);
            Node traverseNode = root;
            Node parent = traverseNode;
            boolean isRight = false;
            if(root == null){return false;}
            while(i!= Integer.parseInt(traverseNode.value)){
                parent = traverseNode;
                if(i < Integer.parseInt(traverseNode.value)){
                    isRight = false;
                    traverseNode = traverseNode.left;
                }
                else {
                    isRight = true;
                    traverseNode = traverseNode.right;
                }

                if(traverseNode == null){
                    return  false;
                }
            }


//            if(traverseNode.left.value == "null"){
//                traverseNode.left = null;
//            }
            //If it is a leaf node
            if (traverseNode.left == null && traverseNode.right == null){

                if (traverseNode == root){
                    root = null;
                }else if (isRight){
                    parent.right = null;
                }else{
                    parent.left = null;
                }
                return  true;
            }
            // if no left child
            else if (traverseNode.left == null){
                if(traverseNode == root){
                    root = root.right;
                }
                //if it is right child
                else if(isRight){
                    parent.right = traverseNode.right;
                }
                else {
                    parent.left = traverseNode.right;
                }
                return true;
            }
            //if no right child
            else if(traverseNode.right == null){
                if(traverseNode == root){
                    root = root.left;
                }else  if(isRight){
                    parent.right = traverseNode.left;
                }
                else {
                    parent.left = traverseNode.left;
                }
                return true;
            }
            //if traverse node has both left and right
            else {

                Node toReplaceWith;

                Node rightChildOfTraverseNode = traverseNode.right;
                Node travellingNode = rightChildOfTraverseNode;

                if(travellingNode.left == null){
                    toReplaceWith = travellingNode;
                }else{

                    while(travellingNode != null){
                        parent = travellingNode;
                        travellingNode = travellingNode.left;
                    }
                    toReplaceWith = parent;
                }

                //start the replacement process
                if(traverseNode == root){
                    toReplaceWith.left = root.left;
                    toReplaceWith.right = root.right;
                    root = toReplaceWith;

                }else if(isRight){
                    parent.right = toReplaceWith;
                    toReplaceWith.left = traverseNode.left;
                    toReplaceWith.right = traverseNode.right;
                }else {
                    parent.left = toReplaceWith;
                    toReplaceWith.left = traverseNode.left;
                    toReplaceWith = traverseNode.right;
                }
                return true;
            }
        }
    }

    /**
     * The function prints the tree in inorder traversal
     * manner.
     *
     * @param traverseNode  root from traversing will start.
     *
     * @return           returns a string
     */
    public String printTree(Node traverseNode){
        String str="";
        if(traverseNode != null){
            str += "( l " +
                    "" +
                    "" +
                    "+" +
                    "+:";
            str += printTree(traverseNode.left);
            str+= ")";
            str += traverseNode.value + "/1";
            str+="( r :";
            str+= printTree(traverseNode.right);
            str += ")";
        }
        return str;
    }

    /**
     * The function toString overrides the function of
     * object class.
     *
     * @return           returns a string
     */
    @Override
    public String toString(){
        String output= "\n\t\tincludes so many null values = " + nullNodeCount ;
        String str = "\n\t\tValues stored:\t( ";
        System.out.print(output + str);
        str = printTree(root);
        System.out.println(str);
        return "";
    }

}
class Node {
    String value;
    Node left;
    Node right;
    Node(String value) {
        this.value = value;

    }
}//SortedStorage
