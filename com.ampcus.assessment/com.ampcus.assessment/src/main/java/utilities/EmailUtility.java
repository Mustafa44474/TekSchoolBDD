package utilities;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtility {
	private static final String EMAIL_USERNAME = "azami.ampcus@gmail.com";
	private static final String EMAIL_PASSWORD = "pvlfhulhynexhmkv";

	public static void main(String[] args) {

		try {
			System.out.println(getEmailData("name"));
			System.out.println(getEmailData("email"));
			System.out.println(getEmailData("phone"));
			System.out.println(getEmailData("message"));
			System.out.println(getEmailData("captcha"));
			

		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}

	private static String extractData(String content, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			return matcher.group(1).trim();
		}

		return null;
	}

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		if (message.isMimeType("text/plain")) {
			return (String) message.getContent();
		} else if (message.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) message.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					return (String) bodyPart.getContent();
				}
			}
		}
		return null;
	}

	public static String getEmailData(String field) throws MessagingException, IOException {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.host", "imap.gmail.com");
		properties.put("mail.imaps.port", "993");

		Session session = Session.getInstance(properties);
		Store store = session.getStore();
		store.connect("imap.gmail.com", EMAIL_USERNAME, EMAIL_PASSWORD);

		Folder inbox = store.getFolder("inbox");
		inbox.open(Folder.READ_ONLY);

		SearchTerm searchTerm = new SearchTerm() {
			@Override
			public boolean match(Message message) {
				try {
					return message.getSubject().contains("Code Assessment Request - QA Automation Engineer role");
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				return false;
			}
		};

		Message[] messages = inbox.search(searchTerm);

		if (messages.length > 0) {
			Message message = messages[0]; // Assuming the first matched message contains the required data
			String content = getTextFromMessage(message);

			// Extract the data from the email body using regex patterns
			HashMap<String, String> patterns = new HashMap<>();
			patterns.put("name", "a\\. Name: \"(.+?)\"");
			patterns.put("email", "b\\. Email: \"(.+?)\"");
			patterns.put("phone", "c\\. Phone: \"(.+?)\"");
			patterns.put("message", "d\\. Message: \"(.+?)\"");
			patterns.put("captcha", "e\\. What is thirteen minus 6\\? : \"(.+?)\"");
			
			String name = extractData(content, "a\\. Name: \"(.+?)\"");
			String email = extractData(content, "b\\. Email: \"(.+?)\"");
			String phone = extractData(content, "c\\. Phone: \"(.+?)\"");
			String messageContent = extractData(content, "d\\. Message: \"(.+?)\"");

			String captchaAnswer = extractData(content, "e\\. What is thirteen minus 6\\? : \"(.+?)\"");

//			System.out.println("Name: " + name);
//			System.out.println("Email: " + email);
//			System.out.println("Phone: " + phone);
//			System.out.println("Message: " + messageContent);
//			System.out.println("Captcha answer: " + captchaAnswer);
			
	        if (patterns.containsKey(field)) {
	            return extractData(content, patterns.get(field));
	        } else {
	            System.out.println("Invalid field provided");
	            return null;
	        }
			
			
		} else {
			System.out.println("No email found with the given subject");
		}
		

		inbox.close(false);
		store.close();
		return field;
	}

}
