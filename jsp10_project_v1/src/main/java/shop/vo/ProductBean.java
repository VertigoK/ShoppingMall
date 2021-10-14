package shop.vo;

import java.util.Date;

public class ProductBean {
	
	private int prd_num;		// 제품 번호 -> primary key
	private int prd_price;		// 제품 가격
	private int prd_stock;		// 제품 재고량
	private String prd_name;	// 제품 이름
	private String prd_kind;	// 제품 종류
	private String prd_com;		// 제품 제조회사
	private Date prd_date;		// 제품 제조일자 (테스트 용도로 DB에서 자동 생성함)

	public int getPrd_num() {
		return prd_num;
	}

	public void setPrd_num(int prd_num) {
		this.prd_num = prd_num;
	}

	public int getPrd_price() {
		return prd_price;
	}

	public void setPrd_price(int prd_price) {
		this.prd_price = prd_price;
	}

	public int getPrd_stock() {
		return prd_stock;
	}

	public void setPrd_stock(int prd_stock) {
		this.prd_stock = prd_stock;
	}

	public String getPrd_name() {
		return prd_name;
	}

	public void setPrd_name(String prd_name) {
		this.prd_name = prd_name;
	}
	
	public String getPrd_kind() {
		return prd_kind;
	}

	public void setPrd_kind(String prd_kind) {
		this.prd_kind = prd_kind;
	}

	public String getPrd_com() {
		return prd_com;
	}

	public void setPrd_com(String prd_com) {
		this.prd_com = prd_com;
	}

	public Date getPrd_date() {
		return prd_date;
	}

	public void setPrd_date(Date prd_date) {
		this.prd_date = prd_date;
	}

	// toString() 메서드 재정의
	@Override
	public String toString() {
		return "Product [num=" + prd_num + ", name=" + prd_name + ", price=" + prd_price + ", stock=" + prd_stock + "]";
	}

}
