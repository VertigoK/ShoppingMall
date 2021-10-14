package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;
import shop.vo.OrderBean;
import shop.vo.ProductBean;

public class ProductOrderService {

	public ProductBean getProduct(int prd_num) {
		
		ProductBean product = null;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		product = shopDAO.selectProduct(prd_num);
		close(conn);
		
		return product;
		
	}

	public OrderBean getOrder(int ord_num) {
		
		OrderBean order = null;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		order = shopDAO.selectOrder(ord_num);
		close(conn);
		
		return order;
		
	}
	
	public MemberBean getMember(String id) {
		
		MemberBean member = null;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		member = shopDAO.selectMember(id);
		close(conn);
		
		return member;
		
	}

	// 주문시 자동 생성된 주문 번호(ord_num)와 주문 성공 여부(isOrderSuccess) 리턴
	public List<Object> orderProduct(MemberBean member, ProductBean product, int ord_qty) {
		
		// 1. orders 테이블에 새 레코드 생성
		// 2. products 테이블 prd_stock 업데이트
		// 3. members 테이블 mem_pts 업데이트
		
		boolean isOrderSuccess = false;
		int ord_num = 0;
		int insertCount = 0;	
		int updateStockCount = 0;
		int updatePointCount = 0;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		ArrayList<Integer> orderResult = shopDAO.insertOrder(member, product, ord_qty);
		ord_num = orderResult.get(0);
		insertCount = orderResult.get(1);
		updateStockCount = orderResult.get(2);
		updatePointCount = orderResult.get(3);
		
		if(insertCount > 0 && updateStockCount > 0 && updatePointCount > 0) {
			commit(conn);
			isOrderSuccess = true;		
		} else {
			rollback(conn);
		}
		close(conn);
		
		return Arrays.asList(ord_num, isOrderSuccess);
		
	}

}
