package ampcus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ampcus.base.BaseSetup;

public class AmpucsContactPage extends BaseSetup {
	
	public AmpucsContactPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath ="//h1[text()='How to Reach Us?']")
	public WebElement howToReachUs;
	
	@FindBy(xpath ="//img[@title='Ampcus']")
	public WebElement ampcusLogo;
	
	@FindBy(xpath ="//div[@id='inbanner']/img[@class='img-responsive']")
	public WebElement ampcusBackgroundImage;
	
	@FindBy(id= "nf-field-1")
	public WebElement nameInput;
	
	@FindBy(id= "nf-field-2")
	public WebElement emailInput;
	
	@FindBy(id= "nf-field-5")
	public WebElement phoneInput;
	
	@FindBy(id= "nf-field-3")
	public WebElement messageInput;
	
	@FindBy(id= "nf-field-6")
	public WebElement captchaInput;
	
	@FindBy(id= "nf-field-4")
	public WebElement submitButton;
	
	
}
