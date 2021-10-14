package shop.vo;

import java.util.Date;

public class OrderBean {

	private int ord_num;		// 주문 번호 (주문시 자동 생성) -> primary key
	private int ord_qty;		// 주문 수량
	private int ord_cost;		// 주문 비용
	private int ord_prd_num;	// 주문 제품 번호 -> foreign key referencing products(prd_num)
	private String ord_mem_id;	// 주문 회원 아이디 -> foreign key referencing members(mem_id)  
	private Date ord_date;		// 주문 일자 (주문시 자동 생성)

	public int getOrd_num() {
		return ord_num;
	}
	
	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}
	
	public int getOrd_qty() {
		return ord_qty;
	}
	
	public void setOrd_qty(int ord_qty) {
		this.ord_qty = ord_qty;
	}
	
	public int getOrd_cost() {
		return ord_cost;
	}

	public void setOrd_cost(int ord_cost) {
		this.ord_cost = ord_cost;
	}
	
	public int getOrd_prd_num() {
		return ord_prd_num;
	}
	
	public void setOrd_prd_num(int ord_prd_num) {
		this.ord_prd_num = ord_prd_num;
	}
	
	public String getOrd_mem_id() {
		return ord_mem_id;
	}
	
	public void setOrd_mem_id(String ord_mem_id) {
		this.ord_mem_id = ord_mem_id;
	}
	
	public Date getOrd_date() {
		return ord_date;
	}
	
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	
	// toString() 메서드 재정의
	@Override
	public String toString() {
		return "Orders [num=" + ord_num + ", who=" + ord_mem_id +  ", what=" + ord_prd_num + ", when=" + ord_date + "]";
	}

}
