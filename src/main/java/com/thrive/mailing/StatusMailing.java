package com.thrive.mailing;

import com.thrive.funcInterfaces.logs;
import com.thrive.logger.LoggerConfig;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import org.apache.commons.mail.*;

import static com.thrive.browserSetup.ProjectSetup.reportDate;

public class StatusMailing {

    public static void report_mail() {

        // Sets the Logger
        LoggerConfig.setLogger(StatusMailing.class.getName());

        // Create the attachment
        EmailAttachment attach = new EmailAttachment();
        attach.setPath(ExtentReports.reportPath);
        attach.setDisposition(EmailAttachment.ATTACHMENT);
        attach.setName("Extent Reports " + reportDate + ".html");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(new DefaultAuthenticator(ExcelUtils.getData("emails").get("email"),
                ExcelUtils.getData("emails").get("password")));
        email.setBounceAddress("vinay@codilar.com");
        email.setSubject("Test Report Mail " + reportDate);

        try {
            email.setMsg("Please find the attached Test Report");
            email.setFrom(ExcelUtils.getData("emails").get("email"));
            email.addTo(ExcelUtils.getData("emails").get("alt_email"));
            email.attach(attach);
            email.send();
            LoggerConfig.getLogger().info("Mail Sent Successfully");
        } catch (EmailException e) {
            LoggerConfig.setLogger(e.getLocalizedMessage());
        }
    }

    public Email simple_mail_setup() {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(
                new DefaultAuthenticator(ExcelUtils.getData("emails").get("email"),
                        ExcelUtils.getData("emails").get("password")));
        try {
            email.addTo(ExcelUtils.getData("emails").get("alt_email"));
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
