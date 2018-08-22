import java.sql.*;
import java.io.*;
class SearchData
{
	public static void main(String args[ ])
	{
		try
		{

			String eno;

			//create the InputstreamReader

			InputStreamReader ir=new InputStreamReader(System.in);

			//create the Buffered Reader	

			BufferedReader br=new BufferedReader(ir);

			System.out.print("Enter the Enrollment Number :");

			eno=br.readLine();

			//load the database driver
// 1.7
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//
//			//create the connection objec 
//
//			Connection conn;
//			
//			conn=DriverManager.getConnection("jdbc:odbc:emsdb","","");


// 1.8

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/ems.mdb"; 
			
			conn= DriverManager.getConnection(dbURL);;

			//create the statement object 

			java.sql.Statement st=conn.createStatement();

			//define the sql query

			String sql="select * from students where enrollno='"+eno+"'";

			//list all records

			ResultSet rs=st.executeQuery(sql);

			//list all records

			if(rs.next())
			{
				System.out.println("===================================================================");
				System.out.println("Enrollment Number\t\tName\t\tBranch\t\tYear");
				System.out.println("===================================================================");


 				System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));

				System.out.println("===================================================================");
	
				System.out.println();
			}
			else
				System.out.println("Not Record Found.");

			//close the connection 

			conn.close();

		}
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}
}