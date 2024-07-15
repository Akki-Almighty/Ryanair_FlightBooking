package ryanair.flight_booking.pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtrasSeclectionPage extends BasePage {

	private By continue_btn = By.xpath("//button[contains(text(), 'Continue')]");
	private By accountLogInPopUp= By.xpath("//h1[text()='Account log in']");

	public ExtrasSeclectionPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void continueWithoutExtra() {
		log.info("Clicking continue without adding Extras");
	    checkAndClick(continue_btn, 15);
	    checkAndClick(continue_btn, 15);
	    checkAndClick(continue_btn, 5);       
	}
	
	public boolean accountLoginScreenDisplay() {
		log.info("waiting for login screen popup");
		sleep(3000);
		driver.switchTo().frame(0);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		if (w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountLogInPopUp)) != null) {
			return true;
		}else {
			return false;
		}
	}
	
}
