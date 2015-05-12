package five_questions;

/**
 * 编写一个能将给定非负整数列表中的数字排列成最大数字的函数。
 * 例如，给定[50，2，1,9]，最大数字为95021。
 */
public class Quest4 {
	private static int max = 0;
	
	public static void main(String[] args) {
		String[] strs = {"50","2","1","91"};
		quanpai(strs, 0);
		System.out.println(max);
	}

	/**
	 * 对元素进行全排列。
	 * @param strs
	 * @param start
	 */
	private static void quanpai(String[] strs , int start){
		if(start==strs.length-1){
			int i = Integer.parseInt(print(strs));
			if(i>max)
				max=i;
		}
		for(int i=start;i<strs.length;i++){	//包括和自己交换
			exchange(strs,start,i);
			quanpai(strs,start+1);
			exchange(strs,start,i);
		}
		
	}
	
	/**
	 * 交换给定下标元素
	 * @param strs
	 * @param a
	 * @param b
	 */
	private static void exchange(String[] strs,int a,int b){
		String temp = strs[b];
		strs[b] = strs[a];
		strs[a] = temp;
	}
	
	/**
	 * 讲数组转成字符串
	 * @param s
	 * @return
	 */
	private static String print(String[] s){
		StringBuilder sb = new StringBuilder();
		for(String t : s)
			sb.append(t);
		return sb.toString();
	}
}
