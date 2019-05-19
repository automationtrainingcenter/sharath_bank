package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EmployeeCreaionPage {
	private WebDriver driver;

	// Employee Name
	@FindBy(how = How.ID, using = "txtUname")
	private WebElement empName;

	// Login Password
	@FindBy(how = How.ID, using = "txtLpwd")
	private WebElement loginPass;

	// roles
	@FindBy(how = How.ID, using = "lst_Roles")
	private WebElement role;

	// branch
	@FindBy(how = How.ID, using = "lst_Branch")
	private WebElement branch;

	// submit button
	@FindBy(how = How.ID, using = "BtnSubmit")
	private WebElement submit;

	// reset Button
	@FindBy(how = How.ID, using = "btnreset")
	private WebElement reset;

	// cancel Button
	@FindBy(how = How.ID, using = "btnCancel")
	private WebElement cancel;

	// Constructor
	public EmployeeCreaionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill Employee Name
	public void setEmployeeName(String empName) {
		this.empName.sendKeys(empName);
	}

	// fill login password
	public void setPasswd(String passwd) {
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
		this.submit.click();
		return driver.switchTo().alert();
	}

	// click reset
	public void clickReset() {
		this.reset.click();
	}

	// click cancel
	public EmployeeDetailsPage clickCancel() {
		this.cancel.click();
		return PageFactory.initElements(driver, EmployeeDetailsPage.class);
	}

	// validate employee creation reset
	public boolean isEmployeeReset() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return arguments[0].value", this.empName).toString().isEmpty();
	}

}
