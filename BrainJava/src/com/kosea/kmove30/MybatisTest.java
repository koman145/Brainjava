package com.kosea.kmove30;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();

			try {
				
				///////////////////SELECT//////////////////////
				/*
				Member member = new Member();
				member = session.selectOne("org.mybatis.example.SelectMapper.selectMember", 101);
				System.out.println("회원 번호 : " + member.getMno());
				System.out.println("회원 아이디 : " + member.getId());
				System.out.println("회원 비밀번호 : " + member.getPw());
				*/

				///////////////////DELETE//////////////////////
				/*
				int deleteCount = session.delete("org.mybatis.example.SelectMapper.deleteMember", 110);
				System.out.println("삭제 건수  : " + deleteCount);
				*/
				
				///////////////////INSERT//////////////////////
				//Member 클래스에 다중연산자가 정의되어있지 않다면 아래와 같이 하나씩 모두 대입해 주어야 함
				//Member newMember = new Member();
				//newMember.setId("testID");
				//newMember.setPw("123333");
				//newMember.setMno(102);
				/*
				Member newMember = new Member(101, "kosea", "12345");
				int insertCount = session.insert("org.mybatis.example.SelectMapper.insertMember", newMember);
				System.out.println("삽입 건수 : " + insertCount);
				*/
				
				///////////////////UPDATE//////////////////////
				Member member = new Member(102 , "kosea", "12345");
				int updateCount = session.update("org.mybatis.example.SelectMapper.updateMember", member);
				System.out.println("수정건수 : " + updateCount);
				

			} finally {
				session.commit(); // eclipse에서 delete문 실행시 Commit을 따로 해줘야함. querybox 등 에서 실행하면 AutoCommit 이 되어있기 때문에 해줄 필요가 없음
				session.close();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
