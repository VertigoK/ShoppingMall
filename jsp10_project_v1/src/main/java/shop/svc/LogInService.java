package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;

public class LogInService {
	
	public boolean isIdAlready(String id) {
		
		boolean isId = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		isId = shopDAO.isIdAlready(id);
		close(conn);
		
		return isId;
		
	}
	
	public boolean isPasswordCorrect(String id, String pw ) {
		
		boolean isPassword = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		isPassword = shopDAO.isPasswordRight(id, pw);
		close(conn);
		
		return isPassword;
		
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

}
