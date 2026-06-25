package learning_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import learning_JDBC.entity.Students;

public class Jdbc {

	public static void main(String[] args) throws SQLException {
		
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
//		Statement statement = connection.createStatement();
//		statement.executeUpdate("insert into students  values (5, 'Kriti','Female','Buxar');");
		
//		optimized, shortcut
//		DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root").createStatement().executeUpdate("insert into students  values (5, 'Kriti','Female','Buxar');");
//		
//		System.out.println("main");
		
		
		Students s1 = new Students(8, "Anjali", "Female", "Buxar");
		
		DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root").createStatement().executeUpdate("insert into students  values ("+s1.getId()+", '"+s1.getName()+"','"+s1.getGender()+"','"+s1.getCity()+"');");
		
		System.out.println("main");
		
		
	}

}
