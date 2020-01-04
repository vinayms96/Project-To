package com.thrive.modules;

import org.apache.commons.mail.EmailAttachment;

public class statusMailing {
	
	public static void failReportMail() {
		
		EmailAttachment attach = new EmailAttachment();
		attach.setPath("");
		attach.setDisposition(EmailAttachment.ATTACHMENT);
		attach.setDescription("Please find the attached Test Report");
		attach.setName("EXtent Reports");
		
	}

}
