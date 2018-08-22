import java.sql.*;

class ListAll
{
	void list()
	{
		try
		{
			//load the database driver

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/courses.mdb"; 
			
						conn= DriverManager.getConnection(dbURL);;

			//create the statement object 

			java.sql.Statement st=conn.createStatement();

			//define the sql query

			String sql="select * from students";

			//list all records
                        try{

			ResultSet rs=st.executeQuery(sql);
                        while(rs.next())
			{
 				System.out.println("Enrollment Number : "+rs.getString(1));
				System.out.println("Name                       : "+rs.getString(2));
				System.out.println("Branch                     : "+rs.getString(3));
				System.out.println("Year                         : "+rs.getString(4));

				System.out.println();
			}
                        
                        }
                        catch(Exception e){
                            System.out.println("error in query execution:\n"+e);
                        }
			//list all records

			
			

			//close the connection 

			conn.close();

		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}
}