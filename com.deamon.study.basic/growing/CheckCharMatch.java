package growing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

/**
 * ���һ�γ������硰��������{���������� �Ƿ��໥��Ի�ȱ�� ��{����}�ǺϷ��ģ� �����������ǺϷ���
 */
public class CheckCharMatch {

	// ����
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
	// ��Ҫ�����ַ�
	private char[][] check_char;

	// �������ַ���
	private StringBuffer input = new StringBuffer();

	// ������
	private String output = "";

	/**
	 * @param chars ��Ҫ�����ַ�����
	 * @param input �������Ҫ�����ַ���{{'[',']'},{'(',')'},{'<','>'}}
	 */
	public Check_Match(char[][] chars, String input) {
		this.check_char = chars;
		this.input.append(input);
	}

	/**
	 * @param chars ��Ҫ�����ַ�����
	 * @param input ��Ҫ�����ļ�
	 * @throws IOException
	 */
	public Check_Match(char[][] chars, File input) throws IOException {
		this.check_char = chars;
		// ����ļ��Ϸ�
		if (input != null && input.isFile()) {
			FileInputStream fis = new FileInputStream(input);
			byte[] b = new byte[64];
			while ((fis.read(b)) != -1) {
				this.input.append(new String(b));
			}
			System.out.println("file valid. ��");
		} else {
			System.out.println("file input not valid ��\nplease check.");
			System.exit(-1);
		}
		// ���ƥ���ַ��Ϸ�
		if (this.check_char[0].length != 2) {
			System.out.println("checked char input not valid ��\nplease check.");
			System.exit(-1);
		}
	}

	/**
	 * �õ�������
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
				// �ҵ���������ջ
				if (temp == check_char[j][0]) {
					stack.push(temp);
					break;
				}
				// �ҵ������ջƥ��
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
		// ɨ����ɣ����ջ�Ƿ�Ϊ��
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}
}