package com.deamon.main;

public class Main {

	public static void main(String[] args) {
		parseArgs(args);
	}
	
	/**
	 * ���� ��������
	 * @param args
	 */
	private static void parseArgs(String[] args){
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-c":
				//to do
				System.out.println("run config -c");
				break;
			default:
				//to do
				break;
			}
		}
	}
}
