package com.daniweb.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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

	@Test
	void click(){
		driver.get("http://localhost:" + port);

		driver.findElement(By.cssSelector(".btn-outline-primary"))
				.click();
	}

	@Test
	void clickThenVerify(){
		driver.get("http://localhost:" + port);

		driver.findElement(By.xpath("//button[contains(.,'Get started')]"))
				.click();

		var title = driver.getTitle();
		assertEquals(title, "Get Started");
	}

	@Test
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

}
