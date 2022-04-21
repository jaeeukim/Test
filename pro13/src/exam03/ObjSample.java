package exam03;

import java.io.Serializable;

public class ObjSample implements Serializable{ //직렬화하기 위해서 필요한것 
	//객체 -> 바이트 데이터 (직렬화) : 쓰는거
	//객체 <- 바이트 데이터 (역직렬화) : 읽는거
	private int num;
	private double point;
	private boolean ys;
	private String name;
	
	public ObjSample(int i, double d, boolean b, String s) {
		num = i;
		point = d;
		ys = b;
		name = s;
	}

	@Override
	public String toString() {
		return "ObjSample [num=" + num + ", point=" + point + ", ys=" + ys + ", name=" + name + "]";
	}
	
	
}
