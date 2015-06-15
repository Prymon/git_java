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
 * 配置文件操作工具类
 * @author Deamon
 */
public class XMLUtil {
	
	/**
	 * 私有化构造方法
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
	 * 读取指定xml文件指定节点下配置
	 * @param nodename	节点名称
	 * @param path	配置文件路径
	 * @return
	 * @throws DocumentException 读取文件出现错误
	 */
	public static Map<String,String> getNodeContentMaps(String nodename,String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		if(nodename!=null&&path!=null){
			File xmlfile = new File(path);
			Document doc = reader.read(xmlfile);
			//得到第二级名为nodename的子节点
			Element elementnode = doc.getRootElement().element(nodename);
			if(elementnode==null)
				return null;
			//遍历节点内容
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
		//参数为空
		if(filepath==null||filepath.isEmpty())
			return userpath;
		//格式化参数
		filepath = filepath.replace("/", "\\");
		if(filepath.startsWith("/")||filepath.startsWith("\\"))
			return userpath+filepath;
		return userpath+"\\"+filepath;
	}

}
