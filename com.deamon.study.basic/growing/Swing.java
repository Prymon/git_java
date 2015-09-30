package growing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Swing {
	public static void main(String[] args) {
		// setTest();
		// testList();
		String str = "дк";
		Integer itg = 22312;
		HashSet set = new HashSet();
		set.add(str);
		set.add(itg);
		Iterator ite = set.iterator();
		Object temp = ite.next();
		System.out.println(temp.hashCode());
		temp = ite.next();
		System.out.println(temp.hashCode());
	}

	private static void testList() {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		List<Integer> list = new ArrayList<Integer>();
		list.add(a);
		list.add(a);
		System.out.println(list.size());
	}

	private static void setTest() {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		System.out.println((a == b) + "--" + a.hashCode() + b.hashCode());
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(a);
		set.add(b);
		System.out.println(set.size());
		System.out.println();
		Iterator<Integer> ite = set.iterator();
		Integer temp = ite.next();
		System.out.println(temp == a);
		System.out.println(temp == b);
	}
}

/*






*/