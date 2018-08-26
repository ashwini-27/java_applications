// package server;
import java.io.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
public class FileServer extends Thread {
	
	private ServerSocket ss;
	
	public FileServer(int port){
		try {
			ss = new ServerSocket(port);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (true){
			try{
				Socket clientSock = ss.accept();
				saveFile(clientSock);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {
		

		String uname="ashwini";
		String upass="ashwini";

		// uname pass will further come from mdb files
		BufferedReader br= new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
		String tuname=br.readLine();
		String tpass=br.readLine();
		// br.close();
		PrintWriter wr= new PrintWriter(clientSock.getOutputStream());

		if(tuname.equals(uname)&&tpass.equals(upass))
		{
			wr.println("200");
			wr.flush();
	
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());		
		// byte empty[]= new byte[1];

		int read = 0;
		byte[] buffer = new byte[4096];
		if((read=dis.read(buffer,0,buffer.length))>0)
		{
		System.out.println("enter file path where to be saved");
		Scanner scn= new Scanner(System.in);
		String filepath=scn.nextLine();
		FileOutputStream fos = new FileOutputStream(filepath);
		fos.write(buffer);
		//byte[] filesiz=new byte[4];
		// BufferedReader br= new BufferedReader(new InputStreamReader(dis));
		//int filesize = 15123; // Send file size in separate msg
		Date strt=new Date();

		// dis.read(filesiz,0,filesiz.length);
		// int filesize = ByteBuffer.wrap(filesiz).getInt();
		// System.out.println("size of file in bytes="+filesize);
		
		int totalRead = 0;
		//int remaining = filesize;
		while((read = dis.read(buffer, 0, buffer.length)) > 0) {
			// while(dis.read(buffer,0)>0){
			totalRead += read;
			// remaining -= read;
			// System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}
		// StringBuilder out=new StringBuilder();
		// String line;
		// while((line=br.hasNextLine())!=null)
		// 	{
		// 		//fos.write(line);
		// 		out.append(line);
		// 	}
		// fos.write(out);

		br.close();
		wr.close();
		fos.close();
		dis.close();
		Date close=new Date();
		long total=close.getTime()-strt.getTime();
		System.out.println("total time taken\t=\t"+total);
	}
	else{
		System.out.println("there is no data in the file");
	}
		}
		else
		{
			wr.println("error with error code");

		wr.flush();
		wr.close();
		}

	}
	
	public static void main(String[] args) {
		FileServer fs = new FileServer(1988);
		fs.start();
	}

}