package test;

import java.io.IOException;   
import java.util.Properties;   
    
/**   
 * 读取配置文件中key对应的value   
 * @author JunjieQin   
 */ 
public class RemoteConfigUtil {   
    private String REMOTE_HOST_IP;   
    private String LOGIN_ACCOUNT;   
    private String LOGIN_PASSWORD;   
    private String SHARE_DOC_NAME;   
    private String sourcePath;   
    private String targetPath;   
    
    //无参构造方法   
    public RemoteConfigUtil() {   
        try {   
            // 读取配置文件   
            Properties prop = new Properties();   
            prop.load(this.getClass().getClassLoader().getResourceAsStream("copyRemoteFile.properties"));   
            // 根据 key 获取 value   
            REMOTE_HOST_IP = prop.getProperty("REMOTE_HOST_IP");   
            LOGIN_ACCOUNT = prop.getProperty("LOGIN_ACCOUNT");   
            LOGIN_PASSWORD = prop.getProperty("LOGIN_PASSWORD");   
            SHARE_DOC_NAME = prop.getProperty("SHARE_DOC_NAME");   
            sourcePath = prop.getProperty("sourcePath");   
            targetPath = prop.getProperty("targetPath");   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
    public String getLOGIN_ACCOUNT() {   
        return LOGIN_ACCOUNT;   
    }   
    
    public void setLOGIN_ACCOUNT(String login_account) {   
        LOGIN_ACCOUNT = login_account;   
    }   
    
    public String getLOGIN_PASSWORD() {   
        return LOGIN_PASSWORD;   
    }   
    
    public void setLOGIN_PASSWORD(String login_password) {   
        LOGIN_PASSWORD = login_password;   
    }   
    
    public String getREMOTE_HOST_IP() {   
        return REMOTE_HOST_IP;   
    }   
    
    public void setREMOTE_HOST_IP(String remote_host_ip) {   
        REMOTE_HOST_IP = remote_host_ip;   
    }   
    
    public String getSHARE_DOC_NAME() {   
        return SHARE_DOC_NAME;   
    }   
    
    public void setSHARE_DOC_NAME(String share_doc_name) {   
        SHARE_DOC_NAME = share_doc_name;   
    }   
    
    public String getSourcePath() {   
        return sourcePath;   
    }   
    
    public void setSourcePath(String sourcePath) {   
        this.sourcePath = sourcePath;   
    }   
    
    public String getTargetPath() {   
        return targetPath;   
    }   
    
    public void setTargetPath(String targetPath) {   
        this.targetPath = targetPath;   
    }   
} 