package chap09;

public class StringBuilderExample1 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("역사를 하노라고 맨땅을 파다가 ");
		sb.ensureCapacity(50);
		
		sb.capacity();
		
		System.out.println(sb.capacity());
				
		System.out.println(sb);
		System.out.println(sb.append("커다란 고인돌을 끄집어 내어놓고 보니"));
		
		System.out.println(sb.capacity());
			
		System.out.println(sb.insert(26,"하나"));
		System.out.println(sb.delete(21, 23)); //21번째 인덱스부터 23-1번째 인덱스까지
		
		sb.replace(21, 22, "고인돌");
		
		System.out.println(sb);
		System.out.println(sb.deleteCharAt(9));
		
		sb.reverse();
		System.out.println(sb);
		
		sb.reverse();
		System.out.println(sb);
		
		System.out.println(sb.capacity());
		
		sb.trimToSize();
		
		System.out.println(sb.capacity());
	}
}
