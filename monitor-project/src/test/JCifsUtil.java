package test;

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
 
import jcifs.smb.SmbFile; 
import jcifs.smb.SmbFileInputStream; 
 
/**
 * 使用JCIFS获取远程共享文件
 * @see 关于jcifs的介绍,网上有一大片,这里谈到的远程文件指的是网络共享文件
 * @see JCIFS官网为http://jcifs.samba.org/,以后准备写成一个工具类,故命名JCifsUtil
 * @see 据网络所说:JCIFS比较适用于单域环境,多域环境就会很麻烦(本人尚未验证),详见http://jusescn.iteye.com/blog/757475
 * @create Apr 22, 2013 11:48:15 PM
 * @author 玄玉<http://blog.csdn.net/jadyer>
 */ 
public class JCifsUtil { 
    public static void main(String[] args) { 
        getRemoteFile("jadyer", "myJavaSE", "192.168.8.2/我的测试用例/", "D:/mylocal/"); 
        //getRemoteFile("jadyer", "myJavaSE", "192.168.8.2/我的测试用例/平安银行接入.et", "D:/mylocal/");  
        System.out.println(System.getenv("JAVA_HOME")); 
    } 
     
     
    /**
     * 拷贝远程文件到本地目录
     * @param smbFile        远程SmbFile
     * @param localDirectory 本地存储目录,本地目录不存在时会自动创建,本地目录存在时可自行选择是否清空该目录下的文件,默认为不清空
     * @return boolean 是否拷贝成功
     */ 
    private static boolean copyRemoteFile(SmbFile smbFile, String localDirectory) { 
        SmbFileInputStream in = null; 
        FileOutputStream out = null; 
        try { 
            File[] localFiles = new File(localDirectory).listFiles(); 
            if(null == localFiles){ 
                //目录不存在的话,就创建目录  
                //new File("D:/aa/bb.et").mkdirs()会在aa文件夹下创建一个名为bb.et的文件夹  
                new File(localDirectory).mkdirs(); 
            }else if(localFiles.length > 0){ 
//              for(File file : localFiles){  
//                  //清空本地目录下的所有文件  
//                  //new File("D:/aa/bb.et").delete()会删除bb.et文件,但aa文件夹还存在  
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
     * 获取远程文件
     * @param remoteUsername 远程目录访问用户名
     * @param remotePassword 远程目录访问密码
     * @param remoteFilepath 远程文件地址,该参数需以IP打头,如'192.168.8.2/aa/bb.java'或者'192.168.8.2/aa/',如 '192.168.8.2/aa'是不对的
     * @param localDirectory 本地存储目录,该参数需以'/'结尾,如'D:/'或者'D:/mylocal/'
     * @return boolean 是否获取成功
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
