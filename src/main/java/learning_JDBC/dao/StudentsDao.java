package learning_JDBC.dao;

import java.sql.SQLException;
import java.util.List;

import learning_JDBC.entity.Students;

public interface StudentsDao {
	
	public void saveStudents(Students s1);
	public void updateStudents (Students s1) throws SQLException;
	public void deleteStudents(int id);
	public Students getStudents(int id);
	public void printAllStudents();
	public List<Students> getAllStudents();
	
}
