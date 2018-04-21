package model;

public class ComReqModel {
	private String isGetCount;
	private String pageIndex;
	private String pageSize;
	
	public ComReqModel() {
		
	}
	
	public String getIsGetCount() {
		return this.isGetCount;
	}
	
	public void setIsGetCount(String isGetCount) {
		this.isGetCount = isGetCount;
	}
	
	public String getPageIndex() {
		return this.pageIndex;
	}
	
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getPageSize() {
		return this.pageSize;
	}
	
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
