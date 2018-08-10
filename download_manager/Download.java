import java.io.*;
import java.net.*;
import java.util.*;
class Download extends Observable implements Runnable {
private static final int max_buffer=1024;
public static final String[] statuses={"Downloading","Paused","Complete","Cancelled","Error"};
public static final int paused=1;	
public static final int complete=2;
public static final int cancelled=3;
public static final int error=4;
private URL url;
private int size;
private int downloaded;
private int status;
public static final int downloading =0;


public Download(URL url){
	this.url=url;
	size=-1;
	downloaded=0;
	status=downloading;
	download();
}
public String getUrl()
{
	return url.toString();
}
public int getSize()
{
	return size;
}
public int getStatus()
{
	return status;
}
public void pause()
{
	status=paused;
	statechanged();
}
public void resume()
{
	status=downloading;
	statechanged();
	download();
}
public void cancel(){
	status=cancelled;
	statechanged();
}
private void error()
{
	status=error;
	statechanged();
}

private void download(){
	Thread thread=new Thread(this);
	thread.start();
}

private String getFileName(URL url)
{
	String file_name=url.getFile();
	return file_name.substring(file_name.lastIndexOf('/')+1);
}
public float getProgress(){
	return ((float)(downloaded/size))*100;
}
public void run(){
	RandomAccessFile file =null;
	InputStream stream=null;
	try{
			HttpURLConnection connection =(HttpURLConnection)url.openConnection();
			connection.setRequestProperty("range","bytes="+downloaded+"-");
			connection.connect();
			if(connection.getResponseCode()/100!=2){
				error();
			}
			int length=connection.getContentLength();
			if(length<1){
				error();
			}
			if(size==-1)
			{
				size=length;
				statechanged();

			}
			file= new RandomAccessFile(getFileName(url),"rw");
			file.seek(downloaded);
			stream=connection.getInputStream();
			while(status==downloading){
				byte buffer[];
				if(size-downloaded>max_buffer){
					buffer=new byte[max_buffer];
				}
				else {
					buffer=new byte[size-downloaded];
				}
				int read=stream.read(buffer);
				if(read==-1)
					break;
				file.write(buffer,0,read);
				downloaded+=read;
				statechanged();	
			}
			if(status==downloading){
				status=complete;
				statechanged();
			}
		}catch(Exception e){
				error();
			}
			finally{
				if(file!=null){
					try{
						file.close();

					}catch(Exception e){}
				}
				if(stream!=null)
				{
					try{
						stream.close();

					}catch(Exception e){

					}
				}
			}

	}
private void statechanged(){
		setChanged();
		notifyObservers();
	}


}