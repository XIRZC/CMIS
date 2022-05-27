package vo;

public class Teacher {
	private String id;
	private String name;
	private String sex;
	private String title;
	private String pwd;
	public Teacher() {}
	public Teacher(String id,String name,String sex,String title,String pwd) {
		this.id=id;
		this.name=name;
		this.sex=sex;
		this.title=title;
		this.pwd=pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
