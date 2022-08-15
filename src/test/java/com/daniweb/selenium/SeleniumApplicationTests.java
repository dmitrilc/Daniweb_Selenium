package com.daniweb.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
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
	void test() {
		driver.get("http://localhost:" + port);

		new WebDriverWait(driver, ofSeconds(5))
				.until(titleIs("Pricing example · Bootstrap v5.2"));
	}

	@Test
	void automatedTest(){
		driver.get("http://localhost:" + port);

		driver.findElement(By.cssSelector("body")).click();
		driver.findElement(By.cssSelector(".fs-4")).click();
		driver.findElement(By.linkText("Features")).click();
		driver.findElement(By.linkText("Enterprise")).click();
		driver.findElement(By.linkText("Support")).click();
		driver.findElement(By.linkText("Pricing")).click();
		driver.findElement(By.cssSelector(".border-primary .w-100")).click();
		driver.findElement(By.cssSelector(".btn-outline-primary")).click();
	}

}
