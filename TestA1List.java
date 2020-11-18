public class TestA1List{
	public static void print(A1List d){
        //A1List current=this.getFirst();
        int count = 0; 
        for (d =d.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.key+" ");
            count = count + 1;
        }
        System.out.println();
        System.out.println("size "+count);
    }
	public static void main(String[] args){
		A1List d=new A1List();
		d=d.Insert(0,100,100);
		print(d);
		A1List a=d.Find(-1,true);
		if(a==null){
			System.out.println("null");
		}
		else{
			System.out.println(a.key);
		}
		d=d.Insert(-1,-1,-1);
		print(d);
		//Dictionary s=new Dictionary 
		A1List b=d.Find(-1,true);
		if(b==null){
			System.out.println("null");
		}
		else{
			System.out.println(b.key);
		}
		Dictionary c= new A1List(-1,-1,-1);
		//boolean res=d.Delete(b);
		d.Delete(c);
		//System.out.println(res);
		print(d);
		d=d.Insert(0,10,10);
		//Dictionary c=new A1List(-1,-1,-1);
		//boolean res1=d.Delete(b);
		//System.out.println(res1);
		print(d);
	}
	/*public static void main(String[] args){
		A1List a= new A1List();
		a.Insert(0,20,20);
		print(a);
		a.Insert(20,80,60);
		print(a);
	}*/
}