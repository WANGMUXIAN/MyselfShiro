package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
 * 将组合成功的字符串写入文件中保存
 */
public class WriteProductFileName {
	public static void write(String FileName) throws IOException
	{
		FileWriter fileWriter = null;
		String fileName=new String("D://ProductName.txt");
		if (isExists(fileName))
		{
			fileWriter = new FileWriter(fileName ,true);
		}
		else
		{
			fileWriter = new FileWriter(fileName ,true);
		} 
		fileWriter.write(FileName);
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static boolean isExists(String s)
	{
		File f = new File(s);
		if(f.exists())
			return true;
		else
			return false;
	}

}
