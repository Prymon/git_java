package growing;

import java.io.UnsupportedEncodingException;

/**
 * @author Deamon String «–ªª±‡¬Îµƒ≤‚ ‘
 */
public class CharsetTest {
	public static void main(String[] args) {
		try {
			String str = "ƒ„ ‘ ‘";
			byte[] bytes = str.getBytes("utf-8");
			System.out.println(bytes.length);
			for (byte b : bytes) {
				System.out.print(b);
			}
			String str2;
			str2 = new String(bytes, "utf-8");
			System.out.println("\n" + str2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
