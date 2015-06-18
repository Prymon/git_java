package five_questions;

import java.lang.reflect.Array;


/**
 * ��дһ������ϲ��б�Ԫ�صĺ��������磺�����������б�Ϊ[a��B��C]��[1��2��3]����������[a��1��B��2��C��3]�� 
 * @author hfrz
 */
public class Quest2 {
	
	public static void main(String[] args) throws Exception {
		Integer[] arrs1 = {1,2,3,4},arrs2 = {7,8,9};
		Integer[] out = bin3(arrs1, arrs2, Integer.class);	//�������
		for(int t : out )
			System.out.print(t+" ");
	}
	
	/**
	 * ֻ��֧��һ�³��ȵ�������
	 * @param arrs1	����1
	 * @param arrs2	����2
	 * @return	�ϲ�������
	 * @throws Exception	����1��2����һ��
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
	 * ֧�����������ǿ�����ϲ�
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
	 * ֧��������������ĺϲ�������֧�ֻ���������������
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
