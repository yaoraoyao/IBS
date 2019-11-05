package cn.itsource.ibs.query;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseQuery<T> {

	//当前显示第几页 默认显示第一页【主要用来接收前端传递的请求参数】
	private Integer pageNo = 1;
	
	//每页显示多少行，默认10行【主要用来接收前端传递的请求参数】
	private Integer pageSize = 10;

	//排序方式
	private String orderType = "DESC";

	//排序的属性名称
	private String orderProperty = "id";
	
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	/**
	 * SpringDataJPA的分页的当前页码是从0开始的
	 * @return
	 */
	public Integer getJpaPageNo(){
		return pageNo-1;
	}


	public abstract Specification<T> getSpecification();
	public abstract Pageable getPageable();
	public abstract Sort getSort();

}
