import java.sql.*;
import java.io.*;
class InsertRec
{
	void insertdb()
	{
		try
		{

			String csid, csname,csfee;

			//create the InputstreamReader

			InputStreamReader ir=new InputStreamReader(System.in);

			//create the Buffered Reader	

			BufferedReader br=new BufferedReader(ir);

			System.out.print("Enter the Course ID :");
			

			csid=br.readLine();

			System.out.print("Enter the Course Name:");
			

			csname=br.readLine();
			System.out.print("Enter the Course Fees :");
			

			csfee=br.readLine();
			//load the database driver

			/*
                        java 1.7 version
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			//create the connection objec 

			Connection conn;
			
			conn=DriverManager.getConnection("jdbc:odbc:coursedb","","");
                        */
			//create the statement object 

                        
                        
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/course.mdb"; 
			
			conn= DriverManager.getConnection(dbURL);;
                        
			java.sql.Statement st=conn.createStatement();

			ResultSet rs=st.executeQuery("select * from csdb where courseid='"+csid+"'");

			if(rs.next())
				System.out.println("Record Already exists.");
			else
			{

				//define the sql query

				String sql="insert into csdb values('"+csid+"','"+csname+"','"+csfee+"')";

			
				st.executeUpdate(sql);



				System.out.println("Record Saved.");
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