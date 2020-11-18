// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize<=0){
            return -1;
        }
        Dictionary found = freeBlk.Find(blockSize, false);
        if (found == null) return -1;
        if (found.size == blockSize){
            int add = found.address;
            allocBlk.Insert(found.address, blockSize, found.address);
            freeBlk.Delete(found);
            return add;
        }else{
            allocBlk.Insert(found.address, blockSize, found.address);
            int add = found.address;
            freeBlk.Insert(found.address + blockSize, found.size - blockSize, found.size-blockSize);
            freeBlk.Delete(found);
            return add;
        }
    } 
    
    public int Free(int startAddr) {
        if (startAddr<0){
            return -1;
        }
        Dictionary found = allocBlk.Find(startAddr, true);
        if (found == null) return -1;
        else{
            allocBlk.Delete(found);
            freeBlk.Insert(found.address, found.size, found.size);
            return 0;
        }
    }
}