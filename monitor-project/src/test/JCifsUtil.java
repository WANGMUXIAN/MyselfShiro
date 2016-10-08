package test;

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
 
import jcifs.smb.SmbFile; 
import jcifs.smb.SmbFileInputStream; 
 
/**
 * ʹ��JCIFS��ȡԶ�̹����ļ�
 * @see ����jcifs�Ľ���,������һ��Ƭ,����̸����Զ���ļ�ָ�������繲���ļ�
 * @see JCIFS����Ϊhttp://jcifs.samba.org/,�Ժ�׼��д��һ��������,������JCifsUtil
 * @see ��������˵:JCIFS�Ƚ������ڵ��򻷾�,���򻷾��ͻ���鷳(������δ��֤),���http://jusescn.iteye.com/blog/757475
 * @create Apr 22, 2013 11:48:15 PM
 * @author ����<http://blog.csdn.net/jadyer>
 */ 
public class JCifsUtil { 
    public static void main(String[] args) { 
        getRemoteFile("jadyer", "myJavaSE", "192.168.8.2/�ҵĲ�������/", "D:/mylocal/"); 
        //getRemoteFile("jadyer", "myJavaSE", "192.168.8.2/�ҵĲ�������/ƽ�����н���.et", "D:/mylocal/");  
        System.out.println(System.getenv("JAVA_HOME")); 
    } 
     
     
    /**
     * ����Զ���ļ�������Ŀ¼
     * @param smbFile        Զ��SmbFile
     * @param localDirectory ���ش洢Ŀ¼,����Ŀ¼������ʱ���Զ�����,����Ŀ¼����ʱ������ѡ���Ƿ���ո�Ŀ¼�µ��ļ�,Ĭ��Ϊ�����
     * @return boolean �Ƿ񿽱��ɹ�
     */ 
    private static boolean copyRemoteFile(SmbFile smbFile, String localDirectory) { 
        SmbFileInputStream in = null; 
        FileOutputStream out = null; 
        try { 
            File[] localFiles = new File(localDirectory).listFiles(); 
            if(null == localFiles){ 
                //Ŀ¼�����ڵĻ�,�ʹ���Ŀ¼  
                //new File("D:/aa/bb.et").mkdirs()����aa�ļ����´���һ����Ϊbb.et���ļ���  
                new File(localDirectory).mkdirs(); 
            }else if(localFiles.length > 0){ 
//              for(File file : localFiles){  
//                  //��ձ���Ŀ¼�µ������ļ�  
//                  //new File("D:/aa/bb.et").delete()��ɾ��bb.et�ļ�,��aa�ļ��л�����  
//                  file.delete();  
//              }  
            } 
            in = new SmbFileInputStream(smbFile); 
            out = new FileOutputStream(localDirectory + smbFile.getName()); 
            byte[] buffer = new byte[1024]; 
            int len = -1; 
            while((len=in.read(buffer)) != -1){ 
                out.write(buffer, 0, len); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false; 
        } finally { 
            if(null != out){ 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            if(null != in){ 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
        return true; 
    } 
     
     
    /**
     * ��ȡԶ���ļ�
     * @param remoteUsername Զ��Ŀ¼�����û���
     * @param remotePassword Զ��Ŀ¼��������
     * @param remoteFilepath Զ���ļ���ַ,�ò�������IP��ͷ,��'192.168.8.2/aa/bb.java'����'192.168.8.2/aa/',�� '192.168.8.2/aa'�ǲ��Ե�
     * @param localDirectory ���ش洢Ŀ¼,�ò�������'/'��β,��'D:/'����'D:/mylocal/'
     * @return boolean �Ƿ��ȡ�ɹ�
     */ 
    public static boolean getRemoteFile(String remoteUsername, String remotePassword, String remoteFilepath, String localDirectory) { 
        boolean isSuccess = false; 
        if(remoteFilepath.startsWith("/") || remoteFilepath.startsWith("\\")){ 
            return isSuccess; 
        } 
        if(!(localDirectory.endsWith("/") || localDirectory.endsWith("\\"))){ 
            return isSuccess; 
        } 
        try { 
            SmbFile smbFile = new SmbFile("smb://" + remoteUsername + ":" + remotePassword + "@" + remoteFilepath); 
            if(smbFile.isDirectory()){ 
                for(SmbFile file : smbFile.listFiles()){ 
                    isSuccess = copyRemoteFile(file, localDirectory); 
                } 
            }else if(smbFile.isFile()){ 
                isSuccess = copyRemoteFile(smbFile, localDirectory); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return isSuccess; 
    } 
} 
