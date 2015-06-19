package samples;
public class SampleTwo {
	
	public int GreatestCommonDivisor(int a, int b){
		if(b==0) return a;
		return GreatestCommonDivisor(b, a%b);
	}
	
	public String CallMeA(String s){
		return CallMeB(s);
	}
	
	public String CallMeB(String s){
		return CallMeC(s);
	}
	
	public String CallMeC(String s){
		if(s.length() == 0 ) return "";
		else if(s.length()%2 == 0 ) return CallMeA(s.substring(1)); 
		else return CallMeB(s.substring(1));
	}
	
	public static void main(String[] args) {
		SampleTwo tester = new SampleTwo();
		int gcd = tester.GreatestCommonDivisor(5, 12);
		tester.CallMeA(""+gcd+"@");
	}
	
	
	
	
}

