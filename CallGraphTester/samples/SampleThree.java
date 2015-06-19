package samples;


class BasicClass {
	public int getNumber(){
		return 5;
	}
}
public class SampleThree extends BasicClass{
	public int getNumber(){
		return super.getNumber();
	}
	public static void main(String[] args) {
		SampleThree sample = new SampleThree();
		sample.getNumber();
	}
}

