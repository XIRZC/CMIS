package vo;

public class weekcourse {
	private int secno;
	private String secname;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
	public void setWeekCourse(int weekdayno,String course) {
		switch(weekdayno) {
		case 1:setOne(course);break;
		case 2:setTwo(course);break;
		case 3:setThree(course);break;
		case 4:setFour(course);break;
		case 5:setFive(course);break;
		}
	}
	public String getWeekCourse(int weekdayno) {
		String course=null;
		switch(weekdayno) {
		case 1:course=one;break;
		case 2:course=two;break;
		case 3:course=three;break;
		case 4:course=four;break;
		case 5:course=five;break;
		}
		return course;
	}
	public int getSecno() {
		return secno;
	}
	public void setSecno(int secno) {
		this.secno = secno;
	}
	public String getSecname() {
		return secname;
	}
	public void setSecname(String secname) {
		this.secname = secname;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
}
