package ryanair.flight_booking.pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeatSelectionPage extends BasePage {

	private By addRecommendedSeats = By.xpath("//button[contains(text(),'Add recommended seats')]");
	private By fasttrack = By.xpath("//button[contains(text(),' No, thanks ')]");
	private By continue_btn = By.xpath("//button[contains(text(), 'Continue')]");

	public SeatSelectionPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/**
	 * Selecting recommended Seat and Fast track
	 */
	public void selectRecommendedSeats() {
		log.info("Clicking recommended seats");
		click(addRecommendedSeats);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fastTrack_no_Thanks = wait.until(ExpectedConditions.visibilityOfElementLocated(fasttrack));
		if (fastTrack_no_Thanks.isDisplayed()) {
			click(fasttrack);
			log.info("fasttrack nothanks clicked.");
		} else {
			log.info("fasttrack nothanks is not visible.");
		}
	}

	/**
	 * @return Baggage selection page
	 */
	public BaggageSelectionPage navigateToBaggageSelection() {
		log.info("returning Object for BaggageSelectionPage");
		return (new BaggageSelectionPage(driver, log));
	}

}
