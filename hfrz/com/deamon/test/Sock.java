package com.deamon.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Sock {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		// new Thread(new server()).start();

		Socket socket;
		socket = new Socket("127.0.0.1", 4700);
		BufferedReader sysin = new BufferedReader(new InputStreamReader(
				System.in));
		PrintWriter socketout = new PrintWriter(socket.getOutputStream());
		BufferedReader socketin = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		// ¿ªÊ¼¶ÁÐ´
		String line = sysin.readLine();
		while (!line.equals("exit")) {
			socketout.println(line);
			System.out.println("client send:" + line);
			socketout.flush();
			line = socketin.readLine();
			System.out.println("client received :" + line);
			line = sysin.readLine();
		}

	}

}