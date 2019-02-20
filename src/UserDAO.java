import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.*;

public class UserDAO {

	private Connection myConn;
	
	public UserDAO () throws Exception
	{
		connect();
	}
	
	public void addUser(User user) throws Exception
	{
		connect();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try
		{
			myStmt = myConn.prepareStatement("insert into employees"
					+ " values (?, ?, ?, ?)");
			
			myStmt.setInt(1, user.getEmpNumber());
			myStmt.setString(2, user.getUserName());
			myStmt.setString(3, user.getPassword());
			myStmt.setBoolean(4, user.isAdmin());
			
			myStmt.executeUpdate();
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public void removeUser(User user) throws Exception
	{
		connect();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try
		{
			myStmt = myConn.prepareStatement("delete from employees where empNumber=?");
			
			myStmt.setInt(1, user.getEmpNumber());
		
			myStmt.executeUpdate();
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public void updateEmployee(User user) throws Exception
	{
		connect();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try
		{
			myStmt = myConn.prepareStatement("update employees"
					+ " set empNumber=?, userName=?, password=?, isAdmin=?"
					+ " where empNumber=? OR userName=?");
			
			myStmt.setInt(1, user.getEmpNumber());
			myStmt.setString(2, user.getUserName());
			myStmt.setString(3, user.getPassword());
			myStmt.setBoolean(4, user.isAdmin());
			myStmt.setInt(5, user.getEmpNumber());
			myStmt.setString(6, user.getUserName());
			myStmt.executeUpdate();
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public boolean findUser(String userName) throws Exception
	{
		connect();
		boolean isFound = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		try 
		{
			myStmt = myConn.createStatement();
			String sql = "Select * from employees Where username='" + userName + "'";
			myRs = myStmt.executeQuery(sql);
	        while (myRs.next())
	        {
	            if (userName.equals(myRs.getString("userName")))
	            	isFound = true;
	        }
			return isFound;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isFound = false;
			return isFound;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public boolean findUser(int empNumber) throws Exception
	{
		connect();
		boolean isFound = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		try 
		{
			myStmt = myConn.createStatement();
			String sql = "Select * from employees Where empNumber=" + empNumber;
			myRs = myStmt.executeQuery(sql);
	        while (myRs.next())
	        {
	            if (empNumber == myRs.getInt("empNumber"))
	            	isFound = true;
	        }
			return isFound;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isFound = false;
			return isFound;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public boolean isAdmin(String userName) throws Exception
	{
		connect();
		boolean isAdmin = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		try 
		{
			myStmt = myConn.createStatement();
			String sql = "Select * from employees Where username='" + userName + "'";
			myRs = myStmt.executeQuery(sql);
	        while (myRs.next())
	        {
	            if (userName.equals(myRs.getString("userName")))
	            {
	                if (myRs.getBoolean("isAdmin"))
	                	isAdmin = true;
	                else
	                	isAdmin = false;
	            }
	        }
			return isAdmin;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			isAdmin = false;
			return isAdmin;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public List<User> getAllUsers() throws Exception
	{
		connect();
		List<User> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try 
		{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from employees");
			while (myRs.next())
			{
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			return list;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	public User getUser(String userName) throws Exception
	{
		connect();
		Statement myStmt = null;
		ResultSet myRs = null;
		User tempUser = null;
		try 
		{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from employees");
			while (myRs.next())
			{
				if (userName.equals(myRs.getString("userName")))
				{
					tempUser = convertRowToUser(myRs);
				}
			}
			return tempUser;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
	}
	
	private User convertRowToUser(ResultSet myRs) throws SQLException 
	{
		int empNumber = myRs.getInt("empNumber");
		String userName = myRs.getString("userName");
		String password = myRs.getString("password");
		Boolean isAdmin = myRs.getBoolean("isAdmin");
		User tempUser = new User(empNumber, userName, password, isAdmin);
		return tempUser;
	}
	
	private void connect() throws Exception
	{
		Properties props = new Properties();
		props.load(new FileInputStream("sql/pos.properties"));
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB Connection successful to: " + dburl);
	}
			
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException 
	{
		if (myRs != null) 
			myRs.close();
		if (myStmt != null) 	
			myStmt.close();
		if (myConn != null) 
			myConn.close();
	}
}