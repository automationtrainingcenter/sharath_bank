package in.srssprojects.keximbank;


import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RoleUpdationPage {
	private WebDriver driver;

	// role name
	@FindBy(how = How.ID, using = "txtrNameU")
	private WebElement roleName;

	// role type
	@FindBy(how = How.ID, using = "lstRtype")
	private WebElement roleType;

	// role submit
	@FindBy(how = How.ID, using = "btnupdate")
	private WebElement update;

	// role Cancel Button
	@FindBy(how = How.ID, using = "Btncancel")
	private WebElement cancel;

	public RoleUpdationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill role name
	public void setRoleName(String roleName) {
		this.roleName.clear();
		this.roleName.sendKeys(roleName);
	}

	// select role type
	public void selectRoleType(String roleType) {
		new Select(this.roleType).selectByVisibleText(roleType);
	}

	// click submit
	public Alert clickUpdate() {
		this.update.click();
		return driver.switchTo().alert();
	}

	// click cancel
	public RoleDetailsPage clickCancel() {
		this.cancel.click();
		return PageFactory.initElements(driver, RoleDetailsPage.class);
	}

}