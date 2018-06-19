
public class Test {
	public static void main(String args[]) {
		
		/*	분모가  1씩 증가하며 더해짐
		double sum = 0;
		double i;
		for (i = 1; i <= 100; i++) {
			sum = sum + (1 / i);
		}
		System.out.println(i);
		System.out.prsntln(sum);
		*/
		
		int i=0, sum=0, sw = 0;
		
		while ( i<100) {
			i++;
			if(sw==0) {
				sum= sum +i;
				sw=1;
			}
			else {
				sum=sum-i;
				sw=0;
			}
		}
		System.out.println(sum);
	}
}
