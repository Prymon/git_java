package five_questions;

import java.lang.reflect.Array;


/**
 * 编写一个交错合并列表元素的函数。例如：给定的两个列表为[a，B，C]和[1，2，3]，函数返回[a，1，B，2，C，3]。 
 * @author hfrz
 */
public class Quest2 {
	
	public static void main(String[] args) throws Exception {
		Integer[] arrs1 = {1,2,3,4},arrs2 = {7,8,9};
		Integer[] out = bin3(arrs1, arrs2, Integer.class);	//测试语句
		for(int t : out )
			System.out.print(t+" ");
	}
	
	/**
	 * 只能支持一致长度的数组结合
	 * @param arrs1	数组1
	 * @param arrs2	数组2
	 * @return	合并后数组
	 * @throws Exception	数组1，2长度一致
	 */
	private static int[] bin(int[] arrs1,int[] arrs2) throws Exception{
		if(arrs1==null||arrs2==null)
			throw new Exception("arrs is null");
		int[] outs = new int[arrs1.length+arrs2.length];
		int a = arrs1.length;
		int b = arrs2.length;
		if(a!=b)
			throw new Exception("length invalidate");
		for (int i = 0; i < a; i++) {
			outs[i*2] = arrs1[i];
			outs[i*2+1] = arrs2[i];
		}
		return outs;
	}
	
	/**
	 * 支持任意两个非空数组合并
	 * @param arrs1
	 * @param arrs2
	 * @return
	 * @throws Exception
	 */
	private static int[] bin2(int[] arrs1,int[] arrs2) throws Exception{
		if(arrs1==null||arrs2==null)
			throw new Exception("arrs is null");
		int[] outs = new int[arrs1.length+arrs2.length];
		int a = arrs1.length;
		int b = arrs2.length;
		int[] longs,shorts;
		longs = (a>=b?arrs1:arrs2);
		shorts = (a>=b?arrs2:arrs1);
		for (int i = 0; i < shorts.length; i++) {
			outs[2*i] = arrs1[i];
			outs[2*i+1] = arrs2[i];
		}
		int j = 2*shorts.length;
		for (int i = shorts.length; i < longs.length; i++) {
			outs[j++] = longs[i];
		}
		return outs;
	}
	
	/**
	 * 支持任意两个数组的合并。但不支持基本数据类型数组
	 * @param arrs1
	 * @param arrs2
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private static <T>	T[] bin3(T[] arrs1,T[] arrs2,Class<?> type) throws Exception{
		if(arrs1==null||arrs2==null)
			throw new Exception("arrs is null");
		int a = arrs1.length;
		int b = arrs2.length;
		T[] outs = (T[]) Array.newInstance(type,a+b); 
		T[] longs,shorts;
		longs = (a>=b?arrs1:arrs2);
		shorts = (a>=b?arrs2:arrs1);
		for (int i = 0; i < shorts.length; i++) {
			outs[2*i] = arrs1[i];
			outs[2*i+1] = arrs2[i];
		}
		int j = 2*shorts.length;
		for (int i = shorts.length; i < longs.length; i++) {
			outs[j++] = longs[i];
		}
		return outs;
	}
	
}
