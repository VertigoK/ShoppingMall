package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;

public class DeleteAccountService {

	public boolean isPasswordCorrect(String id, String pw ) {
			
			boolean isPassword = false;
			
			Connection conn = getConnection();
			ShopDAO shopDAO = ShopDAO.getInstance();
			shopDAO.setConnection(conn);
			isPassword = shopDAO.isPasswordRight(id, pw);
			close(conn);
			
			return isPassword;
			
	}
	
	public boolean deleteAccount(String id) {
		
		boolean isDeleteSuccess = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		int deleteCount = shopDAO.deleteMember(id);
		
		if(deleteCount > 0) {
			commit(conn);
			isDeleteSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isDeleteSuccess;
		
	}
	
}
