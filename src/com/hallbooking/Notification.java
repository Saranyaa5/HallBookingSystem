//package com.hallbooking;
//import java.util.Properties;
//import javax.util.*;
//import javax.mail.internet.*;
//
//public class Notification {
//  private static final String FROM_EMAIL = "your-email@gmail.com";  // Change this
//  private static final String APP_PASSWORD = "your-app-password";  // Use App Password
//
//  public static void sendEmail(String toEmail, String subject, String messageBody) {
//      Properties props = new Properties();
//      props.put("mail.smtp.host", "smtp.gmail.com");
//      props.put("mail.smtp.port", "587");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//
//      Session session = Session.getInstance(props, new Authenticator() {
//          protected PasswordAuthentication getPasswordAuthentication() {
//              return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
//          }
//      });
//
//      try {
//          Message message = new MimeMessage(session);
//          message.setFrom(new InternetAddress(FROM_EMAIL));
//          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//          message.setSubject(subject);
//          message.setText(messageBody);
//          Transport.send(message);
//          System.out.println("Email sent successfully to " + toEmail);
//      } catch (MessagingException e) {
//          System.out.println("Failed to send email.");
//      }
//  }
//}
