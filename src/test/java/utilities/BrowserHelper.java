package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserHelper extends GenericHelper{
	public WebDriver driver;
	
	public void launchBrowser(String browserName, String url) {
		browserName = browserName.toLowerCase();
		String os = System.getProperty("os.name").toLowerCase();
		if(browserName.equals("chrome")  && os.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", getFilePath("drivers", "chromedriver.exe"));
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox") && os.contains("windows")) {
			System.setProperty("webdriver.gecko.driver", getFilePath("drivers", "geckodriver.exe"));
			driver = new FirefoxDriver();
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", getFilePath("drivers", "chromedriver"));
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", getFilePath("drivers", "geckodriver"));
			driver = new FirefoxDriver();
		}else{
			throw new RuntimeException("invalid browser name");
		}
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	
	public void launchBrowser(String brName, String url, String nodeUrl, String os) {
		DesiredCapabilities caps = new DesiredCapabilities();
		if(os.equalsIgnoreCase("windows")) {
			caps.setPlatform(Platform.WINDOWS);
		}else if(os.equalsIgnoreCase("mac")) {
			caps.setPlatform(Platform.MAC);
		}else if(os.equalsIgnoreCase("linux")) {
			caps.setPlatform(Platform.LINUX);
		}
		
		if(brName.equalsIgnoreCase("chrome")) {
			caps = DesiredCapabilities.chrome();
		}else if(brName.equalsIgnoreCase("firefox")) {
			caps = DesiredCapabilities.firefox();
		}
		
		try {
			driver = new RemoteWebDriver(new URL(nodeUrl), caps);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getCurrentUrl() {
		if(driver != null) {
			return driver.getCurrentUrl();
		}else {
			return "";
		}
	}
		
	public void sleep(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void closeBrowser() {
		if(driver.getWindowHandles().size() > 1) {
			driver.quit();
		}else {
			driver.close();
		}
	}
	
	
	

}
