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

public class SearchFlightPage extends BasePage{

	private By flightsTab_btn = By.xpath("//div[text()='flights']/parent::hp-search-widget-tab/parent::button");
	private By returnTrip_radiobtn = By.xpath("//span[text()='Return trip']");
	private By oneway_radiobtn = By.xpath("//span[text()='One way']");
	private By departure_input = By.xpath("//input[@id='input-button__departure']");
	private By destination_input = By.xpath("//input[@id='input-button__destination']");
	private By clearSelction = By.xpath("//button[contains(text(),'Clear selection')]");
	private By searchFlight_btn = By.xpath("//button/span[text()='Search']");
	private By depart_dateBox = By.xpath("//div[contains(text(),'Depart')]/following-sibling::div[text()=' Choose date ']");	
	private By return_dateBox = By.xpath("//div[contains(text(),'Return')]/following-sibling::div[text()=' Choose date ']");
	private By nextMonth_btn = By.xpath("//div[@data-ref='calendar-btn-next-month']");
	private By noOfpassenger_input = By.xpath("//ry-input-button[@uniqueid='passengers']");
	private By adultPassengerCounterValue = By.xpath("//*[@data-ref='passengers-picker__adults']//div[@data-ref='counter.counter__value']");
	private By adultPassengerCounterIncrement = By.xpath("//*[@data-ref='passengers-picker__adults']//div[@data-ref='counter.counter__increment']");
	
	public SearchFlightPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	/**
	 * Please provide From (Departure) and To (Destination)
	 * @param departure
	 * @param destination
	 */
	public void bookFlight(String departure, String destination) {
		log.info("Selecting Departure airport :"+ departure+ " and Destination airport "+ destination);
		click(departure_input);
		click(clearSelction);
		selectAirport(departure_input, departure);
		selectAirport(destination_input, destination);
	}
	
	/**
	 * Please provide Departure Date and Destination Date
	 * @param departureDate
	 * @param returnDate
	 */
	public void departureAndReturnDate(String departureDate, String returnDate) {
		log.info("Selecting Departure and return date");
		String regex = ".*[a-zA-Z]+.*";
		if (departureDate.matches(regex)) {
			 selectDate(convertTodate(departureDate));
		} else {
			selectDate(departureDate);
		}
		
		if (returnDate.matches(regex)) {
			 selectDate(convertTodate(returnDate));
		} else {
			selectDate(returnDate);
		}
	}

	/**
	 * Provide number of adult Passengers
	 * @param noOfadultPassengers
	 */
	public void numberOfadultPassengers(int noOfadultPassengers) {
		log.info("Adding number of passengers");
		int currentCounterValue = Integer.parseInt(driver.findElement(adultPassengerCounterValue).getText());
		while (currentCounterValue !=noOfadultPassengers ) {
			click(adultPassengerCounterIncrement);
			currentCounterValue++;
		}
	}
	
	/**
	 * Provide number of adult Passengers
	 * @param noOfadultPassengers
	 */
	public FlightSelectionPage navigateToFlightSelectionPage() {
		click(searchFlight_btn);
		log.info("returning Object for FlightSelectionPage");
		return (new FlightSelectionPage(driver, log));
	}
	
	private void selectAirport(By ele, String airportToselect) {
		type(ele,airportToselect);
		List<WebElement> listAirports = driver.findElements(By.xpath("//span[@data-ref='airport-item__name']"));
		for (Iterator iterator = listAirports.iterator(); iterator.hasNext();) {
			WebElement aiport = (WebElement) iterator.next();
			String airportDisplayed = aiport.getText();
			if(airportDisplayed.trim().equalsIgnoreCase(airportToselect)) {
				aiport.click();
				sleep(1000);
				break;
			}
		}

	// For countries logic can be implemented
	//		List<WebElement> listCountries = driver.findElements(By.xpath("//span[@data-ref='country__name']"));
	//		for (Iterator iterator = listCountries.iterator(); iterator.hasNext();) {
	//			WebElement country = (WebElement) iterator.next();
	//			if (country.getText().toLowerCase().contains(Value.toLowerCase())){
	//				country.click();
	//				break;
	//			} 
	//		}
	}
	
	
	private void selectDate(String dateToSelect) {
		boolean dateFoundonview = false;
			while(!dateFoundonview) {
				 List<WebElement> calenderDates = driver.findElements(By.cssSelector(".calendar-body__cell"));
					for (Iterator iterator = calenderDates.iterator(); iterator.hasNext();) {
						WebElement selected_date = (WebElement) iterator.next();
						if (dateToSelect.equals(selected_date.getAttribute("data-id"))) {
							selected_date.click();
							sleep(1000);
							dateFoundonview = true;
							break;
					}
				}
					if(dateFoundonview) {
						break;
					}else {
						click(nextMonth_btn);
					}
			}
	}
	
	private String convertTodate(String dateToconvert) {
		 // Define the input date format (with a placeholder year)
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH);
        // Define the output date format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate="";
        try {
            // Append a default year to the input date string
        	dateToconvert = dateToconvert + " 2024"; // You can replace it with the desired year
            // Parse the input date string to a LocalDate
            LocalDate date = LocalDate.parse(dateToconvert, inputFormatter);
            // Format the LocalDate to the desired output format
            formattedDate = date.format(outputFormatter);
            System.out.println("Formatted Date: " + formattedDate);
       }catch (Exception e) {
			e.printStackTrace();
		}
		return formattedDate;
	}
}
