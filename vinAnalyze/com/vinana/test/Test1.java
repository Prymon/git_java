package com.vinana.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.vinana.mysql.MysqlConnectionPool;

public class Test1 {

	public static void main(String[] args) {
		get();
	}
	
	static void get(){
		File f = new File("C:\\input.txt");
		FileReader r = null;
		try {
			r = new FileReader(f);
			int s = 1/0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{System.out.println(1);}

	}
}
