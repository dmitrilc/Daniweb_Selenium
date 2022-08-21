package com.daniweb.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SeleniumApplicationTests {

	@LocalServerPort
	int port;

	WebDriver driver;

	@BeforeAll
	static void setupAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setup() {
		driver = new ChromeDriver();
	}

	@AfterEach
	void teardown() {
		driver.quit();
	}

	//@Test
	void click(){
		driver.get("http://localhost:" + port);

		driver.findElement(By.cssSelector(".btn-outline-primary"))
				.click();
	}

	//@Test
	void clickThenVerify(){
		driver.get("http://localhost:" + port);

		driver.findElement(By.xpath("//button[contains(.,'Get started')]"))
				.click();

		var title = driver.getTitle();
		assertEquals(title, "Get Started");
	}

	//@Test
	void inputText(){
		driver.get("http://localhost:" + port + "/checkout");

		// More organic if you click on
		// the text box before sending text
		driver.findElement(By.id("firstName"))
				.click();

		driver.findElement(By.id("firstName"))
				.sendKeys("Anne");
		driver.findElement(By.id("lastName"))
				.sendKeys("Chan");
	}

	//@Test
	void automaticScrollDown(){
		driver.get("http://localhost:" + port + "/start");

		var link = driver.findElement(By.linkText("Last Link"));

		//link.click(); //First way


		//Second way
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.elementToBeClickable(link))
				.click();
	}

	//@Test
	void automaticScrollUp(){
		driver.get("http://localhost:" + port + "/start");

		// Scrolls to end of page
		new Actions(driver)
				.sendKeys(Keys.END)
				//.keyDown(Keys.COMMAND) // For Mac
				//.sendKeys(Keys.DOWN)
				.perform();

		var link = driver.findElement(By.linkText("First Link"));
		link.click();

/*		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.elementToBeClickable(link))
				.click();*/
	}

	//@Test
	void scrollByDown() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		new Actions(driver)
				.scrollByAmount(0, 10)
				.perform();

		new Actions(driver)
				.scrollByAmount(0, 500)
				.perform();

		Thread.sleep(5000);
	}

	//@Test
	void scrollByUp() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		// Scrolls to end of page
		new Actions(driver)
				.sendKeys(Keys.END)
				.perform();

		Thread.sleep(2000);

		new Actions(driver)
				.scrollByAmount(0, -1000)
				.perform();

		Thread.sleep(5000);
	}

	//@Test
	void scrollByXDown() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		new Actions(driver)
				.scrollByAmount(100, 0)
				.perform();

		Thread.sleep(1000);

		new Actions(driver)
				.scrollByAmount(100, 0)
				.perform();

		Thread.sleep(5000);
	}

	//@Test
	void scrollByPgDnKey() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		new Actions(driver)
				.sendKeys(Keys.PAGE_DOWN)
				.perform();

		Thread.sleep(1000);
	}

	//@Test
	void scrollByPgUpKey() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		// Scrolls to end of page
		new Actions(driver)
				.sendKeys(Keys.END)
				.perform();

		Thread.sleep(1000);

		new Actions(driver)
				.sendKeys(Keys.PAGE_UP)
				.perform();

		Thread.sleep(1000);
	}

	//@Test
	void scrollByArrowDnKey() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		new Actions(driver)
				.sendKeys(Keys.ARROW_DOWN);

		Thread.sleep(1000);

		new Actions(driver)
				.sendKeys(Keys.ARROW_DOWN);

		Thread.sleep(1000);
	}

	//@Test
	void scrollByArrowUpKey() throws InterruptedException {
		driver.get("http://localhost:" + port + "/start");

		// Scrolls to end of page
		new Actions(driver)
				.sendKeys(Keys.END)
				.perform();

		new Actions(driver)
				.sendKeys(Keys.ARROW_UP);

		Thread.sleep(1000);

		new Actions(driver)
				.sendKeys(Keys.ARROW_UP);

		Thread.sleep(1000);
	}

	@Test
	void explicitWait(){
		driver.get("http://localhost:" + port + "/waits");

/*		new WebDriverWait(driver, Duration.ofSeconds(4))
				.until(_d -> driver.findElement(By.cssSelector("p")));*/

		new WebDriverWait(driver, Duration.ofSeconds(6))
				.until(_d -> driver.findElement(By.cssSelector("p")));
	}

	@Test
	void repeatedExplicitWaits(){
		driver.get("http://localhost:" + port + "/waits");

		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(_d -> driver.findElement(By.cssSelector("p")));

		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(_d -> driver.findElement(By.cssSelector("a")));

		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(_d -> driver.findElement(By.cssSelector("h1")));
	}

	@Test
	void shortImplicitWait(){
		driver.get("http://localhost:" + port + "/waits");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		var p = driver.findElement(By.cssSelector("p"));
		var a = driver.findElement(By.cssSelector("a"));
		var h1 = driver.findElement(By.cssSelector("h1"));
	}

	@Test
	void implicitWait(){
		driver.get("http://localhost:" + port + "/waits");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		var p = driver.findElement(By.cssSelector("p"));
	}

}
