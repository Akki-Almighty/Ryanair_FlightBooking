package ryanair.flight_booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaggageSelectionPage extends BasePage {

	private By smallBaggage = By.xpath("//*[@id='ry-radio-button--0']/parent::*");
	private By continue_btn = By.xpath("//button[contains(text(), 'Continue')]");

	public BaggageSelectionPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/**
	 * please provide small/large baggage
	 * @param bagType
	 */
	public void selectBaggage(String bagType) {
		if (bagType.contains("small")) {
			log.info("Selecing small baggage");
			checkAndClick(smallBaggage, 30);
		} else {
			log.info("To select bag other then small functionality needs to be added");
			checkAndClick(smallBaggage, 30);
		}
	}
	
	/**
	 * @return Baggage selection page
	 */
	public ExtrasSeclectionPage navigateToExtrasSeclection() {
		click(continue_btn);
		log.info("returning Object for ExtrasSeclectionPage");
		return (new ExtrasSeclectionPage(driver, log));
	}
}
