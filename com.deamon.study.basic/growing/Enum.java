package growing;

public class Enum {
	public static void main(String[] args) {
		colors color = colors.RED;
		System.out.println(color);
	}
}

/* 枚举 */
enum colors {
	RED("red"), BLUE("blue"), YELLOW("yellow");

	private String color;

	colors(String str) {
		this.color = str;
	}

	public String toString() {
		return this.color;
	}
}

/* 可用于 */