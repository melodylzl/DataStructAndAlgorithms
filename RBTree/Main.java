package RBTree;

public class Main {

    public static void main(String[] args){
        RBTree rbTree = new RBTree();
        rbTree.insert(10);
        rbTree.insert(85);
        rbTree.insert(15);
        rbTree.insert(70);
        rbTree.insert(20);
        rbTree.insert(60);
        rbTree.insert(30);
        rbTree.insert(50);
        rbTree.insert(65);
        rbTree.insert(80);
        rbTree.insert(90);
        rbTree.insert(40);
        rbTree.insert(5);
        rbTree.insert(55);

        rbTree.delete(70);
        rbTree.delete(10);
        rbTree.delete(30);

        rbTree.print();
    }
}
