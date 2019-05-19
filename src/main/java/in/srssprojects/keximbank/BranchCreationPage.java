package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BranchCreationPage {

	private WebDriver driver;

	// branch name
	@FindBy(how = How.ID, using = "txtbName")
	private WebElement branchName;

	// address1
	@FindBy(how = How.ID, using = "txtAdd1")
	private WebElement address1;

	// zipcode
	@FindBy(how = How.ID, using = "txtZip")
	private WebElement zipCode;

	// country
	@FindBy(how = How.ID, using = "lst_counrtyU")
	private WebElement country;

	// state
	@FindBy(how = How.ID, using = "lst_stateI")
	private WebElement state;

	// city
	@FindBy(how = How.ID, using = "lst_cityI")
	private WebElement city;

	// submit
	@FindBy(how = How.ID, using = "btn_insert")
	private WebElement submit;

	// reset
	@FindBy(how = How.ID, using = "Btn_Reset")
	private WebElement reset;

	// cancel
	@FindBy(how = How.ID, using = "Btn_cancel")
	private WebElement cancel;

	public BranchCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// fill branch name
	public void setBranchName(String branchName) {
		this.branchName.sendKeys(branchName);
	}

	// fill address1
	public void setAddress1(String address1) {
		this.address1.sendKeys(address1);
	}

	// fill zip code
	public void setZipCode(String zipCode) {
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
	public BranchDetailsPage clickCancel() {
		this.cancel.click();
		return PageFactory.initElements(driver, BranchDetailsPage.class);
	}

}
