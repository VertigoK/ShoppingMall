package shop.svc;

import java.sql.Connection;
import java.util.ArrayList;

import shop.dao.ShopDAO;
import shop.vo.ProductBean;

import static db.JDBCUtility.*;

public class ProductListService {

	public int getListCount() {
		
		int listCount = 0;
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		listCount = shopDAO.selectListCount();
		close(conn);
		return listCount;
		
	}

	public ArrayList<ProductBean> getProductList(int page, int limit, int sort) {
		
		ArrayList<ProductBean> productList = null;
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		productList = shopDAO.selectProductList(page, limit, sort);
		close(conn);
		return productList;
		
	}

}
