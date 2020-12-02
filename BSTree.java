// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.

    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){

        super(address, size, key);
    }
    private BSTree getSentinel(){
        BSTree current = this;
        while(current.parent != null){
            current = current.parent;
        }
        return current;
    }
    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree newbie = new BSTree(address, size, key);
        BSTree current = this.getSentinel();
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
                    return newbie;
                }
                current = current.right;
            }
            else if (current.right == null){
                if(newbie.key>current.key || (newbie.key == current.key && newbie.address > current.address)){
                    current.right = newbie;
                    newbie.parent = current;
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
            return newbie;
        }
        current.right = newbie;
        newbie.parent = current;
        return newbie;
    }
    private BSTree getSuccessor(){
        BSTree node = this;
        if (node.right!=null){
            BSTree current = node.right;
            while(current.left!=null){
                current = current.left;
            }
            return current;
        }
        else{
            BSTree succ = node;
            while(succ.parent!=null && succ.parent.left!=succ){
                succ = succ.parent;
            }
            if(succ.parent == null){
                return null;
            }
            return succ.parent;
        }
    }
    private BSTree getPredecessor(){
        BSTree node = this;
        if (node.left!=null){
            BSTree current = node.left;
            while(current.right!=null){
                current = current.right;
            }
            return current;
        }
        else{
            BSTree pred = node;
            while(pred.parent!=null && pred.parent.right!=pred){
                pred = pred.parent;
            }
            if(pred.parent == null || pred.parent.parent == null){
                return null;
            }
            return pred.parent;
        }
    }
    private void Delete_node(){
        BSTree current = this;
        if (current.left == null && current.right==null){
            if (current.parent.left == current){
                current.parent.left = null;
            }else{
                current.parent.right = null;
            }
            current.parent = null;
        }
        else if (current.left!=null && current.right==null){
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
        }
        else if (current.right!=null && current.left == null){
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
        }
        else{
            BSTree succ = current.getSuccessor();
            int ky = succ.key;
            int ad = succ.address;
            int sz = succ.size;;
            succ.Delete_node();
            current.key = ky;
            current.address = ad;
            current.size = sz;
        }
    }
    private void Delete_node_pr(){
        BSTree current = this;
        if (current.left == null && current.right==null){
            if (current.parent.left == current){
                current.parent.left = null;
            }else{
                current.parent.right = null;
            }
            current.parent = null;
        }
        else if (current.left!=null && current.right==null){
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
        }
        else if (current.right!=null && current.left == null){
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
        }
        else{
            BSTree pred = current.getPredecessor();
            int ky = pred.key;
            int ad = pred.address;
            int sz = pred.size;;
            pred.Delete_node();
            current.key = ky;
            current.address = ad;
            current.size = sz;
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
                this.getSuccessor().Delete_node();
            }
            else if (this.getPredecessor()!=null){
                this.key = this.getPredecessor().key;
                this.address = this.getPredecessor().address;
                this.size = this.getPredecessor().size;
                this.getPredecessor().Delete_node_pr();
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
        BSTree current = this.getSentinel();
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
            current.Delete_node();
            return true;
        }
        if (current.right!=null && current.getSuccessor() == this) current.Delete_node_pr();
        else{
            current.Delete_node();
        }
        //current.Delete_node();
        return true;
    }
    public BSTree Find(int key, boolean exact) {
        BSTree current = this;
        while (current.parent != null) {
            current = current.parent;
        }
        if (current.right == null) return null;
        current = current.right;
        if (exact) {
            BSTree ans = null;
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
        BSTree ans = null;
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

    public BSTree getFirst()
    {
        BSTree current = this;
        while(current.parent!=null){
            current = current.parent;
        }
        if(current.right == null){
            return null;
        }
        return current.getSuccessor();
    }

    public BSTree getNext()
    {
        if(this.parent == null){
            return null;
        }
        return this.getSuccessor();
    }

    private boolean checkTree(BSTree root, BSTree min, BSTree max){
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
    private boolean checkCycle(BSTree root){
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
    public boolean sanity()
    {
        BSTree slow = this;
        BSTree fast = this.parent;
        while (fast!=null && fast.parent!=null){
            if (fast == slow){
                //System.out.println("Check4");
                return false;
            }
            fast = fast.parent.parent;
            slow = slow.parent;
        }
        BSTree curr = this.getSentinel();
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
        BSTree min = new BSTree(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
        BSTree max = new BSTree(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        if (!checkTree(curr, min, max)) {
            //System.out.println("Check9");
            return false;
        }
        return true;
    }
}


 


