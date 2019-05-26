package in.srssprojects.keximbank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//using page object model
public class BankHomePage {
	WebDriver driver;

	// username
	private WebElement username() {
		WebElement ele = driver.findElement(By.id("txtuId"));
		return ele;
	}

	// password
	private WebElement password() {
		return driver.findElement(By.id("txtPword"));
	}

	// login button
	private WebElement loginButton() {
		return driver.findElement(By.id("login"));
	}

	public BankHomePage(WebDriver driver) {
		this.driver = driver;
	}

	// fill username
	public void setUserName(String username) {
		this.username().sendKeys(username);
	}

	// fill password
	public void setPassword(String password) {
		this.password().sendKeys(password);
	}

	// click login button
	public void clickLogin() {
		this.loginButton().click();
	}
	
	
}
