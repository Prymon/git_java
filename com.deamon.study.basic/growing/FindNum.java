package growing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 要求： 给定一个字符串，例如 abc123de4f67,把里面所有的数字字串找出来，并且存贮起来。
 * 例如你要把123，4，67找出来，放到3个char数组里。
 */
public class FindNum {

	public static void main(String[] args) throws IOException {
		ArrayList<char[]> outlist = new ArrayList<char[]>();
		// getnum("12abc123de4f67ss",outlist);
		getnum("12d", outlist);
		// 输出结果
		String output = "";
		output += "number of arrays is :" + outlist.size() + "\r\nvalue is :";
		for (char[] c : outlist) {
			output += String.valueOf(c) + " ";
		}
		// 打印
		System.out.println(output);

		// 输出到文件
		File file = null;
		do {
			System.out.println("please input the path of output file you want to stored:");
			String path = new Scanner(System.in).next();
			file = new File(path);
		} while (file == null || file.exists());
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(output.getBytes());
		fos.close();
	}

	private static void getnum(String var, ArrayList<char[]> list) {
		char[] cvars = var.toCharArray();
		String out = ""; // 接收输出数据
		char tempc; // 临时变量
		int index = 0; // 0代表未开始记录
		for (int i = 0; i < cvars.length; i++) {
			tempc = cvars[i];
			if (tempc >= '0' && tempc <= '9') {
				index = 1;
				out += (tempc + "");
			} else if (index == 1) {
				out += "x";
				index = 0;
			}
		}
		String[] strs = out.split("x");
		for (String t : strs) {
			char[] cs = t.toCharArray();
			list.add(cs);
		}
	}

}
