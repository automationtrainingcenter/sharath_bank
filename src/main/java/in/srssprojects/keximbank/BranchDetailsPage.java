package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BranchDetailsPage {
	private WebDriver driver;
	
	//new branch button
	@FindBy(how = How.ID, using = "BtnNewBR")
	private WebElement newBranch;
	
	
	//branch details table
	private By branchTable = By.cssSelector("#DG_bankdetails>tbody");
	
	public BranchDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//click new branch button
	public BranchCreationPage clickNewBranchButton() {
		this.newBranch.click();
		return PageFactory.initElements(driver, BranchCreationPage.class);
	}
	
	
	//click on edit
	public BranchUpdationPage clickEdit(String branchID) {
		TableHelper.handleTable(driver, branchTable, branchID, "edit");
		return PageFactory.initElements(driver, BranchUpdationPage.class);
	}
	
	//click on delete
	public Alert clickDelete(String branchID) {
		TableHelper.handleTable(driver, branchTable, branchID, "delete");
		return driver.switchTo().alert();
	}

}
