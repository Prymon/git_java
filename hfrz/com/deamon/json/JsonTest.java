package com.deamon.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import net.sf.json.JSONObject;

public class JsonTest {
	
	public static void main(String[] args) throws IOException {
		jsonTest();

	}

	/**
	 * Json≤‚ ‘
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void jsonTest() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"c:/input.txt")));
		String str = "", temp = "";
		do {
			str += temp;
			if(temp!=null&&temp!="")
				str+="\n\r";
			temp = reader.readLine();
		} while (temp != null);
		String[] inputs = str.split("\n\r");
		System.out.println(inputs[1].length());
		JSONObject json = JSONObject.fromObject(inputs[1]);
		Iterator<String> keys_ite = json.keys();
		while (keys_ite.hasNext()) {
			String name = keys_ite.next();
			String value = json.getString(name);
	    	value=value.replace("\\s", "");
	    	value=value.replace("\"", "");
	    	value=value.replace("[", "");
	    	value=value.replace("]", "");
			System.out.println(name+":  "+value);
		}
	}
}
