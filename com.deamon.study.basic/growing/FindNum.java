package growing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Ҫ�� ����һ���ַ��������� abc123de4f67,���������е������ִ��ҳ��������Ҵ���������
 * ������Ҫ��123��4��67�ҳ������ŵ�3��char�����
 */
public class FindNum {

	public static void main(String[] args) throws IOException {
		ArrayList<char[]> outlist = new ArrayList<char[]>();
		// getnum("12abc123de4f67ss",outlist);
		getnum("12d", outlist);
		// ������
		String output = "";
		output += "number of arrays is :" + outlist.size() + "\r\nvalue is :";
		for (char[] c : outlist) {
			output += String.valueOf(c) + " ";
		}
		// ��ӡ
		System.out.println(output);

		// ������ļ�
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
		String out = ""; // �����������
		char tempc; // ��ʱ����
		int index = 0; // 0����δ��ʼ��¼
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
