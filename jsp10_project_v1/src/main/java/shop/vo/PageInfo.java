package shop.vo;

public class PageInfo {
	
	private int page;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int listCount;
	
	private int numOfPages;	// number of pages to show at the bottom page access
	private int limit;		// number of products to show per page
	private int sort;		// ways of sorting products
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getListCount() {
		return listCount;
	}
	
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	public int getNumOfPages() {
		return numOfPages;
	}
	
	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
