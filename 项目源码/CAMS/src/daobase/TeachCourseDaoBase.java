package daobase;

import java.util.List;

import vo.teachcourse;

public interface TeachCourseDaoBase {
	List<teachcourse> selectByTid(String tid);
	List<teachcourse> selectAll();
}
