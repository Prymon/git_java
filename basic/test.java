
public class test {

	private static final String ARMY_PLATE_STRING = "VKHBSLJMGC";	//���о����Ƶ���ͷ��ĸ
	public static void main(String[] args) {
		String plate = "��V56667";
		boolean b = ARMY_PLATE_STRING.contains(plate.substring(1, 2));
		System.out.println(b);
	}
}
