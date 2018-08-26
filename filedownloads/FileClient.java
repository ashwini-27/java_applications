// package client;
// import java.io.DataOutputStream;
// import java.io.FileInputStream;
// import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.io.*;
public class FileClient{
	
	private Socket s;
	Scanner scn= new Scanner(System.in);
	public FileClient(String host, int port) {
		try {
			s = new Socket(host, port);
			sendFile();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile() throws IOException {


		PrintWriter wr= new PrintWriter(s.getOutputStream());

		System.out.println("Enter username:\t");
		String uname=scn.nextLine();
		System.out.println("Enter password:\t");
		String pass=scn.nextLine();
		wr.println(uname);
		wr.println(pass);
		wr.flush();
		// wr.close();
		BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
		String response = br.readLine();
		// br.close();
        if(response.equals("200"))
		{
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		System.out.println("enter the file path to send to client\n");
		String file= scn.nextLine();
		try{
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		int	written=0;
		while(fis.read(buffer) > 0) {
			dos.write(buffer);
			written=written+buffer.length;
			System.out.println("Written on socket\t"+written);
		}
		wr.close();
		br.close();
		fis.close();
		dos.close();
	}
	catch(Exception e)
	{
		System.out.println("there is an error "+e);
	}
		}
		else
		{
				System.out.println("This is the response: " + response);
        		System.out.println("there is some issue with user credentials");
		}	
	}
	
	public static void main(String[] args) {
		




		FileClient fc = new FileClient("recevers public ip", 1988);
	}

}