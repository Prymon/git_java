package five_questions;

/**
 * 编写一个计算前100位斐波那契数的函数。根据定义，斐波那契序列的前两位数字是0和1，随后的每个数字是前两个数字的和。
 * 例如，前10位斐波那契数为：0，1，1，2，3，5，8，13，21，34。
 */
public class Quest3 {
	
	public static void main(String[] args) {
		int[] input = new int[100];
		input[98] = 1;
		input = get(400);
		StringBuilder sb = new StringBuilder();
		for(int t:input)
			sb.append(t);
		System.out.println(sb);
	}
	
	private static int[] get(int x){
		if(x==0)
			return new int[100];
		if(x==1){
			int ret[] = new int[100];
			ret[99] = 1;
			return ret;
		}
			
		int[] a = new int[100];
		int[] b = new int[100];
		b[99]=1;
		int[] temp;
		for (int i = 0; i < x-1; i++) {
			temp = b.clone();
			b=addArrs(a, b);
			a=temp;
		}
		return b;
	}
	
	private static int[] addArrs(int[] arr1,int[] arr2){
		int[] ret = new int[100];
		int jinwei = 0,yushu=0;
		for (int i = 99; i >= 0; i--) {
			yushu = (arr1[i]+arr2[i])%10;
			ret[i] = yushu+jinwei;
			jinwei = (arr1[i]+arr2[i])/10;
		}
		return ret;
	}
	
	
}
