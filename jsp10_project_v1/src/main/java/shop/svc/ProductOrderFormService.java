package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.ProductBean;

public class ProductOrderFormService {

	public ProductBean getProduct(int prd_num) {

		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		ProductBean product = shopDAO.selectProduct(prd_num);
		close(conn);
		return product;
		
	}

}
