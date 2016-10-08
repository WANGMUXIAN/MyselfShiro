package test;

import java.io.BufferedOutputStream;   
import java.io.BufferedReader;   
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileNotFoundException;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.InputStreamReader;   
import java.io.OutputStream;   
import java.net.MalformedURLException;   
import java.net.UnknownHostException;   
import java.util.ArrayList;   
import java.util.List;   
    
import jcifs.smb.SmbException;   
import jcifs.smb.SmbFile;   
import jcifs.smb.SmbFileInputStream;   
import jcifs.smb.SmbFileOutputStream;   
    
public class RemoteFileUtil {      
        
    private ArrayList filelist = new ArrayList();   
    //RemoteConfigUtil rc = new RemoteConfigUtil();   
    
    private String remoteHostIp;  //Զ������IP      
    private String account;       //��½�˻�      
    private String password;      //��½����      
    private String shareDocName;  //�����ļ�������      
           
    /**     
     * Ĭ�Ϲ��캯��     
     */    
//    public RemoteFileUtil(){     
//        this.remoteHostIp = rc.getREMOTE_HOST_IP();      
//        this.account = rc.getLOGIN_ACCOUNT();      
//        this.password = rc.getLOGIN_PASSWORD();      
//        this.shareDocName = rc.getSHARE_DOC_NAME();      
//    }      
      public RemoteFileUtil(){     
      this.remoteHostIp = "192.168.7.10";      
      this.account = "test1";      
      this.password = "135790_test1";      
      this.shareDocName = "";      
      }      
    /**     
     * ���캯��     
     * @param remoteHostIp  Զ������Ip     
     * @param account       ��½�˻�     
     * @param password      ��½����     
     * @param sharePath     �����ļ���·��     
     */    
    public RemoteFileUtil(String remoteHostIp, String account, String password,String shareDocName) {      
        this.remoteHostIp = remoteHostIp;      
        this.account = account;      
        this.password = password;      
        this.shareDocName = shareDocName;      
    }         
           
