package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.vo.ActionForward;
import shop.vo.MemberBean;

public class LogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		HttpSession session = req.getSession();
		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		
		if(member == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 되지 않았습니다. 로그인 후에 로그아웃을 할 수 있습니다!');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			session.invalidate();  // logiInfo와 orderInfo를 가지고 있는 session 종료
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/");
		}
		
		return forward;
		
	}

}
