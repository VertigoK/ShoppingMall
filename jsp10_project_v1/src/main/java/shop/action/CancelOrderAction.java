package shop.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.CancelOrderService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;
import shop.vo.OrderBean;
import shop.vo.ProductBean;

public class CancelOrderAction implements Action {

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
		} else if(req.getParameter("ord_num") == null || req.getParameter("prd_num") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			boolean isCancelSuccess = false;
			
			int ord_num = Integer.parseInt(req.getParameter("ord_num"));
			int prd_num = Integer.parseInt(req.getParameter("prd_num"));
			CancelOrderService cancelOrderService = new CancelOrderService();
			OrderBean order = cancelOrderService.getOrder(ord_num);
			ProductBean product = cancelOrderService.getProduct(prd_num);
			
			// 주문 후 30분 이상 경과하면 주문 취소 불가
			Date date = new Date(System.currentTimeMillis());		
			long differenceInTime = date.getTime() - order.getOrd_date().getTime();
			int differenceInMinutes = (int) (differenceInTime / 1000 / 60);
			
			if(differenceInMinutes >= 30) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('주문 후 30분 이상 경과하여 주문을 취소할 수 없습니다!');");
				out.println("history.go(-1)");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				isCancelSuccess = cancelOrderService.cancelOrder(member, product, order);
				
				if(!isCancelSuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('주문 취소에 실패했습니다!!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					// 기존 loginInfo session 업데이트!
					member = cancelOrderService.getMember(member.getMem_id());
					session.setAttribute("loginInfo", member); 				// session!!
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("/misc/cancel_success.jsp");				
				}
			}	
		}
		
		return forward;
		
	}

}
