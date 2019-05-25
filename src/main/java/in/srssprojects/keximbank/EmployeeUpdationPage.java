package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EmployeeUpdationPage {
	private WebDriver driver;

	// Employee Name
	@FindBy(how = How.ID, using = "txtBnameU")
	private WebElement empName;

	// Login Password
	@FindBy(how = How.ID, using = "txtPwdU")
	private WebElement loginPass;

	// roles
	@FindBy(how = How.ID, using = "lstrolesU")
	private WebElement role;

	// branch
	@FindBy(how = How.ID, using = "lstBidU")
	private WebElement branch;

	// submit button
	@FindBy(how = How.ID, using = "BtnUpdate")
	private WebElement update;

	// cancel Button
	@FindBy(how = How.ID, using = "btncancelU")
	private WebElement cancel;

	// Constructor
	public EmployeeUpdationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill Employee Name
	public void setEmployeeName(String empName) {
		this.empName.clear();
		this.empName.sendKeys(empName);
	}

	// fill login password
	public void setPasswd(String passwd) {
		this.loginPass.clear();
		this.loginPass.sendKeys(passwd);
	}

	// Select role
	public void setRole(String role) {
		new Select(this.role).selectByVisibleText(role);
	}

	// select Branch
	public void setBranch(String branchName) {
		new Select(this.branch).selectByVisibleText(branchName);
	}

	// click submit
	public Alert clickSubmit() {
		this.update.click();
		return driver.switchTo().alert();
	}

	// click cancel
	public EmployeeDetailsPage clickCancel() {
		this.cancel.click();
		return PageFactory.initElements(driver, EmployeeDetailsPage.class);
	}

}
