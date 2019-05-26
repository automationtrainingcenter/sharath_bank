package in.srssprojects.keximbank;

import org.openqa.selenium.WebDriver;
//using Page factory
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {

	WebDriver driver;

	// home
	@FindBy(how = How.CSS, using = "a[href='adminflow.aspx']")
	private WebElement home;

	// logout
	@FindBy(css = "a[href='home.aspx']")
	private WebElement logout;

	// branches
	@FindBy(how = How.CSS, using = "a[href='admin_banker_master.aspx']")
	private WebElement branches;

	// roles
	@FindBy(how = How.CSS, using = "a[href='Admin_Roles_details.aspx']")
	private WebElement roles;

	// employees
	@FindBy(how = How.CSS, using = "a[href='Admin_Emp_details.aspx']")
	private WebElement employees;

	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click home button
	public AdminHomePage clickHome() {
		this.home.click();
		return this;
	}

	// click logout button
	public BankHomePage clickLogout() {
		this.logout.click();
		return new BankHomePage(driver);
	}

	// click branches
	public BranchDetailsPage clickBranches() {
		this.branches.click();
		return PageFactory.initElements(driver, BranchDetailsPage.class);
	}

	// click roles
	public RoleDetailsPage clickRoles() {
		this.roles.click();
		return PageFactory.initElements(driver, RoleDetailsPage.class);
	}

	// click employess
	public EmployeeDetailsPage clickEmployees() {
		this.employees.click();
		return PageFactory.initElements(driver, EmployeeDetailsPage.class);
	}
	
	public boolean isAdminHomePageDispalyed() {
		return this.logout.isDisplayed() && driver.getCurrentUrl().contains("adminflow");
	}

}
