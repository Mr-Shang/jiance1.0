package com.ccu.util;

import java.net.*;
import java.io.*;

public class ClientSocket extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static int counter =0;
	private int id = counter++;
	private static int threadcount = 0;
	public static int threadCount()
	{
		return threadcount;
	}
	public ClientSocket(InetAddress addr,int port) {
		threadcount++;
		try {
			socket = new Socket(addr, port);
		}
		catch(IOException e) {}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			start();
		}
		catch(IOException e) {
			try {
				socket.close();
			}
			catch(IOException ex) {}
		}
	}
	
	public void run() {
		try {
			for(int i=0; i<5; i++) {
				out.println("");//传送数据
				String str = in.readLine();//接收数据
				out.println(str);
			}
			out.println("END");		
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				socket.close();				
			}
			catch(IOException e) {}
			threadcount--;
		}
	}

	public String initClientSocket(InetAddress addr, int port) throws IOException, InterruptedException{
		while(true) {
			if(ClientSocket.threadCount() < 5)
				new ClientSocket(addr, port);
			Thread.sleep(100);
		}
	}
}
