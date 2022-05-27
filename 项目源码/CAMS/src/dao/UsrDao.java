package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daobase.UsrDaoBase;
import util.DBUtil;
import vo.usr;

public class UsrDao implements UsrDaoBase{
	public usr select(String id) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		usr u = new usr();
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from usr where id=?");
			state.setString(1,id);
			rs = state.executeQuery();
			if (rs.next()) {
				u.setId(rs.getString("id"));
				u.setPwd(rs.getString("pwd")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return u;
	}
}
