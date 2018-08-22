import java.sql.*;

class listcourse
{
	void list()
	{
		try
		{
			//load the database driver

		/*	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			//create the connection object 

			Connection conn;
			
			conn=DriverManager.getConnection("jdbc:odbc:coursedb","","");

		*/



			//load the database driver

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/course.mdb"; 
			
			conn= DriverManager.getConnection(dbURL);;

			//create the statement object 

			java.sql.Statement st=conn.createStatement();

			//define the sql query

			String sql="select * from csdb";

			//list all records

			ResultSet rs=st.executeQuery(sql);

			//list all records

			while(rs.next())
			{
 				System.out.println("courseid : "       +rs.getString(1));
				System.out.println("coursename : " +rs.getString(2));
				System.out.println("coursefee : "     +rs.getString(3));
				

				System.out.println();
			}
			

			//close the connection 

			conn.close();

		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}
}