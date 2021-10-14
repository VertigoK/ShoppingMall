package shop.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.svc.ProductDetailService;
import shop.vo.ActionForward;
import shop.vo.ProductBean;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		if(req.getParameter("prd_num") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			int prd_num = Integer.parseInt(req.getParameter("prd_num"));
			ProductDetailService productDetailService = new ProductDetailService();
			ProductBean product = productDetailService.getProduct(prd_num); 
			req.setAttribute("product", product);
			
			forward = new ActionForward();
			forward.setPath("/shop/product_detail.jsp");
		}
		
		return forward;
		
	}

}
