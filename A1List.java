// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) {
        super(address, size, key);
        this.next = null;
        this.prev = null;
    }

    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newbie = new A1List(address, size, key);
        if(this.next==null){
            return null;
        }
        A1List current = this;
        newbie.next = current.next;
        newbie.prev = current;
        current.next = newbie;
        newbie.next.prev = newbie;
        return newbie;
    }

    public boolean Delete(Dictionary d)
    {
        /*A1List current = this;
        if (current.prev!=null && current.next!=null && current.key == d.key && current.address==d.address && current.size==d.size){
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current.prev = null;
            current.next = null;
            return true;
        }*/
        if ((this.address == d.address && this.size == d.size && this.key == d.key) && (this.next!=null && this.prev!=null)){
            this.address = this.prev.address;
            this.size = this.prev.size;
            this.key = this.prev.key;
            this.prev = this.prev.prev;
            if(this.prev==null){
                return true;
            }
            this.prev.next.next = null;
            this.prev.next.prev = null;
            this.prev.next = this;
            return true;
        }
        A1List current = this;
        while(current!=null){
            if (current.next!=null && current.prev!=null && current.key == d.key && current.address==d.address && current.size==d.size){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current.next = null;
                current.prev = null;
                return true;
            }
            current = current.next;
        }
        current = this;
        while(current!=null){
            if(current.next!=null && current.prev!=null && current.key == d.key && current.address==d.address && current.size==d.size){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current.next = null;
                current.prev = null;
                return true;
            }
            current = current.prev;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        if (exact) {
            A1List current = this;
            while(current.prev!=null){
                current = current.prev;
            }
            while (current!=null) {
                if (current.key == k && current.next!=null && current.prev!=null) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }else{
            A1List current = this;
            while(current.prev!=null){
                current = current.prev;
            }
            while (current!=null) {
                if (current.key >= k && current.next!=null && current.prev!=null) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {
        A1List current = this;
        while(current.prev!=null){
            current = current.prev;
        }
        if(current.next.next==null){
            return null;
        }
        return current.next;
    }
    
    public A1List getNext() 
    {
        //if this is tail or this.next is tail, return null
        if(this.next.next==null){
            return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        A1List current = this.getFirst();
        if (current == null){
            //Empty Dictionary
            return true;
        }
        while (current.prev!=null && current.next!=null) {
            //detecting self loops
            if (current.next.prev != current) return false;
            if (current.prev.next != current) return false;
            current = current.next;
        }
        if(this.next!=null) {
            //Cycle Detection (forward)
            A1List fast = this.next;
            A1List slow = this;
            while (fast.next != null && (fast.next.key != -1 || fast.next.address != -1 || fast.next.size != -1)) {
                if (fast == slow) {
                    return false;
                }
                fast = fast.next.next;
                slow = slow.next;
            }
        }
        if(this.prev!=null) {
            //Cycle Detection (backward)
            A1List fast = this.prev;
            A1List slow = this;
            while (fast.prev != null && (fast.prev.key != -1 || fast.prev.address != -1 || fast.prev.size != -1)) {
                if (fast == slow) {
                    return false;
                }
                fast = fast.prev.prev;
                slow = slow.prev;
            }
        }
        return true;
    }
}


