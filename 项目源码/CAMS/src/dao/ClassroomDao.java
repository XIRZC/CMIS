package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.classroom;
import daobase.ClassroomDaoBase;
import util.DBUtil;

public class ClassroomDao implements ClassroomDaoBase{
	public classroom selectById(String rid) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		classroom r = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from classroom where id=?");
			state.setString(1,rid);
			rs = state.executeQuery();
			if (rs.next()) {
				r=new classroom();
				int floor=Integer.parseInt(rid.substring(2,3));
				String building=rid.substring(0,2);
				r.setId(rid);
				r.setSeatnum(rs.getInt("seatnum")); 
				r.setIsstair(rs.getString("isstair"));
				r.setFloor(floor);
				r.setBuilding(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return r;
	}
	public List<classroom> selectByBuilding(String bno){
		List<classroom> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		String b=bno.substring(0,2);
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from classroom where id like '"+b+"%'");
			rs = state.executeQuery();
			while (rs.next()) {
				classroom r = new classroom();
				String rid=rs.getString("id");
				int floor=Integer.parseInt(rid.substring(2,3));
				String building=rid.substring(0,2);
				r.setId(rid);
				r.setSeatnum(rs.getInt("seatnum")); 
				r.setIsstair(rs.getString("isstair"));
				r.setFloor(floor);
				r.setBuilding(building);
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	public List<classroom> selectAll(){
		List<classroom> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from classroom");
			rs = state.executeQuery();
			while (rs.next()) {
				String rid=rs.getString("id");
				int floor=Integer.parseInt(rid.substring(2,3));
				String building=rid.substring(0,2);
				classroom r = new classroom();
				r.setId(rid);
				r.setSeatnum(rs.getInt("seatnum")); 
				r.setIsstair(rs.getString("isstair"));
				r.setFloor(floor);
				r.setBuilding(building);
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	public void add(classroom r) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("insert into classroom values(?,?,?)");
			state.setString(1, r.getId());
			state.setInt(2, r.getSeatnum());
			state.setString(3, r.getIsstair());
			
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
	}
	public void delete(String rid) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("delete from classroom where id=?");
			state.setString(1, rid);
			
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
	}
	public void update(classroom r) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("update classroom set seatnum=?,isstair=? where id=?");
			state.setInt(1, r.getSeatnum());
			state.setString(2, r.getIsstair());
			state.setString(3, r.getId());
			
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
	}
}
