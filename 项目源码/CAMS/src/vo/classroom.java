package vo;

public class classroom {
	private String id;
	private int seatnum;
	private String isstair;
	private String building;
	private int floor;
	public classroom() {}
	public classroom(String rid,String seatnum,String isstair) {
		setId(rid);
		setSeatnum(Integer.parseInt(seatnum));
		setIsstair(isstair);
		setBuilding(rid.substring(0,2));
		setFloor(Integer.parseInt(rid.substring(2,3)));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}
	public String getIsstair() {
		return isstair;
	}
	public void setIsstair(String isstair) {
		this.isstair = isstair;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
}
