package chap09;

public class Exercise9_2 {
	
	public static void main(String args[]) {
		
		String str1 = "Java Programing";
		int index = str1.lastIndexOf("P", 2);
		
		if(index < 0)
			System.out.println("검색 결과가 없습니다.");
		else
			System.out.println(index + " 인덱스에 위치하고 있습니다.");	
		
		
		String str = "꽃나무는 제가 생각하는 꽃나무를 " + 
					 "열심으로 생각하는 것처럼 열심으로 꽃을 피워가지고 섰소";
		System.out.println(str.indexOf('꽃'));
		System.out.println(str.indexOf('꽃',3));
		System.out.println(str.indexOf("꽃나무"));
		System.out.println(str.indexOf("꽃나무", 3));
		System.out.println(str.lastIndexOf('꽃'));
		System.out.println(str.lastIndexOf('꽃', 20));
		System.out.println(str.lastIndexOf("꽃나무"));
		System.out.println(str.lastIndexOf("꽃나무", 20));
		
	}

}