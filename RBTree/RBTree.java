package RBTree;

import java.util.LinkedList;

public class RBTree {

    /**
     * 节点结构
     */
    public static class Node{
        int mValue;
        Node mParent;
        Node mLeft;
        Node mRight;
        boolean mIsRed;

        public Node(int value,Node parent,boolean isRed){
            mValue = value;
            mParent = parent;
            mIsRed = isRed;
        }
    }

    /**
     * 根节点
     */
    private Node mRoot;


    public RBTree(){

    }


    /**
     * 插入节点
     * @param value 节点值
     */
    public void insert(int value){
        // 先找到要插入位置的父节点
        Node insertNodeParent = findInsertNodeParent(mRoot,value);
        if (insertNodeParent == null){
            // 如果父节点为空，表示当前是一个空树，创建根节点即可，不需要做调整
            mRoot = new Node(value,null,false);
        }else{
            // 创建插入节点
            Node insertNode = new Node(value,insertNodeParent,true);
            if (insertNodeParent.mValue > value){
                // 如果父节点的值大于插入节点的值，插入节点插到父节点的左孩子节点
                insertNodeParent.mLeft = insertNode;
            }else{
                // 如果父节点的值小于或等于插入节点的值，插入节点插到父节点的右孩子节点
                insertNodeParent.mRight = insertNode;
            }
            //调整树
            adjustTree(insertNode);
        }
    }

    /**
     * 插入节点后调整树
     * @param currentNode 当前节点
     */
    private void adjustTree(Node currentNode){
        if (currentNode == null){
            return;
        }
        // 当前节点的父亲节点
        Node parent = currentNode.mParent;
        if (parent == null || parent.mParent == null){
            return;
        }
        // 当前节点的祖父节点
        Node grandFather = parent.mParent;
        // 当前节点的叔叔节点
        Node uncle = grandFather.mLeft == parent ? grandFather.mRight : grandFather.mLeft;
        // 只有父亲节点是红色的时候需要调整
        if (parent.mIsRed){
            //叔叔节点是红色
            if (isRed(uncle)){
                //父亲节点变黑、叔叔节点变黑，祖父节点变红，将祖父节点设为当前节点继续调整
                parent.mIsRed = false;
                uncle.mIsRed = false;
                grandFather.mIsRed = true;
                adjustTree(grandFather);
            }else{
                //叔叔节点是黑色
                if (parent == grandFather.mLeft){
                    //父亲节点是祖父节点的左孩子
                    if (parent.mLeft == currentNode){
                        //当前节点是父亲节点的左孩子
                        //父亲节点变黑，祖父节点变红，以祖父节点右旋
                        parent.mIsRed = false;
                        grandFather.mIsRed = true;
                        rotateRight(grandFather);
                    }else{
                        //当前节点是父亲节点的右孩子
                        //以父亲节点右旋，将父亲节点作为当前节点继续调整
                        rotateRight(parent);
                        adjustTree(parent);
                    }
                }
                else{
                    //父亲节点是祖父节点的右孩子
                    if (parent.mLeft == currentNode){
                        //当前节点是是父亲节点的左孩子
                        //以父亲节点右旋，将父亲节点作为当前节点继续调整
                        rotateRight(parent);
                        adjustTree(parent);
                    }else{
                        //当前节点是父亲节点的右孩子
                        //以祖父节点右旋，祖父节点染红，父亲节点变黑
                        rotateLeft(grandFather);
                        grandFather.mIsRed = true;
                        parent.mIsRed = false;
                    }
                }
            }
        }
        // 如果根节点是红色，变黑
        if (isRed(mRoot)){
            mRoot.mIsRed = false;
        }
    }

    /**
     * 查找要插入位置的父亲节点
     * @param node 当前查找的节点
     * @param value 要插入的元素
     * @return 插入位置的父亲节点
     */
    private Node findInsertNodeParent(Node node,int value){
        if (node == null){
            return null;
        }
        Node temp;
        if (node.mValue > value){
            temp = findInsertNodeParent(node.mLeft,value);
        }else{
            temp = findInsertNodeParent(node.mRight,value);
        }
        return temp == null ? node : temp;
    }

    /**
     * 左旋
     *       x                 y
     *     /  \              /  \
     *    xl   y    ---->   x    yr
     *       /  \         /  \
     *      yl   yr     xl   yl
     * @param x 旋转的节点
     */
    private void rotateLeft(Node x){
        Node y = x.mRight;
        x.mRight = y.mLeft;

        if (y.mLeft != null){
            y.mLeft.mParent = x;
        }
        y.mParent = x.mParent;

        if (x == mRoot){
            mRoot = y;
        }else if (x == x.mParent.mLeft){
            x.mParent.mLeft = y;
        }else{
            x.mParent.mRight = y;
        }

        y.mLeft = x;
        x.mParent = y;
    }

    /**
     * 右旋
     *        x                 y
     *      /  \              /  \
     *     y   xr    ---->   yl   x
     *   /  \                   /  \
     *  yl   yr               yr   xr
     * @param x 旋转的节点
     */
    private void rotateRight(Node x){
        Node y = x.mLeft;
        x.mLeft = y.mRight;

        if (y.mRight != null){
            y.mRight.mParent = x;
        }
        y.mParent = x.mParent;

        if (x == mRoot){
            mRoot = y;
        }else if (x == x.mParent.mLeft){
            x.mParent.mLeft = y;
        }else{
            x.mParent.mRight = y;
        }

        y.mRight = x;
        x.mParent = y;
    }


    /**
     * 删除元素
     * @param value 删除的元素
     */
    public void delete(int value){
        delete(find(value));
    }

