package utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXML {
	private static String filepath;
	private static String chaokuaiFilePath;
	private static String kuaisuFilePath;
	private static String zuizhongFilePath;
	static{
		filepath = ParseXML.class.getClassLoader().getResource("filepaths.xml").getPath();
		chaokuaiFilePath = ParseXML.class.getClassLoader().getResource("chaokuaiFilePaths.xml").getPath();
		kuaisuFilePath = ParseXML.class.getClassLoader().getResource("kuaisuFilePaths.xml").getPath();
		zuizhongFilePath = ParseXML.class.getClassLoader().getResource("zuizhongFilePaths.xml").getPath();
	}
	public static String[] ReadXML() throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(filepath);
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		String[] fp = new String[20];
		for (int i= 0;i<childElements.size();i++) {
			fp[i] = childElements.get(i).elementText("value");		
		}
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0; i<=fp.length-1; i++)
		{
			if(fp[i]!=null){
				al.add(fp[i]);
			}
			
		}
		String[] tem = new String[al.size()];
		for(int i=0; i<=al.size()-1; i++)
		{
			tem[i]=al.get(i);
		}
		return tem;
	}
	public static String[] ReadChaoKuaiXML() throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(chaokuaiFilePath);
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		String[] fp = new String[20];
		for (int i= 0;i<childElements.size();i++) {
			fp[i] = childElements.get(i).elementText("value");		
		}
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0; i<=fp.length-1; i++)
		{
			if(fp[i]!=null){
				al.add(fp[i]);
			}
			
		}
		String[] tem = new String[al.size()];
		for(int i=0; i<=al.size()-1; i++)
		{
			tem[i]=al.get(i);
		}
		return tem;
	}
	public static String[] ReadKuaiSuXML() throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(kuaisuFilePath);
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		String[] fp = new String[20];
		for (int i= 0;i<childElements.size();i++) {
			fp[i] = childElements.get(i).elementText("value");		
		}
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0; i<=fp.length-1; i++)
		{
			if(fp[i]!=null){
				al.add(fp[i]);
			}
			
		}
		String[] tem = new String[al.size()];
		for(int i=0; i<=al.size()-1; i++)
		{
			tem[i]=al.get(i);
		}
		return tem;
	}
	public static String[] ReadZuiZhongXML() throws Exception {
		SAXReader reader = new SAXReader();
		File file = new File(zuizhongFilePath);
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		String[] fp = new String[20];
		for (int i= 0;i<childElements.size();i++) {
			fp[i] = childElements.get(i).elementText("value");		
		}
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0; i<=fp.length-1; i++)
		{
			if(fp[i]!=null){
				al.add(fp[i]);
			}
			
		}
		String[] tem = new String[al.size()];
		for(int i=0; i<=al.size()-1; i++)
		{
			tem[i]=al.get(i);
		}
		return tem;
	}
}