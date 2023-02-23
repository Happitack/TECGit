package dk.tec.clu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTools 
{
	private String connectionStr = 
			"jdbc:sqlserver://CHRISTIAN-PC;databaseName=WebApi; encrypt=true;trustServerCertificate=true;";
	private Connection conn;
	private Statement stmt;

	private void connect()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionStr, "sa", "1234");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public Persons getPersonById(int perID)
	{
		connect();
		
		Persons person = null;
		
		String selectStr = "Select * from Persons where personID = " + perID;
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			if(result.next())
			{
				person = new Persons();
				person.perID = result.getInt("PersonID");
				//person.perName = result.getString("PersonName");
				//person.perAddress = result.getString("elevPersonAddress");
				//person.hairId = result.getInt("HairID");
				//person.favorite = result.getBoolean("Favorite");
				//person.personTlf = result.getString("PersonTlf");
				//person.interest1 = result.getInt("Interest1");
				//person.interest1 = result.getInt("Interest2");
				//person.interest1 = result.getInt("Interest3");
				//person.interest1 = result.getInt("Interest4");
			}
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return person;
	}
}
