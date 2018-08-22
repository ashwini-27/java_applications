import java.sql.*;
import java.io.*;
class SearchCourse
{
void seartab()
{

try
{
//
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//Connection conn;
//conn=DriverManager.getConnection("jdbc:odbc:coursedb","","");
    
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			//create the connection objec 

			Connection conn;
			  String dbURL = "jdbc:ucanaccess://" + "/home/ashwini/Desktop/java_old/course.mdb"; 
			
			conn= DriverManager.getConnection(dbURL);;
java.sql.Statement st=conn.createStatement();
String csid, csname,csfee;

			//create the InputstreamReader

			InputStreamReader ir=new InputStreamReader(System.in);

			//create the Buffered Reader	

			BufferedReader br=new BufferedReader(ir);

			System.out.print("Enter the Course ID :");
			

			csid=br.readLine();


String sql="select * from csdb where courseid='"+csid+"'";
ResultSet rs=st.executeQuery(sql);

System.out.println("===================================================================");
System.out.println("Course ID\t\tCourse Name\t\tCourse Fees");
System.out.println("===================================================================");

			//list all records

			while(rs.next())
			{
 				System.out.print(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t\t"+rs.getString(3));

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