package dk.tec.clu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBTool 
{
	private String connectionStr = 
			"jdbc:sqlserver://CHRISTIAN-PC;databaseName=WebApi; encrypt=true;trustServerCertificate=true;";
	private Connection conn;
	private Statement stmt;

	private void connect() {
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = DriverManager.getConnection(connectionStr, "sa", "1234");
	        stmt = conn.createStatement();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public Person getPersonById(int perID) {
	    connect();

	    Person person = null;

	    String selectStr = "SELECT * FROM Persons WHERE personID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(selectStr)) {
	        pstmt.setInt(1, perID);
	        ResultSet result = pstmt.executeQuery();

	        if (result.next()) {
	            person = new Person();
	            person.perID = result.getInt("PersonID");
	            person.perName = result.getString("PersonName");
	            person.perAddress = result.getString("PersonAddress");
	            person.perPhone = result.getString("PhoneNumber");
	            person.hairId = result.getInt("HairID");
	            person.favorite = result.getBoolean("Favorite");
	            person.interest1 = result.getInt("Interest1");
	            person.interest2 = result.getInt("Interest2");
	            person.interest3 = result.getInt("Interest3");
	            person.interest4 = result.getInt("Interest4");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return person;
	}
	
	public List<Person> getAllPersons() throws SQLException {
	    connect();
	    List<Person> persons = new ArrayList<>();
	    String selectStr = "SELECT * FROM Persons";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(selectStr)) {
	        ResultSet result = pstmt.executeQuery();
	        
	        while (result.next()) {
	            Person person = new Person();
	            person.perID = result.getInt("PersonID");
	            person.perName = result.getString("PersonName");
	            person.perAddress = result.getString("PersonAddress");
	            person.perPhone = result.getString("PhoneNumber");
	            person.hairId = result.getInt("HairID");
	            person.favorite = result.getBoolean("Favorite");
	            person.interest1 = result.getInt("Interest1");
	            person.interest2 = result.getInt("Interest2");
	            person.interest3 = result.getInt("Interest3");
	            person.interest4 = result.getInt("Interest4");
	            persons.add(person);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conn.close();
	    }
	    
	    return persons;
	}
	
	public void addPerson(Person person) throws SQLException {
	    connect();
	    String insertStr = "INSERT INTO Persons (PersonName, PersonAddress, PhoneNumber, HairID, Favorite, Interest1, Interest2, Interest3, Interest4) " +
	                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(insertStr)) {
	        pstmt.setString(1, person.perName);
	        pstmt.setString(2, person.perAddress);
	        pstmt.setString(3, person.perPhone);
	        pstmt.setInt(4, person.hairId);
	        pstmt.setBoolean(5, person.favorite);
	        pstmt.setInt(6, person.interest1);
	        pstmt.setInt(7, person.interest2);
	        pstmt.setInt(8, person.interest3);
	        pstmt.setInt(9, person.interest4);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conn.close();
	    }
	}
    
	public void updatePerson(Person person) throws SQLException {
	    connect();
	    String updateStr = "UPDATE Persons SET PersonName = ?, PersonAddress = ?, PhoneNumber = ?, HairID = ?, Favorite = ?, Interest1 = ?, Interest2 = ?, Interest3 = ?, Interest4 = ? WHERE PersonID = ?";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(updateStr)) {
	        pstmt.setString(1, person.perName);
	        pstmt.setString(2, person.perAddress);
	        pstmt.setString(3, person.perPhone);
	        pstmt.setInt(4, person.hairId);
	        pstmt.setBoolean(5, person.favorite);
	        pstmt.setInt(6, person.interest1);
	        pstmt.setInt(7, person.interest2);
	        pstmt.setInt(8, person.interest3);
	        pstmt.setInt(9, person.interest4);
	        pstmt.setInt(10, person.perID);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conn.close();
	    }
	}
    
	public void deletePerson(int perID) throws SQLException {
	    connect();
	    String deleteStr = "DELETE FROM Persons WHERE PersonID = ?";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(deleteStr)) {
	        pstmt.setInt(1, perID);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conn.close();
	    }
	}
}