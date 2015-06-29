package samples;
public class SampleOne {
	
	public static void uncalledMethod(){
		int x = 3;
	}

	public static void fooA(int k){
		fooB(k+1);
	}	
	public static void fooB(int k){}
	public static void main(String[] args) {
		SampleOne.fooA(0);
	}
	
}



