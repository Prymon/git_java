package growing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;;

public class ListStudy {
	@Test
	// list是否可以用增强for
	public void test1() {
		List<String> list = new LinkedList<String>();
		list.add("str1");
		list.add("str2");
		list.add("str3");
		for (String temp : list) {
			System.out.println(temp);
		}
	}

	@Test
	// list是否有序
	public void test2() {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 10; i > 0; i--) {
			set.add(i);
		}
		Iterator<Integer> ite = set.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}

	}

}
