package com.codecool.shop.controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class EmailSender {

    void sendEmail(String email, String firstName, String lastName) {

        final String username = "codelabor1@gmail.com";
        final String password = "Codelabor1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("codelabor1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Codecool shop registration");
            message.setText("Dear "+ firstName + " " + lastName + "!" + "\n\n Thank you for your registration. \n " +
                    "Please buy very expensive things. Please do not trust in George Soon!"
                    + "\n\n Best Regards \n" +
                    " George Soon");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}