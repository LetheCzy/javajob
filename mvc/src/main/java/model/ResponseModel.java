package model;

public class ResponseModel<T extends Object>  {

	private T data;
	private int totalCount;
	
	public ResponseModel(T data){
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public int getTotalCount() {
		return this.totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
