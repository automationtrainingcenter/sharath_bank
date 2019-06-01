package in.srssprojects.keximbank;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BasicExecution extends TestExecution{
	@BeforeClass(groups = { "role", "employee", "valid", "reset", "cancel", "duplicate", "blank" })
	public void launchBrowser() {
		launchBrowser(readProperty("browser"), readProperty("url"));
		if (getCurrentUrl().contains("srssprojects")) {
			bankHomePage = new BankHomePage(driver);
		}
	}
}
