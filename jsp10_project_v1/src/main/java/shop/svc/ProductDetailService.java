package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.ProductBean;

public class ProductDetailService {

	public ProductBean getProduct(int prd_num) {

		ProductBean product = null;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		product = shopDAO.selectProduct(prd_num);
		close(conn);
		
		return product;
		
	}

}
