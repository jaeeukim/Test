package exam10;

public class Main {

	public static void main(String[] args) {
		Operator op = new Calculator();
		
		System.out.println(op.add(4, 2));
		System.out.println(op.sub(4, 2));
		System.out.println(op.mul(4, 2));
		System.out.println(op.div(4, 2));
		
//	    =======================================
		Operator op2 = new EngineerCal();
		
		System.out.println(op.add(4, 2));
		System.out.println(op.sub(4, 2));
		System.out.println(op.mul(4, 2));
		System.out.println(op.div(4, 2));

		Engineer eng = (Engineer)op2;
		System.out.println(eng.mod(4, 2));
		System.out.println(eng.sqrd(4, 2));
		System.out.println(eng.abs(-4));
		
		//========================================
		Operator op3 = new ProgrammerCal();
		System.out.println(op.add(4, 2));
		System.out.println(op.sub(4, 2));
		System.out.println(op.mul(4, 2));
		System.out.println(op.div(4, 2));
		
		Programmer pro = (Programmer)op3;
		System.out.println(pro.binary(100));
		System.out.println(pro.octal(100));
		System.out.println(pro.hexa(100));
	}

}
