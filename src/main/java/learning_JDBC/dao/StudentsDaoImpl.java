package learning_JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import learning_JDBC.entity.Students;

public class StudentsDaoImpl implements StudentsDao {
	
	public static final String INSERT_QUERRY = "INSERT into students (Id, Name, Gender, Address) VALUES (%d, '%s', '%s', '%s')";
	public static final String UPDATE_QUERRY = "UPDATE students SET Id = %d, Name = '%s', Gender = '%s', Address = '%s' where Id = %d";
	
	static Connection connection = null;
	static {
		try { 
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void saveStudents(Students s1) {
		try(Statement statement = connection.createStatement();){
//			statement.executeUpdate("insert into students  values ("+s1.getId()+", '"+s1.getName()+"','"+s1.getGender()+"','"+s1.getCity()+"');");
			statement.executeUpdate(String.format(INSERT_QUERRY, s1.getId(), s1.getName(), s1.getGender(), s1.getCity()));
			
			System.out.println("insert into students  values ("+s1.getId()+", '"+s1.getName()+"','"+s1.getGender()+"','"+s1.getCity()+"');");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudents(Students s1) throws SQLException {
		Statement statement = connection.createStatement();
//			statement.executeUpdate("UPDATE students SET Name = ' "+s1.getName()+"',Gender = ' "+s1.getGender()+" ' , Address = ' "+s1.getCity()+" ' where id = ' "+s1.getId()+"' ");
			statement.executeUpdate(String.format(UPDATE_QUERRY,s1.getId(), s1.getName(), s1.getGender(), s1.getCity(), s1.getId() ));
			
	}

	@Override
	public void deleteStudents(int id) {
		try (Statement statement	= connection.createStatement();)
		{
			statement.executeUpdate("DELETE from students where id ="+ id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Students getStudents(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printAllStudents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Students> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}
