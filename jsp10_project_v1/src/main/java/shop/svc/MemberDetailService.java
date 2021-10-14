package shop.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import shop.dao.ShopDAO;
import shop.vo.MemberBean;

public class MemberDetailService {

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
