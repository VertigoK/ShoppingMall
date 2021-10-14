package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;
import shop.vo.OrderBean;
import shop.vo.ProductBean;

public class CancelOrderService {
	
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

	public boolean cancelOrder(MemberBean member, ProductBean product, OrderBean order) {
		
		// 1. orders 테이블에서 주문 삭제
		// 2. products 테이블 prd_stock 업데이트
		// 3. members 테이블 mem_pts 업데이트
		
		boolean isCancelSuccess = false;
		
		int deleteCount = 0;
		int updateStockCount = 0;
		int updatePointCount = 0;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		ArrayList<Integer> cancelResult = shopDAO.deleteOrder(member, product, order);
		deleteCount = cancelResult.get(0);
		updateStockCount = cancelResult.get(1);
		updatePointCount = cancelResult.get(2);
		
		if(deleteCount > 0 && updateStockCount > 0 && updatePointCount > 0) {
			commit(conn);
			isCancelSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isCancelSuccess;
		
	}

}
