package samples;
public class SampleOne {
	
	public static void uncalledMethod(){
		int x = 3;
	}

	public static void fooA(int k){
		fooB(k+1);
	}	
	public static void fooB(int k){
		//fooC(k+1);
	}
	public static void main(String[] args) {
		//MeuTeste b = new MeuTeste();
		//b.fooA(0);
		SampleOne.fooA(0);
		//SampleOne.fooB(0);
	}
	
}



