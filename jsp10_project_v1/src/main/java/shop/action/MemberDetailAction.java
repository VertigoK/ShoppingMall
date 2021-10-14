package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.MemberDetailService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		HttpSession session = req.getSession();
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		
		if(member == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인 상태가 아닙니다. 로그인 후 이용하세요!');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			MemberDetailService memberDetailService = new MemberDetailService();
			member = memberDetailService.getMember(member.getMem_id());
			session.setAttribute("loginInfo", member);	// Update session to remove data discrepancies. 
			
			forward = new ActionForward();
			forward.setPath("/shop/member_detail.jsp");
		}

		return forward;
		
	}

}
