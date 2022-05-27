package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Teacher;
import daobase.TeacherDaoBase;

public class TeacherDao implements TeacherDaoBase{
	public Teacher select(String id) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		Teacher s = new Teacher();
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from teacher where id=?");
			state.setString(1,id);
			rs = state.executeQuery();
			if (rs.next()) {
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				s.setSex(rs.getString("sex"));
				s.setTitle(rs.getString("title"));
				s.setPwd(rs.getString("pwd"));  //密码默认与学号相同，可自定义修改
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return s;
	}
 
	
	public List<Teacher> selectAll() {
		List<Teacher> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select * from teacher");
			rs = state.executeQuery();
			while (rs.next()) {
				Teacher s = new Teacher();
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				s.setSex(rs.getString("sex"));
				s.setTitle(rs.getString("title"));
				s.setPwd(rs.getString("pwd"));  //密码默认与学号相同，可自定义修改
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5、关闭
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
 
	
	public void add(Teacher s) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("insert into teacher values(?,?,?,?,?)");
			state.setString(1, s.getId());
			state.setString(2, s.getName());
			state.setString(3, s.getSex());
			state.setString(4, s.getTitle());
			state.setString(5, s.getId());   //密码默认与学号相同
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}
 
	
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("delete from teacher where id=?");
			state.setString(1, id);
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}
 
	
	public Teacher update(Teacher s) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("update  teacher  set name=?,sex=?,title=?,pwd=? where  id=?");
			state.setString(5, s.getId());
			state.setString(1, s.getName());
			state.setString(2, s.getSex());
			state.setString(3, s.getTitle());
			state.setString(4, s.getPwd());
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
		return s;
	}
 

 
	
	public List<Teacher> queryByName(String name) {
		List<Teacher> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			state = conn.prepareStatement("select * from student where name like ?");
			state.setString(1, "%" + name + "%");
			rs = state.executeQuery();
			while (rs.next()) {
				Teacher c = new Teacher();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setSex(rs.getString("sex"));
				c.setTitle(rs.getString("title"));
				c.setPwd(rs.getString("pwd"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	public void setFavicon(String tid, InputStream is) throws Exception {  //学生端上传图片
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("update teacher set favicon=? where id=?");
			state.setBinaryStream(1, is, is.available());
			state.setString(2, tid);
			state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs, state, conn);
		}
	}

	public InputStream getFavicon(String tid) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		InputStream is = null;
		try {
			conn = DBUtil.getConnection();
			state = conn.prepareStatement("select favicon from teacher where id=?");
			state.setString(1,tid);
			rs = state.executeQuery();
			if(rs.next()){
				is = rs.getBinaryStream(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
 
		}
		return is;
	}
}
