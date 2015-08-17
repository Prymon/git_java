package com.intedio.test.protocol;

public final class DataServiceConstant {
	
	
	// file name size
	public static int FILE_NAME_SIZE = 128;
	public static int FILE_PATH_SIZE = 128;
	
	public static int ID_SIZE = 64;
	
	public static int BASE64_SIZE = 24;
	
	public static String MAGIC_HEADER = "hfrz";
	
	public static int MAGIC_LENGTH = MAGIC_HEADER.getBytes().length;
	
	public static enum RETURN_CODE
	{
		SUCCESS ,
		FAIL ,
		OTHER 	
	}

}
