package shop.svc;

import java.sql.Connection;
import java.util.ArrayList;

import shop.dao.ShopDAO;
import shop.vo.OrderBean;

import static db.JDBCUtility.*;

public class OrderListService {

	public ArrayList<OrderBean> getOrderList(String mem_id) {
		
		ArrayList<OrderBean> orderList = null;
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		orderList = shopDAO.selectOrderList(mem_id);
		close(conn);
		return orderList;
		
	}

}
