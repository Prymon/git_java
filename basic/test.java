
public class test {

	private static final String ARMY_PLATE_STRING = "VKHBSLJMGC";	//所有军车牌的牌头字母
	public static void main(String[] args) {
		String plate = "蒙V56667";
		boolean b = ARMY_PLATE_STRING.contains(plate.substring(1, 2));
		System.out.println(b);
	}
}
