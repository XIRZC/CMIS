package daobase;

import vo.classroom;
import java.util.ArrayList;
import java.util.List;

public interface ClassroomDaoBase {
	classroom selectById(String rid);
	List<classroom> selectByBuilding(String bno);
	List<classroom> selectAll();
	void add(classroom r);
	void update(classroom r);
	void delete(String rid);
}
