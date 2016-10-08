package utils;
import java.util.Date;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class SendMail {
	public static String SMTP_HOST = "smtp.163.com";
	public static String SMTP_PORT = "25";
	public static String SMTP_PROTOCOL = "SMTP";
	public static String SMTP_AUTH = "true";
	public static String AUTH_USER = "lyf08103716@163.com";
	public static String AUTH_PASSWORD = "08103716";
	public static String FROM_ADDRESS = "lyf08103716@163.com";
/**
* 发送email,目的地址为一个
* @param to 目的email地址
* @param title email的标题
* @param content email的内容
* @return 返回是否发送成功
*/
	public static boolean send(String to,String title,String content){
		boolean isSuccess = true;
		if( to == null || title == null || content == null)
			return false;
		Properties property = new Properties();
		//设置一些基本属性
		property.put("mail.smtp.host", SMTP_HOST);
		property.put("mail.smtp.port", SMTP_PORT);
		property.put("mail.smtp.protocol" , SMTP_PROTOCOL);
		property.put("mail.smtp.auth",SMTP_AUTH);
		MyAuthenticator myauth = new MyAuthenticator(AUTH_USER,AUTH_PASSWORD);
		//获得发送邮件的会话
		Session mailSession = Session.getDefaultInstance(property,myauth);
		//生成发送的消息
		Message message = new MimeMessage(mailSession);
		try{
			//形成发送的mail地址
			InternetAddress fromAddress = new InternetAddress(FROM_ADDRESS);
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress(to);
			//加入发送消息的目的地址addRecipients()两个重载函数
			message.addRecipient(Message.RecipientType.TO, toAddress);
			//设置消息题
			message.setSubject(title);
			//设置消息主题
			message.setText(content);
			//保存
			message.saveChanges();
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		//发送mail
		try{
			Transport.send(message, message.getRecipients(Message.RecipientType.TO));
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}
/**
* 发送email,目的地址为一组
* @param toList 一组email地址
* @param title email的标题
* @param content email的内容
* @return boolean 返回是否成功
*/
	public static boolean send(List<String> toList,String title,String content){
		boolean isSuccess = true;
		if( toList == null || title == null || content == null || toList.size() == 0 )
			return false;
		Properties property = new Properties();
		//设置一些基本属性
		property.put("mail.smtp.host", SMTP_HOST);
		property.put("mail.smtp.port", SMTP_PORT);
		property.put("mail.smtp.protocol" , SMTP_PROTOCOL);
		property.put("mail.smtp.auth",SMTP_AUTH);
		MyAuthenticator myauth = new MyAuthenticator(AUTH_USER,AUTH_PASSWORD);
		//获得发送邮件的会话
		Session mailSession = Session.getDefaultInstance(property,myauth);
		//生成发送的消息
		Message message = new MimeMessage(mailSession);
		try{
			//形成发送的mail地址
			InternetAddress fromAddress = new InternetAddress(FROM_ADDRESS);
			message.setFrom(fromAddress);
			for(String to:toList){
				InternetAddress toAddress = new InternetAddress(to);
				//加入发送消息的目的地址addRecipients()两个重载函数
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			//设置消息题
			message.setSubject(title);
			//设置消息主题
			message.setText(content);
			//保存
			message.saveChanges();
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		//发送mail
		try{
			Transport.send(message, message.getRecipients(Message.RecipientType.TO));
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}
/**
* @param args
*/
//	public static void main(String[] args) {
//		// TODO
//		String to = "775287397@qq.com";
//		String title = "email 测试程序";
//		String content = "我测试用得，不要回复呀。";
//		List<String> toList = new ArrayList();
//		toList.add(to);
//		toList.add("775287397@qq.com");
//		boolean res = SendMail.send(toList, title, content);
//		if( res == true )
//			System.out.println("发送成功");
//	}
}
	class MyAuthenticator extends Authenticator{
		private String user;
		private String password;
		public MyAuthenticator(String user,String password){
			this.user = user;
			this.password = password;
		}
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
}