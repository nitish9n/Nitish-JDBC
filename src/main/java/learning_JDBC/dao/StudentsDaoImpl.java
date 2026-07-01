package learning_JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import learning_JDBC.entity.Students;

public class StudentsDaoImpl implements StudentsDao {

	public static final String INSERT_QUERRY = "INSERT into students (Id, Name, Gender, Address) VALUES (%d, '%s', '%s', '%s')";
	public static final String UPDATE_QUERRY = "UPDATE students SET Id = %d, Name = '%s', Gender = '%s', Address = '%s' where Id = %d";
	public static final String SELECT_QUERRY = "SELECT * from students";
	public static final String GET_STUDENT_BY_ID = "SELECT * from students where id = %d";
	public static final String GET_STUDENT_BY_NAME = "SELECT * from students where name = '%s'";
	private static final String DELETE_QUERY = "DELETE FROM students WHERE ID = %d";

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
		try (Statement statement = connection.createStatement();) {
//			statement.executeUpdate("insert into students  values ("+s1.getId()+", '"+s1.getName()+"','"+s1.getGender()+"','"+s1.getCity()+"');");
			statement.executeUpdate(
					String.format(INSERT_QUERRY, s1.getId(), s1.getName(), s1.getGender(), s1.getCity()));

			System.out.println("insert into students  values (" + s1.getId() + ", '" + s1.getName() + "','"
					+ s1.getGender() + "','" + s1.getCity() + "');");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudents(Students s1) throws SQLException {
		Statement statement = connection.createStatement();
//			statement.executeUpdate("UPDATE students SET Name = ' "+s1.getName()+"',Gender = ' "+s1.getGender()+" ' , Address = ' "+s1.getCity()+" ' where id = ' "+s1.getId()+"' ");
		statement.executeUpdate(
				String.format(UPDATE_QUERRY, s1.getId(), s1.getName(), s1.getGender(), s1.getCity(), s1.getId()));

	}

	@Override
	public void deleteStudents(int id) {
		try (Statement statement = connection.createStatement();) {
			statement.executeUpdate(String.format(DELETE_QUERY, id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void printAllStudents() {
		try (Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(SELECT_QUERRY);

			while (resultSet.next()) {

				System.out.println("Id = " + resultSet.getInt(1) + "    Name = " + resultSet.getString(2)
						+ "    Gender = " + resultSet.getString(3) + "     Address = " + resultSet.getString(4));

			}

			System.out.println(SELECT_QUERRY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Students getStudentById(int id) {
		
		Students s = new Students();         // dummy object

		try (Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(String.format(GET_STUDENT_BY_ID, id));

			while (resultSet.next()) {

				s.setId(resultSet.getInt(1));
				s.setName(resultSet.getString(2));
				s.setGender(resultSet.getString(3));
				s.setCity(resultSet.getString(4));
				
				System.out.println(String.format(GET_STUDENT_BY_ID, id));
				
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	
	@Override
	public Students getStudentByName(String name) {
		Students s = new Students();         // dummy object

		try (Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(String.format(GET_STUDENT_BY_NAME, name));

			while (resultSet.next()) {

				s.setId(resultSet.getInt(1));
				s.setName(resultSet.getString(2));
				s.setGender(resultSet.getString(3));
				s.setCity(resultSet.getString(4));
				
				System.out.println(String.format(GET_STUDENT_BY_NAME, name));
				
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	
	

	@Override
	public List<Students> getAllStudents() {
		
		
		
		
		
		return null;
	}
}
