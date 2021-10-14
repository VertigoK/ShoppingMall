package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.svc.ProductOrderFormService;
import shop.vo.ActionForward;
import shop.vo.MemberBean;
import shop.vo.ProductBean;

public class ProductOrderFormAction implements Action {

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
			if(req.getParameter("prd_num") == null) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('정상적인 접근이 아닙니다!');");
				out.println("history.go(-1)");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				int prd_num = Integer.parseInt(req.getParameter("prd_num"));
				ProductOrderFormService productOrderFormService = new ProductOrderFormService();
				ProductBean product = productOrderFormService.getProduct(prd_num);
				req.setAttribute("product", product);
				
				forward = new ActionForward();
				forward.setPath("/shop/product_order.jsp");
			}
		}
		
		return forward;
		
	}

}
