package ryanair.flight_booking.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightSelectionPage extends BasePage {
	
	private By firstOutboundFlight_btn = By.xpath("//flight-card-new[@data-e2e='flight-card--outbound']//button[1]");
	private By firstInboundFlight_btn = By.xpath("//flight-card-new[@data-e2e='flight-card--inbound']//button[1]");
	private By continueWithBasicfare_th = By.xpath("//th[@data-e2e='fare-card-standard']/div");
	private By continueWithBasicfare_btn = By.xpath("//button[@data-e2e='value']");
	private By logInLater = By.xpath("//span[contains(text(),'Log in later')]");
	
	// first passenger details
	private By titleFirstPassenger= By.xpath("//div[@class='dropdown body-l-lg body-l-sm'][1]");
	private By titleMS = By.xpath("//div[@class='dropdown-item__label body-l-lg body-l-sm'][1]");
	private By nameFirstPassenger= By.xpath("//*[@id='form.passengers.ADT-0.name']");
	private By surnameFirstPassenger = By.xpath("//*[@id='form.passengers.ADT-0.surname']");
	//second passenger details
	private By titleSecondPassenger= By.xpath("//div[@class='dropdown body-l-lg body-l-sm']//span[contains(text(),'-')][1]");
	private By nameSecondPassenger= By.xpath("//*[@id='form.passengers.ADT-1.name']");
	private By surnameSecondPassenger = By.xpath("//*[@id='form.passengers.ADT-1.surname']");
	private By continue_btn = By.xpath("//button[contains(text(), 'Continue')]");
	
	public FlightSelectionPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	/**
	 * Select first available flight
	 */
	public void selectFirstflight() {
		log.info("Selecting first flights for both inbond and outbound");
		click(firstOutboundFlight_btn);
		click(firstInboundFlight_btn);	
		click(continueWithBasicfare_th);
		click(continueWithBasicfare_btn);
	}
	
	/**
	 * Click LogIn Later
	 * @param logInNow
	 */
	public void logIn(boolean logInNow) {
		if (logInNow) {
			log.info("Code for login needs to be implemented");
			click(logInLater);	
		}else
		{
			log.info("Clicking log in later");
			click(logInLater);
		}
	}
	
	/**
	 * Fill random passenger details
	 */
	public void fillPassengerDetails() {
		log.info("Scrolling to passenger view");
		WebElement firstpassengername = driver.findElement(nameFirstPassenger);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstpassengername);
		log.info("Adding passenger details");
		click(titleFirstPassenger);
		click(titleMS);
		type(nameFirstPassenger, randomNameGenerator());
		type(surnameFirstPassenger, randomSurNameGenerator());
		click(titleSecondPassenger);
		click(titleMS);
		type(nameSecondPassenger, randomNameGenerator());
		type(surnameSecondPassenger, randomSurNameGenerator());
	}
	
	/**
	 * Click continue and navigate to seat selection page
	 * @return seat selection page
	 */
	public SeatSelectionPage navigateToSeatSelection() {
		click(continue_btn);
		log.info("returning Object for SeatSelectionPage");
		return (new SeatSelectionPage(driver, log));
	}
	
	private String randomNameGenerator() {
        List<String> firstNames = Arrays.asList("John", "Jane", "Michael", "Emily", "David", "Emma", "Daniel", "Olivia", "James", "Sophia");
        Random random = new Random();
        return(firstNames.get(random.nextInt(firstNames.size())));
	}
	
	private String randomSurNameGenerator() {
        List<String> surnames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez");
        Random random = new Random();
        return(surnames.get(random.nextInt(surnames.size())));
	}
}
