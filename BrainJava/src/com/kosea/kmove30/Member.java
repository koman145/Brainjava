package com.kosea.kmove30;

public class Member {

	int mno;
	String id;
	String pw;
	
	public  Member() {
		
	}

	public Member(int mno, String id, String pw) {
		super();
		this.mno = mno;
		this.id = id;
		this.pw = pw;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
