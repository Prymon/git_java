package com.intedio.test.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


public abstract  class ServicePacket {
	
	
	public static  Logger logger = Logger.getLogger(ServicePacket.class);
	
	public abstract  int getPacketLength();
	
	
	public  abstract Boolean parseFromData( byte[] packet);
	
	public String getBase64String(byte[] data)
	{
		
			MessageDigest mDigest;
			try {
				mDigest = MessageDigest.getInstance("MD5");
				byte[] md5Bytes = mDigest.digest(data);
				return new sun.misc.BASE64Encoder().encode(md5Bytes);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
			return null;
	}
	
	
	public String base64Encoder(byte[] data)
	{
		return new sun.misc.BASE64Encoder().encode(data);
	}
	public byte[] base64Decoder(String data)
	{
		try {
			byte [] decodeData = new sun.misc.BASE64Decoder().decodeBuffer(data);
			return decodeData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("base64 decoder error");
		}
		return null;
	}
	
	public abstract Boolean readFromBuffer(ByteBuffer buffer) ;
		
	public Boolean checkDataConstant(byte[] base64Bytes, byte[] data) 
	{

			String base64String = new String(base64Bytes);	
			String dataBase64String;
			dataBase64String = getBase64String(data);
			if (! base64String.equals(dataBase64String))
			{
				return false;
			}
			return true;
	}
	
	public abstract Boolean readFromStream(DataInputStream in) ;

	
	
	public abstract java.nio.ByteBuffer  convertToBuffer() ;

	
	public byte[]  getBytes()
	{
		return convertToBuffer().array();
	}
	
	public abstract void write(DataOutputStream out);


}
