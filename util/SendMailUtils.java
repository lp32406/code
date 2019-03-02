package com.eb.dianlianbao_server.util;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
public class SendMailUtils {
	    /**
	     * 
	     * @param smtp
	     * @throws Exception 
	     */
	public static int sendMailCode(String code,String emali) throws MessagingException {  
	    	 // 配置发�?�邮件的环境属�??
			int result=1;
	        try {
				final Properties props = new Properties();  
				/* 
				 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / 
				 * mail.user / mail.from 
				 */  
				// 表示SMTP发�?�邮件，�?要进行身份验�?  
				props.put("mail.smtp.auth", "true");  
				props.put("mail.smtp.host", "smtp.126.com");  
				// 发件人的账号  
				props.put("mail.user", "a1051125828@126.com");  
				// 访问SMTP服务时需要提供的密码  
				props.put("mail.password", "15815005093A");  
  
				// 构建授权信息，用于进行SMTP进行身份验证  
				Authenticator authenticator = new Authenticator() {  
				    @Override  
				    protected PasswordAuthentication getPasswordAuthentication() {  
				        // 用户名�?�密�?  
				        String userName = props.getProperty("mail.user");  
				        String password = props.getProperty("mail.password");  
				        return new PasswordAuthentication(userName, password);  
				    }  
				};  
				// 使用环境属�?�和授权信息，创建邮件会�?  
				Session mailSession = Session.getInstance(props, authenticator);  
				// 创建邮件消息  
				MimeMessage message = new MimeMessage(mailSession);  
				// 设置发件�?  
//				props.put("mail.user", "社交金融");
				InternetAddress form = new InternetAddress(  
				        props.getProperty("mail.user"));  
				
//				message.setFrom(form);  
				message.setFrom(new InternetAddress("社交金融"+" <"+form+">"));
  
				// 设置收件�?  
				InternetAddress to = new InternetAddress(emali);  
				message.setRecipient(RecipientType.TO, to);  
  
				// 设置邮件标题  
				message.setSubject("邮箱验证�?");  
  
				// 设置邮件的内容体  
				message.setContent("验证码为:"+code+",十分钟后失效", "text/html;charset=UTF-8");  
  
				// 发�?�邮�?  
				Transport.send(message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result=-1;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result=-1;
			}
	        return result;
	}
}
