package model;

public class User {

	private long _id;
	private String _name;
	private int _age;
	
	public User(long id,String name,int age) {
		_id = id;
		_name = name;
		_age = age;
	}
	
	public long getId() {
		return _id;
	}
	
	public void setId(long value) {
		_id = value;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String value) {
		_name = value;
	}
	
	public int getAge() {
		return _age;
	}
	
	public void setAge(int value) {
		_age = value;
	}
}
