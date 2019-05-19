package in.srssprojects.keximbank;

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
	
	public BranchDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//click new branch button
	public BranchCreationPage clickNewBranchButton() {
		this.newBranch.click();
		return PageFactory.initElements(driver, BranchCreationPage.class);
	}

}
