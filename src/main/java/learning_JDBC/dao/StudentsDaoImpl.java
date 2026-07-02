package learning_JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import learning_JDBC.entity.Students;

public class StudentsDaoImpl implements StudentsDao {

	public static final String GET_STUDENT_BY_NAME = "SELECT * from students where name = '%s'";

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
		try ( PreparedStatement ps = connection.prepareStatement("INSERT into students (Id, Name, Gender, Address) VALUES (?, ?, ?, ?)");) 
		{
			ps.setInt(1, s1.getId());
			ps.setString(2, s1.getName());
			ps.setString(3, s1.getGender());
			ps.setString(4, s1.getCity());
			
			ps.executeUpdate();
			

			System.out.println("INSERT into students (Id, Name, Gender, Address) VALUES (?, ?, ?, ?)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudents(Students s1) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE students SET Id = ?, Name = ?, Gender = ?, Address = ? where Id = ?");
		
		ps.setInt(1, s1.getId());
		ps.setString(2, s1.getName());
		ps.setString(3, s1.getGender());
		ps.setString(4, s1.getCity());
		ps.setInt(5, s1.getId());
		
		ps.executeUpdate();
	}

	@Override
	public void deleteStudents(int id) {
		try ( PreparedStatement ps = connection.prepareStatement("DELETE FROM students WHERE ID = ?");) {
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void printAllStudents() {
		try (PreparedStatement ps = connection.prepareStatement("SELECT * from students");) {
			
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				String address = resultSet.getString(4);
				
				System.out.println( id +":   "+ name+",   "+ gender+",   "+ address);
				System.out.println("---------------------------------");
//				 or
//				System.out.println(resultSet.getInt(1)+":   "+resultSet.getString(2)+",    "+resultSet.getString(3)+",    "+resultSet.getString(4));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Students getStudentById(int id) {
		
		Students s = new Students();         // dummy object

		try (PreparedStatement ps = connection.prepareStatement("SELECT * from students where id = ?");) {
			ps.setInt(1, id);	
			

			ResultSet resultSet = ps.executeQuery();
			resultSet.next();   // it is mandatory because, before it, it points -1 row(by default). now it points first row 

				 s.setId(resultSet.getInt(1));
				 s.setName(resultSet.getString(2));
				 s.setGender(resultSet.getString(3));
				 s.setCity(resultSet.getString(4));
				

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	
	@Override
	public Students getStudentByName(String name) {			// SQL injection
		Students s = new Students();         // dummy object

		try (Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(String.format(GET_STUDENT_BY_NAME, name));

			while (resultSet.next()) {

				System.out.println("Id = " + resultSet.getInt(1) + "    Name = " + resultSet.getString(2)
				+ "    Gender = " + resultSet.getString(3) + "     Address = " + resultSet.getString(4));

			}
				System.out.println(String.format(GET_STUDENT_BY_NAME, name));
				
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	
	

	@Override
	public List<Students> getAllStudents() {
		
		List<Students> std = new ArrayList<>();
		
		
		try (PreparedStatement ps = connection.prepareStatement("SELECT * from students");) {
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Students s = new Students();
				
				s.setId(resultSet.getInt(1));
				s.setName(resultSet.getString(2));
				s.setGender(resultSet.getString(3));
				s.setCity(resultSet.getString(4));
				
				std.add(s);
			
			}
			
//			System.out.println(String.format(SELECT_QUERRY));


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public void transaction() {            // Transaction
		
		Students s1 = new Students();
		try {
			connection.setAutoCommit(false);
		
		
		try(PreparedStatement ps1 = connection.prepareStatement("UPDATE students SET Id = ?, Name = ? where Id = ?");
			PreparedStatement ps2 = connection.prepareStatement("UPDATE students SET Gender = ?, Address = ? where Id = ?")	) 
		{
			
			ps1.setInt(1, s1.getId());
			ps1.setString(2, s1.getName());
			ps1.setInt(3, s1.getId());
			
			ps2.setString(1, s1.getGender());
			ps2.setString(2, s1.getCity());
			ps2.setInt(3, s1.getId());
			
			ps1.executeUpdate();
			ps2.executeUpdate();
			
			connection.commit();
			System.out.println("Transaction success");
			
		} catch (SQLException e) {
			connection.rollback();
			System.err.println("Transaction failed. Rolling back changes.");
			e.printStackTrace();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void batchProcessing() throws SQLException {
		connection.setAutoCommit(false);
		PreparedStatement ps = connection.prepareStatement("INSERT into students (Id, Name, Gender, Address) VALUES (?, ?, ?, ?)");
		
		for (int i = 1; i <= 10; i++) {
			
			ps.setInt(1, 6+i );
			ps.setString(2, "Muskan "+i);
			ps.setString(3, "Female");
			ps.setString(4, "Patna");
			
			ps.addBatch();
			
		
		if (i%2 == 0) {
			ps.executeBatch();
			connection.commit();
			System.out.println("Batch updated successfully");
			}
		}
		
	}
}
