/**
 * 	2018. 5. 15.  Dev Kim.J.H

 */
package chap09;

/**
 * @author Administrator
 *
 */
public class StringTest1 {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis(); //시작하는 시점 계산
		
		for(int i=1; i<1111111111; i++) {
			
		}
		
		//실행시간을 측정하고 싶은 코드 시작		
		String str =	"아름다운 이 땅에 금수강산에           "+
						"단군 할아버지가 터 잡으시고           "+
						"홍익인간 뜻으로 나라 세우니           "+
						"대대손손 훌륭한 인물도 많아           "+
						"고구려 세운 동명왕 백제 온조왕        "+
						"알에서 나온 혁거세                    "+
						"만주 벌판 달려라 광개토대왕           "+
						"신라 장군 이사부                      "+
						"백결선생 떡 방아                      "+
						"삼천 궁녀 의자왕                      "+
						"황산벌의 계백                         "+
						"맞서 싸운 관창                        "+
						"역사는 흐른다                         "+
						"말 목 자른 김유신 통일 문무왕         "+
						"원효대사 해골물 혜초 천축국           "+
						"바다의 왕자 장보고 발해 대조영        "+
						"귀주대첩 강감찬 서희 거란족           "+
						"무단 정치 정중부                      "+
						"화포 최무선 죽림칠현 김부식           "+
						"지눌국사 조계종 의천                  "+
						"천태종 대마도 정벌 이종무             "+
						"일편단심 정몽주                       "+
						"목화씨는 문익점                       "+
						"해동공자 최충                         "+
						"삼국유사 일연                         "+
						"역사는 흐른다                         ";
		//실행시간을 측정하고 싶은 코드 종료
		System.out.println(str);
		
		/*
		//실행시간을 측정하고 싶은 코드 시작	
		String 	str = "아름다운 이 땅에 금수강산에           ";
				str +="단군 할아버지가 터 잡으시고           ";
				str +="홍익인간 뜻으로 나라 세우니           ";
				str +="대대손손 훌륭한 인물도 많아           ";
				str +="고구려 세운 동명왕 백제 온조왕        ";
				str +="알에서 나온 혁거세                    ";
				str +="만주 벌판 달려라 광개토대왕           ";
				str +="신라 장군 이사부                      ";
				str +="백결선생 떡 방아                      ";
				str +="삼천 궁녀 의자왕                      ";
				str +="황산벌의 계백                         ";
				str +="맞서 싸운 관창                        ";
				str +="역사는 흐른다                         ";
				str +="말 목 자른 김유신 통일 문무왕         ";
				str +="원효대사 해골물 혜초 천축국           ";
				str +="바다의 왕자 장보고 발해 대조영        ";
				str +="귀주대첩 강감찬 서희 거란족           ";
				str +="무단 정치 정중부                      ";
				str +="화포 최무선 죽림칠현 김부식           ";
				str +="지눌국사 조계종 의천                  ";
				str +="천태종 대마도 정벌 이종무             ";
				str +="일편단심 정몽주                       ";
				str +="목화씨는 문익점                       ";
				str +="해동공자 최충                         ";
				str +="삼국유사 일연                         ";
				str +="역사는 흐른다                         ";
		//실행시간을 측정하고 싶은 코드 종료
		System.out.println(str);*/
		
		
		for(int i=1; i<1111111111; i++) {
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println( "실행시간:" + (end - start)/1000.0);
		
		
	}	

}
