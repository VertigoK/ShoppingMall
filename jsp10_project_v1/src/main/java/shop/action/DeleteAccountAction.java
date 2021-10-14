package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.DeleteAccountService;
import shop.vo.ActionForward;

public class DeleteAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		if(req.getParameter("id") == null || req.getParameter("pw") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			DeleteAccountService deleteAccountService = new DeleteAccountService();
			boolean isPassword = deleteAccountService.isPasswordCorrect(id, pw);
			
			if(!isPassword) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다. 다시 입력하세요!');");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				boolean isDeleteSuccess = deleteAccountService.deleteAccount(id);
				if(!isDeleteSuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('회원 탈퇴에 실패했습니다!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					HttpSession session = req.getSession();
					session.invalidate();	// logiInfo와 orderInfo를 가지고 있는 session도 함께 종료
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("/misc/delete_success.jsp");
				}
			}
		}

		return forward;
		
	}

}
