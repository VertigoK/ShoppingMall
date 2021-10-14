package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;

public class SignUpService {

	public boolean isIdAlready(String id) {
		
		boolean isId = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		isId = shopDAO.isIdAlready(id);
		close(conn);
		
		return isId;
		
	}

	public boolean signUpMember(MemberBean member) {
	
		boolean isSignUpSuccess = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		int registerCount = shopDAO.registerMember(member);

		if(registerCount > 0) {
			commit(conn);
			isSignUpSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isSignUpSuccess;
		
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
