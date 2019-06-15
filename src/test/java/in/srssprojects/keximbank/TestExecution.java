package in.srssprojects.keximbank;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.BrowserHelper;
import utilities.ExcelHelper;
import utilities.TestListener;

@Listeners(TestListener.class)
public class TestExecution extends BrowserHelper {
	BankHomePage bankHomePage;
	AdminHomePage adminHomePage;
	RoleDetailsPage roleDetailsPage;
	private RoleCreationPage roleCreationPage;
	private Alert alert;
	private EmployeeDetailsPage employeeDetailsPage;
	private EmployeeCreaionPage employeeCreationPage;
	private BranchDetailsPage branchDetailsPage;
	private BranchUpdationPage branchUpdationPage;

	@Test(priority = 1, groups = {"datadriven", "role", "employee", "valid", "reset", "cancel", "duplicate", "blank" })
	public void loginTest() {
		bankHomePage.setUserName(readProperty("username"));
		bankHomePage.setPassword(readProperty("password"));
		bankHomePage.clickLogin();
		adminHomePage = PageFactory.initElements(driver, AdminHomePage.class);
		Assert.assertTrue(adminHomePage.isAdminHomePageDispalyed());
	}

	@Test(priority = 2, groups = { "role", "valid" })
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

	@Test(priority = 4, groups = { "role", "reset" })
	public void roleCreatoinReset() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		roleCreationPage.clickReset();
		Assert.assertEquals(roleCreationPage.isFormReset(), true);
	}

	@Test(priority = 3, dependsOnMethods = { "roleCreationWithValidData" }, groups = { "role", "duplicate" })
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

	@Test(priority = 5, groups = { "role", "cancel" })
	public void roleCreationCancel() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		roleCreationPage.setRoleName(SampleTestData.ROLENAME);
		roleCreationPage.selectRoleType(SampleTestData.ROLETYPE);
		roleDetailsPage = roleCreationPage.clickCancel();
		Assert.assertTrue(roleDetailsPage.isNewRoleDisplayed());
	}

	@Test(priority = 6, groups = { "role", "blank" })
	public void roleCreationWithBlankData() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		alert = roleCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("please fill in"));
	}

	// branchCreationWithValidData
	// branchCreationWithDuplicateData
	// branchCreationWithBlankData
	// branchCreationReset
	// branchCreationCancel

	// employeeCreationWithValidData
	@Test(priority = 12, groups = { "employee", "valid" })
	public void employeeCreationWithValidData() {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		employeeCreationPage.setEmployeeName(SampleTestData.EMPNAME);
		employeeCreationPage.setPasswd(SampleTestData.LOGINPASS);
		employeeCreationPage.setRole(SampleTestData.ROLENAME);
		employeeCreationPage.setBranch(SampleTestData.BRANCH);
		alert = employeeCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("sucessfully"));

	}

	// employeeCreaetionWithDuplicateData
	@Test(priority = 13, groups = { "employee", "duplicate" })
	public void employeeCreationWithDuplicateData() {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		employeeCreationPage.setEmployeeName(SampleTestData.EMPNAME);
		employeeCreationPage.setPasswd(SampleTestData.LOGINPASS);
		employeeCreationPage.setRole(SampleTestData.ROLENAME);
		employeeCreationPage.setBranch(SampleTestData.BRANCH);
		alert = employeeCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.toLowerCase().contains("existed"));
	}

	// employeeCreationWithBlankData
	@Test(priority = 14, groups = { "employee", "blank" })
	public void employeeCreationWithBlankData() {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		alert = employeeCreationPage.clickSubmit();
		String alertText = alert.getText();
		alert.accept();
		AssertJUnit.assertTrue(alertText.toLowerCase().contains("please fill in"));
	}

	// employeeCreationReset
	@Test(priority = 15, groups = { "employee", "reset" })
	public void employeeCreationReset() {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		employeeCreationPage.setEmployeeName(SampleTestData.EMPNAME);
		employeeCreationPage.setPasswd(SampleTestData.LOGINPASS);
		employeeCreationPage.setRole(SampleTestData.ROLENAME);
		employeeCreationPage.setBranch(SampleTestData.BRANCH);
		employeeCreationPage.clickReset();
		sleep(2000);
		Assert.assertTrue(employeeCreationPage.isEmployeeReset());
	}

	// employeeCreationCancel
	@Test(priority = 16, groups = { "employee", "cancel" })
	public void employeeCreationCancel() {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		employeeCreationPage.clickCancel();
		Assert.assertTrue(employeeDetailsPage.isNewEmployeeDisplayed());
	}

	@Test(priority = 17, groups = { "role", "datadriven" })
	public void roleCreationResetWithMultipleDataWithoutDP() {
		roleDetailsPage = adminHomePage.clickRoles();
		roleCreationPage = roleDetailsPage.clickNewRole();
		ExcelHelper excel = new ExcelHelper();
		excel.openExcel("", "testdata.xls", "roleData");
		int nor = excel.rowCount();
		for (int i = 1; i <= nor; i++) {
			String roleName = excel.readData(i, 0);
			String roleType = excel.readData(i, 2);
			roleCreationPage.setRoleName(roleName);
			roleCreationPage.selectRoleType(roleType);
			roleCreationPage.clickReset();
			Assert.assertTrue(roleCreationPage.isFormReset());
		}
	}
	
	@DataProvider(name = "empData")
	public Object[][] getEmpData() {
		ExcelHelper excel = new ExcelHelper();
		return excel.readSheetData("", "testdata.xls", "employeeData");
	}
	
	@Test(priority = 18, groups= {"employee", "datadriven"}, dataProvider = "empData")
	public void employeeCreationResetWithDP(String empName, String password, String roleName, String branchName) {
		employeeDetailsPage = adminHomePage.clickEmployees();
		employeeCreationPage = employeeDetailsPage.clickNewEmployee();
		employeeCreationPage.setEmployeeName(empName);
		employeeCreationPage.setPasswd(password);
		employeeCreationPage.setRole(roleName);
		employeeCreationPage.setBranch(branchName);
		employeeCreationPage.clickReset();
		Assert.assertTrue(employeeCreationPage.isEmployeeReset());
	}
	
	
	@Test(priority = 19, groups = {"branch"})
	public void branchUpdationWithValidData() {
		branchDetailsPage=adminHomePage.clickBranches();
		branchUpdationPage = branchDetailsPage.clickEdit("236");
		branchUpdationPage.setAddress1("newAddress");
		Alert alert = branchUpdationPage.clickUpdate();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.contains("updated Sucessfully"));
	}

	@AfterClass(groups = { "datadriven","role", "employee", "valid", "reset", "cancel", "duplicate", "blank" })
	public void browserClose() {
		closeBrowser();
	}

}
