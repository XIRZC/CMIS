package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daobase.TeachCourseDaoBase;
import util.DBUtil;
import vo.teachcourse;

public class TeachCourseDao implements TeachCourseDaoBase{
	public List<teachcourse> selectByTid(String tid) {
		List<teachcourse> list=new ArrayList<teachcourse>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from teachcourseView where tid=?");
			state.setString(1,tid);
			rs = state.executeQuery();
			while(rs.next()) {
				teachcourse tc = new teachcourse();
				tc.setCid(rs.getString("cid"));
				tc.setCname(rs.getString("cname"));
				tc.setWeeksno(rs.getInt("weeksno"));
				tc.setWeekeno(rs.getInt("weekeno"));
				tc.setType(rs.getString("type"));
				tc.setTname(rs.getString("tname"));
				tc.setTid(tid);
				list.add(tc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return list;
	}
	public List<teachcourse> selectAll() {
		List<teachcourse> list=new ArrayList<teachcourse>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from teachcourseView where type=?");
			state.setString(1, "选修课");
			rs = state.executeQuery();
			while(rs.next()) {
				teachcourse tc = new teachcourse();
				tc.setCid(rs.getString("cid"));
				tc.setCname(rs.getString("cname"));
				tc.setWeeksno(rs.getInt("weeksno"));
				tc.setWeekeno(rs.getInt("weekeno"));
				tc.setType(rs.getString("type"));
				tc.setTname(rs.getString("tname"));
				tc.setTid(rs.getString("tid"));
				list.add(tc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return list;
	}
}
