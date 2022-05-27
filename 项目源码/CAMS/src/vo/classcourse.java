package vo;

public class classcourse implements Comparable<classcourse> {
	private String cname;
	private String tname;
	private int secno;
	private String time;
	private String secname;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getSecno() {
		return secno;
	}
	public void setSecno(int secno) {
		this.secno = secno;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSecname() {
		return secname;
	}
	public void setSecname(String secname) {
		this.secname = secname;
	}
	@Override
	public int compareTo(classcourse cc) {
		return this.secno-cc.getSecno();
	}
}
