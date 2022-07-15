package chapter06;

public class Ex02 {

	public static void main(String[] args) {

		ImplCalculator1 ic1 = new ImplCalculator1();
		ImplCalculator2 ic2 = new ImplCalculator2();
		
		ExecTimeCalculator etc = new ExecTimeCalculator(ic1);
		etc.factorial(1000);
		
		etc = new ExecTimeCalculator(ic2);
		etc.factorial(1000);
		
		
	}

}
