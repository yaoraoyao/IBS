package cn.itsource.ibs.utils;

import java.util.List;

public class MyPage<T> {

	//当前显示第几页 默认显示第一页【主要用来接收前端传递的请求参数】
	private Integer pageNo = 1;
	
	//每页显示多少行，默认10行【主要用来接收前端传递的请求参数】
	private Integer pageSize = 10;
	
	//总共有多少行
	private Long total;
	
	//当前页的数据集合
	private List<T> rows;
	
	//总共有多少页
	private Long totalPage;
	
	//上一页的页码
	private Integer prev;
	
	//下一页的页码
	private Integer next;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPrev() {
		return prev;
	}

	public void setPrev(Integer prev) {
		this.prev = prev;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public MyPage() {
	}

	public MyPage(Integer pageNo, Integer pageSize, Long total, List<T> rows) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
		//总页数计算出来
		this.totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		//上一页的页码页是计算出来
		this.prev = pageNo == 1 ? 1 : pageNo - 1;
		//下一页的页码页是计算出来
		this.next = pageNo == totalPage.intValue() ? totalPage.intValue() : pageNo + 1;
	}
	
}
