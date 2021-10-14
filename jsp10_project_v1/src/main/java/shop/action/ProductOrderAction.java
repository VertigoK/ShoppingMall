package shop.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.ProductOrderService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;
import shop.vo.OrderBean;
import shop.vo.ProductBean;

public class ProductOrderAction implements Action {

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
		} else if(req.getParameter("prd_stock") == null
				|| req.getParameter("ord_qty") == null
				|| req.getParameter("prd_num") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			int prd_stock = Integer.parseInt(req.getParameter("prd_stock"));
			int ord_qty = Integer.parseInt(req.getParameter("ord_qty"));
			
			if(ord_qty > prd_stock) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('주문 수량이 재고량보다 많습니다. 재고량 확인 후 다시 주문하세요!');");
				out.println("history.go(-1)");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				int ord_num = 0;
				boolean isOrderSuccess = false;
				
				int prd_num = Integer.parseInt(req.getParameter("prd_num"));
				ProductOrderService productOrderService = new ProductOrderService();
				ProductBean product = productOrderService.getProduct(prd_num);
				
				List<Object> orderResult = productOrderService.orderProduct(member, product, ord_qty);
				ord_num = (int) orderResult.get(0);
				isOrderSuccess = (boolean) orderResult.get(1);
				
				if(!isOrderSuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('제품 주문에 실패했습니다!!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					// 기존 loginInfo session 업데이트!
					member = productOrderService.getMember(member.getMem_id());
					session.setAttribute("loginInfo", member); 				// session!!
					
					// 신규 orderInfo session 생성!
					OrderBean order = productOrderService.getOrder(ord_num);
					session.setAttribute("orderInfo", order);				// session!!
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("/misc/order_success.jsp");
				}
			}
		}

		return forward;
		
	}

}
