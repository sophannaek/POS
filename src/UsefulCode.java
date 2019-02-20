public class UsefulCode 
{
	// Action to match user name and password in a hash map.
	
	/*@SuppressWarnings("serial")
	AbstractAction loginAction = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try
	    	{
	    		String userName = txtUserName.getText();
				String password = txtPassword.getText();
				boolean userFound = userDAO.findUser(userName, password);
				if (userFound) 
				{
					lblInvalidUsernameOr.setVisible(false);
					txtUserName.setText(null);
					txtPassword.setText(null);
					mainMenu.main(null);
					frmPosSystemLogin.setVisible(false);
				}
				else 
				{
					lblInvalidUsernameOr.setVisible(true);
					txtUserName.setText(null);
					txtPassword.setText(null);
					txtUserName.requestFocus();
				}
	    	}
	    	catch (Exception ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    }
	};*/
	
	//================================================================================================================
	
	// Function to get all users from the table. Used in the DAO.
	
	/*
	public List<User> getAllUsers() throws Exception
	{
		List<User> list = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try 
		{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from admins");
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
	*/
	
	//================================================================================================================
	
	// Function to convert a row from the database table to a user object. Used in DAO.
	
	/*
	private User convertRowToUser(ResultSet myRs) throws SQLException 
	{
		int empNumber = myRs.getInt("empNumber");
		String userName = myRs.getString("userName");
		String password = myRs.getString("password");		
		User tempUser = new User(empNumber, userName, password);
		return tempUser;
	}
	*/
}