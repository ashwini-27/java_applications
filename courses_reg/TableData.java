import java.sql.*;

class TableData
{
	public static void main(String args[ ])
	{
		try
		{
			//load the database driver

//			1.7
                    //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//
//			//create the connection objec 
//
//			Connection conn;
//			
//			conn=DriverManager.getConnection("jdbc:odbc:emsdb","","");

			//create the statement object 

                    
                    
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/ems.mdb"; 
			
			conn= DriverManager.getConnection(dbURL);;
                    
			java.sql.Statement st=conn.createStatement();

			//define the sql query

			String sql="select * from students";

			//list all records

			ResultSet rs=st.executeQuery(sql);

			System.out.println("===================================================================");
			System.out.println("Enrollment Number\t\tName\t\tBranch\t\tYear");
			System.out.println("===================================================================");

			//list all records

			while(rs.next())
			{
 				System.out.print(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));

				System.out.println();
			}
			
			System.out.println("===================================================================");

			//close the connection 

			conn.close();

		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}
}