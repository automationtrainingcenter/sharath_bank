package in.srssprojects.keximbank;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.BrowserHelper;

public class TestExecution extends BrowserHelper {
	BankHomePage bankHomePage;
	AdminHomePage adminHomePage;
	RoleDetailsPage roleDetailsPage;
	private RoleCreationPage roleCreationPage;
	private Alert alert;

	@BeforeClass
	public void launchBrowser() {
		launchBrowser(readProperty("browser"), readProperty("url"));
		if (getCurrentUrl().contains("srssprojects")) {
			bankHomePage = new BankHomePage(driver);
		}
	}

	@Test(priority = 1)
	public void loginTest() {
		bankHomePage.setUserName(readProperty("username"));
		bankHomePage.setPassword(readProperty("password"));
		bankHomePage.clickLogin();
		adminHomePage = PageFactory.initElements(driver, AdminHomePage.class);
		Assert.assertTrue(adminHomePage.isAdminHomePageDispalyed());
	}
	
	@Test(priority = 2)
	public void roleCreationWithValidData() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		alert = roleCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("sucessfully"));
	}
	
	
	@Test(priority = 4)
	public void roleCreatoinReset() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		roleCreationPage.clickReset();
		Assert.assertEquals(roleCreationPage.isFormReset(), true);
	}
	
	@Test(priority = 3, dependsOnMethods= {"roleCreationWithValidData"})
	public void roleCreationWithDuplicateData() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		alert = roleCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("existed"));
	}
	
	@Test(priority = 5)
	public void roleCreationCancel() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		roleDetailsPage = roleCreationPage.clickCancel();
		Assert.assertTrue(roleDetailsPage.isNewRoleDisplayed());
	}
	
	@Test(priority = 6)
	public void roleCreationWithBlankData() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		alert = roleCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("please fill in"));
	}
	
	//branchCreationWithValidData
	//branchCreationWithDuplicateData
	//branchCreationWithBlankData
	//branchCreationReset
	//branchCreationCancel
	
	
	//employeeCreationWithValidData
	//employeeCreaetionWithDuplicateData
	//employeeCreationWithBlankData
	//employeeCreationReset
	//employeeCreationCancel
	
	@AfterClass
	public void browserClose() {
		closeBrowser();
	}

}
