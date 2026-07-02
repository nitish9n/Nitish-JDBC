package learning_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import learning_JDBC.dao.StudentsDao;
import learning_JDBC.dao.StudentsDaoImpl;
import learning_JDBC.entity.Students;

public class Jdbc {

	public static void main(String[] args) throws SQLException  {
		
		
//		Students s1 = new Students(2, "Muskan","Female", "Patna");
		  
		StudentsDao edao = new StudentsDaoImpl();
		
//		edao.saveStudents(s1);
		
//		edao.deleteStudents(7);
		
//		edao.updateStudents(s1);
		
//		edao.printAllStudents();
		 
//		Students p = edao.getStudentById(1);
//		System.out.println(p);
		
		List<Students> p = edao.getAllStudents();
		System.out.println(p);
//		or
//		System.out.println(edao.getAllStudents());
		
//		 edao.getStudentByName("Musk' or ' 1 = 1");      // SQl injection
		
		
		
		
		
		
	}

}
