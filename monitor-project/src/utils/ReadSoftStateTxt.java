package utils;
 
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.TimerTask;

import service.impl.SFastOETServlet;
 
public class ReadSoftStateTxt {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static LinkedList<String> readTxtFile(String filePath){
    	LinkedList<String> list = new LinkedList<String>();
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){         //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));    //考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;  
                    while((lineTxt = bufferedReader.readLine()) != null ){
                    	if(!lineTxt.equals("")&&lineTxt.indexOf("#")==-1)
                        list.add(lineTxt);
                    }
                    read.close();
        }else{
            String message = "找不到指定文件";
            list.add(message);
        }
        } catch (Exception e) {
        	String message = "读取文件内容出错";
            list.add(message);
        }
		return list;
    }   
}

