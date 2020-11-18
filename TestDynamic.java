//import java.util.Scanner;

public class TestDynamic {

    public static void printarray(A1DynamicMem fox) {
        //Dictionary free1 = fox.freeBlk.getFirst();
        //Dictionary alloc1 = fox.allocBlk.getFirst();
        for (Dictionary d = fox.freeBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println(" ");
        for (Dictionary d = fox.allocBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println("");
        System.out.println("");
    }
    public static void main(String[] args) {
        //Scanner s = new Scanner(System.in);
        A1DynamicMem tip = new A1DynamicMem(20);
        printarray(tip);
        System.out.println("Allocate(10)");
        tip.Allocate(10);
        printarray(tip);
        System.out.println("Allocate(20)");
        tip.Allocate(20);
        printarray(tip);
        System.out.println("Free(50)");
        tip.Free(50);
        printarray(tip);
        System.out.println("Free(0)");
        tip.Free(0);
        printarray(tip);
        System.out.println("Free(10)");
        tip.Free(10);
        printarray(tip);

        
    }
    
}
