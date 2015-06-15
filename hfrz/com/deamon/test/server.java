package com.deamon.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable {

	public static void main(String[] args) {
		new Thread(new server()).start();
	}

	@Override
	public void run() {
		try {
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void listen() throws IOException, InterruptedException {
		ServerSocket server = new ServerSocket(4700);
		System.out.println("server started..");
		Socket socket = null;
		String line = "";
		Thread.sleep(3000);
		socket = server.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		while (!line.equals("end")) {
			line = reader.readLine();
			System.out.println("-----------------------------");
			System.out.println("server received :" + line);
			writer.println("request receive over.");
			writer.flush();
			System.out.println("server send msg");
			System.out.println("-----------------------------");
		}
		System.out.println("server error");
		server.close();
	}
}