    /**     
     * ��Զ�̹����ļ����ж�ȡ������     
     * @param remoteFileName  �ļ���  ˵��������Ϊ����Ŀ¼�µ����·��     
     *  ��Զ���ļ���·��Ϊ��shareDoc\test.txt,�����Ϊtest.txt(����shareDocΪ����Ŀ¼����);     
     *  ��Զ���ļ���·��Ϊ��shareDoc\doc\text.txt,�����Ϊdoc\text.txt;     
     * @return  �ļ���������     
     */    
    public List<String> readFile(String remoteFileName){      
        SmbFile smbFile = null;      
        BufferedReader reader = null;      
        List<String> resultLines = null;      
        //���������ַ���,��ȡ���ļ�����      
        String conStr = null;      
        conStr = "smb://"+account+":"+password+"@"+remoteHostIp+"/"+shareDocName+"/"+remoteFileName;      
        try {      
            smbFile = new SmbFile(conStr);      
        } catch (MalformedURLException e) {      
            e.printStackTrace();      
        }      
        //����reader      
        try {      
            reader = new BufferedReader(new InputStreamReader(new SmbFileInputStream(smbFile)));      
        } catch (SmbException e) {      
            e.printStackTrace();      
        } catch (MalformedURLException e) {      
            e.printStackTrace();      
        } catch (UnknownHostException e) {      
            e.printStackTrace();      
        }             
        //ѭ�����ļ����ж�ȡ      
        String line;      
        try {      
            line = reader.readLine();      
            if(line != null && line.length()>0){      
                resultLines = new ArrayList<String>();      
            }      
            while (line != null) {      
                resultLines.add(line);      
                line = reader.readLine();      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        //����      
        return resultLines;      
    }      
           
    /**     
     * ��Զ�̹����ļ�����д��     
     * @param is                �����ļ���������     
     * @param remoteFileName    Զ���ļ���    ˵��������Ϊ����Ŀ¼�µ����·��     
     *  ��Զ���ļ���·��Ϊ��shareDoc\test.txt,�����Ϊtest.txt(����shareDocΪ����Ŀ¼����);     
     *  ��Զ���ļ���·��Ϊ��shareDoc\doc\text.txt,�����Ϊdoc\text.txt;     
     * @return       
     */    
    public boolean writeFile(InputStream is,String remoteFileName){      
        SmbFile smbFile = null;      
        OutputStream os = null;      
        byte[] buffer = new byte[1024*8];      
        //���������ַ���,��ȡ���ļ�����      
        String conStr = null;      
        conStr = "smb://"+account+":"+password+"@"+remoteHostIp+"/"+shareDocName+"/"+remoteFileName;      
        try {      
            smbFile = new SmbFile(conStr);      
        } catch (MalformedURLException e) {      
            e.printStackTrace();      
            return false;      
        }      
               
        //��ȡԶ���ļ��������д�ļ���Զ�̹����ļ���      
        try {      
            os = new BufferedOutputStream(new SmbFileOutputStream(smbFile));      
            while((is.read(buffer))!=-1){      
                os.write(buffer);             
            }      
        } catch (Exception e) {      
            e.printStackTrace();      
            return false;      
        }       
               
        return true;      
    }      
           
           
    /**     
     * ��Զ�̹����ļ�����д������     
     * @param localFileName   Ҫд��ı����ļ�ȫ��     
     * @param remoteFileName  Զ���ļ���    ˵��������Ϊ����Ŀ¼�µ����·��     
     *  ��Զ���ļ���·��Ϊ��shareDoc\test.txt,�����Ϊtest.txt(����shareDocΪ����Ŀ¼����);     
     *  ��Զ���ļ���·��Ϊ��shareDoc\doc\text.txt,�����Ϊdoc\text.txt;     
     * @return     
     */    
    public boolean writeFile(String localFileFullName ,String remoteFileName){      
        try {      
            return writeFile(new FileInputStream(new File(localFileFullName)),remoteFileName);      
        } catch (FileNotFoundException e) {      
            e.printStackTrace();      
            return false;      
        }      
    }      
           
    /**     
     * ��Զ�̹����ļ�����д������     
     * @param localFileName   Ҫд��ı����ļ�     
     * @param remoteFileName  Զ���ļ���    ˵��������Ϊ����Ŀ¼�µ����·��     
     *  ��Զ���ļ���·��Ϊ��shareDoc\test.txt,�����Ϊtest.txt(����shareDocΪ����Ŀ¼����);     
     *  ��Զ���ļ���·��Ϊ��shareDoc\doc\text.txt,�����Ϊdoc\text.txt;     
     * @return     
     */    
    public boolean writeFile(File localFile ,String remoteFileName){      
        try {      
            return writeFile(new FileInputStream(localFile),remoteFileName);      
        } catch (FileNotFoundException e) {      
            e.printStackTrace();      
            return false;      
        }      
    }      
    
           
    /**     
     * ��Զ�̹����ļ������ļ�     
     * @return  �����ļ�    
     */    
    public List<String> getFiles(){      
        SmbFile smbFile = null;      
        BufferedReader reader = null;      
        List<String> resultLines = new ArrayList();      
        //���������ַ���,��ȡ���ļ�����      
        String conStr = null;      
        conStr = "smb://"+account+":"+password+"@"+remoteHostIp+"/"+shareDocName+"/";      
        try {      
            smbFile = new SmbFile(conStr);      
        } catch (MalformedURLException e) {      
            e.printStackTrace();      
        }      
        //����reader      
        try {      
          String[] a = smbFile.list();   
          for(int i=0;i<a.length;i++){   
            resultLines.add(a[i]);   
            System.out.println(a[i]);   
          }   
        } catch (SmbException e) {      
            e.printStackTrace();      
        } catch (Exception e) {      
            e.printStackTrace();      
        }             
        //����      
        return resultLines;      
    }      
        
    /** �ڱ���Ϊ�������������ļ���    
     * @param remoteUrl Զ�̼����·��    
     */    
    public void smbMkDir(String name) {   
        // ע��ʹ��jcifs-1.3.15.jar��ʱ�� ����Զ�̼������ʱ��������ǰ����Ҫ����Smb   
        // ����һ��Զ���ļ�����   
        String conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName;   
        SmbFile remoteFile;   
        try {   
            remoteFile = new SmbFile(conStr + "/" + name);   
            if (!remoteFile.exists()) {   
                remoteFile.mkdir();// ����Զ���ļ���   
            }   
        } catch (MalformedURLException e) {   
            e.printStackTrace();   
        } catch (SmbException e) {   
            e.printStackTrace();   
        }   
    }   
        
    /**   
     * ɾ���ļ���   
     * @param folderPath �����ļ�����һ���ļ�����   
     * @return   
     */ 
    public void delFolder(String folderPath) {   
        //String conStr = "smb://"+LOGIN_ACCOUNT+":"+LOGIN_PASSWORD+"@"+remoteHostIp+"/"+shareDocName;    
        try {   
            delAllFile(folderPath); //ɾ����������������   
            String filePath = folderPath;   
            filePath = filePath.toString();   
                
            SmbFile myFilePath = new SmbFile(filePath);   
            myFilePath.delete(); //ɾ�����ļ���   
        }   
        catch (Exception e) {   
            String message = ("ɾ���ļ��в�������");   
            System.out.println(message);   
        }   
    }   
        
        
    /**   
     * ɾ�������ļ�����һ���ļ�����   
     * @param path �����ļ�����һ���ļ�����   
     * @return   
     * @return   
     */ 
    public boolean delAllFile(String path) {   
        boolean bea = false;   
        try {   
            SmbFile file = new SmbFile(path);   
            if (!file.exists()) {   
                return bea;   
            }   
            if (!file.isDirectory()) {   
                return bea;   
            }   
            String[] tempList = file.list();   
            SmbFile temp = null;   
            for (int i = 0; i < tempList.length; i++) {   
                if (path.endsWith("/")) {   
                    temp = new SmbFile(path + tempList[i]);   
                } else {   
                    temp = new SmbFile(path + "/" + tempList[i]);   
                }   
                if (temp.isFile()) {   
                    temp.delete();   
                }   
                if (temp.isDirectory()) {   
                    delAllFile(path + "/" + tempList[i] + "/");// ��ɾ���ļ���������ļ�   
                    delFolder(path + "/" + tempList[i] + "/");// ��ɾ�����ļ���   
                    bea = true;   
                }   
            }   
            return bea;   
        } catch (Exception e) {   
            return bea;   
        }   
    }   
    
        
        
    /**   
     * ���������ļ��е�����   
     * @param oldPath ׼��������Ŀ¼   
     * @param newPath ָ������·������Ŀ¼   
     * @return   
     */ 
    public void copyFolder(String oldPath, String newPath) {   
        String conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName;   
        System.err.println(conStr);   
        try {   
            /**   
             * ��������ļ���ɾ���ļ�    
             * SmbFile exittemp = new SmbFile(conStr + "/"+newPath);   
             * if(exittemp.exists()){   
             *      delFolder(conStr+"/"+newPath+"/");    
             * }   
             */ 
            SmbFile exittemps = new SmbFile(conStr + "/" + newPath);   
            if (!exittemps.exists()) {   
                exittemps.mkdirs(); // ����ļ��в����� �������ļ���   
            }   
            File a = new File(oldPath);   
            String[] file = a.list();   
            File temp = null;   
            for (int i = 0; i < file.length; i++) {   
                if (oldPath.endsWith("/")) {   
                    temp = new File(oldPath + file[i]);   
                } else {   
                    temp = new File(oldPath + "/" + file[i]);   
                }   
                if (temp.isFile()) {   
                    if (temp.exists()) {   
                        writeFile(temp, newPath + "/" + file[i]);   
                    }   
                }   
                if (temp.isDirectory()) {// ��������ļ���   
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);   
                }   
            }   
    
        } catch (Exception e) {   
            String message = "���������ļ������ݲ�������";   
            System.out.println(message);   
        }   
    }   
        
    /**   
     * �����ļ���Զ�̼���������Ŀ��·���������򴴽�����֮������   
     * @param localFileFullName ����ָ���ļ�·��   
     * @param targetDir Ŀ��·��   
     */ 
    public void copyFileToRemoteDir(String localFileFullName, String targetDir) {   
        System.err.println(localFileFullName + "--" + targetDir);   
        RemoteFileUtil rf = new RemoteFileUtil();   
        InputStream is = null;   
        SmbFile smbFile = null;   
        OutputStream os = null;   
        byte[] buffer = new byte[1024 * 8];   
        // ���������ַ���,��ȡ���ļ�����   
        String conStr = null;   
        conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + targetDir;   
        System.err.println(conStr);   
        SmbFile sf;   
        try {   
            sf = new SmbFile("smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + targetDir);   
            if (!sf.exists()) {   
                // �½�Ŀ��Ŀ¼   
                sf.mkdirs();   
                is = new FileInputStream(new File(localFileFullName));   
                // ��ȡԶ���ļ��������д�ļ���Զ�̹����ļ���   
                os = new BufferedOutputStream(new SmbFileOutputStream(smbFile));   
                while ((is.read(buffer)) != -1) {   
                    os.write(buffer);   
                }   
            }   
        } catch (Exception e) {   
            System.err.println("��ʾ�����������ļ������ݲ�������");   
        }   
    
        File file = new File(localFileFullName);   
        if (file.isFile()) {   
            File sourceFile = file;     // Դ�ļ�   
            File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file.getName());// Ŀ���ļ�   
            String name = file.getName();// �ļ���   
            if (targetDir != null && targetFile != null) {   
                rf.writeFile(sourceFile, "/" + targetDir + "/" + name); // �����ļ�   
            } else if (targetFile != null) {   
                rf.writeFile(sourceFile, name); // �����ļ�   
            }   
        }   
    }   
        
    /**   
     * ѭ����ȡ�ļ���������ƥ����ļ�   
     * @param strPath ·��   
     * @param subStr ƥ���ַ�   
     * @return   
     */ 
    public ArrayList refreshFileList(String strPath, String subStr) {   
        File dir = new File(strPath);   
        File[] files = dir.listFiles();   
        if (files == null)   
            return null;   
        for (int i = 0; i < files.length; i++) {   
            if (!files[i].isDirectory()) {   
                String strFileName = files[i].getAbsolutePath().toLowerCase();   
                if (files[i].getName().indexOf(subStr) >= 0) {   
                    filelist.add(files[i].getName());   
                }   
            }   
        }   
        return filelist;   
    }   
    
    // ���Դӱ��ظ����ļ���Զ��Ŀ��Ŀ¼��������ͨ��   
    public static void main(String[] args) {   
        RemoteConfigUtil rc = new RemoteConfigUtil();   
        RemoteFileUtil util = new RemoteFileUtil();   
        util.copyFileToRemoteDir(rc.getSourcePath(), rc.getTargetPath());   
    }   
} 
