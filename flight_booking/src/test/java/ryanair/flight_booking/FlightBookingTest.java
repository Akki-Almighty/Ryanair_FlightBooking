package ryanair.flight_booking;

import ryanair.flight_booking.base.CsvDataProviders;
import java.util.Map;
import ryanair.flight_booking.base.TestUtilities;
import ryanair.flight_booking.pages.BaggageSelectionPage;
import ryanair.flight_booking.pages.ExtrasSeclectionPage;
import ryanair.flight_booking.pages.FlightSelectionPage;
import ryanair.flight_booking.pages.SearchFlightPage;
import ryanair.flight_booking.pages.SeatSelectionPage;
import ryanair.flight_booking.pages.WelcomePage;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class FlightBookingTest extends TestUtilities {
	
	@Test
	public void bookFlightTest() {
		
		WelcomePage welcomepage = new WelcomePage(driver, log);
		
		welcomepage.navigateToRyanair();
		welcomepage.cookiesSelection("accept");
		takeScreenshot("Welcome Page");
		
		assertEquals(welcomepage.getTitle(),"Official Ryanair website | Cheap flights in Europe | Ryanair");
		
		SearchFlightPage searchFlightPage = welcomepage.SearchFlight();
		searchFlightPage.bookFlight("Dublin", "Barcelona");
		searchFlightPage.departureAndReturnDate("2024-07-25","2024-08-25");
		searchFlightPage.numberOfadultPassengers(2);
		takeScreenshot("SearchFlight Page");
		
		FlightSelectionPage flightSelectionPage = searchFlightPage.navigateToFlightSelectionPage();
		flightSelectionPage.selectFirstflight();
		flightSelectionPage.logIn(false); // Log In Later
		flightSelectionPage.fillPassengerDetails();
		takeScreenshot("FlightSelection Page");
		
		SeatSelectionPage seatSelectionPage = flightSelectionPage.navigateToSeatSelection();
		seatSelectionPage.selectRecommendedSeats();
		takeScreenshot("SeatSelection Page");
		
		BaggageSelectionPage baggageSelectionPage = seatSelectionPage.navigateToBaggageSelection();
		baggageSelectionPage.selectBaggage("small");
		takeScreenshot("BaggageSelection Page");
		
		ExtrasSeclectionPage extrasSeclectionPage = baggageSelectionPage.navigateToExtrasSeclection();
		extrasSeclectionPage.continueWithoutExtra();
		takeScreenshot("ExtrasSeclection Page");
		
		assertEquals(extrasSeclectionPage.accountLoginScreenDisplay(), true);
	}
	
	
	@Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void bookFlightParameterizedTest(Map<String, String> testData) {
		String departure_airport = testData.get("departure_airport");
		String destination_airport = testData.get("destination_airport");
		String departure_date = testData.get("departure_date");
		String return_date = testData.get("return_date");
		
		WelcomePage welcomepage = new WelcomePage(driver, log);
		
		welcomepage.navigateToRyanair();
		welcomepage.cookiesSelection("accept");
		takeScreenshot("Welcome Page");
		
		assertEquals(welcomepage.getTitle(),"Official Ryanair website | Cheap flights in Europe | Ryanair");
		
		SearchFlightPage searchFlightPage = welcomepage.SearchFlight();
		searchFlightPage.bookFlight(departure_airport, destination_airport);
		searchFlightPage.departureAndReturnDate(departure_date,return_date);
		searchFlightPage.numberOfadultPassengers(2);
		takeScreenshot("SearchFlight Page");
		
		FlightSelectionPage flightSelectionPage = searchFlightPage.navigateToFlightSelectionPage();
		flightSelectionPage.selectFirstflight();
		flightSelectionPage.logIn(false); // Log In Later
		flightSelectionPage.fillPassengerDetails();
		takeScreenshot("FlightSelection Page");
		
		SeatSelectionPage seatSelectionPage = flightSelectionPage.navigateToSeatSelection();
		seatSelectionPage.selectRecommendedSeats();
		takeScreenshot("SeatSelection Page");
		
		BaggageSelectionPage baggageSelectionPage = seatSelectionPage.navigateToBaggageSelection();
		baggageSelectionPage.selectBaggage("small");
		takeScreenshot("BaggageSelection Page");
		
		ExtrasSeclectionPage extrasSeclectionPage = baggageSelectionPage.navigateToExtrasSeclection();
		extrasSeclectionPage.continueWithoutExtra();
		takeScreenshot("ExtrasSeclection Page");
		
		assertEquals(extrasSeclectionPage.accountLoginScreenDisplay(), true);
	}
}
