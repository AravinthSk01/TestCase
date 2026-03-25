//package com.rvz.transfer.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.CALLS_REAL_METHODS;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class Automation {
//private WebDriver webdriver;
//    
//    @BeforeEach
//    public void setUpDriver() {
//    	webdriver = new ChromeDriver();
//    }
//    
//    @Test
//    public void openBank() {
//    	webdriver.get("http://localhost:5173/");
//    	String s1 = webdriver.getCurrentUrl();
//    	assertEquals("http://localhost:5173/", s1);
//    }
//    
//    @Test
//    public void testButton() {
//    	webdriver.get("http://localhost:5173/");
//    	
//    	WebElement Profile_button = webdriver.findElement(By.id("profileid"));
//    	Profile_button.click();
//    	
//    	assertEquals(webdriver.findElement(By.className("card-title")).getText(), "Aravinth");
//
//    }
//    
//    @Test
//    public void successPayment() {
//    	webdriver.get("http://localhost:5173/");
//    	
//    	WebElement Account_NO = webdriver.findElement(By.name("toAccount"));
//    	Account_NO.sendKeys("101112");
//    	WebElement Amount = webdriver.findElement(By.name("amount"));
//    	Amount.sendKeys("10");
//    	webdriver.findElement(By.cssSelector(".btn.btn-success.w-100")).click();
//    	 WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(5));
//    	    WebElement alert = wait.until(
//    	        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.mt-3"))
//    	    );
//
//    	    assertEquals("Transfer successful!", alert.getText());
//    }
//    
//    @Test
//    public void failurePayment() {
//    	webdriver.get("http://localhost:5173/");
//    	
//    	WebElement Account_NO = webdriver.findElement(By.name("toAccount"));
//    	Account_NO.sendKeys("10122");
//    	WebElement Amount = webdriver.findElement(By.name("amount"));
//    	Amount.sendKeys("100000");
//    	webdriver.findElement(By.cssSelector(".btn.btn-success.w-100")).click();
//    	 WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(5));
//    	    WebElement alert = wait.until(
//    	        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger.mt-3"))
//    	    );
//
//    	    assertEquals("Insufficient balance!", alert.getText());
//    }
//    
//    @Test
//    public void testTransaction() {
//    	webdriver.get("http://localhost:5173/");
//    	
//    	WebElement Profile_button = webdriver.findElement(By.id("profileid"));
//    	Profile_button.click();
//    	
//    	assertEquals(webdriver.findElement(By.className("card-title")).getText(), "Aravinth");
//
//    }
//    
//    @AfterEach
//    public void takeDown() {
//    	webdriver.close();
//    }
//}
