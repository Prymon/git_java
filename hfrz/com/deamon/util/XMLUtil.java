package com.deamon.util;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * �����ļ�����������
 * @author Deamon
 */
public class XMLUtil {
	
	/**
	 * ˽�л����췽��
	 */
	private XMLUtil(){}
	
	public static void main(String[] args) {
		try {
			String path = getUserPath("/conf/config.xml");
			Map<String, String> nodes = getNodeContentMaps("runtime", path);
			System.out.println(nodes);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡָ��xml�ļ�ָ���ڵ�������
	 * @param nodename	�ڵ�����
	 * @param path	�����ļ�·��
	 * @return
	 * @throws DocumentException ��ȡ�ļ����ִ���
	 */
	public static Map<String,String> getNodeContentMaps(String nodename,String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		if(nodename!=null&&path!=null){
			File xmlfile = new File(path);
			Document doc = reader.read(xmlfile);
			//�õ��ڶ�����Ϊnodename���ӽڵ�
			Element elementnode = doc.getRootElement().element(nodename);
			if(elementnode==null)
				return null;
			//�����ڵ�����
			Iterator nodeite = elementnode.elementIterator();
			Map<String, String> nodesmap = new HashMap<String, String>();
			while(nodeite.hasNext()){
				Element ele = (Element) nodeite.next();
				nodesmap.put(ele.getName(), ele.getText());
			}
			return nodesmap;
		}
		return null;
	}
	
	public static String getUserPath(String filepath){
		String userpath = System.getProperty("user.dir");
		//����Ϊ��
		if(filepath==null||filepath.isEmpty())
			return userpath;
		//��ʽ������
		filepath = filepath.replace("/", "\\");
		if(filepath.startsWith("/")||filepath.startsWith("\\"))
			return userpath+filepath;
		return userpath+"\\"+filepath;
	}

}
