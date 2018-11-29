package BinaryTree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<V> {

    public static class Node<V>{
        public V value;
        public Node<V> left;
        public Node<V> right;
        public Node<V> parent;

        public Node(V value){
            this.value = value;
        }

    }

    private Node<V> mRoot;

    public void add(V v){
        if (null == mRoot){
            mRoot = new Node<>(v);
        }else{
            Queue<Node<V>> queue = new LinkedList<>();
            queue.add(mRoot);
            Node<V> temp;
            while ((temp = queue.poll()) != null){
                if (temp.left == null){
                    temp.left = new Node<>(v);
                    temp.left.parent = temp;
                    break;
                }else if (temp.right == null){
                    temp.right = new Node<>(v);
                    temp.right.parent = temp;
                    break;
                }else{
                    queue.add(temp.left);
                    queue.add(temp.right);
                }
            }
        }
    }

    public void preOrderTraversal(){
        Stack<Node<V>> stack = new Stack<>();
        Node<V> cur = mRoot;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                System.out.print(cur.value + ",");
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        System.out.println();
    }


    public void midOrderTraversal(){
        Stack<Node<V>> stack = new Stack<>();
        Node<V> cur = mRoot;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.print(cur.value + ",");
            cur = cur.right;
        }
        System.out.println();
    }

    public void lastOrderTraversal(){
        Stack<Node<V>> stack = new Stack<>();
        Node<V> cur = mRoot,last = null;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == last){
                System.out.print(cur.value + ",");
                stack.pop();
                last = cur;
                cur = null;
            }else{
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public void levelTraversal(){
        Queue<Node<V>> queue = new LinkedList<>();
        Node<V> cur = mRoot;
        while (cur != null || !queue.isEmpty()){
            if (cur != null){
                System.out.print(cur.value + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
            cur = queue.poll();
        }
        System.out.println();
    }


}
