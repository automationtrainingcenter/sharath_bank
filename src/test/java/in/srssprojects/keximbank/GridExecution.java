package in.srssprojects.keximbank;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class GridExecution extends TestExecution{
	@BeforeClass(groups = {"datadriven", "role", "employee", "valid", "reset", "cancel", "duplicate", "blank" })
	@Parameters({ "browser", "url", "nodeURL", "os" })
	public void browserLaunch(String brName, String u, String nodeUrl, String os) {
		launchBrowser(brName, u, nodeUrl, os);
		if (getCurrentUrl().contains("srssprojects")) {
			bankHomePage = new BankHomePage(driver);
		}
	}
}
