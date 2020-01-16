package com.thrive.mailing;

import com.thrive.utils.ExcelUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.thrive.reportSetup.ExtentReports;

public class StatusMailing {

    public static void report_mail() {

        // Create the attachment
        EmailAttachment attach = new EmailAttachment();
        attach.setPath(ExtentReports.reportPath);
        attach.setDisposition(EmailAttachment.ATTACHMENT);
        attach.setDescription("Please find the attached Test Report");
        attach.setName("Extent Reports");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(new DefaultAuthenticator(ExcelUtils.getData("emails").get(3), ExcelUtils.getData("emails").get(4)));

        try {
            email.setFrom(ExcelUtils.getData("emails").get(3));
            email.addTo(ExcelUtils.getData("emails").get(7));
            email.attach(attach);
        } catch (EmailException e) {
            e.printStackTrace();
        }

        email.setBounceAddress("vinay@codilar.com");
        email.setSubject("Test Fail Report");

        try {
            email.send();
            System.out.println("Mail Sent Successfully");
        } catch (EmailException e) {
            System.out.println("Mail could NOT be sent");
            e.printStackTrace();
        }
    }

    public Email simple_mail_setup() {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(
                new DefaultAuthenticator(ExcelUtils.getData("emails").get(3), ExcelUtils.getData("emails").get(4)));
        try {
            email.addTo(ExcelUtils.getData("emails").get(7));
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static void pass_msg_mail() {
        StatusMailing setMail = new StatusMailing();
        Email email = setMail.simple_mail_setup();
        email.setSubject("Test Pass Mail");
        try {
            email.setMsg("Test Execution is completed and Tests Passed");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static void fail_msg_mail() {
        StatusMailing setMail = new StatusMailing();
        Email email = setMail.simple_mail_setup();
        email.setSubject("Test Fail Mail");
        try {
            email.setMsg("Test Execution is completed and Some Tests Failed");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
