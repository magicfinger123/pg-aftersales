/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.EmailRecipentEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.repository.EmailsRepository;
import com.pgagtersales.pgaftersales.service.EmailRecipentService;
import com.pgagtersales.pgaftersales.shared.AppConstants;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.EmailsDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class SendMail {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    EmailsRepository emailsRepository;

    @Autowired
    AppConstants appConstants;

    @Autowired
    Utils utils;

    public void sendEmailWithAttachment(String message, String recipent, int ccs, String subject) throws MessagingException, IOException {
        String cc;
       switch (ccs){
            case AppConstants.AFTERSALES_RECIPENTS:
                    cc = utils.getEmails("aftersales").getEmailAddress();
                break;
            case AppConstants.SALES_RECIPENT:
                    cc = utils.getEmails("sales").getEmailAddress();
                break;
            case AppConstants.FINANCE_AND_AFTERSALES:
                    cc = utils.getEmails("finance_aftersales").getEmailAddress();
                break;
           case AppConstants.HR_RECIPENTS:
                   cc = utils.getEmails("hr").getEmailAddress();
               break;
           case AppConstants.FINANCE_AND_SALES:
                   cc = utils.getEmails("finance_sales").getEmailAddress();
               break;
            default:
                cc = utils.getEmails("default").getEmailAddress();
        }

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
       /* //String[] recipent = {"exc.easy@gmail.com","ossaimike8@gmail.com"};*/

        helper.setTo(InternetAddress.parse(recipent));
        helper.setCc(InternetAddress.parse(cc));
        // helper.setFrom("noreply@pg.com");

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(message, true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        try {
            javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }

    }
}
