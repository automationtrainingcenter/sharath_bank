package in.srssprojects.keximbank;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class ParameterExecution extends TestExecution{
	@BeforeClass(groups = {"datadriven", "role", "employee", "valid", "reset", "cancel", "duplicate", "blank" })
	@Parameters({ "browser", "url" })
	public void browserLaunch(String brName, String u) {
		launchBrowser(brName, u);
		if (getCurrentUrl().contains("srssprojects")) {
			bankHomePage = new BankHomePage(driver);
		}
	}
}
