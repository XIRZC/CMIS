package daobase;

import java.io.InputStream;
import java.util.List;

import vo.Teacher;

public interface TeacherDaoBase {
	List<Teacher> selectAll();
	 
	Teacher select(String id);
 
	void add(Teacher c);
 
	void delete(String id);
 
	Teacher update(Teacher c);
 
	List<Teacher> queryByName(String name);
	
	void setFavicon(String tid, InputStream is) throws Exception;
	
	InputStream getFavicon(String tid);
}
