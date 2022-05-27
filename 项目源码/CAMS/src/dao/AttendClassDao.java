package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daobase.AttendClassDaoBase;
import util.DBUtil;
import vo.attendclass;
import vo.classroom;

public class AttendClassDao implements AttendClassDaoBase{
	public List<attendclass> selectRoomCourseDay(String rid,int weekno,int weekdayno){  //根据教室号查今日课程表
		List<attendclass> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			int type=(weekno%2==0)?2:1;  //判断单双周，单周为1，双周为2
			state = conn.prepareStatement("select * from attendclassView where rid=?"
					+ " and (? between weeksno and weekeno)"
					+" and (type=3 or type=?) and weekdayno=?");
			state.setString(1,rid);
			state.setInt(2, weekno);
			state.setInt(3, type);
			state.setInt(4, weekdayno);
			rs = state.executeQuery();
			while (rs.next()) {
				attendclass ac = new attendclass();
				ac.setCid(rs.getString("cid"));
				ac.setCname(rs.getString("cname"));
				ac.setTid(rs.getString("tid"));
				ac.setTname(rs.getString("tname"));
				ac.setRid(rs.getString("rid"));
				ac.setSeatnum(rs.getInt("seatnum"));
				ac.setIstair(rs.getString("isstair"));
				ac.setWeeksno(rs.getInt("weeksno"));
				ac.setWeekeno(rs.getInt("weekeno"));
				ac.setWeekdayno(rs.getInt("weekdayno"));
				ac.setSecsno(rs.getInt("secsno"));
				ac.setSeceno(rs.getInt("seceno"));
				ac.setType(rs.getInt("type"));
				list.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	public List<attendclass> selectRoomCourseWeek(String rid,int weekno){  //根据教室号查本周课程表
		List<attendclass> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			int type=(weekno%2==0)?2:1;  //判断单双周，单周为1，双周为2
			System.out.println("-----------type-----------"+type);
			state = conn.prepareStatement("select * from attendclassView where rid=?"
					+ " and (? between weeksno and weekeno)"
					+" and (type=3 or type=?)");
			state.setString(1,rid);
			state.setInt(2, weekno);
			state.setInt(3, type);
			rs = state.executeQuery();
			while (rs.next()) {
				System.out.println("1111111111");
				attendclass ac = new attendclass();
				System.out.println("----------"+rs.getString("cname"));
				ac.setCid(rs.getString("cid"));
				ac.setCname(rs.getString("cname"));
				ac.setTid(rs.getString("tid"));
				ac.setTname(rs.getString("tname"));
				ac.setRid(rs.getString("rid"));
				ac.setSeatnum(rs.getInt("seatnum"));
				ac.setIstair(rs.getString("isstair"));
				ac.setWeeksno(rs.getInt("weeksno"));
				ac.setWeekeno(rs.getInt("weekeno"));
				ac.setWeekdayno(rs.getInt("weekdayno"));
				ac.setSecsno(rs.getInt("secsno"));
				ac.setSeceno(rs.getInt("seceno"));
				ac.setType(rs.getInt("type"));
				list.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	public List<classroom> selectWeekEmptyRoom(int type,int weeksno,int weekeno,int weekdayno,int secno){  //查询单/双/连续周条件下的某课程周范围下空教室表
		List<classroom> list = new ArrayList<>();
		List<String> used=new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection(); 
			state = conn.prepareStatement("select rid from attendclassView where type=? and (weeksno>=? and weekeno<=?)"+
						" and weekdayno=? and (? between secsno and seceno)");
			state.setInt(1,type);
			state.setInt(2,weeksno);
			state.setInt(3,weekeno);
			state.setInt(4, weekdayno);
			state.setInt(5, secno);
			rs = state.executeQuery();
			while (rs.next()) {
				used.add(rs.getString("rid"));
			}
			state=conn.prepareStatement("select * from classroom");
			rs = state.executeQuery();
			while (rs.next()) {
				String rid=rs.getString("id");
				if(used.contains(rid))		//若已包含则代表该教室已被占用 继续遍历
					continue;
				classroom r = new classroom();
				int floor=Integer.parseInt(rid.substring(2,3));
				String building=rid.substring(0,2);
				r.setId(rid);
				r.setSeatnum(rs.getInt("seatnum")); 
				r.setIsstair(rs.getString("isstair"));
				r.setFloor(floor);
				r.setBuilding(building);
				list.add(r);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	
	public List<classroom> selectEmptyRoom(String building,int weekno,int weekdayno,int secno){   //查询给定教学楼，周序号，星期号，节数查询空教室
		List<classroom> list = new ArrayList<>();
		List<String> used=new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			int type=(weekno%2==0)?2:1;  //判断单双周，单周为1，双周为2
			state = conn.prepareStatement("select rid from attendclassView where (rid like ?) and (? between weeksno and weekeno)"+
					" and weekdayno=? and (? between secsno and seceno) and  (type=3 or type=?)");
			state.setString(1,building+"%");
			state.setInt(2,weekno);
			state.setInt(3,weekdayno);
			state.setInt(4, secno);
			state.setInt(5, type);
			rs = state.executeQuery();
			while (rs.next()) {
				used.add(rs.getString("rid"));
			}
			state=conn.prepareStatement("select * from classroom where id like ?");
			state.setString(1, building+"%");
			rs = state.executeQuery();
			while (rs.next()) {
				String rid=rs.getString("id");
				if(used.contains(rid))		//若已包含则代表该教室已被占用 继续遍历
					continue;
				classroom r = new classroom();
				int floor=Integer.parseInt(rid.substring(2,3));
				r.setId(rid);
				r.setSeatnum(rs.getInt("seatnum")); 
				r.setIsstair(rs.getString("isstair"));
				r.setFloor(floor);
				r.setBuilding(building);
				list.add(r);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	
	
	public List<attendclass> selectCourseRecord(String tid,String cid){   //查看某教师某课程已有的排课记录
		List<attendclass> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from attendclassView where tid=? and cid=?");
			state.setString(1,tid);
			state.setString(2, cid);
			rs = state.executeQuery();
			while (rs.next()) {
				attendclass ac = new attendclass();
				ac.setCid(rs.getString("cid"));
				ac.setCname(rs.getString("cname"));
				ac.setTid(rs.getString("tid"));
				ac.setTname(rs.getString("tname"));
				ac.setRid(rs.getString("rid"));
				ac.setSeatnum(rs.getInt("seatnum"));
				ac.setIstair(rs.getString("isstair"));
				ac.setWeeksno(rs.getInt("weeksno"));
				ac.setWeekeno(rs.getInt("weekeno"));
				ac.setWeekdayno(rs.getInt("weekdayno"));
				ac.setSecsno(rs.getInt("secsno"));
				ac.setSeceno(rs.getInt("seceno"));
				ac.setType(rs.getInt("type"));
				list.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	/*try {
		conn = DBUtil.getConnection();
		int type=(weekno%2==0)?2:1;  //判断单双周，单周为1，双周为2
		if(building!=null&&building!="") {   
			state = conn.prepareStatement("select rid from attendclassView where (rid like ?) and (? between weeksno and weekeno)"+
					" and weekdayno=? and (? between secsno and seceno) and  (type=3 or type=?)");
			state.setString(1,building+"%");
			state.setInt(2,weekno);
			state.setInt(3,weekdayno);
			state.setInt(4, secno);
			state.setInt(5, type);
		}
		else {//教学楼为空则显示所有教学楼 
			state = conn.prepareStatement("select rid from attendclassView where  (? between weeksno and weekeno)"+
					" and weekdayno=? and (? between secsno and seceno) and  (type=3 or type=?)");
			state.setInt(1,weekno);
			state.setInt(2,weekdayno);
			state.setInt(3, secno);
			state.setInt(4, type);
		}
		rs = state.executeQuery();
		while (rs.next()) {
			used.add(rs.getString("rid"));
		}
		if(building!=null) {
			state=conn.prepareStatement("select * from classroom where id like ?");
			state.setString(1, building+"%");
		}
		else {
			state=conn.prepareStatement("select * from classroom");
		}
		rs = state.executeQuery();
		while (rs.next()) {
			String rid=rs.getString("id");
			if(used.contains(rid))		//若已包含则代表该教室已被占用 继续遍历
				continue;
			classroom r = new classroom();
			int floor=Integer.parseInt(rid.substring(2,3));
			r.setId(rid);
			r.setSeatnum(rs.getInt("seatnum")); 
			r.setIsstair(rs.getString("isstair"));
			r.setFloor(floor);
			r.setBuilding(building);
			list.add(r);
		}
	} */
	public void add(attendclass ac) {			//给指定老师所教的课排教室
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			/*
			 * state = conn.prepareStatement("insert into teaches values(?,?,?)");
			 * state.setString(3, ac.getTid()); state.setString(2, ac.getCid());
			 * state.setString(1, ac.getRid()); state.executeUpdate();
			 */
			
			state = conn.prepareStatement("insert into timespan values(?,?,?,?,?,?,?,?)");
			state.setString(1, ac.getAno());
			state.setString(2, ac.getTid());
			state.setString(3, ac.getCid());
			state.setString(4, ac.getRid());
			state.setInt(5, ac.getType());
			state.setInt(6, ac.getWeekdayno());
			state.setInt(7, ac.getSecsno());
			state.setInt(8, ac.getSeceno());
			
			state.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}
	public void update(attendclass ac) {		//给指定老师所教的课修改排教室的信息
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("update timespan set weekdayno=?,type=?,secsno=?,seceno=?,rid=?,cid=?,tid=?"
					+ " where ano=?");
			System.out.println(ac.toString());
			state.setInt(1, ac.getWeekdayno());
			state.setInt(2, ac.getType());
			state.setInt(3, ac.getSecsno());
			state.setInt(4, ac.getSeceno());
			state.setString(5, ac.getRid());
			state.setString(6, ac.getCid());
			state.setString(7, ac.getTid());
			state.setString(8, ac.getAno());
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}
	public void delete(attendclass ac) {		//给指定老师所教的课删除排教室的信息
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("delete from timespan where ano=?");
			//System.out.println("ano:"+ac.getAno());
			state.setString(1, ac.getAno());
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}
}
