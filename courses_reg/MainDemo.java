import java.util.*;

class MainDemo
{

	public static void main(String args[ ])
	{
		Scanner scn=new Scanner(System.in);
	
		System.out.println("\t\tCourse Management System");

		System.out.println("\t1. List All");	
		System.out.println("\t2. List Tabluar Form");
		System.out.println("\t3. Add Course");
		System.out.println("\t4. Search Course");
		System.out.print("\t5. Delete Course");

		System.out.print("Enter the Choice :");
	
		int choice=Integer.parseInt(scn.nextLine());

		switch(choice)
		{
			case 1:
				listcourse ob=new listcourse();
				ob.list();

				break;
			case 2:
				tabularform ob2=new tabularform();
				ob2.listtab();

				break;
			case 3:
				InsertRec ob3=new InsertRec();
				ob3.insertdb();

				break;
			case 4:
				SearchCourse ob4=new SearchCourse();
				ob4.seartab();

				break;
			case 5:
				DeleteCourse ob5=new DeleteCourse();
				ob5.deltab();

				break;
			default:

				System.out.println("Invalid Choice.");
		}				
	}
}
