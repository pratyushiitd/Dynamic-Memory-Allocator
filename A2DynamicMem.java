// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    public int Free(int startAddr) {
        if (startAddr<0){
            return -1;
        }
        Dictionary found = allocBlk.Find(startAddr, true);
        if (found == null) return -1;
        else{
            int ad = found.address;
            int sz = found.size;
            allocBlk.Delete(found);
            freeBlk.Insert(ad, sz, sz);
            return 0;
        }
    }
/*    public void printBlk(){
        System.out.print("\nfreeBlk is : ");
        Dictionary first = this.freeBlk.getFirst();
        while(first!=null){
            System.out.print(" ("+first.address+", "+first.size+") ");
            if(this.freeBlk==first) System.out.print(" <-- ");
            first = first.getNext();
        }

        System.out.print("\nallocBlk is : ");
        first = this.allocBlk.getFirst();
        while(first!=null){
            System.out.print(" ("+first.address+", "+first.size+ " "") ");
            if(this.allocBlk==first) System.out.print(" <-- ");
            first = first.getNext();
        }
        System.out.print("\n");
    }*/
    public void Defragment() {
        Dictionary temp;
        if (this.type == 2){
            temp = new BSTree();
        }
        else{
            temp = new AVLTree();
        }
        for (Dictionary fir = freeBlk.getFirst();  fir!=null; fir = fir.getNext()){
            int ad = fir.address;
            int sz = fir.size;
            temp.Insert(ad, sz, ad);
        }
        Dictionary cur1 = temp.getFirst();
        while(cur1!=null && cur1.getNext()!=null){
            Dictionary cur2 = cur1.getNext();
            if (cur2.address == cur1.address + cur1.size) {
                int cur1ad = cur1.address;
                int cur1sz = cur1.size;
                int cur2ad = cur2.address;
                int cur2sz = cur2.size;
                Dictionary d1;
                Dictionary d2;
                Dictionary d3;
                Dictionary d4;
                if (this.type == 2) {
                    d1 = new BSTree(cur1ad, cur1sz, cur1sz);
                    d2 = new BSTree(cur2ad, cur2sz, cur2sz);
                    d3 = new BSTree(cur1ad, cur1sz, cur1ad);
                    d4 = new BSTree(cur2ad, cur2sz, cur2ad);
                }
                else{
                    d1 = new AVLTree(cur1ad, cur1sz, cur1sz);
                    d2 = new AVLTree(cur2ad, cur2sz, cur2sz);
                    d3 = new AVLTree(cur1ad, cur1sz, cur1ad);
                    d4 = new AVLTree(cur2ad, cur2sz, cur2ad);
                }
                freeBlk.Delete(d1);
                freeBlk.Delete(d2);
                freeBlk.Insert(cur1ad, cur1sz + cur2sz, cur1sz + cur2sz);
                temp.Delete(d3);
                temp.Delete(d4);
                cur1 = temp.Insert(cur1ad, cur1sz + cur2sz, cur1ad);
            }
            else{
                cur1 = cur1.getNext();
            }
        }
    }
}