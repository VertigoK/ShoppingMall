package shop.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.OrderListService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;
import shop.vo.OrderBean;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		ArrayList<OrderBean> orderList = null; 
		
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
			OrderListService orderListService = new OrderListService();
			orderList = orderListService.getOrderList(member.getMem_id());
			
			// 주문 내역이 없으면 "orderList" = null 전달
			req.setAttribute("orderList", orderList);
			
			forward = new ActionForward();
			forward.setPath("/shop/order_list.jsp");
		}
		
		return forward;
		
	}

}
