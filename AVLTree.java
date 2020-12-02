// Class: Height balanced AVL Tree
// Binary Search Tree
import java.lang.*;
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree(){
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key){
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the AVLTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private AVLTree getSentinel(){
        AVLTree current = this;
        while(current.parent != null){
            current = current.parent;
        }
        return current;
    }
    private int getHeight(AVLTree root){
        if (root == null) return -1;
        return root.height;
    }
/*    private int getHeight_var(AVLTree root){
        if (root == null) return -1;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }*/
    private void UpdateHeight(AVLTree root){
        if (root == null || root.parent == null) return;
        AVLTree current = root;
        while (current.parent!=null){
            current.height = 1 + Math.max(getHeight(current.left), getHeight(current.right));
            //System.out.println(current.address + " " + current.size + " " + current.key + " ***height " + current.height);
            current = current.parent;
        }
    }
    private void checkBalance(AVLTree root){
        if (root == null || root.parent == null) return;
        AVLTree current = root;
        while (current.parent != null){
            if ((getHeight(current.left) - getHeight(current.right)) > 1 || (getHeight(current.left) - getHeight(current.right)) < -1){
                //System.out.println("Rebalance " + current.address + " " + current.size + " " + current.key);
                 rebalance(current);
                 UpdateHeight(current);
            }
            current = current.parent;
        }
    }
    private void rebalance(AVLTree root){
        if (getHeight(root.left) > getHeight(root.right)){
            if (getHeight(root.left.left) >= getHeight(root.left.right)){
                root = rightrotate(root);
                return;
            }
            root.left = leftrotate(root.left);
            root = rightrotate(root);
            return;
        }
        if (getHeight(root.right.right) >= getHeight(root.right.left)){
            root = leftrotate(root);
            return;
        }
        root.right = rightrotate(root.right);
        root = leftrotate(root);
    }

    private AVLTree rightrotate(AVLTree node){
        AVLTree par = node.parent;
        AVLTree temp = node.left;
        node.left = temp.right;
        if (node.left!=null){
            node.left.parent = node;
        }
        temp.right = node;
        temp.right.parent = temp;
        if (par.right == node){
            par.right = temp;
        }else{
            par.left = temp;
        }
        temp.parent = par;
        par.height = 1 + Math.max(getHeight(par.left), getHeight(par.right));
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        temp.height = 1 + Math.max(getHeight(temp.left), getHeight(temp.right));
        //UpdateHeight(temp);
        //System.out.println("Rotated Node" + node.address + " " + node.key + "height " + node.height);
        //System.out.println("Rotated Node" + temp.address + " " + temp.key + "height " + temp.height);
        return temp;
    }

    private AVLTree leftrotate(AVLTree node){
        AVLTree par = node.parent;
        AVLTree temp = node.right;
        node.right = temp.left;
        if (node.right!=null){
            node.right.parent = node;
        }
        temp.left = node;
        temp.left.parent = temp;
        if (par.left == node){
            par.left = temp;
        }else{
            par.right = temp;
        }
        temp.parent = par;
        par.height = 1 + Math.max(getHeight(par.left), getHeight(par.right));
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        temp.height = 1 + Math.max(getHeight(temp.left), getHeight(temp.right));
        //UpdateHeight(temp);
        //System.out.println("Rotated Node" + node.address + " " + node.key + "height " + node.height);
        //System.out.println("Rotated Node" + temp.address + " " + temp.key + "height " + temp.height);
        return temp;
    }
    public AVLTree Insert(int address, int size, int key)
    {
        AVLTree newbie = new AVLTree(address, size, key);
        AVLTree current = this.getSentinel();
        if(current.right==null){
            current.right = newbie;
            newbie.parent = current;
            return newbie;
        }
        current = current.right;
        while (current.left!=null || current.right!=null){
            if (current.left==null){
                if(newbie.key < current.key || (newbie.key==current.key && newbie.address <= current.address)) {
                    current.left = newbie;
                    newbie.parent = current;
                    UpdateHeight(newbie);
                    checkBalance(newbie);
                    return newbie;
                }
                current = current.right;
            }
            else if (current.right == null){
                if(newbie.key>current.key || (newbie.key == current.key && newbie.address > current.address)){
                    current.right = newbie;
                    newbie.parent = current;
                    UpdateHeight(newbie);
                    checkBalance(newbie);
                    return newbie;
                }
                current = current.left;
            }
            else if(newbie.key<current.key || (newbie.key == current.key && newbie.address <= current.address)){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        if(newbie.key<current.key || (newbie.key == current.key && newbie.address <= current.address)){
            current.left = newbie;
            newbie.parent = current;
            UpdateHeight(newbie);
            checkBalance(newbie);
            return newbie;
        }
        current.right = newbie;
        newbie.parent = current;
        UpdateHeight(newbie);
        checkBalance(newbie);
        return newbie;
    }
    private AVLTree getSuccessor(){
        AVLTree node = this;
        if (node.right!=null){
            AVLTree current = node.right;
            while(current.left!=null){
                current = current.left;
            }
            return current;
        }
        else{
            AVLTree succ = node;
            while(succ.parent!=null && succ.parent.left!=succ){
                succ = succ.parent;
            }
            if(succ.parent == null){
                return null;
            }
            return succ.parent;
        }
    }
    private AVLTree getPredecessor(){
        AVLTree node = this;
        if (node.left!=null){
            AVLTree current = node.left;
            while(current.right!=null){
                current = current.right;
            }
            return current;
        }
        else{
            AVLTree pred = node;
            while(pred.parent!=null && pred.parent.right!=pred){
                pred = pred.parent;
            }
            if(pred.parent == null || pred.parent.parent == null){
                return null;
            }
            return pred.parent;
        }
    }
    private AVLTree Delete_node(){
        AVLTree current = this;
        if (current.left == null && current.right==null){
            AVLTree ans = current.parent;
            if (current.parent.left == current){
                current.parent.left = null;
            }else{
                current.parent.right = null;
            }
            current.parent = null;
            return ans;
        }
        else if (current.left!=null && current.right==null){
            AVLTree ans = current.parent;
            if (current.parent.left == current){
                current.parent.left = current.left;
                current.left.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }else if(current.parent.right == current){
                current.parent.right = current.left;
                current.left.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }
            return ans;
        }
        else if (current.right!=null && current.left == null){
            AVLTree ans = current.parent;
            if(current.parent.left == current){
                current.parent.left = current.right;
                current.right.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }else if(current.parent.right == current){
                current.parent.right = current.right;
                current.right.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }
            return ans;
        }
        else{
            AVLTree succ = current.getSuccessor();
            AVLTree ans = succ.parent;
            int ky = succ.key;
            int ad = succ.address;
            int sz = succ.size;;
            succ.Delete_node();
            current.key = ky;
            current.address = ad;
            current.size = sz;
            return ans;
        }
    }
    private AVLTree Delete_node_pr(){
        AVLTree current = this;
        if (current.left == null && current.right==null){
            AVLTree ans = current.parent;
            if (current.parent.left == current){
                current.parent.left = null;
            }else{
                current.parent.right = null;
            }
            current.parent = null;
            return ans;
        }
        else if (current.left!=null && current.right==null){
            AVLTree ans = current.parent;
            if (current.parent.left == current){
                current.parent.left = current.left;
                current.left.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }else if(current.parent.right == current){
                current.parent.right = current.left;
                current.left.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }
            return ans;
        }
        else if (current.right!=null && current.left == null){
            AVLTree ans = current.parent;
            if(current.parent.left == current){
                current.parent.left = current.right;
                current.right.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }else if(current.parent.right == current){
                current.parent.right = current.right;
                current.right.parent = current.parent;
                current.parent = null;
                current.left = null;
                current.right = null;
            }
            return ans;
        }
        else{
            AVLTree pred = current.getPredecessor();
            AVLTree ans = pred.parent;
            int ky = pred.key;
            int ad = pred.address;
            int sz = pred.size;;
            pred.Delete_node();
            current.key = ky;
            current.address = ad;
            current.size = sz;
            return ans;
        }
    }
    public boolean Delete(Dictionary e)
    {
        if (this.key == e.key && this.address == e.address && this.size == e.size){
            if (this.parent==null) return false;
            if (this.getSuccessor()!=null){
                this.key = this.getSuccessor().key;
                this.address = this.getSuccessor().address;
                this.size = this.getSuccessor().size;
                AVLTree ans = this.getSuccessor().Delete_node();
                UpdateHeight(ans);
                checkBalance(ans);
            }
            else if (this.getPredecessor()!=null){
                this.key = this.getPredecessor().key;
                this.address = this.getPredecessor().address;
                this.size = this.getPredecessor().size;
                AVLTree ans = this.getPredecessor().Delete_node_pr();
                UpdateHeight(ans);
                checkBalance(ans);
            }
            else{
                this.key = this.parent.key;
                this.address = this.parent.address;
                this.size = this.parent.size;
                this.parent.right = null;
                this.parent = null;
            }
            return true;
        }
        AVLTree current = this.getSentinel();
        if (current.right==null) return false;
        current = current.right;
        while(current!=null && (current.key!=e.key || current.address!=e.address || current.size!=e.size)){
            if (e.key < current.key || (e.key == current.key && e.address <= current.address)){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        if (current == null) return false;
        if (this.parent == null){
            AVLTree ans = current.Delete_node();
            UpdateHeight(ans);
            checkBalance(ans);
            return true;
        }
        if (current.right!=null && current.getSuccessor() == this) {
            AVLTree ans = current.Delete_node_pr();
            UpdateHeight(ans);
            checkBalance(ans);
            //System.out.println("Hello");
        }
        else{
            AVLTree ans = current.Delete_node();
            UpdateHeight(ans);
            checkBalance(ans);
        }
        return true;
    }
    public AVLTree Find(int key, boolean exact) {
        AVLTree current = this;
        while (current.parent != null){
            current = current.parent;
        }
        if (current.right == null) return null;
        current = current.right;
        if (exact) {
            AVLTree ans = null;
            while (current != null) {
                if (current.key < key) {
                    current = current.right;
                } else if (current.key > key) {
                    current = current.left;
                } else {
                    ans = current;
                    current = current.left;
                }
            }
            return ans;
        }
        //return findMin(current, key);
        AVLTree ans = null;
        while (current != null) {
            if (current.key < key) {
                current = current.right;
            } else {
                ans = current;
                current = current.left;
            }
        }
        return ans;
    }

    public AVLTree getFirst()
    {
        AVLTree current = this;
        while(current.parent!=null){
            current = current.parent;
        }
        if(current.right == null){
            return null;
        }
        return current.getSuccessor();
    }

    public AVLTree getNext()
    {
        if(this.parent == null){
            return null;
        }
        return this.getSuccessor();
    }

    private boolean checkTree(AVLTree root, AVLTree min, AVLTree max){
        if (root == null){
            return true;
        }
        if (root.key < min.key || root.key > max.key){
            //System.out.println("Check5");
            return false;
        }
        else if(root.key == min.key && root.address <= min.address){
            return false;
        }
        else if (root.key == max.key && root.address > max.address){
            return false;
        }
        return checkTree(root.left, min, root) && checkTree(root.right, root, max);
    }
    private boolean checkCycle(AVLTree root){
        if (root == null) return true;
        if (root.left == null && root.right == null){
            return true;
        }
        else if (root.left == null && root.right != null){
            if (root.right.parent!= root) {
                //System.out.println("Check1");
                return false;
            }
            return checkCycle(root.right);
        }
        else if (root.right == null && root.left!=null){
            if (root.left.parent!=root) {
                //System.out.println("Check2");
                return false;
            }
            return checkCycle(root.left);
        }
        if (root.left.parent!= root || root.right.parent!=root){
            //System.out.println("Check3");
            return false;
        }
        return checkCycle(root.left) && checkCycle(root.right);
    }
    private boolean isHeightbalanced(AVLTree root){
        if (root == null){
            return true;
        }
        int heightdiff = getHeight(root.left) - getHeight(root.right);
        if (heightdiff < -1 || heightdiff > 1){
            return false;
        }
        return isHeightbalanced(root.left) && isHeightbalanced(root.right);
    }
    public boolean sanity()
    {
        if (this.parent != null) {
            AVLTree slow = this;
            AVLTree fast = this.parent;
            while (fast != null && fast.parent != null) {
                if (fast == slow) {
                    //System.out.println("Check4");
                    return false;
                }
                fast = fast.parent.parent;
                slow = slow.parent;
            }
        }
        AVLTree curr = this.getSentinel();
        if (curr.parent != null || (curr.address!=-1 || curr.key!=-1 || curr.size!=-1)){
            //System.out.println("Check10");
            return false;
        }
        if (curr.right == null) return true;
        if (!checkCycle(curr)) {
            //System.out.println("Check10");
            return false;
        }
        curr = curr.right;
        AVLTree min = new AVLTree(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
        AVLTree max = new AVLTree(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        if (!checkTree(curr, min, max)) {
            //System.out.println("Check9");
            return false;
        }
        if (!isHeightbalanced(curr)) return false;
        return true;
    }
}


