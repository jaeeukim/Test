package exam4;

public class Sample05 {

	public static void main(String[] args) {
		// 복합 대입 연산
		int num1 = 1;
		
		num1 += 1; //2
		System.out.println(num1);
		
		num1 += 2; //4
		System.out.println(num1);
		
		num1 -= 3; //1
		System.out.println(num1);
		
		num1 *= 4; //4
		System.out.println(num1);
		
		num1 /= 2; //2
		System.out.println(num1);
		
		num1 %=2; //0
		System.out.println(num1);
		
		int age = 26;
		
		String result = age >= 20 ? "성인" : "청소년";
		System.out.println(result);
		
		int num2 = 15;
		result = num2 % 2 ==0 ? "짝수" : "홀수";
		
		System.out.printf("%d는 %s입니다.", num2, result);
	}

}
