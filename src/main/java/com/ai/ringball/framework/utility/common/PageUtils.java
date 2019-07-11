package com.ai.ringball.framework.utility.common;


import com.ai.ringball.framework.constants.SysConstants;

public class PageUtils {

	private int pageSize = 10;

	private int pageNo = 1;

	private String pagename = SysConstants.CONSTANT_NULL_STRING;

	private int pageCount = 0;

	private String page = "";

	private String rows = "";

	private String sort = "";

	private String sortOrder = "";

	public String getPage() {

		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean hasNext() {
		if (pageNo < getPageNum()) {
			return true;
		}
		return false;
	}

	public int getPageNum() {
		return (pageCount / pageSize + (pageCount % pageSize == 0 ? 0 : 1));
	}

	public int getPageSize() {
		if (!"".equals(rows)) {
			pageSize = Integer.valueOf(rows);
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		if (!"".equals(page)) {
			pageNo = Integer.valueOf(page);
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageStartNum() {
		return (pageNo - 1) * pageSize;
	}

	public Object getPageEndNum() {
		// return pageSize;
		return pageSize * pageNo + 1;
	}

	/*
	 * public String toString(String type){ boolean isC = "CN".equals(type);
	 * String returnString =SysConstants.CONSTANT_NULL_STRING; returnString +=
	 * (pageNo >1?"<a href='"
	 * +pagename+"pageNo="+(pageNo-1)+"'>"+(isC?"上一页":"Previous"
	 * )+"</a>&nbsp;&nbsp;":SysConstants.CONSTANT_NULL_STRING)+
	 * (isC?("&nbsp;&nbsp;第"
	 * +pageNo+"页/共"+getPageNum()+"页&nbsp;&nbsp;共&nbsp;"+pageCount
	 * +"&nbsp;个产品&nbsp;&nbsp;"):("&nbsp;PAGE&nbsp;"+pageNo+"/"+getPageNum()))+
	 * ((pageCount>pageNo*pageSize)?"<a href='"+pagename+"pageNo="+(pageNo+1)
	 * +"'>"
	 * +(isC?"下一页":"Next")+"</a>&nbsp;&nbsp;":SysConstants.CONSTANT_NULL_STRING
	 * );
	 * 
	 * returnString += "<select onchange='window.location=this.value'>"; for(int
	 * i=1;i<=getPageNum();i++){ returnString +="<option "
	 * +(pageNo==i?"selected": SysConstants.CONSTANT_NULL_STRING)+" value='"
	 * +pagename+"pageNo="+i+"'>"+i+"</option>"; }
	 * 
	 * returnString += "</select>"; return returnString;
	 * 
	 * }
	 */

}
