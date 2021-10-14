package shop.vo;

import java.util.Date;

public class MemberBean {
	
	private String mem_id;		// 회원 아이디 -> primary key
	private String mem_pw;		// 회원 비밀번호
	private String mem_name;	// 회원 이름
	private String mem_addr;	// 회원 주소
	private String mem_tel;		// 회원 전화번호
	private String mem_email;	// 회원 이메일 주소
	private int mem_pts;		// 회원 포인트
	private Date mem_date;		// 회원 가입일 (회원 가입시 자동 생성)
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public int getMem_pts() {
		return mem_pts;
	}

	public void setMem_pts(int mem_pts) {
		this.mem_pts = mem_pts;
	}

	public Date getMem_date() {
		return mem_date;
	}

	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}

	
	// toString() 메서드 재정의
	@Override
	public String toString() {
		return "Member [id=" + mem_id + ", password=" + mem_pw + ", name=" + mem_name + "]";
	}
	
}
