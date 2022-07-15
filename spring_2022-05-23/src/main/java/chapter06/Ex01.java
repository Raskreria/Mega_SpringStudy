package chapter06;

public class Ex01 {

	public static void main(String[] args) {

		//반복문 factorial 수행 시간 측정
		long start1 = System.currentTimeMillis();
		//현재 시간을 유닉스 타임스탬프로 밀리초단위(1/1000초)로 계산해서 리턴해줌.
		
		ImplCalculator1 impl1 = new ImplCalculator1();
		impl1.factorial(10000);
		
		long end1 = System.currentTimeMillis();
		long duration1 = end1 - start1;

		System.out.println("결과를 구하기 까지 걸린 시간 => " + duration1);
		
		
		
		//재귀함수 factorial 수행 시간 측정
		long start2 = System.currentTimeMillis();
		
		ImplCalculator2 impl2 = new ImplCalculator2();
		impl2.factorial(10000);
		
		long end2 = System.currentTimeMillis();
		long duration2 = end2 - start2;

		System.out.println("결과를 구하기 까지 걸린 시간 => " + duration2);
				

	}

}
