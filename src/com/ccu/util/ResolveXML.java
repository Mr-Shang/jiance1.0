package com.ccu.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;



/**
 * 解析XML文件
 * @author CCU
 *
 */
public class ResolveXML {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map = resloveXML();
		System.out.println("获取的规则1为：" + map.get("regular1"));
	}
	
	/**
	 * 解析xml文件
	 * @return Map<String, String> map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> resloveXML() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			 SAXBuilder sb=new SAXBuilder(); 
			 Document doc=sb.build("jiance1.0_profile.xml"); //构造文档对象
			 Element root=doc.getRootElement(); //获取根元素
			 List list=root.getChildren("regular-list");//取名字为regular-list的所有元素 
			 Element element=(Element)list.get(0); 
			 // String name=element.getAttributeValue("name"); //获取标签中的属性
			 String regular1=element.getChildText("regular1");//取regular-list子元素指定标签名的标签内的文本内容 
			 String regular2=element.getChildText("regular2");
			 String regular3=element.getChildText("regular3");
			 System.out.println("规则1:" + regular1);
			 System.out.println("规则2:" + regular2);
			 System.out.println("规则3:" + regular3);
			 map.put("regular1", regular1);
			 map.put("regular2", regular2);
			 map.put("regular3", regular3);
			 return map;
		}catch(Exception e) {
			System.out.println("解析xml文件失败");
			e.printStackTrace();
			return null;
		}
	}
}
