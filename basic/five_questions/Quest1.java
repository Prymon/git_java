package five_questions;

/**
 * 使用for循环、while循环和递归写出3个函数来计算给定数列的总和。
 *
 */
public class Quest1 {
	public static void main(String[] args) {
		int arrs[] = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(forSum(arrs));
		System.out.println(whileSum(arrs));
		System.out.println(recurSum(arrs,0));
		
	}
	
	//for
	private static int forSum(int[] arrs){
		int total = 0;
		for(int t:arrs)
			total+=t;
		return total;
	}
	
	//while
	private static int whileSum(int[] arrs){
		int total = 0;
		int i=0;
		while(i<arrs.length)
			total+=arrs[i++];
		return total;
	}
	
	//recursive
	private static int recurSum(int[] arrs,int i){
		int total = 0;
		if(i==arrs.length-1)
			return arrs[i];
		return total +=  arrs[i]+recurSum(arrs,i++);
	}
	
}
