package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BranchUpdationPage {

	private WebDriver driver;

	// branch name
	@FindBy(how = How.ID, using = "txtbnameU")
	private WebElement branchName;

	// address1
	@FindBy(how = How.ID, using = "txtadd1u")
	private WebElement address1;

	// zipcode
	@FindBy(how = How.ID, using = "txtzipu")
	private WebElement zipCode;

	// country
	@FindBy(how = How.ID, using = "drlst_countryU")
	private WebElement country;

	// state
	@FindBy(how = How.ID, using = "lst_stateU")
	private WebElement state;

	// city
	@FindBy(how = How.ID, using = "LST_cityU")
	private WebElement city;

	// submit
	@FindBy(how = How.ID, using = "btnupdate")
	private WebElement update;

	// cancel
	@FindBy(how = How.ID, using = "Btncancel")
	private WebElement cancel;

	public BranchUpdationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill branch name
	public void setBranchName(String branchName) {
		this.branchName.clear();
		this.branchName.sendKeys(branchName);
	}

	// fill address1
	public void setAddress1(String address1) {
		this.address1.clear();
		this.address1.sendKeys(address1);
	}

	// fill zip code
	public void setZipCode(String zipCode) {
		this.zipCode.clear();
		this.zipCode.sendKeys(zipCode);
	}

	// select country
	public void selectCountry(String country) {
		new Select(this.country).selectByVisibleText(country);
	}

	// select state
	public void selectState(String state) {
		new Select(this.state).selectByVisibleText(state);
	}

	// select city
	public void selectCity(String city) {
		new Select(this.city).selectByVisibleText(city);
	}

	// click update
	public Alert clickUpdate() {
		this.update.click();
		return driver.switchTo().alert();
	}

	

	// click cancel
	public BranchDetailsPage clickCancel() {
		this.cancel.click();
		return PageFactory.initElements(driver, BranchDetailsPage.class);
	}

}
