package com.intedio.test.protocol;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.google.common.primitives.Ints;
import com.intedio.test.protocol.DataServiceConstant.RETURN_CODE;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





public class BusinessServicePacket extends ServicePacket {
	
	public Map<String, Object> textDataMap = new HashMap<String, Object>();
	public Map<String, byte[]> binaryDataMap = new HashMap<String, byte[]>();
	public BusinessServicePacket(){}
	
	public BusinessServicePacket(RETURN_CODE code, String message)
	{
		textDataMap.put("returnCode", code.ordinal());
		textDataMap.put("msg", message);
	}
	
	public int getTextDataLength()
	{
		String jsonString = JSONArray.fromObject(textDataMap).toString();
		return jsonString.length();
	}
	
	public int getBinaryDataLength()
	{
		int length = 0;
		for( String key: binaryDataMap.keySet())
		{
			length += Ints.BYTES * 2 + key.getBytes().length + binaryDataMap.get(key).length;
		}
		return length + Ints.BYTES;
	}
	
	@Override
	public Boolean parseFromData(byte[] packet) {
		
		try 
		{
			int offset = 0;
			String magicHeader = new String(packet,offset,DataServiceConstant.MAGIC_LENGTH);
			offset += DataServiceConstant.MAGIC_LENGTH;
			if (!DataServiceConstant.MAGIC_HEADER.equalsIgnoreCase(magicHeader))
			{
				return false;
			}
			int textDataLength = ByteBuffer.wrap(packet, offset, Ints.BYTES).getInt();
			offset += Ints.BYTES;
			String textDataString = new String(packet, offset, textDataLength);
			offset += textDataLength;
			this.parseTextData(textDataString);
			ByteBuffer.wrap(packet, offset, Ints.BYTES).getInt();
			offset += Ints.BYTES;
			int binaryDataCount = ByteBuffer.wrap(packet, offset, Ints.BYTES).getInt();
			offset += Ints.BYTES;
			for ( int i = 0 ; i < binaryDataCount; i++)
			{
				int keyLength = ByteBuffer.wrap(packet, offset, Ints.BYTES).getInt();
				offset += Ints.BYTES;
				String key = new String(packet, offset, keyLength);
				offset += keyLength;
				int dataLength = ByteBuffer.wrap(packet, offset, Ints.BYTES).getInt();
				offset += Ints.BYTES;
				binaryDataMap.put(key, ByteBuffer.allocate(dataLength).put(packet, offset, dataLength).array());
				offset += dataLength;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	public void parseTextData(String jsonString)
	{
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		
		for( Object key: jsonObject.keySet())
		{
			textDataMap.put(key.toString(), jsonObject.get(key).toString());
		}

	}
	@Override
	public Boolean readFromBuffer(ByteBuffer buffer) {
		return parseFromData(buffer.array());
	}
	
	@Override
	public Boolean readFromStream(DataInputStream in) {
		// TODO Auto-generated method stub
		try 
		{
			
			byte[] magicHeader =  new byte[DataServiceConstant.MAGIC_LENGTH];
			in.readFully(magicHeader);
			
			if (!DataServiceConstant.MAGIC_HEADER.equalsIgnoreCase(new String(magicHeader)))
			{
				return false;
			}
			int textDataLength = in.readInt();
			byte[] textDataBytes = new byte[textDataLength];
			in.readFully(textDataBytes);
			this.parseTextData(new String(textDataBytes));
			in.readInt();
			int binaryDataCount = in.readInt();
			for( int i = 0; i < binaryDataCount; i++)
			{
				int keyLength = in.readInt();
				byte[] keyBytes = new byte[keyLength];
				in.readFully(keyBytes);
				String key = new String(keyBytes);
				int dataLength = in.readInt();
				byte[] dataBytes = new byte[dataLength];
				in.readFully(dataBytes);
				binaryDataMap.put(key, dataBytes);
					
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			return false;
		}		
	}

	@Override
	public ByteBuffer convertToBuffer() {
		
		try 
		{
			String jsonString = JSONObject.fromObject(textDataMap).toString();
			int textDataLength = jsonString.getBytes().length;
			int binaryDataLength = getBinaryDataLength();
			ByteBuffer byteBuffer = ByteBuffer.allocate(DataServiceConstant.MAGIC_LENGTH + Ints.BYTES *2 + textDataLength + binaryDataLength );
			byteBuffer.put(DataServiceConstant.MAGIC_HEADER.getBytes());
			byteBuffer.putInt(textDataLength);
			byteBuffer.put(jsonString.getBytes());
			byteBuffer.putInt(binaryDataLength);
			byteBuffer.putInt(binaryDataMap.size());
			for( String key: binaryDataMap.keySet() )
			{
				byteBuffer.putInt(key.getBytes().length);
				byteBuffer.put(key.getBytes());
				byteBuffer.putInt(binaryDataMap.get(key).length);
				byteBuffer.put(binaryDataMap.get(key));
			}
			return byteBuffer;
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void write(DataOutputStream out) {
		// TODO Auto-generated method stub
		try 
		{
			String jsonString = JSONObject.fromObject(textDataMap).toString();
			//System.out.println(jsonString);  
			int textDataLength = jsonString.getBytes().length;
			int binaryDataLength = getBinaryDataLength();
			out.write(DataServiceConstant.MAGIC_HEADER.getBytes());
			out.writeInt(textDataLength);
			out.write(jsonString.getBytes());
			out.writeInt(binaryDataLength);
			out.writeInt(binaryDataMap.size());
			for( String key: binaryDataMap.keySet())
			{
				out.writeInt(key.getBytes().length);
				out.write(key.getBytes());
				out.writeInt(binaryDataMap.get(key).length);
				out.write(binaryDataMap.get(key));
			}	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public int getPacketLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addFilePath(String filePath)
	{
		try 
		{
			if ( filePath != "")
				{
					String basicString = textDataMap.get("basic").toString();
					JSONObject jsonObject = JSONObject.fromObject(basicString);	
					jsonObject.put("SnapShotPath", filePath);
					textDataMap.put("basic", jsonObject.toString());
				}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
	}

	public Map<String, String> convertToJson()
	{
		Map<String, String>  mergeMap = new HashMap<String, String>();
		Map<String, Object>  binaryMap = new HashMap<String, Object>();
		Map<String, String>  jsonMap = new HashMap<String, String>();
		mergeMap.put("textMap",JSONObject.fromObject(textDataMap).toString());
		for( String key: binaryDataMap.keySet())
		{
			binaryMap.put(key, base64Encoder(binaryDataMap.get(key)));
		}
		mergeMap.put("binaryMap", JSONObject.fromObject(binaryMap).toString());
		String id = textDataMap.get("carId").toString();
		String jsonString = JSONObject.fromObject(mergeMap).toString();
		jsonMap.put(id, jsonString);
		return jsonMap;
	}
	
	
	public void parseFromJson(String jsonString)
	{
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject textMapJsonObject = JSONObject.fromObject(jsonObject.getString("textMap"));
		JSONObject binaryMapJsonObject = JSONObject.fromObject(jsonObject.getString("binaryMap"));
		for ( Object key: textMapJsonObject.keySet())
		{
			String keyString = (String)key;
			textDataMap.put(keyString, textMapJsonObject.getString(keyString));
		}
		for ( Object key: binaryMapJsonObject.keySet())
		{
			String keyString = (String)key;
			binaryDataMap.put(keyString,base64Decoder(binaryMapJsonObject.getString(keyString)));
		}
	}
}