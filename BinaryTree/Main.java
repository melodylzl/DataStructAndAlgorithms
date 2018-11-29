package BinaryTree;


public class Main {

    public static void main(String[] args){
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        binaryTree.add(10);
        binaryTree.add(85);
        binaryTree.add(15);
        binaryTree.add(70);
        binaryTree.add(20);
        binaryTree.add(60);
        binaryTree.add(30);
        binaryTree.add(50);

        binaryTree.preOrderTraversal();
        binaryTree.midOrderTraversal();
        binaryTree.lastOrderTraversal();
        binaryTree.levelTraversal();
    }
}
