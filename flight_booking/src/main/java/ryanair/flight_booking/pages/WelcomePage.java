package ryanair.flight_booking.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomePage extends BasePage {

	private String url = "https://www.ryanair.com/us/en";
	private By acceptCookies = By.cssSelector(".cookie-popup-with-overlay__button-settings[data-ref='cookie.accept-all']");
	private By cookie_no_thanks = By.cssSelector(".cookie-popup-with-overlay__button-settings[data-ref='cookie.no-thanks']");
	private By cookie_view_settings = By.cssSelector(".cookie-popup-with-overlay__button-settings[data-ref='cookie.view-settings']");			
	private By ryanairLogo = By.xpath("//a[@title='Ryanair']");

	public WelcomePage(WebDriver driver, Logger log) {
		super(driver,log);
	}
	
	/**
	 * This method used to open Ryanair official URL.
	 * @return 
	 */
	public void navigateToRyanair() {
		log.info("Opening Ryanair website"+ url);
		openUrl(url);
	}
	
	/**
	 * Please provide either accept / No thanks 
	 * @param cookieSelection
	 */
	public void cookiesSelection(String cookieSelection) {
		if (cookieSelection.toLowerCase().contains("accept")) {
			log.info("selecting Cookies:"+ cookieSelection);
			click(acceptCookies);
		} else {
			click(cookie_no_thanks);
		}
	}
	
	/**
	 * object for search flight page
	 * @return new SearchFlightPage(driver)
	 */
	public SearchFlightPage SearchFlight() {
		log.info("returning Object for SearchFlightPage");
		return(new SearchFlightPage(driver, log));
	}

}
