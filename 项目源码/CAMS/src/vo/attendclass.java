package vo;

public class attendclass {
	private String ano;
	private String cid;
	private String cname;
	private String tid;
	private String tname;
	private String rid;
	private int seatnum;
	private String istair;
	private int weeksno;
	private int weekeno;
	private int weekdayno;
	private int secsno;
	private int seceno;
	private int type;

	public attendclass() {
	}

	public attendclass(int weekdayno, int type, int secsno, int seceno, String rid, String cid, String tid) {
		this.tid = tid;
		this.ano=tid+"-";
		this.cid = cid;
		this.ano+=cid+"-";
		this.rid = rid;
		this.ano+=rid+"-";
		this.type = type;
		this.ano+=type+"-";
		this.weekdayno = weekdayno;
		this.ano+=weekdayno+"-";
		this.secsno = secsno;
		this.ano+=secsno+"-";
		this.seceno = seceno;
		this.ano+=seceno;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public int getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}

	public String getIstair() {
		return istair;
	}

	public void setIstair(String istair) {
		this.istair = istair;
	}

	public int getWeeksno() {
		return weeksno;
	}

	public void setWeeksno(int weeksno) {
		this.weeksno = weeksno;
	}

	public int getWeekeno() {
		return weekeno;
	}

	public void setWeekeno(int weekeno) {
		this.weekeno = weekeno;
	}

	public int getWeekdayno() {
		return weekdayno;
	}

	public void setWeekdayno(int weekdayno) {
		this.weekdayno = weekdayno;
	}

	public int getSecsno() {
		return secsno;
	}

	public void setSecsno(int secsno) {
		this.secsno = secsno;
	}

	public int getSeceno() {
		return seceno;
	}

	public void setSeceno(int seceno) {
		this.seceno = seceno;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "attendclass [ano=" + ano + ", cid=" + cid + ", tid=" + tid + ", rid=" + rid + ", weekdayno=" + weekdayno
				+ ", secsno=" + secsno + ", seceno=" + seceno + ", type=" + type + "]";
	}
}
