package test;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.Date;  
      
import jcifs.smb.SmbFile;  
import jcifs.smb.SmbFileInputStream;  
        
public class TestReadSmb {     
        public static void main(String[] args){     
                //String smbMachine="smb://test1:135790_test1@192.168.7.10/public/home/test1/Downloads/1.txt";  
        		String smbMachine="smb://10.12.5.151/1/1.txt";
                String localPath="C:/Users/lyf/Desktop/2.txt";     
                File file=readFromSmb(smbMachine,localPath);     
                removeFile(file);     
        }     
        
        /** ***   
         * 从smbMachine读取文件并存储到localpath指定的路径   
         *    
         * @param smbMachine   
         *             共享机器的文件,如smb://xxx:xxx@10.108.23.112/myDocument/测试文本.txt,xxx:xxx是共享机器的用户 名密码   
         * @param localpath   
         *            本地路径   
         * @return   
         */    
        public static File readFromSmb(String smbMachine,String localpath){     
            File localfile=null;     
            InputStream bis=null;     
            OutputStream bos=null;     
            try{     
                SmbFile rmifile = new SmbFile(smbMachine);     
                String filename=rmifile.getName();     
                bis=new BufferedInputStream(new SmbFileInputStream(rmifile));     
                localfile=new File(localpath+File.separator+filename);    
                System.out.println("localfile=="+localfile);  
                bos=new BufferedOutputStream(new FileOutputStream(localfile));     
                int length=rmifile.getContentLength();    
                System.out.println("length=="+length);  
                byte[] buffer=new byte[length];     
                Date date=new Date();     
                bis.read(buffer);    
                bos.write(buffer);   
      
                Date end=new Date();     
                int time= (int) ((end.getTime()-date.getTime())/1000);     
                if(time>0)     
                    System.out.println("用时:"+time+"秒 "+"速度:"+length/time/1024+"kb/秒");                 
            } catch (Exception e){      
                System.out.println(e.getMessage());     
                     
            }finally{     
                try {     
                    bos.close();     
                    bis.close();     
                } catch (IOException e) {     
                    e.printStackTrace();     
                }                 
            }     
            return localfile;     
        }     
        public static boolean removeFile(File file) {     
            return file.delete();     
        }     
    }    