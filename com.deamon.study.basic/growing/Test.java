package growing;

import java.util.HashMap;
import java.util.Map;

public class Test {
	private int sum = 0;

	public void JumpFloor(int target) {
		if (target == 1) {
			sum++;
			return;
		}
		if (target == 2) {
			sum += 2;
			return;
		} else {
			JumpFloor(target - 1);
			JumpFloor(target - 2);
		}
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.JumpFloor(10);
		System.out.println(t.sum);
	}

}
