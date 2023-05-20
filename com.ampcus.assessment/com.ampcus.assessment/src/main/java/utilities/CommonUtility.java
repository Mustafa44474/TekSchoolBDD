package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ampcus.base.BaseSetup;



public class CommonUtility extends BaseSetup {

	public WebDriverWait getWait() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
	}

	public WebElement waitTillClickable(WebElement element) {
		return this.getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitTillClickable(By by) {
		return this.getWait().until(ExpectedConditions.elementToBeClickable(by));
	}

	public WebElement waitTillPresence(WebElement element) {
		return this.getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitTillPresence(By by) {
		return this.getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void click(WebElement element) {
		this.waitTillClickable(element).click();
	}

	public void sendText(WebElement element, String value) {
		this.waitTillPresence(element).sendKeys(value);
	}

	public String getElementText(WebElement element) {
        return this.waitTillPresence(element).getText();
    }
	
	public void takeScreenShot(String imageName) throws IOException {
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./output/"+imageName+".png"));
    }
	public byte[] takeScreenShotAsBytes() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
	
	public void click(By by) {
		this.waitTillClickable(by).click();
		
	}
	public void sendText(By by, String value) {
		this.waitTillPresence(by).sendKeys(value);
	}
	
	public String getElementText(By by) {
		return this.waitTillPresence(by).getText();
	}
	
	public String getTitle() {
		String title = getDriver().getTitle();
		return title;
	}
	
	public String getCurrentURL() {
		String url = getDriver().getCurrentUrl();
		return url;
	}
    public  boolean isElementEnabled(WebElement ele) {
        if (ele.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }
    
    public  boolean isElementDisplayed(WebElement ele) {
        if (ele.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
   
}
