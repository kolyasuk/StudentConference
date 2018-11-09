package devlight.edu.conference.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	private final JavaMailSender sender;

	public EmailSender(JavaMailSender sender) {
		super();
		this.sender = sender;
	}

	public void sendEmail(String to, String subject, String text) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(to);
		helper.setText(text);
		helper.setSubject(subject);

		sender.send(message);
	}

}
