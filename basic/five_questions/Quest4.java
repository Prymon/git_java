package five_questions;

/**
 * ��дһ���ܽ������Ǹ������б��е��������г�������ֵĺ�����
 * ���磬����[50��2��1,9]���������Ϊ95021��
 */
public class Quest4 {
	private static int max = 0;
	
	public static void main(String[] args) {
		String[] strs = {"50","2","1","91"};
		quanpai(strs, 0);
		System.out.println(max);
	}

	/**
	 * ��Ԫ�ؽ���ȫ���С�
	 * @param strs
	 * @param start
	 */
	private static void quanpai(String[] strs , int start){
		if(start==strs.length-1){
			int i = Integer.parseInt(print(strs));
			if(i>max)
				max=i;
		}
		for(int i=start;i<strs.length;i++){	//�������Լ�����
			exchange(strs,start,i);
			quanpai(strs,start+1);
			exchange(strs,start,i);
		}
		
	}
	
	/**
	 * ���������±�Ԫ��
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
	 * ������ת���ַ���
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
