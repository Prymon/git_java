package growing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

/**
 * 检测一段程序中如“【”，“{”，“（” 是否相互配对或缺少 如{（）}是合法的， 而【（】）是合法的
 */
public class CheckCharMatch {

	// 测试
	public static void main(String[] args) {
		try {
			File file = new File("c:/inpu.txt");
			char[][] check_char = new char[][] { { '[', ']' }, { '<', '>' } };
			Check_Match test = new Check_Match(check_char, file);
			System.out.println(test.isvalid());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Check_Match {
	// 需要检测的字符
	private char[][] check_char;

	// 待检测的字符串
	private StringBuffer input = new StringBuffer();

	// 输出结果
	private String output = "";

	/**
	 * @param chars 需要检测的字符数组
	 * @param input 输入的需要检测的字符串{{'[',']'},{'(',')'},{'<','>'}}
	 */
	public Check_Match(char[][] chars, String input) {
		this.check_char = chars;
		this.input.append(input);
	}

	/**
	 * @param chars 需要检测的字符数组
	 * @param input 需要检测的文件
	 * @throws IOException
	 */
	public Check_Match(char[][] chars, File input) throws IOException {
		this.check_char = chars;
		// 检测文件合法
		if (input != null && input.isFile()) {
			FileInputStream fis = new FileInputStream(input);
			byte[] b = new byte[64];
			while ((fis.read(b)) != -1) {
				this.input.append(new String(b));
			}
			System.out.println("file valid. √");
		} else {
			System.out.println("file input not valid ×\nplease check.");
			System.exit(-1);
		}
		// 检测匹配字符合法
		if (this.check_char[0].length != 2) {
			System.out.println("checked char input not valid ×\nplease check.");
			System.exit(-1);
		}
	}

	/**
	 * 得到输出结果
	 * 
	 * @return
	 */
	public boolean isvalid() {
		int length = this.input.length();
		Stack<Character> stack = new Stack<Character>();
		char temp;
		for (int i = 0; i < length; i++) {
			temp = this.input.charAt(i);
			for (int j = 0; i < this.check_char.length; i++) {
				// 找到左项则入栈
				if (temp == check_char[j][0]) {
					stack.push(temp);
					break;
				}
				// 找到右项出栈匹配
				if (temp == check_char[j][1]) {
					// valid
					if (stack.isEmpty() || (stack.pop()) != check_char[j][0]) {
						return false;
					}
					// invalid
					else {
						break;
					}
				}
			}
		}
		// 扫描完成，检查栈是否为空
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}
}