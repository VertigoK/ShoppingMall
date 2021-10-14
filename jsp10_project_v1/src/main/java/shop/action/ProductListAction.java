package shop.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.svc.ProductListService;
import shop.vo.ActionForward;
import shop.vo.PageInfo;
import shop.vo.ProductBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		
		int page = 1;
		int numOfPages = 10;	// number of pages to show at the bot
		tom page access
		int limit = 10;			// number of products to show per page	
		int sort = 0;			// ways of sorting products
		
		if(req.getParameter("page") != null ) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("limit") != null) limit = Integer.parseInt(req.getParameter("limit"));		
		if(req.getParameter("sort") != null) sort = Integer.parseInt(req.getParameter("sort"));

		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();
		productList = productListService.getProductList(page, limit, sort);
		
		// 총 페이지 수
		int totalPage = (int) Math.ceil((double) listCount / limit);
		
		// 현재 페이지 액세스에서 보여줄 시작 페이지 번호
		int startPage = ((int) Math.floor((double) (page-1) / numOfPages)) * numOfPages + 1;
		
		// 현재 페이지 액세스에서 보여줄 마지막 페이지 번호
		int endPage = startPage + numOfPages - 1;
		if(endPage > totalPage) endPage = totalPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setNumOfPages(numOfPages);
		pageInfo.setLimit(limit);
		pageInfo.setSort(sort);
		
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("productList", productList);

		ActionForward forward = new ActionForward();
		forward.setPath("/shop/product_list.jsp");
		return forward;
		
	}

}
