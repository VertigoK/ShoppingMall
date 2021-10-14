package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;

public class MemberModifyService {

	public boolean modifyMember(MemberBean member) {
		
		boolean isModifySuccess = false;
		
		Connection conn = getConnection();
		ShopDAO shopDAO = ShopDAO.getInstance();
		shopDAO.setConnection(conn);
		int updateCount = shopDAO.updateMember(member);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isModifySuccess;
		
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