    /**
     *             z
     *            / \
     *         p[y]  ..
     *         /  \
     *        ..   y
     *            /
     *           x
     * 删除节点
     * @param z 删除的节点
     */
    private void delete(Node z){
        if (z == null){
            return;
        }
        Node y;
        if (z.mLeft == null || z.mRight == null){
            y = z;
        }else{
            y = treeSuccessor(z);
        }
        Node x;
        if (y.mLeft != null){
            x = y.mLeft;
        }else{
            x = y.mRight;
        }
        if (x != null){
            x.mParent = y.mParent;
        }
        if (y.mParent == null){
            mRoot = x;
        }else if (y == y.mParent.mLeft){
            y.mParent.mLeft = x;
        }else{
            y.mParent.mRight = x;
        }
        if (y != z){
            z.mValue = y.mValue;
        }
        if (!y.mIsRed){
            deleteFixUp(y.mParent,x);
        }
    }

    /**
     *         p         p
     *       /  \   或  /  \
     *      x    b     b   x
     *  p:父亲节点
     *  x:当前节点
     *  b:兄弟节点
     *  @param p 父亲节点
     *  @param x 当前节点
     */
    private void deleteFixUp(Node p,Node x){
        //兄弟节点
        Node b;
        while (x != mRoot && isBlack(x)){
            // x是父节点的左孩子节点
            if (x == p.mLeft){
                b = p.mRight;
                /* case 1: 兄弟节点是红色
                 * 处理：兄弟节点变黑,父节点变红，以父节点左旋，重新设置兄弟节点
                 */
                if (isRed(b)){
                    b.mIsRed = false;
                    p.mIsRed = true;
                    rotateLeft(p);
                    b = p.mRight;
                }
                /* case 2: 兄弟节点是黑色且兄弟节点的左、右孩子节点均是黑色
                 * 处理：兄弟节点变红,将父节点设为当前节点
                 */
                if (isBlack(b.mLeft) && isBlack(b.mRight)){
                    b.mIsRed = true;
                    x = p;
                    p = x.mParent;
                }
                /* case 3: 兄弟节点是黑色且兄弟节点的左孩子节点是红，右孩子节点是黑
                 * 处理：将兄弟节点的左孩子节点变黑，兄弟节点变红，以兄弟节点右旋，
                 * 将父亲节点的当前右孩子节点设为兄弟节点
                 */
                else if (isBlack(b.mRight)){
                    b.mLeft.mIsRed = false;
                    b.mIsRed = true;
                    rotateRight(b);
                    b = p.mRight;
                }
                /* case 4: 兄弟节点是黑色且兄弟节点的右孩子节点是红，左孩子节点颜色随意
                 * 处理：兄弟节点染成父节点的颜色，父亲节点变黑，兄弟节点的右孩子节点变黑
                 * 以父亲节点左旋，将当前节点设为根节点，算法结束
                 */
                else{
                    b.mIsRed = p.mIsRed;
                    p.mIsRed = false;
                    b.mRight.mIsRed = false;
                    rotateLeft(p);
                    x = mRoot;
                }
            }
            else{
                // x是父节点的右孩子节点
                b = p.mLeft;
                if (isRed(b)){
                    b.mIsRed = false;
                    p.mIsRed = true;
                    rotateRight(p);
                    b = p.mLeft;
                }
                if (isBlack(b.mLeft) && isBlack(b.mRight)){
                    b.mIsRed = true;
                    x = p;
                    p = x.mParent;
                }else if (isBlack(b.mLeft)){
                    b.mRight.mIsRed = false;
                    b.mIsRed = true;
                    rotateLeft(b);
                    b = p.mLeft;
                }else{
                    b.mIsRed = p.mIsRed;
                    p.mIsRed = false;
                    b.mLeft.mIsRed = false;
                    rotateRight(p);
                    x = mRoot;
                }
            }
        }
        x.mIsRed = false;
    }


    /**
     * 查找元素
     * @param value 要查找的元素
     * @return 查找元素所在的节点
     */
    public Node find(int value){
        return find(mRoot,value);
    }

    /**
     * 查找元素
     * @param node 当前查找的位置
     * @param value 要查找的元素
     * @return 查找元素所在的节点
     */
    private Node find(Node node,int value){
        if (node == null){
            return null;
        }
        if (node.mValue > value){
            return find(node.mLeft,value);
        }else if (node.mValue < value){
            return find(node.mRight,value);
        }else{
            return node;
        }
    }

    /**
     * 找出当前节点的后续节点（左边），即值仅次于当前节点的值的节点
     * @param node 当前节点
     * @return 后续节点
     */
    private Node treeSuccessor(Node node){
        if (node == null || node.mLeft == null){
            return null;
        }
        node = node.mLeft;
        while (node.mRight != null){
            node = node.mRight;
        }
        return node;
    }

    /**
     * 节点是否红色
     * @param node 节点
     * @return true or false
     */
    private boolean isRed(Node node){
        return node != null && node.mIsRed;
    }

    /**
     * 节点是否黑色
     * @param node 节点
     * @return true or false
     */
    private boolean isBlack(Node node){
        return node == null || !node.mIsRed;
    }


    private void print(Node node){
        if (node == null){
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(mRoot);
        Node temp;
        while ((temp = queue.poll()) != null){
            System.out.print(temp.mValue + ",");
            if (temp.mLeft != null){
                queue.add(temp.mLeft);
            }
            if (temp.mRight != null){
                queue.add(temp.mRight);
            }
        }
    }


    public void print(){
        print(mRoot);
    }


}
