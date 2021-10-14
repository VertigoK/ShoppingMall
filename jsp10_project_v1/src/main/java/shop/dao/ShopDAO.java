package shop.dao;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shop.vo.MemberBean;
import shop.vo.OrderBean;
import shop.vo.ProductBean;

public class ShopDAO {

	// Singleton Pattern
	private ShopDAO() {}
	private static ShopDAO shopDAO;
	public static ShopDAO getInstance() {
		if(shopDAO == null) shopDAO = new ShopDAO();
		return shopDAO;
	}
	
	// DB Connection
	Connection conn = null;
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	// 로그인 ID 존재 여부 확인
	public boolean isIdAlready(String id) {

		boolean isId = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) isId = true;
		} catch (SQLException e) {
			System.out.println("아이디 존재 여부 확인 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return isId;
		
	}
	
	// 로그인 PASSWORD 매칭 확인
	public boolean isPasswordRight(String id, String pw) {
		
		boolean isPassword = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {		// ID가 존재하는 경우
				if(pw.equals(rs.getString("mem_pw"))) isPassword = true;
			}
		} catch (SQLException e) {
			System.out.println("비밀번호 매칭 확인 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return isPassword;
		
	}
	
	// 회원 정보 보기 
	public MemberBean selectMember(String id) {
		
		MemberBean member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new MemberBean();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pw(rs.getString("mem_pw"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_addr(rs.getString("mem_addr"));
				member.setMem_tel(rs.getString("mem_tel"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_pts(rs.getInt("mem_pts"));
				member.setMem_date(rs.getTimestamp("mem_date"));
			}
		} catch (SQLException e) {
			System.out.println("회원 정보 보기 실패! " + e.getMessage());
		}
		
		return member;
		
	}
	
	// 회원 정보 수정 
	public int updateMember(MemberBean member) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update members set mem_pw = ?, mem_name = ?, mem_addr = ? " +
					 ", mem_tel = ?, mem_email = ? where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMem_pw());
			pstmt.setString(2, member.getMem_name());
			pstmt.setString(3, member.getMem_addr());
			pstmt.setString(4, member.getMem_tel());
			pstmt.setString(5, member.getMem_email());
			pstmt.setString(6, member.getMem_id());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원 정보 수정 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
				
		return updateCount;
		
	}

	// 전체 제품 갯수 구하기
	public int selectListCount() {
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from products";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("전체 제품 갯수 가져오기 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return listCount;
		
	}

	// 제품 목록 조회
	public ArrayList<ProductBean> selectProductList(int page, int limit, int sort) {
		
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		ProductBean product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from products order by prd_num limit ?, " + limit;	// default (sort=0)
		switch(sort) {
			case 1: sql = "select * from products order by prd_price desc limit ?, " + limit; break;
			case 2: sql = "select * from products order by prd_price limit ?, " + limit; break;
			case 3: sql = "select * from products order by prd_stock desc limit ?, " + limit; break;
			case 4: sql = "select * from products order by prd_stock limit ?, " + limit; break;
		}
		
		int startRow = (page - 1) * limit;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductBean();
				product.setPrd_num(rs.getInt("prd_num"));
				product.setPrd_price(rs.getInt("prd_price"));
				product.setPrd_stock(rs.getInt("prd_stock"));
				product.setPrd_name(rs.getString("prd_name"));
				product.setPrd_kind(rs.getString("prd_kind"));
				product.setPrd_com(rs.getString("prd_com"));
				product.setPrd_date(rs.getTimestamp("prd_date"));
				productList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("제품 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productList;
		
	}

	// 제품 정보 조회
	public ProductBean selectProduct(int prd_num) {

		ProductBean product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from products where prd_num = " + prd_num;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = new ProductBean();
				product.setPrd_num(rs.getInt("prd_num"));
				product.setPrd_price(rs.getInt("prd_price"));
				product.setPrd_stock(rs.getInt("prd_stock"));
				product.setPrd_name(rs.getString("prd_name"));
				product.setPrd_kind(rs.getString("prd_kind"));
				product.setPrd_com(rs.getString("prd_com"));
				product.setPrd_date(rs.getTimestamp("prd_date"));
			}
		} catch (SQLException e) {
			System.out.println("제품 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return product;
		
	}

	// 주문 정보 조회
	public OrderBean selectOrder(int ord_num) {
		
		OrderBean order = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from orders where ord_num = " + ord_num;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				order = new OrderBean();
				order.setOrd_num(rs.getInt("ord_num"));
				order.setOrd_qty(rs.getInt("ord_qty"));
				order.setOrd_cost(rs.getInt("ord_cost"));
				order.setOrd_prd_num(rs.getInt("ord_prd_num"));
				order.setOrd_mem_id(rs.getString("ord_mem_id"));
				order.setOrd_date(rs.getTimestamp("ord_date"));
			}
		} catch (SQLException e) {
			System.out.println("주문 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return order;
		
	}
	
	// 주문 목록 조회
	public ArrayList<OrderBean> selectOrderList(String mem_id) {
		
		ArrayList<OrderBean> orderList = null;
		OrderBean order = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from orders where ord_mem_id = ? order by ord_num desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0)	{
					orderList = new ArrayList<OrderBean>();	// 주문 내역이 없으면 orderList = null 리턴
					i++;
				}
				order = new OrderBean();
				order.setOrd_num(rs.getInt("ord_num"));
				order.setOrd_qty(rs.getInt("ord_qty"));
				order.setOrd_cost(rs.getInt("ord_cost"));
				order.setOrd_prd_num(rs.getInt("ord_prd_num"));
				order.setOrd_mem_id(rs.getString("ord_mem_id"));
				order.setOrd_date(rs.getTimestamp("ord_date"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			System.out.println("주문 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return orderList;
		
	}
	
	// 주문 삭제 (orders, products, members 테이블 모두 바뀜)
	public ArrayList<Integer> deleteOrder(MemberBean member, ProductBean product, OrderBean order) {

		ArrayList<Integer> cancelResult = new ArrayList<Integer>();
		
		int deleteCount = 0;
		int updateStockCount = 0;
		int updatePointCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from orders where ord_num = ?";
		
		try {
			
			// 1. members 테이블의 현재 회원 포인트에서 취소된 구매 가격의 5%만큼 빼기
			int new_mem_pts = member.getMem_pts() - (int) (order.getOrd_cost() * 0.05);
			updatePointCount = updateMemberPoint(member.getMem_id(), new_mem_pts);
			
			// 2. products 테이블의 현재 재고량에 취소된 주문 수량만큼 더하기
			int new_prd_stock = product.getPrd_stock() + order.getOrd_qty();
			updateStockCount = updateProductStock(product.getPrd_num(), new_prd_stock);
			
			// 3. orders 테이블에서 주문 삭제 -> 1 & 2에서 order 정보가 이용되므로 가장 나중에 삭제
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getOrd_num());
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("주문 삭제 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		cancelResult.add(deleteCount);
		cancelResult.add(updateStockCount);
		cancelResult.add(updatePointCount);
		
		return cancelResult;
		
	}

	// 주문 등록 (orders, products, members 테이블 모두 바뀜)
	public ArrayList<Integer> insertOrder(MemberBean member, ProductBean product, int ord_qty) {
		
		ArrayList<Integer> orderResult = new ArrayList<Integer>();
		
		int ord_num = 0;
		int insertCount = 0;
		int updateStockCount = 0;
		int updatePointCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select max(ord_num) from orders";
		String sql2 = "insert into orders values(?,?,?,?,?,now())";
		
		try {
			
			// 1. 자동 생성된 주문 번호 가져오기 (ord_num 리턴)
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) ord_num = rs.getInt(1) + 1;
			else ord_num = 1;
			
			// 2. orders 테이블에 주문 등록하기 (insertCount 리턴)
			int ord_cost = product.getPrd_price() * ord_qty;
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, ord_num);
			pstmt.setInt(2, ord_qty);
			pstmt.setInt(3, ord_cost);
			pstmt.setInt(4, product.getPrd_num());
			pstmt.setString(5, member.getMem_id());
			insertCount = pstmt.executeUpdate();

			// 3. products 테이블의 현재 재고량에서 주문 수량만큼 빼기 (updateStockCount 리턴)
			int new_prd_stock = product.getPrd_stock() - ord_qty;
			updateStockCount = updateProductStock(product.getPrd_num(), new_prd_stock);

			// 4. members 테이블의 현재 회원 포인트에 구매 가격의 5% 적립하기 (updatePointCount 리턴)
			int new_mem_pts = member.getMem_pts() + (int) (ord_cost * 0.05);
			updatePointCount = updateMemberPoint(member.getMem_id(), new_mem_pts);
			
		} catch (SQLException e) {
			System.out.println("주문 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		orderResult.add(ord_num);
		orderResult.add(insertCount);
		orderResult.add(updateStockCount);
		orderResult.add(updatePointCount);
		
		return orderResult;
		
	}

	// 주문 등록/삭제시 제품 재고량 업데이트
	private int updateProductStock(int prd_num, int new_prd_stock) {

		int updateStockCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update products set prd_stock = ? where prd_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, new_prd_stock);
			pstmt.setInt(2, prd_num);
			updateStockCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("제품 재고량 업데이트 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}

		return updateStockCount;
		
	}
	
	// 주문 등록/삭제시 회원 포인트 업데이트
	private int updateMemberPoint(String mem_id, int new_mem_pts) {
		
		int updatePointCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update members set mem_pts = ? where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, new_mem_pts);
			pstmt.setString(2, mem_id);
			updatePointCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원 포인트 업데이트 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updatePointCount;
		
	}

	// 회원 등록
	public int registerMember(MemberBean member) {
		
		int registerCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into members values(?,?,?,?,?,?,?,now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pw());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_addr());
			pstmt.setString(5, member.getMem_tel());
			pstmt.setString(6, member.getMem_email());
			pstmt.setInt(7, 10000);			// 회원 가입시 포인트 10000 적립
			registerCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}

		return registerCount;
		
	}

	// 회원 삭제
	public int deleteMember(String id) {
		
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "delete from members where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원 삭제 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
		
	}

}
