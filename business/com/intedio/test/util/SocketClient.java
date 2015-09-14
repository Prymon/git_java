package com.intedio.test.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import net.sf.json.JSONObject;

import com.intedio.test.protocol.BusinessServicePacket;

/**
 * Socket客户端发送程序
 * 
 * @author dimen
 */
public class SocketClient extends Thread {
	/** 端口号 */
	public static int PORT = 9888;

	/** ip地址 */
	public static String IP = "localhost";

	/** 输出流 */
	private DataOutputStream out;
	private Socket socket;

	public SocketClient() {
	}

	public SocketClient(String ip, int port) {
		SocketClient.PORT = port;
		SocketClient.IP = ip;
	}

	/**
	 * 发送数据包
	 * 
	 * @param jsnoStr
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void sendSocketData(Map<String, Object> map) throws IOException, InterruptedException {
		System.out.println("客户端开启");
		System.out.println("开始发送数据包");
		socket = new Socket(IP, PORT);
		// 初始化资源
		out = new DataOutputStream(socket.getOutputStream());
		BusinessServicePacket csp = new BusinessServicePacket();
		// 填充数据
		csp.textDataMap.putAll(map);
		csp.write(out);
		System.out.println(csp.textDataMap.toString());
		out.flush();
		Thread.sleep(3000);
	}

	public void sendSocketData(String jsonStr) throws IOException, InterruptedException {
		Map<String, Object> map = JSONObject.fromObject(jsonStr);
		sendSocketData(map);
	}
	
	public void receiveSocket() throws IOException{
		ServerSocket serverSocket = new ServerSocket(SocketClient.PORT);
		Socket receiveSocket = serverSocket.accept();
		DataInputStream dis = new DataInputStream(receiveSocket.getInputStream());
		BusinessServicePacket bsp = new BusinessServicePacket();
		bsp.readFromStream(dis);
		System.out.println(bsp.textDataMap);
	}
	

	@Override
	public void run() {
		try {
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("数据包发送完成");
	}

}
