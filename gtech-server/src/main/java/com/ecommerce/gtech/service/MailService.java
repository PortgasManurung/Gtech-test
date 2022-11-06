package com.ecommerce.gtech.service;

import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.ecommerce.gtech.models.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author aditm
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public Mail sendSimpleMessage(Mail mail, Context context) {
        MimeMessage msg = emailSender.createMimeMessage();
        try {
            String emailBody = templateEngine.process("email-template", context);
            MimeMessageHelper message = new MimeMessageHelper(msg, StandardCharsets.UTF_8.name());
            String htmlMsg = mail.getLink();
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(emailBody, true);
            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mail;
    }
}
