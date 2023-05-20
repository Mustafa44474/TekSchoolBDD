package ampcus.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import ampcus.pages.POMFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utilities.CommonUtility;
import utilities.EmailUtility;


public class StepDef extends CommonUtility{
	POMFactory factory = POMFactory.getPOMFactory();
	
	@Given("user is on Ampcus contact site")
	public void userIsOnAmpcusContactSite() throws IOException {
		assertEquals(getTitle(), "Ampcus provides 24x7 service across various industries - USA");
		assertEquals(getCurrentURL() ,"https://www.ampcus.com/contact/");
		takeScreenShot("AmpcusContactSite");
		
	}
	@When("user verifies {string} page is loaded")
	public void userVerifiesPageIsLoaded(String string) {
		waitTillPresence(factory.contactPage().howToReachUs);
		assertEquals(factory.contactPage().howToReachUs.getText(),"HOW TO REACH US?");

	}
	@When("user verifies background image and Ampcus logo is visible")
	public void userVerifiesBackgroundImageAndAmpcusLogoIsVisible() {
		waitTillPresence(factory.contactPage().ampcusLogo);
		waitTillPresence(factory.contactPage().ampcusBackgroundImage);
		assertTrue(isElementDisplayed(factory.contactPage().ampcusLogo));
		assertTrue(isElementDisplayed(factory.contactPage().ampcusBackgroundImage));
	    
	}
	@When("user open email and read below data")
	public void userOpenEmailAndReadBelowData(DataTable dataTable) throws MessagingException, IOException, InterruptedException {
		List<List<String>> emailInformation = dataTable.asLists(String.class);
		sendText(factory.contactPage().nameInput,EmailUtility.getEmailData(emailInformation.get(0).get(0)));
		sendText(factory.contactPage().emailInput,EmailUtility.getEmailData(emailInformation.get(0).get(1)));
		sendText(factory.contactPage().phoneInput,EmailUtility.getEmailData(emailInformation.get(0).get(2)));
		sendText(factory.contactPage().messageInput,EmailUtility.getEmailData(emailInformation.get(0).get(3)));
		sendText(factory.contactPage().captchaInput,EmailUtility.getEmailData(emailInformation.get(0).get(4)));
		takeScreenShot("emailValueEntered");
	}
	@When("user verifies submit button is clickable")
	public void userVerifiesSubmitButtonIsClickable() {
		waitTillPresence(factory.contactPage().submitButton);
		assertTrue(isElementEnabled(factory.contactPage().submitButton));
	}
	
}
