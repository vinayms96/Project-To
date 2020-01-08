package com.thrive.modules;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.thrive.reportSetup.extentReports;
import com.thrive.utils.Property;

public class statusMailing {

	public static void report_mail() {

		// Create the attachment
		EmailAttachment attach = new EmailAttachment();
		attach.setPath(extentReports.reportPath);
		attach.setDisposition(EmailAttachment.ATTACHMENT);
		attach.setDescription("Please find the attached Test Report");
		attach.setName("Extent Reports");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setSSLOnConnect(true);
		email.setAuthenticator(new DefaultAuthenticator("topintest@gmail.com", "test#123"));

		try {
			email.setFrom("vinay@codilar.com");
			email.addTo("vinay.srikrishna@gmail.com");
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
				new DefaultAuthenticator(Property.getProperty("adminMail"), Property.getProperty("adminPass")));
		try {
			email.addTo(Property.getProperty("toMailOne"));
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public static void pass_msg_mail() {
		statusMailing setMail = new statusMailing();
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
		statusMailing setMail = new statusMailing();
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
