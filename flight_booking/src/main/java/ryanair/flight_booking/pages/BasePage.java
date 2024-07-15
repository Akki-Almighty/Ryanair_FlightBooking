package ryanair.flight_booking.pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected Logger log;

	public BasePage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	protected void openUrl(String url) {
		driver.get(url);
	}

	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * Click with timeout
	 * 
	 * @param locator
	 */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 15);
		find(locator).click();
	}

	/**
	 * Click and Sendkeys to element
	 * 
	 * @param locator
	 * @param value
	 */
	protected void type(By locator, String value) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
		find(locator).clear();
		find(locator).sendKeys(value);

//		 for (char ch : value.toCharArray()) {
//             ele.sendKeys(Character.toString(ch));
//             // added a short sleep to simulate typing delay
//             try {
//                 Thread.sleep(10); // Sleep for 10 milliseconds
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }

	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(condition);
	}

	/**
	 * Get the Title for the page
	 * 
	 * @return Title
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("element not found in given time interval");
				e.printStackTrace();
			}
			attempts++;
		}
	}
	
	protected void checkAndClick(By locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		try {
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
			ele.click();
		} catch (Exception e) {
			System.out.println("Element is not visible/clickable in"+ timeOutInSeconds +" seconds");
		}
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
