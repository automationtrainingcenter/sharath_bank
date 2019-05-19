package in.srssprojects.keximbank;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RoleDetailsPage {
	WebDriver driver;

	// new role button
	@FindBy(how = How.ID, using = "btnRoles")
	private WebElement newRole;

	public RoleDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public RoleCreationPage clickNewRole() {
		this.newRole.click();
		return PageFactory.initElements(driver, RoleCreationPage.class);
	}

	public boolean isNewRoleDisplayed() {
		return this.newRole.isDisplayed();
	}
}
