package com.rvz.transfer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
	
	private WebDriver webdriver;
	
	@BeforeEach
	public void setUp() {
		webdriver = new ChromeDriver();
	}
	
	@Test
	public void loadWeb() {
		webdriver.get("http://localhost:5173/");
		WebElement s = webdriver.findElement(By.id("Heading"));
		assertEquals("Secure Payment System", s.getText());
	}
	
	@Test
	public void showProfile() {
		webdriver.get("http://localhost:5173/");
		WebElement Profile = webdriver.findElement(By.id("profileid"));
		Profile.click();
		
		assertEquals(webdriver.findElement(By.className("card-title")).getText(), "Aravinth");
	}
	
	@Test
	public void profileButton() {
		webdriver.get("http://localhost:5173/");
		WebElement profile_Button = webdriver.findElement(By.id("profileid"));
		
		assertEquals(profile_Button.getText(), "Show Profile");
	}
	
	
	@Test
	public void transactionButton() {
		webdriver.get("http://localhost:5173/");
		WebElement Transaction_Button = webdriver.findElement(By.id("transactionid"));
		
		assertEquals(Transaction_Button.getText(), "Show Transactions");
	}
	
	@Test
	public void failedPayment() {
		webdriver.get("http://localhost:5173/");
		
		WebElement Account_number = webdriver.findElement(By.name("toAccount"));
		Account_number.sendKeys("23456");
		
		WebElement Amount = webdriver.findElement(By.name("amount"));
		Amount.sendKeys("20000");

    	webdriver.findElement(By.cssSelector(".btn.btn-success.w-100")).click();
		
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(5));
		
		WebElement alert = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger.mt-3"))
				);
		
		assertEquals("Insufficient balance!", alert.getText());
	}
	
	@Test
	public void successPayment() {
		webdriver.get("http://localhost:5173/");
		
		WebElement Account_number = webdriver.findElement(By.name("toAccount"));
		Account_number.sendKeys("23456");
		
		WebElement Amount = webdriver.findElement(By.name("amount"));
		Amount.sendKeys("200");

    	webdriver.findElement(By.cssSelector(".btn.btn-success.w-100")).click();
		
		WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(5));
		
		WebElement alert = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.mt-3"))
				);
		
		assertEquals("Transfer successful!", alert.getText());
	}
	
	
	
	@AfterEach
	public void tearDown() {
		webdriver.close();
	}
	
}
