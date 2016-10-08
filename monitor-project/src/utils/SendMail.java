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
* ����email,Ŀ�ĵ�ַΪһ��
* @param to Ŀ��email��ַ
* @param title email�ı���
* @param content email������
* @return �����Ƿ��ͳɹ�
*/
	public static boolean send(String to,String title,String content){
		boolean isSuccess = true;
		if( to == null || title == null || content == null)
			return false;
		Properties property = new Properties();
		//����һЩ��������
		property.put("mail.smtp.host", SMTP_HOST);
		property.put("mail.smtp.port", SMTP_PORT);
		property.put("mail.smtp.protocol" , SMTP_PROTOCOL);
		property.put("mail.smtp.auth",SMTP_AUTH);
		MyAuthenticator myauth = new MyAuthenticator(AUTH_USER,AUTH_PASSWORD);
		//��÷����ʼ��ĻỰ
		Session mailSession = Session.getDefaultInstance(property,myauth);
		//���ɷ��͵���Ϣ
		Message message = new MimeMessage(mailSession);
		try{
			//�γɷ��͵�mail��ַ
			InternetAddress fromAddress = new InternetAddress(FROM_ADDRESS);
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress(to);
			//���뷢����Ϣ��Ŀ�ĵ�ַaddRecipients()�������غ���
			message.addRecipient(Message.RecipientType.TO, toAddress);
			//������Ϣ��
			message.setSubject(title);
			//������Ϣ����
			message.setText(content);
			//����
			message.saveChanges();
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		//����mail
		try{
			Transport.send(message, message.getRecipients(Message.RecipientType.TO));
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}
/**
* ����email,Ŀ�ĵ�ַΪһ��
* @param toList һ��email��ַ
* @param title email�ı���
* @param content email������
* @return boolean �����Ƿ�ɹ�
*/
	public static boolean send(List<String> toList,String title,String content){
		boolean isSuccess = true;
		if( toList == null || title == null || content == null || toList.size() == 0 )
			return false;
		Properties property = new Properties();
		//����һЩ��������
		property.put("mail.smtp.host", SMTP_HOST);
		property.put("mail.smtp.port", SMTP_PORT);
		property.put("mail.smtp.protocol" , SMTP_PROTOCOL);
		property.put("mail.smtp.auth",SMTP_AUTH);
		MyAuthenticator myauth = new MyAuthenticator(AUTH_USER,AUTH_PASSWORD);
		//��÷����ʼ��ĻỰ
		Session mailSession = Session.getDefaultInstance(property,myauth);
		//���ɷ��͵���Ϣ
		Message message = new MimeMessage(mailSession);
		try{
			//�γɷ��͵�mail��ַ
			InternetAddress fromAddress = new InternetAddress(FROM_ADDRESS);
			message.setFrom(fromAddress);
			for(String to:toList){
				InternetAddress toAddress = new InternetAddress(to);
				//���뷢����Ϣ��Ŀ�ĵ�ַaddRecipients()�������غ���
				message.addRecipient(Message.RecipientType.TO, toAddress);
			}
			//������Ϣ��
			message.setSubject(title);
			//������Ϣ����
			message.setText(content);
			//����
			message.saveChanges();
		}catch(Exception e){
			isSuccess = false;
			System.out.println(e.getMessage());
		}
		//����mail
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
//		String title = "email ���Գ���";
//		String content = "�Ҳ����õã���Ҫ�ظ�ѽ��";
//		List<String> toList = new ArrayList();
//		toList.add(to);
//		toList.add("775287397@qq.com");
//		boolean res = SendMail.send(toList, title, content);
//		if( res == true )
//			System.out.println("���ͳɹ�");
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