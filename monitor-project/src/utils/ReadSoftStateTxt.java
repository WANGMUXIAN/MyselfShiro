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
     * ���ܣ�Java��ȡtxt�ļ�������
     * ���裺1���Ȼ���ļ����
     * 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
     * 3����ȡ������������Ҫ��ȡ�����ֽ���
     * 4��һ��һ�е������readline()��
     * ��ע����Ҫ���ǵ����쳣���
     * @param filePath
     */
    public static LinkedList<String> readTxtFile(String filePath){
    	LinkedList<String> list = new LinkedList<String>();
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));    //���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;  
                    while((lineTxt = bufferedReader.readLine()) != null ){
                    	if(!lineTxt.equals("")&&lineTxt.indexOf("#")==-1)
                        list.add(lineTxt);
                    }
                    read.close();
        }else{
            String message = "�Ҳ���ָ���ļ�";
            list.add(message);
        }
        } catch (Exception e) {
        	String message = "��ȡ�ļ����ݳ���";
            list.add(message);
        }
		return list;
    }   
}

