package ryanair.flight_booking.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	private Logger log;

	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}

	public WebDriver createDriver() {
		log.info("Create driver: " + browser);

		switch (browser) {
		case "chrome":
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			driver.set(new FirefoxDriver());
			break;

		case "chromeheadless":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--remote-debugging-port=9222");
			driver.set(new ChromeDriver(chromeOptions));
			break;

		default:
			log.info("No browser provided: " + browser + ", starting default chrome.");
			driver.set(new ChromeDriver());
			break;
		}
		return driver.get();
	}
}
