package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.MemberModifyService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;

public class MemberModifyAction implements Action {

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
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			member = new MemberBean();
			boolean isModifySuccess = false;
			
			member.setMem_id(req.getParameter("id"));
			member.setMem_pw(req.getParameter("pw"));
			member.setMem_name(req.getParameter("name"));
			member.setMem_addr(req.getParameter("addr"));
			member.setMem_tel(req.getParameter("tel"));
			member.setMem_email(req.getParameter("email"));
			
			MemberModifyService memberModifyService = new MemberModifyService();
			isModifySuccess = memberModifyService.modifyMember(member);
			
			if(!isModifySuccess) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('회원 정보 수정에 실패했습니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				member = memberModifyService.getMember(req.getParameter("id"));
				session.setAttribute("loginInfo", member);		// session 임에 주의하자!!!
		
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("/memberDetail.sh");
			}
		}
		
		return forward;
		
	}

}
