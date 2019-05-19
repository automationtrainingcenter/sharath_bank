package in.srssprojects.keximbank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EmployeeDetailsPage {

	WebDriver driver;

	// new Employee
	@FindBy(how = How.ID, using = "BtnNew")
	private WebElement newEmp;

	// Constructor
	public EmployeeDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click new Employee
	public EmployeeCreaionPage clickNewEmployee() {
		this.newEmp.click();
		return PageFactory.initElements(driver, EmployeeCreaionPage.class);
	}

	public boolean isNewEmployeeDisplayed() {
		return this.newEmp.isDisplayed();
	}
}
